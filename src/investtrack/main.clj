(ns investtrack.main
  (:require [investtrack.routes :refer [app-routes]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [org.httpkit.server :refer [run-server]])
  (:gen-class))

(def app (->
          app-routes
          (wrap-json-body)
          (wrap-json-response)
          (wrap-defaults site-defaults)))

(defn -main []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))]
    (run-server app {:port port})
    (println (str "Listening on port: " port))))