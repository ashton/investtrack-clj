(ns investtrack.database.util)

(defn create-influx-point
  "create a influx point with given data"
  [measurement-name tags values timestamp this]

  (assoc
   {}
   :measurement measurement-name
   :tags (select-keys this tags)
   :fields (select-keys this values)
   :timestamp (get this timestamp)))