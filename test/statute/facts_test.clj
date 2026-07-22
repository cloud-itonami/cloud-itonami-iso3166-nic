(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest nic-has-spec-basis
  (let [sb (facts/spec-basis "NIC")]
    (is (= 5 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["NIC" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= #{"nic.ley-1238-contrataciones-administrativas-estado"}
         (set (mapv :statute/id (facts/by-topic "NIC" :public-procurement)))))
  (is (= #{"nic.ley-698-registros-publicos-ley-1035-reforma"}
         (set (mapv :statute/id (facts/by-topic "NIC" :corporate-governance)))))
  (is (= #{"nic.ley-185-codigo-trabajo"}
         (set (mapv :statute/id (facts/by-topic "NIC" :labor)))))
  (is (= #{"nic.ley-344-inversiones-extranjeras"}
         (set (mapv :statute/id (facts/by-topic "NIC" :foreign-investment)))))
  (is (empty? (facts/by-topic "ATL" :labor))))
