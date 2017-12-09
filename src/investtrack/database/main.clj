(ns investtrack.database.main
  (:require [capacitor.core :as influx]
            [clojure.string :refer [join trim]]
            [clojure.string :as str]))
            
(defprotocol InfluxPoint
  "type is able to transform self into an influx point"
  (->influx-point [record]))

(defmacro influx->record
  "tranform a influx point into a project record"
  [influx-point]
  `(let 
    [columns# (map keyword (:columns ~influx-point))
     values# (first (:values ~influx-point))
     record# (-> ~influx-point :name str/capitalize drop-last str/join)]
    (list (symbol (str "map->" record#)) (zipmap columns# values#))))

(def client (influx/make-client {:db "investtrack"}))
(def insert-into (partial influx/write-point client))
(def insert-many (partial influx/write-points client))

(defn query [influx-query]
  (let [result (influx/db-query client influx-query)]
    (influx->record (get-in result [:results 0 :series 0]))))

(defn make-conditions [conditions]
  (if (empty? conditions)
    nil
    (join " AND " (for [[key value] conditions] (str (name key) "=" value)))))

(defn find
  "create a select statement"
  [fields & {:keys [in where]}]
  (let [fields-value (if (keyword? fields) "*" (join ", " fields))]
    (let [selection (str "SELECT " fields-value)]
      (let [source (str "FROM " in)]
        (let [conditions
              (if-let [conditions (make-conditions where)]
                (str "WHERE " conditions))]
          (trim
           (join " " [selection source conditions])))))))