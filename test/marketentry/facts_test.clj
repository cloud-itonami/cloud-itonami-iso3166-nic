(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest nic-has-spec-basis
  (let [sb (facts/spec-basis "NIC")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/corporate-number-spec-basis "NIC")))
    (is (some? (facts/certificado-verificacion-spec-basis "NIC")))))

(deftest nic-rep-spec-basis-is-honestly-absent
  (testing "no verifiable Nicaraguan representative-exclusion-extension provision was located -- deliberately not claimed"
    (is (nil? (facts/rep-spec-basis "NIC")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "NIC")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "NIC" all)))
    (is (not (facts/required-evidence-satisfied? "NIC" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["NIC" "USA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 2 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))

(deftest certificado-verificacion-spec-basis-criteria
  (let [cv (facts/certificado-verificacion-spec-basis "NIC")]
    (is (= 2000000.0
           (get-in cv [:certificado-verificacion-criteria :contratacion-menor-threshold-cordobas])))))
