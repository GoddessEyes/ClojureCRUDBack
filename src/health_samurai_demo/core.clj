(ns health-samurai-demo.core
  (:gen-class))

(require '[ring.adapter.jetty :refer [run-jetty]]
         '[compojure.api.sweet :refer [api routes]]
         '[health-samurai-demo.routes :refer [patient-routes]]
         '[ring.middleware.cors :refer [wrap-cors]]
         '[health-samurai-demo.config :refer [app-port app-host swagger-config]])

(def app
  (-> (api {:swagger swagger-config} (apply routes patient-routes))
      (wrap-cors :access-control-allow-origin [#".*"] :access-control-allow-methods [:get :put :post :delete :patch])))

(defn -main
  [& args]
  (run-jetty app {:port app-port :host app-host :join? false}))
