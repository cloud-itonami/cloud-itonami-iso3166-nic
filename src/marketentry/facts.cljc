(ns marketentry.facts
  "Per-jurisdiction public-sector market-entry regulatory catalog -- the
  G2-style spec-basis table the Market-Entry Compliance Governor checks
  every `:jurisdiction/assess` proposal against ('did the advisor cite an
  OFFICIAL public source for this jurisdiction's requirements, or did it
  invent one?').

  Republic of Nicaragua's real public-sector procurement regime turned
  out to be MID-TRANSITION at the moment this iteration researched it
  (curl/pdftotext-verified 2026-07-22/23) -- the task brief that seeded
  this repo's scaffold assumed Ley No. 737 'Ley de Contrataciones
  Administrativas del Sector Público' is the governing law. This
  iteration did NOT accept that assumption at face value and instead
  fetched Nicaragua's own official gazette directly:

  - **Ley No. 737 has been REPEALED and replaced by Ley No. 1238, 'Ley
    de Contrataciones Administrativas del Estado'.** This iteration
    fetched `nicaraguacompra.gob.ni` (the procurement portal) through
    the Internet Archive Wayback Machine (direct curl/WebFetch to
    `nicaraguacompra.gob.ni` and `legislacion.asamblea.gob.ni` both
    connection-timed-out this iteration on every attempt -- a real,
    disclosed reachability gap, NOT silently papered over) --
    `web.archive.org/web/20260717231455/https://www.nicaraguacompra.gob.ni/`,
    captured 2026-07-17 -- and found Ley 737's own menu entry filed
    under the site's OWN 'Leyes/Norm. Derogadas' (repealed laws/norms)
    category (`href` literally contains
    `/normativa/leyes-norm-derogadas/sector-publico/leyes`), while 'Ley
    No. 1238' appears under the CURRENT 'Contrat. Administrativas del
    Estado' category. This iteration then independently confirmed this
    directly from La Gaceta, Diario Oficial's own site
    (`www.lagaceta.gob.ni`, reachable directly, no Wayback needed) by
    fetching the actual PDF of La Gaceta No. 35 (Viernes 21 de Febrero
    de 2025) -- `www.lagaceta.gob.ni` embeds each edition's PDF bytes as
    a base64 blob inside the page's own PDF.js viewer; this iteration
    extracted and decoded that blob to a real PDF and ran
    `pdftotext -layout` on it. Ley No. 1238's OWN Art. 88 (own text,
    read directly): 'La presente Ley deroga: 1) Ley N°. 737, Ley de
    Contrataciones Administrativas del Sector Público, cuyo texto
    consolidado fue publicado en La Gaceta, Diario Oficial N°. 170 del
    21 de septiembre de 2023; 2) Ley N°. 801, Ley de Contrataciones
    Administrativas Municipales, publicada en La Gaceta, Diario Oficial
    N°. 192 del 9 de octubre de 2012.' Art. 89 (own text): any reference
    to Ley 737/801 'deberá entenderse Ley de Contrataciones
    Administrativas del Estado' -- i.e. Ley 1238 UNIFIES what had been
    two separate sector-público (737) and municipal (801) regimes into
    ONE law. This is a genuinely different and more current finding
    than the task brief assumed, and this catalog builds on the
    VERIFIED-CURRENT law (1238), not the repealed one (737) -- the same
    'investigate rather than assume' discipline this fleet's other
    siblings (e.g. Cuba's centrally-planned-economy correction) already
    established.
  - **Ley No. 1238 was aprobada by the Asamblea Nacional 18 de febrero
    de 2025, promulgada 19 de febrero de 2025 by Co-Presidente Daniel
    Ortega Saavedra and Co-Presidenta Rosario Murillo Zambrana**
    (closing signature block, own text read directly: 'Dado en el
    Salón de Sesiones de la Asamblea Nacional... a los dieciocho días
    del mes de febrero del año dos mil veinticinco... Por tanto.
    Téngase como Ley de la República. Publíquese y Ejecútese. Managua,
    el día diecinueve de febrero del año dos mil veinticinco. Daniel
    Ortega Saavedra, Co-Presidente de la República de Nicaragua.
    Rosario Murillo Zambrana, Co-Presidenta de la República de
    Nicaragua.'), **published in La Gaceta, Diario Oficial No. 35, 21
    de febrero de 2025** (masthead, own text: 'AÑO CXXIX Managua,
    Viernes 21 de Febrero de 2025 ... No. 35'), entering into force 30
    days after publication (Art. 93, own text: 'La presente Ley entrará
    en vigencia en treinta días (30) después de su publicación').
  - **Its implementing Reglamento is Decreto Presidencial No. 07-2025**,
    independently confirmed by this iteration the same way -- fetched
    and pdftotext-verified La Gaceta No. 83 (Lunes 12 de Mayo de 2025),
    own text: 'DECRETO PRESIDENCIAL No. 07-2025 ... REGLAMENTO A LA LEY
    No. 1238 LEY DE CONTRATACIONES ADMINISTRATIVAS DEL ESTADO', issued
    by 'La Presidencia de la República de Nicaragua, Co-Presidente
    Daniel Ortega y Co-Presidenta Rosario Murillo'; its own Art. 1
    cross-confirms Ley 1238's publication citation verbatim ('Ley No.
    1238... publicada en La Gaceta, Diario Oficial No. 35, del
    veintiuno de febrero del año dos mil veinticinco'), an independent
    cross-corroboration of the date above from a SECOND primary
    document.
  - **The Autoridad Rectora is the Ministerio de Hacienda y Crédito
    Público (MHCP), through the Dirección General de Contrataciones
    del Estado (DGCE)** -- Ley 1238 Art. 8 (own text, read directly):
    'La Autoridad Rectora de las Contrataciones Administrativas del
    Estado será el Ministerio de Hacienda y Crédito Público, a través
    de la Dirección General de Contrataciones del Estado (DGCE).' This
    independently confirms the institutional structure the task brief
    named, even though the brief's own law citation (Ley 737) turned
    out to be superseded.
  - **The electronic backbone is SISCAE (Sistema de Contrataciones
    Administrativas Electrónicas), Art. 10** (own text: mandatory for
    all covered entities); **the public-facing single portal is
    `www.nicaraguacompra.gob.ni`**, confirmed not merely from the
    portal's own homepage (which could be read as self-referential) but
    from a THIRD independent primary source inside the SAME La Gaceta
    No. 35 edition that carries Ley 1238's own text: a Ministerio del
    Ambiente y de los Recursos Naturales (MARENA) procurement notice,
    own text read directly: 'informa... que se encuentra disponible...
    en el portal único de contrataciones www.nicaraguacompra.gob.ni,
    convocatoria y documento base de concurso...' -- i.e. a government
    ministry's own contemporaneous public notice, published the same
    day as Ley 1238, independently names this exact portal as 'el
    portal único de contrataciones'.
  - **Contracting modalities are value-tiered by Art. 29** (own text,
    read directly): '2. Licitación: a) Pública: Modalidad para
    contrataciones superiores a diez millones de córdobas
    (C$10,000,000.00). b) Selectiva: Modalidad para contrataciones
    entre dos millones y un córdobas (C$2,000,001.00) a diez millones
    de córdobas (C$10,000,000.00). 3. Contratación Simplificada:
    Modalidad aplicable a causales previstas en la Ley,
    independientemente del monto... 4. Contratación Menor: Modalidad
    para contrataciones que no superen los dos millones de córdobas
    (C$2,000,000.00). 5. Concurso para Consultores: ... sin importar el
    monto.' Art. 30 (own text): MHCP updates these córdoba thresholds
    by Acuerdo Ministerial when accumulated inflation warrants it,
    after consulting the Banco Central de Nicaragua -- this catalog
    does NOT hard-code a self-updating inflation formula (the Law
    itself delegates that to a future ministerial act this iteration
    has not fetched), only the Art. 29 figures as enacted 2025-02-21.
  - **This vertical's FLAGSHIP check basis is Art. 18 numeral 6** (own
    text, read directly): 'Presentar Certificado de Verificación de
    Proveedores del Estado que emita la Comisión de Verificación de
    Proveedores del Estado de la Asamblea Nacional, en el caso de
    contrataciones que superen la Contratación Menor.' -- see
    `marketentry.governor` / `marketentry.registry` for why this is a
    genuinely different check SHAPE from every prior sibling in this
    family: the certifying authority is a body of the LEGISLATIVE
    branch (Asamblea Nacional), not the executive procurement authority
    (DGCE/MHCP) that runs the rest of the pipeline -- a cross-branch
    certification gate, conditioned on the SAME Art. 29 numeral 4
    Contratación Menor threshold (C$2,000,000) already verified above.
  - **Honest gaps this iteration did NOT resolve, disclosed rather than
    papered over**:
    1. Direct fetches of `nicaraguacompra.gob.ni`,
       `legislacion.asamblea.gob.ni` and `www.dgi.gob.ni` (plain HTTP/S,
       and via WebFetch) all connection-timed-out or were WAF-rejected
       every attempt this iteration made; this catalog relies on the
       Wayback Machine for the portal's own page and on La Gaceta's own
       reachable site + an OAS-hosted MESICIC mirror for the primary
       legal texts instead (all still directly fetched and read this
       iteration, never fabricated).
    2. PRONicaragua (`pronicaragua.org`, reachable directly) was
       confirmed to exist as Nicaragua's investment-promotion brand,
       but this iteration did NOT independently locate/fetch the
       decree or law that formally creates it, and the fetched page's
       own front-page content (referencing a '2016' news item) reads as
       stale -- this catalog does not build a check on PRONicaragua and
       does not claim a legal-basis citation for it.
    3. No verifiable Nicaragua-specific representative-exclusion-
       extension provision (analogous to Benin's/CAF's) was located --
       `rep-spec-basis` is deliberately nil, the same honest-absence
       discipline Cuba's catalog already established for this family.
    4. Ley 1238 Art. 21.4-equivalent per-sector discretionary
       delegation is not present in this law (unlike Cuba's Ley 118);
       no scope-narrowing was needed on that axis.

  A jurisdiction not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> requirement map. `:required-evidence` mirrors the generic
  intake/portal-registration/filing evidence set; `:legal-basis` /
  `:owner-authority` / `:provenance` are the G2 citation the governor
  requires before any `:jurisdiction/assess` proposal can commit. NIC
  deliberately carries NO `:rep-owner-authority` -- this iteration did
  not find a verifiable Nicaraguan analogue to CAF's/Benin's
  representative-exclusion-extension provision (see namespace
  docstring). `:certificado-verificacion-owner-authority` /
  `:certificado-verificacion-legal-basis` /
  `:certificado-verificacion-criteria` /
  `:certificado-verificacion-provenance` ground this vertical's
  flagship governor check (`certificado-verificacion-required?`/
  `certificado-verificacion-claim-mismatch?` in
  `marketentry.registry`)."
  {"NIC" {:name "Republic of Nicaragua"
          :owner-authority "Dirección General de Contrataciones del Estado (DGCE), dependencia del Ministerio de Hacienda y Crédito Público (MHCP) -- la Autoridad Rectora de las Contrataciones Administrativas del Estado (Ley No. 1238 Art. 8, own text: 'La Autoridad Rectora de las Contrataciones Administrativas del Estado será el Ministerio de Hacienda y Crédito Público, a través de la Dirección General de Contrataciones del Estado (DGCE)')"
          :legal-basis "Ley No. 1238, Ley de Contrataciones Administrativas del Estado (aprobada por la Asamblea Nacional 18 de febrero de 2025, promulgada 19 de febrero de 2025 por los Co-Presidentes Daniel Ortega Saavedra y Rosario Murillo Zambrana, publicada en La Gaceta, Diario Oficial No. 35, 21 de febrero de 2025, vigente 30 días después per Art. 93) and its Reglamento, Decreto Presidencial No. 07-2025 (La Gaceta, Diario Oficial No. 83, 12 de mayo de 2025). Art. 88 (own text, read directly) expressly repeals Ley No. 737 'Ley de Contrataciones Administrativas del Sector Público' (texto consolidado publicado en La Gaceta No. 170, 21 de septiembre de 2023) and Ley No. 801 'Ley de Contrataciones Administrativas Municipales' (La Gaceta No. 192, 9 de octubre de 2012), unifying both into this single law (Art. 89, own text: toda referencia a la Ley 737/801 'deberá entenderse Ley de Contrataciones Administrativas del Estado')."
          :national-spec "SISCAE (Sistema de Contrataciones Administrativas Electrónicas, Art. 10, mandatory for covered entities) is the electronic backbone; the public single portal is www.nicaraguacompra.gob.ni ('portal único de contrataciones', independently confirmed from a MARENA notice published in the SAME La Gaceta No. 35 edition that carries Ley 1238 itself). Contracting modalities are value-tiered in córdobas (Art. 29): Licitación Pública > C$10,000,000; Licitación Selectiva C$2,000,001-C$10,000,000; Contratación Menor <= C$2,000,000; Contratación Simplificada (cause-gated, value-independent); Concurso para Consultores (value-independent). MHCP updates these thresholds by Acuerdo Ministerial per accumulated inflation, previa consulta con el Banco Central de Nicaragua (Art. 30)."
          :provenance "https://www.lagaceta.gob.ni/la-gaceta-no-35-viernes-21-de-febrero-de-2025/ ; https://www.lagaceta.gob.ni/la-gaceta-no-83-lunes-12-de-mayo-de-2025/ ; https://web.archive.org/web/20260717231455/https://www.nicaraguacompra.gob.ni/ (Wayback fallback -- direct fetch of nicaraguacompra.gob.ni timed out every attempt this iteration; disclosed, not concealed)"
          :required-evidence ["Registro Público Mercantil inscription record (sociedad mercantil registration under the Sistema Nacional de Registros -- SINARE -- created by Ley No. 698, Ley General de los Registros Públicos, as reformed by Ley No. 1035/2020; required for legal personality per Art. 155 numeral 2 (as reformed): 'Las sociedades mercantiles no inscritas, no tendrán personalidad jurídica')"
                              "RUC (Cédula RUC, Registro Único de Contribuyentes) tax registration record (Código Tributario, Ley No. 562, Art. 26, Dirección General de Ingresos)"
                              "DGCE Registro de Proveedores del Estado inscription (Ley No. 1238 Art. 18 numeral 2)"
                              "Certificado de Verificación de Proveedores del Estado record, when the contract value exceeds the Contratación Menor threshold (Comisión de Verificación de Proveedores del Estado de la Asamblea Nacional, Ley No. 1238 Art. 18 numeral 6)"
                              "Authorized-representative confirmation record"]
          :corporate-number-owner-authority "Dirección General de Ingresos (DGI) -- la Administración Tributaria (Código Tributario Art. 26). This iteration confirmed DGI's own site title ('Dirección General de Ingresos (DGI) - República de Nicaragua') via the Wayback Machine; a direct fetch of www.dgi.gob.ni was rejected by the site's own WAF ('Request Rejected') every attempt this iteration made -- disclosed gap, not concealed."
          :corporate-number-legal-basis "Ley No. 562, Código Tributario de la República de Nicaragua (aprobada por la Asamblea Nacional 28 de octubre de 2005, publicada en La Gaceta, Diario Oficial No. 227, 23 de noviembre de 2005) Art. 26 (own text, read directly): 'La Administración Tributaria contará con dependencias administrativas responsables de adjudicar un Código Único de Identificación para el Registro de Contribuyentes... Todos los contribuyentes están obligados a inscribirse en este Registro. El Código Único de Identificación, será denominado Cédula RUC y deberá indicarse en la declaración y pago de impuestos...'"
          :corporate-number-provenance "https://www.oas.org/juridico/spanish/mesicic3_nic_cod_trib_ref.pdf ; https://web.archive.org/web/20260210112952/https://www.dgi.gob.ni/"
          :certificado-verificacion-owner-authority "Comisión de Verificación de Proveedores del Estado de la Asamblea Nacional -- a body of the LEGISLATIVE branch, distinct from DGCE/MHCP (the executive procurement authority that runs the rest of the pipeline)"
          :certificado-verificacion-legal-basis "Ley No. 1238 Art. 18 numeral 6 (own text, read directly): 'Presentar Certificado de Verificación de Proveedores del Estado que emita la Comisión de Verificación de Proveedores del Estado de la Asamblea Nacional, en el caso de contrataciones que superen la Contratación Menor.' The Contratación Menor ceiling is independently defined in Art. 29 numeral 4 (own text, read directly): 'Contratación Menor: Modalidad para contrataciones que no superen los dos millones de córdobas (C$2,000,000.00).'"
          :certificado-verificacion-criteria {:contratacion-menor-threshold-cordobas 2000000.0}
          :certificado-verificacion-provenance "https://www.lagaceta.gob.ni/la-gaceta-no-35-viernes-21-de-febrero-de-2025/"}
   "USA" {:name "United States"
          :owner-authority "U.S. General Services Administration (GSA) / SAM.gov"
          :legal-basis "Federal Acquisition Regulation (FAR); System for Award Management"
          :national-spec "SAM.gov entity registration + NAICS self-certification"
          :provenance "https://sam.gov/"
          :required-evidence ["EIN record"
                              "SAM.gov registration record"
                              "State business registration record"
                              "Authorized-representative record"]}
   "DEU" {:name "Germany"
          :owner-authority "Beschaffungsamt des BMI / e-Vergabe platforms"
          :legal-basis "Gesetz gegen Wettbewerbsbeschränkungen (GWB) / VgV"
          :national-spec "e-Vergabe supplier registration under EU procurement directives"
          :provenance "https://www.evergabe-online.de/"
          :required-evidence ["Handelsregister extract"
                              "e-Vergabe registration record"
                              "USt-IdNr record"
                              "Authorized-representative record"]}})

(defn spec-basis
  "The jurisdiction's requirement map, or nil -- nil means NO spec-basis,
  and the governor must hold any proposal that tries to assess or file
  on it."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report: how many of the requested jurisdictions actually
  have a spec-basis entry. Never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-nic R0: " (count catalog)
                 " jurisdictions seeded with an official spec-basis. "
                 "This is a starting catalog for market-entry navigation, "
                 "not a survey of all ~194 jurisdictions -- extend "
                 "`marketentry.facts/catalog`, never fabricate a "
                 "jurisdiction's requirements.")})))

