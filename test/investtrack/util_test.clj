(ns investtrack.util-test 
  (:require [clojure.test :refer :all]
            [investtrack.util :refer :all]))

(deftest util-test
  (testing "create-influx-point"
    (let 
      [point (create-influx-point
              "measurement-name"
              [:tag1 :tag2]
              [:value]
              :date
              {:tag1 "tag1" :tag2 "tag2" :value 42 :date "date"})]
      
      (is (= (:measurement point) "measurement-name"))
      (is (= (:tags point) {:tag1 "tag1" :tag2 "tag2"}))
      (is (= (:fields point) {:value 42}))
      (is (= (:timestamp point) "date")))))