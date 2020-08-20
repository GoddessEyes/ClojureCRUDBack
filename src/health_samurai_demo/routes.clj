(ns health-samurai-demo.routes
  (:require
   [health-samurai-demo.handlers :refer [get-all-handler
                                         create-patient-handler
                                         delete-patient-handler
                                         get-patient-by-id-handler
                                         update-patient-handler]]
   [health-samurai-demo.schemes :refer [id patient]]
   [compojure.api.sweet :refer :all]))

(def patient-routes
  [(GET "/api/v1/patients" []
     :tags ["patient"]
     :query-params []
     :summary "List all patients"
     (get-all-handler))

   (GET "/api/v1/patients/:patient-id" [patient-id]
     :tags ["patient"]
     :summary "Retrieve single patient"
     (get-patient-by-id-handler patient-id))

   (POST "/api/v1/create_patients" []
     :tags ["patient"]
     :body [create-patient-req patient]
     :summary "Create patient"
     (create-patient-handler create-patient-req))

   (DELETE "/api/v1/delete_patients" []
     :tags ["patient"]
     :body [delete-patient-req id]
     :summary "Delete patient"
     (delete-patient-handler delete-patient-req))

   (PATCH "/api/v1/update_patients/:patient-id" [patient-id]
     :tags ["patient"]
     :body [update-patient-req patient]
     :summary "Edit patient"
     (update-patient-handler patient-id update-patient-req))])