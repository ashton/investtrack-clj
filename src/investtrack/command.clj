(ns investtrack.command
    (:require [investtrack.parsing.bovespa :as parser]
              [investtrack.database :as db]
              [investtrack.util :as util])
    (:gen-class))

(defn -main [path]
    (->> path 
        parser/parse-file
        (map util/bovespa->influx)
        vec
        db/insert-many))