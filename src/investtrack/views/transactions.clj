(ns investtrack.views.transactions
  (:require [investtrack.database.main :as db])
  (:use [ring.util.response]))

(defn index []
    (response
     (db/query (db/find :all :in "transactions"))))