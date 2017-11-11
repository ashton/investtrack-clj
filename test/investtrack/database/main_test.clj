(ns investtrack.database.main-test
  (:require [clojure.test :refer :all]
            [investtrack.database.main :as db]))

(deftest database-test
  (testing "query builder with :all keyword"
    (let [query (db/find :all :in "measurement")]
      (is (= query "SELECT * FROM measurement"))))
      
  (testing "query builder with specific fields"
    (let [query (db/find ["field1" "field2"] :in "measurement")]
      (is (= query "SELECT field1, field2 FROM measurement"))))
    
  (testing "query builder with :all keyword and restrictions"
    (let [query (db/find :all :in "measurement" :where {:condition "value"})]
      (is (= query "SELECT * FROM measurement WHERE condition=value"))))
      
  (testing "query builder with specific fields and multiple restrictions"
    (let [query (db/find ["field1" "field2"] :in "measurement" :where {:condition1 "value1" :condition2 "value2"})]
      (is (= query "SELECT field1, field2 FROM measurement WHERE condition1=value1 AND condition2=value2")))))