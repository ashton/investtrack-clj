(ns investtrack.util)
  
  
(defprotocol InfluxPoint
  "type is able to transform self into an influx point"
  
  (->influx-point [record]))

(defn create-influx-point
  "create a influx point with given data"
  [measurement-name tags values timestamp this]

  (assoc
   {}
   :measurement "shares"
   :tags (select-keys this tags)
   :fields (select-keys this values)
   :timestamp (get timestamp this)))

(defn influx->record
  "tranform a influx point into a clojure map"
  [influx-point]
  (let [item (first (:series influx-point))]
    (let [columns (map keyword (:columns item))]
      (let [values (first (:values item))]
        (zipmap columns values)))))