(ns marketentry.registry-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.registry :as registry]))

(deftest engagement-fee-recompute
  (let [e {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 860000.0}]
    (is (== 860000.0 (registry/compute-engagement-fee e)))
    (is (true? (registry/engagement-fee-matches-claim? e))))
  (let [bad {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 999000.0}]
    (is (false? (registry/engagement-fee-matches-claim? bad)))))

(deftest register-draft-and-submit
  (let [d (registry/register-draft "eng-1" "NIC" 0)
        s (registry/register-submit "eng-1" "NIC" 0)]
    (is (= "NIC-DFT-000000" (get d "draft_number")))
    (is (= "NIC-SUB-000000" (get s "submit_number")))
    (is (nil? (get-in d ["certificate" "proof"])))
    (is (= "draft-unsigned" (get-in s ["certificate" "status"])))))

(deftest register-requires-ids
  (is (thrown? Exception (registry/register-draft "" "NIC" 0)))
  (is (thrown? Exception (registry/register-submit "eng-1" "" 0))))

(deftest certificado-verificacion-required-below-threshold
  (testing "a contract value at or below the Contratación Menor ceiling does NOT require the certificate"
    (is (false? (registry/certificado-verificacion-required? {:contract-value-cordobas 2000000.0})))
    (is (false? (registry/certificado-verificacion-required? {:contract-value-cordobas 500000.0})))
    (is (false? (registry/certificado-verificacion-required? {})))))

(deftest certificado-verificacion-required-above-threshold
  (testing "a contract value exceeding the Contratación Menor ceiling (C$2,000,000) requires the certificate"
    (is (true? (registry/certificado-verificacion-required? {:contract-value-cordobas 2000001.0})))
    (is (true? (registry/certificado-verificacion-required? {:contract-value-cordobas 5000000.0})))))

(deftest certificado-verificacion-mismatch-claim-is-entity-scope-gated
  (testing "an engagement with no claim at all is never flagged"
    (is (false? (registry/certificado-verificacion-claim-mismatch? {:contract-value-cordobas 5000000.0}))))
  (testing "a claimed requirement that does NOT match the independently recomputed threshold test -> mismatch"
    (is (true? (registry/certificado-verificacion-claim-mismatch?
                {:contract-value-cordobas 2500000.0
                 :claimed-certificado-requerido? false})))
    (is (true? (registry/certificado-verificacion-claim-mismatch?
                {:contract-value-cordobas 1500000.0
                 :claimed-certificado-requerido? true}))))
  (testing "a claimed requirement that DOES match -> not flagged"
    (is (false? (registry/certificado-verificacion-claim-mismatch?
                 {:contract-value-cordobas 5000000.0
                  :claimed-certificado-requerido? true})))
    (is (false? (registry/certificado-verificacion-claim-mismatch?
                 {:contract-value-cordobas 500000.0
                  :claimed-certificado-requerido? false})))))
