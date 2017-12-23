(ns investtrack.database.entities
  (:require [korma.core :refer [defentity database belongs-to insert values]]
            [investtrack.database.main :refer [investtrack-db] :rename {investtrack-db db}]))

(defentity prices
  (database db))

(defentity users
  (database db))

(defentity wallets
  (database db)
  (belongs-to users))

(defentity transactions
  (database db)
  (belongs-to users)
  (belongs-to wallets))