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

(def parse-rows (partial map parse-row))

(defn skip-first-and-last [lseq]
  (drop-last
   (next lseq)))

(defn open-file-lazy [path]
  (letfn [(read-next-line [rdr]
            (lazy-seq
             (if-let [line (.readLine rdr)]
               (cons line (read-next-line rdr))
               (do (.close rdr) nil))))]
    (read-next-line (clojure.java.io/reader path))))

(defn parse-file [path]
  "parse every line in a file following bovespa rules, returning a vector of records"
  (-> path
      open-file-lazy
      skip-first-and-last
      parse-rows))