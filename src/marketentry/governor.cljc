(ns marketentry.governor
  "Market-Entry Compliance Governor -- the independent compliance layer
  that earns the MarketEntry-LLM the right to commit. The LLM has no
  notion of Republic of Nicaragua public-procurement law, whether a
  claimed engagement fee actually equals base + months x rate, whether
  the engagement's own declared contract value actually crosses the
  Ley 1238 Art. 29 numeral 4 Contratación Menor threshold that Art. 18
  numeral 6 gates a legislative-branch-issued Certificado de
  Verificación de Proveedores del Estado on, or when a draft stops
  being a draft and becomes a real-world DGCE filing / Registro Público
  Mercantil inscription, so this MUST be a separate system able to
  *reject* a proposal and fall back to HOLD.

  `:itonami.blueprint/governor` is `:market-entry-compliance-governor`
  (shared family keyword on blueprints).

  This blueprint's own text (docs/business-model.md Trust Controls:
  'any actual DGCE filing or Registro Público Mercantil inscription
  requires Market-Entry Compliance Governor clearance and always
  escalates to human sign-off'; 'a false or fabricated regulatory-
  requirement claim is a HARD hold') names exactly the checks below.

  Six checks, in priority order, ALL HARD violations: a human
  approver CANNOT override them. The confidence/actuation gate is
  SOFT: it asks a human to look (low confidence / actuation), and the
  human may approve -- but see `marketentry.phase`: for `:stake
  :actuation/draft-filing`/`:actuation/submit-filing` NO phase ever
  allows auto-commit either. Two independent layers agree that
  actuation is always a human call.

    1. Spec-basis                  -- did the jurisdiction proposal cite
                                       an OFFICIAL source
                                       (`marketentry.facts`), or invent
                                       one?
    2. Evidence incomplete         -- for `:filing/draft`/
                                       `:filing/submit`, has the
                                       jurisdiction actually been
                                       assessed with a full evidence
                                       checklist on file?
    3. Certificado de Verificación
       claim mismatch               -- for `:filing/submit`,
                                       INDEPENDENTLY recompute whether
                                       the engagement's own declared
                                       contract value actually crosses
                                       the Ley 1238 Art. 29 numeral 4
                                       Contratación Menor threshold
                                       (C$2,000,000), and HARD-hold if
                                       the engagement's own claim about
                                       needing a Certificado de
                                       Verificación de Proveedores del
                                       Estado (Art. 18 numeral 6) does
                                       not match. FLAGSHIP genuinely new
                                       check for the iso3166 family
                                       (grep-verified absent as a
                                       governor check function name
                                       fleet-wide at build time) -- a
                                       THRESHOLD-GATED, CROSS-BRANCH
                                       CERTIFICATION REQUIREMENT: the
                                       certifying authority (Comisión de
                                       Verificación de Proveedores del
                                       Estado) is a body of the
                                       LEGISLATIVE branch, not the
                                       executive procurement authority
                                       (DGCE/MHCP) that runs the rest of
                                       this same filing pipeline -- the
                                       first in this family to check a
                                       REQUIRED CROSS-BRANCH APPROVAL,
                                       rather than a same-branch
                                       eligibility/routing/threshold
                                       test.
    4. Engagement fee mismatch     -- for `:filing/submit`,
                                       INDEPENDENTLY recompute whether
                                       the engagement's own `:claimed-
                                       fee` equals `base-fee +
                                       monthly-rate x monitoring-
                                       months` -- honest reapplication
                                       of the ground-truth-recompute
                                       discipline sibling actors use.
    5. Certificado de Verificación
       unverified                    -- for `:filing/submit`, when the
                                       SAME Art. 18.6/Art. 29.4
                                       recompute says a Certificado de
                                       Verificación de Proveedores del
                                       Estado IS required,
                                       INDEPENDENTLY check
                                       `:certificado-verificado?`.
                                       Grounded in the same Comisión de
                                       Verificación de Proveedores del
                                       Estado de la Asamblea Nacional
                                       mechanism as check 3 (see
                                       `marketentry.facts`).
    6. Confidence floor / actuation
       gate                          -- LLM confidence below threshold,
                                       OR the op is `:filing/draft`/
                                       `:filing/submit` (REAL acts)
                                       -> escalate.

  Two more guards, double-draft/double-submit prevention, are enforced
  off dedicated `:drafted?`/`:submitted?` facts (never a `:status`
  value)."
  (:require [marketentry.facts :as facts]
            [marketentry.registry :as registry]
            [marketentry.store :as store]))

(def confidence-floor 0.6)

(def high-stakes
  "Stakes grave enough to always require a human, even when clean.
  Drafting a real DGCE filing package and submitting a real filing /
  Registro Público Mercantil inscription request are the two real-world
  actuation events this actor performs."
  #{:actuation/draft-filing :actuation/submit-filing})

;; ----------------------------- checks -----------------------------

(defn- spec-basis-violations
  "A `:jurisdiction/assess` (or `:filing/draft`/`:filing/submit`)
  proposal with no spec-basis citation is a HARD violation -- never
  invent a jurisdiction's market-entry requirements."
  [{:keys [op]} proposal]
  (when (contains? #{:jurisdiction/assess :filing/draft :filing/submit} op)
    (let [value (:value proposal)]
      (when (or (empty? (:cites proposal))
                (and (contains? value :spec-basis) (nil? (:spec-basis value))))
        [{:rule :no-spec-basis
          :detail "公式spec-basisの引用が無い提案は法域要件として扱えない"}]))))

(defn- evidence-incomplete-violations
  "For `:filing/draft`/`:filing/submit`, the jurisdiction's required
  registration evidence must actually be satisfied."
  [{:keys [op subject]} st]
  (when (contains? #{:filing/draft :filing/submit} op)
    (let [e (store/engagement st subject)
          assessment (store/assessment-of st subject)]
      (when-not (and assessment
                     (facts/required-evidence-satisfied?
                      (:jurisdiction e) (:checklist assessment)))
        [{:rule :evidence-incomplete
          :detail "法域の必要書類(Registro Público Mercantil登録/RUC税務記録/DGCE SISCAE登録/Comisión de Verificación証明書確認/代理人確認等)が充足していない状態での提案"}]))))

(defn- certificado-verificacion-claim-mismatch-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own declared contract value actually crosses the Ley
  1238 Art. 29 numeral 4 Contratación Menor threshold -- the flagship
  check this vertical adds. HARD-hold when the engagement declares a
  `:claimed-certificado-requerido?` that does not match the
  independently recomputed requirement."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (registry/certificado-verificacion-claim-mismatch? e)
        [{:rule :certificado-verificacion-claim-mismatch
          :detail (str subject " は証明書要否(" (:claimed-certificado-requerido? e) ")を申告しているが、"
                      "独立再計算(Ley 1238 Art.18.6/Art.29.4の契約額判定)による正当な要否("
                      (registry/certificado-verificacion-required? e) ")と一致しない")}]))))

(defn- engagement-fee-mismatch-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own claimed fee equals base + months x rate."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when-not (registry/engagement-fee-matches-claim? e)
        [{:rule :engagement-fee-mismatch
          :detail (str subject " の申告手数料(" (:claimed-fee e)
                      ")が独立再計算値(" (registry/compute-engagement-fee e) ")と一致しない")}]))))

(defn- certificado-verificacion-unverified-violations
  "For `:filing/submit`, when the SAME independently recomputed Art.
  18.6/Art. 29.4 requirement says a Certificado de Verificación de
  Proveedores del Estado IS required, INDEPENDENTLY check
  `:certificado-verificado?`. Grounded in the Comisión de Verificación
  de Proveedores del Estado de la Asamblea Nacional mechanism."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (and (registry/certificado-verificacion-required? e)
                 (not (true? (:certificado-verificado? e))))
        [{:rule :certificado-verificacion-unverified
          :detail (str subject " はComisión de Verificación de Proveedores del Estado(Asamblea Nacional)発行の証明書確認を要するが未確認 -- 提出提案は進められない")}]))))

(defn- already-drafted-violations
  "For `:filing/draft`, refuses to draft the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/draft)
    (when (store/engagement-already-drafted? st subject)
      [{:rule :already-drafted
        :detail (str subject " は既にドラフト済み")}])))

(defn- already-submitted-violations
  "For `:filing/submit`, refuses to submit the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (when (store/engagement-already-submitted? st subject)
      [{:rule :already-submitted
        :detail (str subject " は既に提出済み")}])))

(defn check
  "Censors a MarketEntry-LLM proposal against the governor rules.
  Returns {:ok? bool :violations [..] :confidence c :escalate? bool
  :high-stakes? bool :hard? bool}."
  [request _context proposal st]
  (let [hard (into []
                   (concat (spec-basis-violations request proposal)
                           (evidence-incomplete-violations request st)
                           (certificado-verificacion-claim-mismatch-violations request st)
                           (engagement-fee-mismatch-violations request st)
                           (certificado-verificacion-unverified-violations request st)
                           (already-drafted-violations request st)
                           (already-submitted-violations request st)))
        conf (:confidence proposal 0.0)
        low? (< conf confidence-floor)
        stakes? (boolean (high-stakes (:stake proposal)))
        hard? (boolean (seq hard))]
    {:ok?          (and (not hard?) (not low?) (not stakes?))
     :violations   hard
     :confidence   conf
     :hard?        hard?
     :escalate?    (and (not hard?) (or low? stakes?))
     :high-stakes? stakes?}))

(defn hold-fact
  "The audit fact written when a proposal is rejected (HOLD)."
  [request context verdict]
  {:t          :governor-hold
   :op         (:op request)
   :actor      (:actor-id context)
   :subject    (:subject request)
   :disposition :hold
   :basis      (mapv :rule (:violations verdict))
   :violations (:violations verdict)
   :confidence (:confidence verdict)})
