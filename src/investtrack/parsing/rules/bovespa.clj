(ns investtrack.parsing.rules.bovespa
  (:require [clojure.string :refer [trim]]
            [clj-time.format :as dt-fmt]))

(defn parse-float [value]
  "transform a 13 (last 2 decimals) digit number in a float"
  (Double/parseDouble
   (str
    (subs value 0 11) "." (subs value 11))))

(defn parse-date [value]
  "transform value to date in a yyyyMMdd Joda time format"
  (dt-fmt/parse
   (dt-fmt/formatter "yyyyMMdd") value))

(def parse-text trim)

(defn parse-num [value] (Long/parseLong value))

(def historical-data
  [{:id "date" :start-pos 3 :end-pos 10 :formatter parse-date}
   {:id "code" :start-pos 13 :end-pos 24 :formatter parse-text}
   {:id "market-type" :start-pos 25 :end-pos 27 :formatter parse-text}
   {:id "name" :start-pos 28 :end-pos 39 :formatter parse-text}
   {:id "opening-price" :start-pos 57 :end-pos 69 :formatter parse-float}
   {:id "max-price" :start-pos 70 :end-pos 82 :formatter parse-float}
   {:id "min-price" :start-pos 83 :end-pos 95 :formatter parse-float}
   {:id "avg-price" :start-pos 96 :end-pos 108 :formatter parse-float}
   {:id "last-price" :start-pos 109 :end-pos 121 :formatter parse-float}
   {:id "best-buy" :start-pos 122 :end-pos 134 :formatter parse-float}
   {:id "best-sell" :start-pos 135 :end-pos 147 :formatter parse-float}
   {:id "trade-count" :start-pos 148 :end-pos 152 :formatter parse-num}
   {:id "trade-amount" :start-pos 153 :end-pos 170 :formatter parse-num}])