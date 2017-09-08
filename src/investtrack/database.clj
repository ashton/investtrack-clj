(ns investtrack.database
    (:require [capacitor.core :as influx]
              [clojure.string :refer [join trim]]))

(def client (influx/make-client {:db "investtrack"}))
(def query (partial influx/db-query client))
(def insert-into (partial influx/write-point client))

(defn make-conditions [conditions]
    (if (empty? conditions)
        nil
        (join " AND " (for [[key value] conditions] (str (name key) "=" value)))))

(defn select
    "create a select statement"
    [fields & {:keys [from where]}]
    (let [fields-value (if (keyword? fields) "*" (join ", " fields))]
        (let [selection (str "SELECT " fields-value)]
            (let [source (str "FROM " from)]
                (let [conditions 
                    (if-let [conditions (make-conditions where)]
                        (str "WHERE " conditions))]
                    (trim 
                        (join " " [selection source conditions])))))))


(defn record->point
    "transform a parsed record into influx point"
    [record]
)