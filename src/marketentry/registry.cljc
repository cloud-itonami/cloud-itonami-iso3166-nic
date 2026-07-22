(ns marketentry.registry
  "Pure-function market-entry filing-draft + filing-submit record
  construction -- an append-only market-entry book-of-record draft.

  Like every sibling actor's registry, there is no single international
  reference-number standard for a public-sector market-entry filing --
  every jurisdiction assigns its own format. This namespace does NOT
  invent one; it builds a jurisdiction-scoped sequence number and
  validates the record's required fields, the same honest,
  non-fabricating discipline `marketentry.facts` uses.

  `engagement-fee-matches-claim?` is an HONEST reapplication of the
  SAME ground-truth-recompute DISCIPLINE sibling actors use (verify a
  claimed monetary total against the entity's own recorded quantity x
  unit fields), reapplied to a market-entry engagement fee line.

  `certificado-verificacion-required?` / `certificado-verificacion-
  claim-mismatch?` are the SAME discipline applied to a genuinely
  Republic of Nicaragua-specific mechanism: Ley No. 1238, Ley de
  Contrataciones Administrativas del Estado (La Gaceta, Diario Oficial
  No. 35, 21 de febrero de 2025), Art. 18 numeral 6's own requirement
  that an oferente 'Presentar Certificado de Verificación de
  Proveedores del Estado que emita la Comisión de Verificación de
  Proveedores del Estado de la Asamblea Nacional, en el caso de
  contrataciones que superen la Contratación Menor' -- gated on Art. 29
  numeral 4's own Contratación Menor ceiling of C$2,000,000.

  This is a GENUINELY DIFFERENT check SHAPE than every prior iso3166
  sibling this repo mirrors: Cuba's Ley 118 Art. 21 mechanism is a
  SECTOR/MODALITY AUTHORITY-JURISDICTION ROUTING classification,
  Haiti's Arrêté du 23 février 2026 mechanism is a CATEGORY-GATED
  SINGLE-THRESHOLD APPLICABILITY GATE for whether the WHOLE Marché-
  public procedure applies, and the Dominican Republic's MIPYME
  mechanism is a CERTIFICATION-GATED, DUAL-CLOCK ELIGIBILITY test.
  Nicaragua's Art. 18.6/Art. 29.4 mechanism is none of these: it is a
  THRESHOLD-GATED, CROSS-BRANCH CERTIFICATION REQUIREMENT -- the
  certifying authority (Comisión de Verificación de Proveedores del
  Estado) is a body of the LEGISLATIVE branch (Asamblea Nacional), not
  the executive procurement authority (DGCE/MHCP) that runs the rest of
  this same filing pipeline. No prior sibling in this family checks
  whether a filing correctly identifies a REQUIRED CROSS-BRANCH
  APPROVAL, as opposed to a same-branch eligibility/routing/threshold
  test.

  This iteration deliberately did NOT model Art. 30's inflation-linked
  Acuerdo Ministerial threshold-update mechanism (the Law itself
  delegates the actual future córdoba figures to a ministerial act this
  iteration has not independently fetched) -- only the Art. 29 figures
  as enacted 2025-02-21 are recomputed here, the same honest scope-
  narrowing discipline Cuba's two-of-three-tier Ley 118 Art. 21 model
  and Haiti's unmodeled seuils-d'intervention-CNMP sub-thresholds
  already established for this family.

  This namespace is pure data + pure functions -- no I/O, no network
  call to any real government system. It builds the RECORD an operator
  would keep, not the act of submitting a portal registration itself
  (that is `marketentry.operation`'s `:filing/submit`, always
  human-gated -- see README Actuation)."
  (:require [clojure.string :as str]))

(defn- unsigned-certificate
  "Every certificate this actor produces is UNSIGNED -- signature is
  the market-entry operator's act, not this actor's."
  [kind subject record-id]
  {"@context" ["https://www.w3.org/ns/credentials/v2"]
   "type" ["VerifiableCredential" kind]
   "credentialSubject" {"id" subject "record" record-id}
   "proof" nil
   "issued_by_registry" false
   "status" "draft-unsigned"})

