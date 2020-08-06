(ns health-samurai-demo.core)

(require '[ring.adapter.jetty :refer [run-jetty]]
         '[compojure.api.sweet :refer [api routes]]
         '[health-samurai-demo.patient :refer [patient-routes]])

(def swagger-config
  {:ui "/swagger"
   :spec "/swagger.json"
   :options {:ui {:validatorUrl nil}
             :data {:info {:version "1.0.0", :title "Restful CRUD API"}}}})

(def app
  (api
   {:swagger swagger-config}
   (apply routes patient-routes)))

(defn -main
  [& args]
  (run-jetty app {:port 3000}))
