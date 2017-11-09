(ns investtrack.routes
  (:require [compojure.core :refer [defroutes context GET POST]]
            [investtrack.views.transactions :as transaction-views]))

(defroutes app-routes
  (context "/api" []
    (context "/transactions" []
      (GET "/" [] (transaction-views/index)))))