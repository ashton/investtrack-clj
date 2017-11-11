(ns investtrack.util)

(defn create-influx-point
  "create a influx point with given data"
  [measurement-name tags values timestamp this]

  (assoc
   {}
   :measurement measurement-name
   :tags (select-keys this tags)
   :fields (select-keys this values)
   :timestamp (get this timestamp)))

(defn influx->map
  "tranform a influx point into a clojure map"
  [influx-point]
  (let [item (first (:series influx-point))]
    (let [columns (map keyword (:columns item))]
      (let [values (first (:values item))]
        (zipmap columns values)))))