# cloud-itonami-iso3166-nic

Open ISO 3166 Blueprint for **NIC**: Republic of Nicaragua.

- DGCE (Dirección General de Contrataciones del Estado), within the
  Ministerio de Hacienda y Crédito Público (MHCP) -- public-sector
  procurement under Ley No. 1238 "Ley de Contrataciones Administrativas
  del Estado" (2025), which replaced the earlier Ley No. 737 (Sector
  Público) and Ley No. 801 (Municipal) regimes
- Registro Público Mercantil (Sistema Nacional de Registros -- SINARE,
  Ley No. 698/Ley No. 1035) business registration + RUC (Registro Único
  de Contribuyente, Código Tributario Ley No. 562) tax registration

AGPL-3.0-or-later.

## Market-entry / statute catalogs

Governed public-sector market-entry compliance actor, same architecture
as `cloud-itonami-iso3166-cub`/`-hti`/`-dom`. This iteration first
verified Nicaragua's REAL, CURRENT procurement law rather than assuming
the task brief's premise: Ley No. 737 "Ley de Contrataciones
Administrativas del Sector Público" (which the brief named) turned out
to have been REPEALED and replaced by **Ley No. 1238, "Ley de
Contrataciones Administrativas del Estado"** (La Gaceta, Diario
Oficial No. 35, 21 de febrero de 2025), confirmed both from the
procurement portal's own navigation (via the Internet Archive Wayback
Machine, since direct fetches of `nicaraguacompra.gob.ni` timed out
every attempt) and independently from the law's own text (fetched and
`pdftotext`-verified 2026-07-22/23, see the namespace docstrings for
the full research trail, including facts this iteration could NOT
verify, such as PRONicaragua's own legal-creation instrument):

- `src/marketentry/{facts,governor,phase,sim,operation,registry,store,
  marketentryllm}.cljc` -- the actor. `facts.cljc` cites Ley No. 1238
  (aprobada 18 de febrero de 2025, promulgada 19 de febrero de 2025 by
  Co-Presidentes Daniel Ortega y Rosario Murillo, publicada La Gaceta
  No. 35) and its Reglamento, Decreto Presidencial No. 07-2025 (La
  Gaceta No. 83, 12 de mayo de 2025): DGCE/MHCP is the Autoridad
  Rectora, SISCAE is the electronic backbone, and
  `www.nicaraguacompra.gob.ni` is the public single portal (confirmed
  from a MARENA notice published in the SAME La Gaceta edition as Ley
  1238 itself, not merely the portal's own homepage). Contracting
  modalities are value-tiered in córdobas (Art. 29): Licitación Pública
  > C$10,000,000; Licitación Selectiva C$2,000,001-C$10,000,000;
  Contratación Menor <= C$2,000,000.
  `governor.cljc`'s flagship check independently recomputes whether an
  engagement's own declared contract value crosses that Contratación
  Menor threshold and, if so, whether the engagement's claim about
  needing a **Certificado de Verificación de Proveedores del Estado**
  (Art. 18 numeral 6) matches -- a THRESHOLD-GATED, CROSS-BRANCH
  CERTIFICATION check: the certifying authority (Comisión de
  Verificación de Proveedores del Estado) is a body of the
  **legislative** branch (Asamblea Nacional), not the executive
  procurement authority (DGCE/MHCP) that runs the rest of the pipeline
  -- a shape genuinely different from every other iso3166 sibling's
  (none of which check a required CROSS-BRANCH approval).
- `src/statute/facts.cljc` -- general-law catalog: Ley No. 1238 itself
  (also catalogued here as an ongoing compliance statute, not just a
  market-entry gate); Ley No. 698 "Ley General de los Registros
  Públicos" as reformed by Ley No. 1035 (2020) -- Registro Público
  Mercantil business registration, unregistered sociedades mercantiles
  have NO legal personality (Art. 155); Ley No. 562 "Código Tributario"
  (RUC tax registration, Art. 26); Ley No. 185 "Código del Trabajo"
  (labor -- this iteration also independently verified Art. 14's real
  90%-Nicaraguan-workforce quota with a discretionary MITRAB technical
  exception, documented but not built into the flagship check); Ley No.
  344 "Ley de Promoción de Inversiones Extranjeras" (voluntary MIFIC
  Registro Estadístico notification tied to benefits eligibility, NOT a
  mandatory gate).

Every citation is curl/`pdftotext`-verified against an official source
(`www.lagaceta.gob.ni`, `webserver.ineter.gob.ni`) or a legitimate
international-organization mirror (OAS MESICIC, FAO FAOLEX) used only
where a direct `.gob.ni`/`legislacion.asamblea.gob.ni` fetch
connection-timed-out or was WAF-rejected every attempt this iteration
made -- disclosed in the namespace docstrings, never concealed.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Nicaragua:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