(defn required-evidence-satisfied?
  "Does `submitted` (a set/coll of evidence keywords or strings) satisfy
  every evidence item listed for `iso3`? Missing spec-basis -> never
  satisfied."
  [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (let [need (count required-evidence)
          have (count (filter (set submitted) required-evidence))]
      (= need have))))

(defn evidence-checklist [iso3]
  (:required-evidence (spec-basis iso3) []))

(defn rep-spec-basis
  "The jurisdiction's representative-related requirement map, or nil when
  this catalog has no such regime. For NIC this is deliberately nil --
  see the `catalog` docstring's honest-scope-narrowing note (this
  iteration did not locate and confirm a Nicaraguan representative-
  exclusion-extension provision at a specific article number)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))

(defn corporate-number-spec-basis
  "The jurisdiction's corporate-number / tax-id regime, or nil."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority
                       :corporate-number-legal-basis
                       :corporate-number-provenance]))))

(defn certificado-verificacion-spec-basis
  "The jurisdiction's Certificado de Verificación de Proveedores del
  Estado regime, or nil. For NIC this is real and current -- the
  flagship check this vertical adds is grounded here (Ley 1238 Art. 18
  numeral 6, gated on the Art. 29 numeral 4 Contratación Menor
  threshold)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:certificado-verificacion-owner-authority sb)
      (select-keys sb [:certificado-verificacion-owner-authority
                       :certificado-verificacion-legal-basis
                       :certificado-verificacion-criteria
                       :certificado-verificacion-provenance]))))
