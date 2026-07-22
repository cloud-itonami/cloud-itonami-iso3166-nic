(ns statute.facts
  "General-law compliance catalog for the Republic of Nicaragua (NIC) --
  extends this repo's existing `marketentry.facts` (public-sector
  market-entry/foreign-investment only, narrow scope) with a second,
  orthogonal catalog of national statutes a foreign investor operating
  in this jurisdiction must generally track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-deu/-bgr/-aze/-alb/-arm/-atg/-ben/-btn/-caf/
  -cub/-hti/-dom's `statute.facts` (ADR-2607141700, cloud-itonami-
  compliance-fact-federation).

  Every entry cites an OFFICIAL government-hosted URL, or -- where a
  direct .gob.ni fetch connection-timed-out this iteration (a real,
  disclosed reachability problem, see `marketentry.facts` namespace
  docstring) -- a legitimate international-organization mirror (OAS,
  FAO) or Nicaragua's own reachable La Gaceta site, always independently
  fetched and read (curl/pdftotext-verified 2026-07-22/23) this
  iteration -- never fabricated.

  - **Ley No. 1238, Ley de Contrataciones Administrativas del Estado**:
    the SAME law `marketentry.facts` uses as its market-entry spec-
    basis; it is ALSO catalogued here as a general national-law
    reference per this task's own instructions, since a foreign
    investor tracks it both as a market-entry gate and as an ongoing
    compliance statute. See `marketentry.facts` for the full research
    trail on why this iteration cites Ley 1238 (2025) rather than the
    now-repealed Ley 737 the task brief originally assumed.
  - **Company/commercial registration**: Ley No. 698, Ley General de
    los Registros Públicos (aprobada por la Asamblea Nacional, publicada
    en La Gaceta, Diario Oficial No. 239, 17 de diciembre de 2009), as
    reformed by Ley No. 1035 (aprobada 20 de agosto de 2020, publicada
    en La Gaceta, Diario Oficial No. 158, 25 de agosto de 2020). This
    iteration fetched Ley 1035's own reform text (mirrored via a FAOLEX
    copy of the Asamblea Nacional's own `legislacion.asamblea.gob.ni`
    Normaweb page, own document read directly -- direct fetches of
    `legislacion.asamblea.gob.ni` itself connection-timed-out every
    attempt this iteration made). Its own Art. 3 (reforming Ley 698 Art.
    3) confirms verbatim: 'El Sistema Nacional de Registros (SINARE)
    está Integrado por: ... 2. El Registro Público Mercantil...'. Its
    own Art. 155 (as reformed, read directly) confirms sanciones for
    non-registration: 'Las sociedades mercantiles no inscritas, no
    tendrán personalidad jurídica' -- an unregistered mercantile company
    has NO legal personality in Nicaragua, the same 'registration ->
    legal personality' discipline Cuba's Ley 118/Decreto-Ley 226 pairing
    already established for this family, independently re-confirmed
    here for a genuinely different jurisdiction/mechanism. Its own Art.
    156 (as reformed) lists the acts/contracts subject to registration
    (constitution, modification, dissolution of a sociedad mercantil;
    beneficial-owner identification chains when a shareholder is itself
    a company; branch openings/closings; etc.) and its own Art. 121 (as
    reformed, Código de Comercio) requires 'Todo contrato de sociedad
    debe constar en escritura pública.'
  - **Tax registration (RUC)**: Ley No. 562, Código Tributario de la
    República de Nicaragua -- see `marketentry.facts` for the fully
    independently-verified Art. 26 citation and dates (aprobada 28 de
    octubre de 2005, publicada La Gaceta No. 227, 23 de noviembre de
    2005).
  - **Labor**: Ley No. 185, Código del Trabajo (aprobada por la Asamblea
    Nacional 5 de septiembre de 1996, publicada en La Gaceta, Diario
    Oficial No. 205, 30 de octubre de 1996). This iteration fetched this
    law's own text directly from `webserver.ineter.gob.ni` (INETER, an
    official .gob.ni government host that WAS directly reachable, unlike
    several other .gob.ni hosts this iteration tried) and confirmed the
    masthead verbatim: 'LEY No. 185, Aprobada el 5 de Septiembre de
    1996. Publicada en La Gaceta No. 205 del 30 de Octubre de 1996.'
    This iteration ALSO independently found and read Art. 14 (own text):
    'El empleador está obligado a contratar, como mínimo, a un noventa
    por ciento de trabajadores nicaragüenses. El Ministerio del Trabajo,
    en casos debidamente justificados... podrá exceptuar de esta
    limitación a determinados empleadores por razones técnicas.' -- a
    real, verified 90%-national-workforce quota with a discretionary
    MITRAB (Ministerio del Trabajo) technical exception. This is
    documented here as a real, verified fact this catalog does not
    build a governor check around (it is a general ongoing employment
    obligation on any employer, not a market-entry filing gate the way
    Ley 1238 Art. 18.6's Certificado de Verificación is) -- the same
    'document but do not force into the flagship' discipline this
    task's own instructions call for.
  - **Investment framework**: Ley No. 344, Ley de Promoción de
    Inversiones Extranjeras (aprobada por la Asamblea Nacional 27 de
    abril de 2000, promulgada 22 de mayo de 2000 by Presidente Arnoldo
    Alemán Lacayo, publicada en La Gaceta, Diario Oficial No. 97, 24 de
    mayo de 2000 -- own closing signature block read directly, own text:
    'Dada en la ciudad de Managua... a los veintisiete días del mes de
    abril del dosmil... Por tanto: Téngase como ley de la República.
    Publíquese y Ejecútese Managua, 22 de mayo del año dosmil.'). Its
    own Art. 11 repeals the prior Ley de Inversiones Extranjeras, Ley
    No. 127 (12 de abril de 1991). Its own Art. 9 (read directly):
    foreign investors who want the Law's benefits 'deberán notificar al
    Ministerio de Fomento, Industria y Comercio [MIFIC], la inversión
    extranjera que pretendan efectuar, para su inscripción y
    actualización en el Registro Estadístico de Inversiones
    Extranjeras' -- a VOLUNTARY statistical-registration/notification
    regime tied to benefits eligibility, NOT a mandatory market-entry
    gate (this catalog does not conflate it with one). HONEST GAP: this
    iteration confirmed PRONicaragua's own existence directly
    (`pronicaragua.org`, reachable, fetched directly) as Nicaragua's
    investment-promotion brand, but did NOT independently locate/fetch
    the decree or law that formally creates PRONicaragua as an
    institution, and the fetched page's own front-page content reads as
    stale (references a '2016' news item) -- this catalog does not cite
    a legal-basis for PRONicaragua and does not build any check on it.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries. `:statute/url` + `:statute/law-number`
  are the citation the governor requires before any compliance-fact
  proposal referencing this law can commit."
  {"NIC"
   [{:statute/id "nic.ley-1238-contrataciones-administrativas-estado"
     :statute/title "Ley No. 1238, Ley de Contrataciones Administrativas del Estado"
     :statute/jurisdiction "NIC"
     :statute/kind :law
     :statute/law-number "Ley No. 1238, aprobada por la Asamblea Nacional 18 de febrero de 2025, promulgada 19 de febrero de 2025 por los Co-Presidentes Daniel Ortega Saavedra y Rosario Murillo Zambrana, publicada en La Gaceta, Diario Oficial No. 35, 21 de febrero de 2025; Reglamento: Decreto Presidencial No. 07-2025 (La Gaceta, Diario Oficial No. 83, 12 de mayo de 2025). Deroga (Art. 88, own text) la Ley No. 737 (texto consolidado, La Gaceta No. 170, 21 de septiembre de 2023) y la Ley No. 801 (La Gaceta No. 192, 9 de octubre de 2012)."
     :statute/url "https://www.lagaceta.gob.ni/la-gaceta-no-35-viernes-21-de-febrero-de-2025/"
     :statute/url-provenance :official-lagaceta-gob-ni
     :statute/enacted-date "2025-02-19"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:public-procurement}}
    {:statute/id "nic.ley-698-registros-publicos-ley-1035-reforma"
     :statute/title "Ley No. 698, Ley General de los Registros Públicos (reformada por Ley No. 1035)"
     :statute/jurisdiction "NIC"
     :statute/kind :law
     :statute/law-number "Ley No. 698, publicada en La Gaceta, Diario Oficial No. 239, 17 de diciembre de 2009; reformada por Ley No. 1035, Ley de Reforma a la Ley No. 698 y al Código de Comercio de la República de Nicaragua, aprobada 20 de agosto de 2020, publicada en La Gaceta, Diario Oficial No. 158, 25 de agosto de 2020 -- this iteration fetched Ley 1035's own reform text via a FAOLEX-hosted copy of the Asamblea Nacional's own legislacion.asamblea.gob.ni page (direct fetch of legislacion.asamblea.gob.ni itself connection-timed-out every attempt this iteration made, see namespace docstring)"
     :statute/url "https://faolex.fao.org/docs/pdf/nic198050.pdf"
     :statute/url-provenance :fao-faolex-mirror
     :statute/enacted-date "2020-08-20"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "nic.ley-562-codigo-tributario"
     :statute/title "Ley No. 562, Código Tributario de la República de Nicaragua"
     :statute/jurisdiction "NIC"
     :statute/kind :law
     :statute/law-number "Ley No. 562, aprobada por la Asamblea Nacional 28 de octubre de 2005, publicada en La Gaceta, Diario Oficial No. 227, 23 de noviembre de 2005 (own cover page, read directly: 'Ley No. 562 publicada el 23/11/05 en La Gaceta No. 227 / Aprobada por la Asamblea Nacional el 28/10/05')"
     :statute/url "https://www.oas.org/juridico/spanish/mesicic3_nic_cod_trib_ref.pdf"
     :statute/url-provenance :oas-mesicic-mirror
     :statute/enacted-date "2005-10-28"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:tax}}
    {:statute/id "nic.ley-185-codigo-trabajo"
     :statute/title "Ley No. 185, Código del Trabajo"
     :statute/jurisdiction "NIC"
     :statute/kind :law
     :statute/law-number "Ley No. 185, aprobada por la Asamblea Nacional 5 de septiembre de 1996, publicada en La Gaceta, Diario Oficial No. 205, 30 de octubre de 1996 (own masthead, read directly)"
     :statute/url "https://webserver.ineter.gob.ni/leyes/codigo-trabajo.pdf"
     :statute/url-provenance :official-ineter-gob-ni
     :statute/enacted-date "1996-09-05"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:labor}}
    {:statute/id "nic.ley-344-inversiones-extranjeras"
     :statute/title "Ley No. 344, Ley de Promoción de Inversiones Extranjeras"
     :statute/jurisdiction "NIC"
     :statute/kind :law
     :statute/law-number "Ley No. 344, aprobada por la Asamblea Nacional 27 de abril de 2000, promulgada 22 de mayo de 2000 por el Presidente Arnoldo Alemán Lacayo, publicada en La Gaceta, Diario Oficial No. 97, 24 de mayo de 2000 (own closing signature block, read directly). Deroga (Art. 11) la Ley No. 127, del 12 de abril de 1991."
     :statute/url "https://faolex.fao.org/docs/pdf/nic206456.pdf"
     :statute/url-provenance :fao-faolex-mirror
     :statute/enacted-date "2000-04-27"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:foreign-investment}}]})

(defn spec-basis
  "The jurisdiction's statute vector, or nil -- nil means NO spec-basis
  for that jurisdiction yet."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report, same shape/discipline as `marketentry.facts/coverage`:
  never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-nic statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "NIC")) " NIC statute(s) seeded with an "
                 "official citation. Extend `statute.facts/catalog`, never "
                 "fabricate a law-id or URL.")})))

(defn by-topic
  "Statutes for `iso3` tagged with `topic` (e.g. :labor, :data-protection)."
  [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