(defn- zero-pad [n w]
  (let [s (str n)]
    (str (apply str (repeat (max 0 (- w (count s))) "0")) s)))

(defn compute-engagement-fee
  "The ground-truth engagement fee for `engagement`'s own `:base-fee`
  and `:monitoring-months` x `:monthly-rate` -- a single flat
  base + months x rate calculation, not a full pricing engine."
  [{:keys [base-fee monthly-rate monitoring-months]}]
  (+ (double base-fee)
     (* (double monthly-rate) (double monitoring-months))))

(defn engagement-fee-matches-claim?
  "Does `engagement`'s own `:claimed-fee` equal the independently
  recomputed `compute-engagement-fee`?"
  [{:keys [claimed-fee] :as engagement}]
  (== (double claimed-fee) (compute-engagement-fee engagement)))

(def contratacion-menor-threshold-cordobas
  "Ley No. 1238 Art. 29 numeral 4 (own text, read directly):
  'Contratación Menor: Modalidad para contrataciones que no superen los
  dos millones de córdobas (C$2,000,000.00).' Above this ceiling, Art.
  18 numeral 6 requires a Certificado de Verificación de Proveedores
  del Estado. Not the Art. 29 numeral 2 Licitación Pública/Selectiva
  boundary (C$10,000,000 / C$2,000,001) -- a DIFFERENT threshold in the
  SAME article, deliberately not conflated here."
  2000000.0)

(defn certificado-verificacion-required?
  "The ground-truth answer, independently recomputed from `engagement`'s
  own declared `:contract-value-cordobas`, to 'does Art. 18 numeral 6
  require a Certificado de Verificación de Proveedores del Estado for
  this filing' -- true when the contract value exceeds the Art. 29
  numeral 4 Contratación Menor ceiling. A missing/nil
  `:contract-value-cordobas` is treated as 0 (falls through to NOT
  required, the same fail-strict-on-the-DECLARED-value discipline the
  rest of this family uses for a missing declared quantity, rather than
  throwing)."
  [{:keys [contract-value-cordobas]}]
  (> (double (or contract-value-cordobas 0)) contratacion-menor-threshold-cordobas))

(defn certificado-verificacion-claim-mismatch?
  "Does `engagement` declare a `:claimed-certificado-requerido?` that
  does NOT match the independently recomputed
  `certificado-verificacion-required?`? An engagement with no claim at
  all is not flagged by this check (entity/engagement-scope-gated, the
  same discipline Cuba's `:claimed-approval-authority`-gated check and
  Haiti's `:claimed-marche-public?`-gated check use)."
  [{:keys [claimed-certificado-requerido?] :as engagement}]
  (boolean (and (some? claimed-certificado-requerido?)
                (not= claimed-certificado-requerido?
                      (certificado-verificacion-required? engagement)))))

(defn register-draft
  "Validate + construct the FILING-DRAFT registration DRAFT -- the
  market-entry operator's own act of preparing a portal registration
  package. Pure function -- does not touch any real government system."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "draft: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "draft: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "draft: sequence must be >= 0" {})))
  (let [draft-number (str (str/upper-case jurisdiction) "-DFT-" (zero-pad sequence 6))
        record {"record_id" draft-number
                "kind" "filing-draft"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "draft_number" draft-number
     "certificate" (unsigned-certificate "FilingDraft" draft-number draft-number)}))

(defn register-submit
  "Validate + construct the FILING-SUBMIT registration DRAFT -- the
  market-entry operator's own act of actually submitting the DGCE
  filing / Registro Público Mercantil inscription (always human-gated
  upstream)."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "submit: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "submit: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "submit: sequence must be >= 0" {})))
  (let [submit-number (str (str/upper-case jurisdiction) "-SUB-" (zero-pad sequence 6))
        record {"record_id" submit-number
                "kind" "filing-submit"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "submit_number" submit-number
     "certificate" (unsigned-certificate "FilingSubmit" submit-number submit-number)}))

(defn append [history result]
  (conj (vec history) (get result "record")))
