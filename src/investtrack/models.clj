(ns investtrack.models
  (:require
    [investtrack.util :refer [create-influx-point]]
    [investtrack.database :refer [InfluxPoint]]))

(defrecord Share 
  [code
   market-type
   name
   opening-price
   max-price
   min-price
   avg-price
   last-price
   best-buy
   best-sell
   trade-count
   trade-amount
   date]
   
  InfluxPoint
  (->influx-point [this]
    (create-influx-point
      "shares"
      [:code :market-type :name]
      [:opening-price :max-price :min-price :avg-price :last-price :best-buy :best-sell :trade-count :trade-amount]
      :date
      this)))

(defrecord Transaction [share market-type quantity value date]
  InfluxPoint
  (->influx-point [this]
    (create-influx-point
      "transactions"
      [:share :market-type]
      [:value]
      :date
      this)))