(defproject investtrack "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"

  :main investtrack.main
  :aot [investtrack.main]

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [http-kit "2.1.16"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-json "0.4.0"]
                 [clj-time "0.14.0"]
                 [korma "0.4.0"]
                 [org.postgresql/postgresql "9.2-1002-jdbc4"]
                 [ragtime "0.7.2"]]

  :plugins [[lein-ring "0.9.7"]]

  :ring {:handler investtrack.main/app}

  :aliases {"import"   ["run" "-m" "investtrack.scripts.import/import-file"]
            "migrate"  ["run" "-m" "investtrack.scripts.migration/migrate"]
            "rollback" ["run" "-m" "investtrack.scripts.migration/rollback"]}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                 [ring/ring-mock "0.3.0"]
                                 [pjstadig/humane-test-output "0.8.3"]]}})