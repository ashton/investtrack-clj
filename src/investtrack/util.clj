(ns investtrack.util
  (:require [clj-time.coerce :refer [to-long]]))

(defn bovespa->influx-share
  "transforms a bovespa data map into a influx point"
  [bovespa-record]
  (def tags [:code :market-type :name])
  (def values [:opening-price :max-price :min-price :avg-price :last-price :best-buy :best-sell :trade-count :trade-amount])

  (assoc
   {}
   :measurement "shares"
   :tags (select-keys bovespa-record tags)
   :fields (select-keys bovespa-record values)
   :timestamp (to-long (:date bovespa-record))))

(defn influx->map
  "tranform a influx point into a clojure map"
  [influx-point]
  (let [item (first (:series influx-point))]
    (let [columns (map keyword (:columns item))]
      (let [values (first (:values item))]
        (zipmap columns values)))))