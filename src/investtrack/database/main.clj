(ns investtrack.database.main
  (:require [korma.db :refer [defdb postgres]]))

(defdb investtrack-db (postgres {:db "investtrack"
                                 :user "postgres"
                                 :host "localhost"}))