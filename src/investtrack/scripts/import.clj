(ns investtrack.scripts.import
  (:require [clj-time.coerce :as dt]
            [korma.core :refer [insert values]]
            [investtrack.database.entities :as entities]
            [investtrack.parsing.bovespa :as parser]))

(defn- parse-prices
  [path]
  (->> path
       parser/parse-file
       (map #(update % :date dt/to-timestamp))))

(defn import-file
  [& args]
  (let [path (last args)]
    (let [prices (parse-prices path)]
      (let [partition-count (int (Math/ceil (/ (count prices) 1000)))]
        (doseq [price (partition partition-count prices)]
          (insert entities/prices
            (values price)))))))