(require '[investtrack.parsing.bovespa :as parser]
         '[investtrack.database :as db]
         '[investtrack.util :as util])

(do
  (let [path (last *command-line-args*)]
    (->> path
       parser/parse-file
       (map util/bovespa->influx-share)
       vec
       db/insert-many)))