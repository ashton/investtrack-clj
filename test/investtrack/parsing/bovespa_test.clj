(ns investtrack.parsing.bovespa-test
  (:require [clojure.test :refer :all]
            [clojure.string :refer [lower-case]]
            [clj-time.format :as dt-fmt]
            [investtrack.parsing.bovespa :refer :all]))

(deftest bovespa-parsing-test
  (testing "extract-segment without formatter"
    (let [rule {:start-pos 5 :end-pos 9 :formatter identity}
          text "0000VALUE0000"]
      (is (= (extract-segment text rule) "VALUE"))))
  
  (testing "extract-segment with formatter"
    (let [rule {:start-pos 5 :end-pos 9 :formatter #(lower-case %)}
          text "0000VALUE0000"]
      (is (= (extract-segment text rule) "value"))))
  
  (testing "parse-row"
    (let [row "012017100212FIIP11B     010FII RB CAP ICI  ER  MB   R$  000000001944700000000194980000000019250000000001927700000000192500000000019240000000001925000016000000000000000586000000000011296342000000000000009999123100000010000000000000BRFIIPCTF001194"
          parsed (parse-row row)
          dt-tostr (partial dt-fmt/unparse (dt-fmt/formatter "yyyyMMdd"))]
      (is (= (:market-type parsed) "010"))
      (is (= (:name parsed) "FII RB CAP I"))
      (is (= (:trade-amount parsed) 586))
      (is (= (:trade-count parsed) 16))
      (is (= (:last-price parsed) 192.5))
      (is (= (:min-price parsed) 192.5))
      (is (= (:opening-price parsed) 194.47))
      (is (= (:avg-price parsed) 192.77))
      (is (= (:max-price parsed) 194.98))
      (is (= (:code parsed) "FIIP11B"))
      (is (= (:best-buy parsed) 192.4))
      (is (= (:best-sell parsed) 192.5))
      (is (= (dt-tostr (:date parsed)) "20171002"))))
  
  (testing "parse-rows" )
  
  (testing "skip-first-and-last"
    (let [coll [1 2 3 4 5]]
      (is (= (skip-first-and-last coll) [2 3 4])))))