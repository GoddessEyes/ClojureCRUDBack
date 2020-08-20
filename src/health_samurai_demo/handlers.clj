(ns health-samurai-demo.handlers
  (:require
   [health-samurai-demo.crud :refer [all-patients insert-patient delete-patient get-patient-by-id update-patient]]
   [ring.util.http-response :refer [ok]]
   [health-samurai-demo.config :refer [db]]))

(defn get-all-handler []
  (ok {:result (all-patients db)}))

(defn create-patient-handler [create-patient-req]
  (ok {:result (insert-patient db create-patient-req)}))

(defn delete-patient-handler [delete-patient-req]
  (ok {:result (delete-patient db delete-patient-req)}))

(defn get-patient-by-id-handler [patient-id]
  (ok {:result (get-patient-by-id db patient-id)}))

(defn update-patient-handler [patient-id update-patient-req]
  (ok {:result (update-patient db patient-id update-patient-req)}))
