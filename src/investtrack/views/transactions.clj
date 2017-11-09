(ns investtrack.views.transactions
  (:require [investtrack.database :as db])
  (:use [ring.util.response]))

(defn index []
  (let [select-all (db/select :all :from "transactions")]
    (response
     (db/query select-all))))