(ns health-samurai-demo.crud
  (:require [clojure.java.jdbc :as jdbc]
            [health-samurai-demo.utils :refer [get-date-from-string]]))

(defn insert-patient [db patient]
  (jdbc/insert! db :patient {:date_birth  (get-date-from-string (get patient :date_birth))
                             :full_name  (get patient :full_name)
                             :address  (get patient :address)
                             :oms  (get patient :oms)
                             :gender (get patient :gender)}))

(defn all-patients [db]
  (jdbc/query db
              ["SELECT * FROM patient"]))

(defn get-patient-by-id [db patient_id]
  (jdbc/query db ["SELECT * FROM patient WHERE id = ?" (Integer/parseInt patient_id)]))

(defn delete-patient [db patient_id]
  (jdbc/delete! db :patient ["id = ?" (get patient_id :id)]))

(defn update-patient [db patient_id update_schema]
  (jdbc/update! db :patient {:date_birth  (get-date-from-string (get update_schema :date_birth))
                             :full_name  (get update_schema :full_name)
                             :address  (get update_schema :address)
                             :oms  (get update_schema :oms)
                             :gender (get update_schema :gender)}
                ["id = ?" (Integer/parseInt patient_id)]))
