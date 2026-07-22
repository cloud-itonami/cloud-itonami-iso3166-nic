# Business Model — Republic of Nicaragua

## Offer

- DGCE (Dirección General de Contrataciones del Estado), dependencia
  del Ministerio de Hacienda y Crédito Público (MHCP) -- public-sector
  procurement under Ley No. 1238, "Ley de Contrataciones
  Administrativas del Estado" (La Gaceta, Diario Oficial No. 35, 21 de
  febrero de 2025) and its Reglamento, Decreto Presidencial No. 07-2025
  (La Gaceta No. 83, 12 de mayo de 2025). Ley 1238 replaced the earlier
  Ley No. 737 (Sector Público) and Ley No. 801 (Municipal) regimes into
  one law. SISCAE is the electronic backbone; www.nicaraguacompra.gob.ni
  is the public single portal (see `src/marketentry/facts.cljc`)
- Registro Público Mercantil (Sistema Nacional de Registros -- SINARE,
  Ley No. 698/Ley No. 1035) business registration -- required for legal
  personality (Art. 155: unregistered sociedades mercantiles have NO
  legal personality)
- RUC (Cédula RUC, Registro Único de Contribuyentes) tax registration
  (Código Tributario, Ley No. 562, Art. 26, Dirección General de
  Ingresos)
- Certificado de Verificación de Proveedores del Estado gate (flagship
  check) -- bars a filing that claims the wrong requirement status for
  its own declared contract value against the Ley 1238 Art. 29 numeral
  4 Contratación Menor threshold (C$2,000,000); the certifying
  authority (Comisión de Verificación de Proveedores del Estado) is a
  body of the Asamblea Nacional, not the executive procurement
  authority
- value-tiered contracting modalities (Licitación Pública/Selectiva/
  Contratación Menor/Simplificada/Concurso para Consultores)

## Trust Controls

- Any actual DGCE filing or Registro Público Mercantil inscription
  requires Market-Entry Compliance Governor clearance and always
  escalates to human sign-off.
- A false or fabricated regulatory-requirement claim is a HARD hold.
- `:filing/submit` never automated
