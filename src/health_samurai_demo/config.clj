(ns health-samurai-demo.config
  (:require [dotenv :refer [env]]))

(let [db-subname (env "POSTGRES_SUBNAME") db-user (env "POSTGRES_USER") db-password (env "POSTGRES_PASSWORD")]
  (def db {:subprotocol "postgresql"
           :subname db-subname
           :user db-user
           :password db-password
           :zeroDateTimeBehaviour "convertToNull"})
  (prn "DB connection: " db))

(def app-port (Integer/parseInt (env "PORT")))
(def app-host (env "HOST"))
(prn (str "HOST: " "http://" app-host ":" app-port))
(prn (str "Swagger Docs: " "http://" app-host ":" app-port "/api/v1/swagger"))

(def swagger-config
  {:ui "/api/v1/swagger"
   :spec "/swagger.json"
   :options {:ui {:validatorUrl nil}
             :data {:info {:version "2.0.0", :title "Restful CRUD API"}}}})
