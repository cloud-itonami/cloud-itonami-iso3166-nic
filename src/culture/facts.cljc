(ns culture.facts
  "Country-level regional-culture catalog for Nicaragua (NIC) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"NIC"
   [{:culture/id "nic.dish.gallo-pinto"
     :culture/name "Gallo pinto"
     :culture/country "NIC"
     :culture/kind :dish
     :culture/summary "Rice-and-beans dish considered a national dish by both Nicaragua and Costa Rica; its precise origin is uncertain and disputed between the two countries (the so-called \"Gallo Pinto War\")."
     :culture/url "https://en.wikipedia.org/wiki/Gallo_pinto"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nic.dish.nacatamal"
     :culture/name "Nacatamal"
     :culture/country "NIC"
     :culture/kind :dish
     :culture/summary "Traditional Nicaraguan dish similar to a tamale, wrapped in plantain leaves and originating from the Nicarao people who inhabited western Nicaragua."
     :culture/url "https://en.wikipedia.org/wiki/Nacatamal"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nic.dish.vigoron"
     :culture/name "Vigorón"
     :culture/country "NIC"
     :culture/kind :dish
     :culture/summary "Traditional Nicaraguan dish of boiled yuca, chicharrón (fried pork rind) and cabbage curtido served on a banana leaf; per Alejandro Barberena Perez, developed in 1914 in Granada, Nicaragua by Maria Luisa Cisneros Lacayo."
     :culture/url "https://en.wikipedia.org/wiki/Vigor%C3%B3n"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nic.dish.quesillo"
     :culture/name "Quesillo"
     :culture/country "NIC"
     :culture/kind :dish
     :culture/summary "In Nicaragua, quesillo is a corn tortilla wrapped around soft cheese with pickled onions and a sour-cream or liquid-cheese sauce; a beloved street food, disputed between the towns of Nagarote and La Paz Centro in León Department."
     :culture/url "https://en.wikipedia.org/wiki/Quesillo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nic.beverage.pinolillo"
     :culture/name "Pinolillo"
     :culture/country "NIC"
     :culture/kind :beverage
     :culture/summary "Traditional, nutrient-dense cornmeal-and-cacao drink consumed in Nicaragua and Costa Rica for centuries; so widespread and traditional in Nicaragua that Nicaraguans are colloquially called \"pinoleros\" after it."
     :culture/url "https://en.wikipedia.org/wiki/Pinolillo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nic.beverage.flor-de-cana"
     :culture/name "Flor de Caña"
     :culture/country "NIC"
     :culture/kind :beverage
     :culture/summary "Brand of premium rum manufactured and distributed by Compania Licorera de Nicaragua, S.A., headquartered in Managua, Nicaragua; rum production began in 1890 in Chichigalpa and the brand was registered in 1937."
     :culture/url "https://en.wikipedia.org/wiki/Flor_de_Ca%C3%B1a"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nic.craft.joya-de-nicaragua"
     :culture/name "Joya de Nicaragua"
     :culture/country "NIC"
     :culture/kind :craft
     :culture/summary "Cigar brand established in 1968, the oldest brand of cigars still made in Nicaragua, manufactured in Esteli using only Nicaraguan-grown tobacco (puros)."
     :culture/url "https://en.wikipedia.org/wiki/Joya_de_Nicaragua"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nic.festival.palo-de-mayo"
     :culture/name "Palo de Mayo"
     :culture/country "NIC"
     :culture/kind :festival
     :culture/summary "Afro-Nicaraguan festival and dance tradition that originated in Bluefields, Nicaragua, in the 17th century and remains part of the culture of the RAAS (South Caribbean Coast) region."
     :culture/url "https://en.wikipedia.org/wiki/Palo_de_Mayo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nic.festival.la-purisima"
     :culture/name "La Purísima"
     :culture/country "NIC"
     :culture/kind :festival
     :culture/summary "Nationwide Nicaraguan Marian devotion, centered on the Immaculate Conception of El Viejo image (patronage of the Nicaraguan Episcopal Conference), marked by a nine-day novena, home altars, and the December 7 shouting celebration known as La Griteria."
     :culture/url "https://en.wikipedia.org/wiki/Immaculate_Conception_of_El_Viejo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nic.heritage.leon-cathedral"
     :culture/name "León Cathedral"
     :culture/country "NIC"
     :culture/kind :heritage
     :culture/summary "Roman Catholic cathedral in Leon, Nicaragua, elevated by UNESCO to World Heritage Site status on 28 June 2011."
     :culture/url "https://en.wikipedia.org/wiki/Le%C3%B3n_Cathedral,_Nicaragua"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-nic culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "NIC"))
                 " NIC entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
