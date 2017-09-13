(ns investtrack.util
    (:require [clj-time.coerce :refer [to-long]]))

(defn bovespa->influx
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