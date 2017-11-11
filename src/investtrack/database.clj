(ns investtrack.database
  (:require [capacitor.core :as influx]
            [clojure.string :refer [join trim]]
            [investtrack.util :refer [influx->map]]))

(defprotocol InfluxPoint
  "type is able to transform self into an influx point"
  (->influx-point [record]))

(def client (influx/make-client {:db "investtrack"}))
(def insert-into (partial influx/write-point client))
(def insert-many (partial influx/write-points client))
(defn query [influx-query]
  (let [result (influx/db-query client influx-query)]
    (map influx->map (:results result))))

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