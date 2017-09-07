(ns investtrack.parsing.bovespa
  (:require 
    [investtrack.parsing.rules.bovespa :as bovespa-rules]
    [clojure.java.io :refer :all]))

(defn extract-segment [text segment-rule]
    "extract a segment from a line, following the defined rules"
    (let [segment
      (let [start (:start-pos segment-rule)
            end (:end-pos segment-rule)]
        (subs text (dec start) end))]
      ((:formatter segment-rule) segment)))

(defn parse-row [row]
  "parse a row into an record following the bovespa rules"
  (reduce
      #(assoc %1 (keyword (:id %2)) (extract-segment row %2))
      {} 
      bovespa-rules/historical-data))

(defn parse-rows [lines]
  "parse a vector of lines into records following bovespa rules"
  (map parse-row lines))

(defn parse-file [path]
  "parse every line in a file following bovespa rules, returning a vector of records"
  (with-open [rdr (reader path)]
    (let [lines (line-seq rdr)]
      (doall
        (parse-rows
          (drop 1 ; ignoring header line
            (drop-last ; ignoring trailer line
              (into [] lines))))))))