(ns health-samurai-demo.database
  (:require [clojure.java.jdbc :as jdbc]
            [health-samurai-demo.utils :refer [get-date-from-string]]))

(def db {:subprotocol "postgresql"
         :subname "//localhost:5432/demo"
         :user "demo"
         :password "demo"
         :zeroDateTimeBehaviour "convertToNull"})

(defn insertPatient [patient]
  (jdbc/insert! db :patient {:date_birth  (get-date-from-string (get patient :date_birth))
                             :full_name  (get patient :full_name)
                             :address  (get patient :address)
                             :oms  (get patient :oms)
                             :gender (get patient :gender)}))

(defn allPatients []
  (jdbc/query db
              ["SELECT * FROM patient"]))

(defn getPatientById [patient_id]
  (jdbc/query db
              (format "SELECT * FROM patient WHERE id = %s" patient_id)))

(defn deletePatient [patient_id]
  (jdbc/delete! db :patient ["id = ?" (get patient_id :id)]))

(defn updatePatient [patient_id update_schema]
  (jdbc/update! db :patient {:date_birth  (get-date-from-string (get update_schema :date_birth))
                             :full_name  (get update_schema :full_name)
                             :address  (get update_schema :address)
                             :oms  (get update_schema :oms)
                             :gender (get update_schema :gender)}
                [(format "id = %s" patient_id)]))


