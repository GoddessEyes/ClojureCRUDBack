(ns health-samurai-demo.patient
  (:require
   [health-samurai-demo.database :refer [allPatients insertPatient deletePatient]]
   [schema.core :as s]))

(require
 '[compojure.api.sweet :refer :all]
 '[ring.util.http-response :refer [ok]])

(s/defschema PatientSchema
  {:full_name s/Str
   :gender s/Int
   :date_birth s/Str
   :address s/Str
   :oms s/Int})

(s/defschema IdSchema
  {:id s/Int})

(defn get-all-handler []
  (ok {:result (allPatients)}))

(defn create-patient-handler [create-patient-req]
  (ok {:result (insertPatient create-patient-req)}))

(defn delete-patient-handler [delete-patient-req]
  (ok {:result (deletePatient delete-patient-req)}))

(def patient-routes
  [(GET "/patients" []
     :tags ["api"]
     :query-params []
     :summary "adds two numbers together"
     (get-all-handler))

   (POST "/create_patients" []
     :tags ["api"]
     :body [create-patient-req PatientSchema]
     (create-patient-handler create-patient-req))

   (DELETE "/delete_patients" []
     :tags ["api"]
     :body [delete-patient-req IdSchema]
     (delete-patient-handler delete-patient-req))])
