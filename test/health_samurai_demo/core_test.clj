(ns health-samurai-demo.core-test
  (:require [clojure.test :refer :all]
            [health-samurai-demo.core :refer :all]
            [health-samurai-demo.crud :refer [insert-patient get-patient-by-id all-patients delete-patient update-patient]]
            [clojure.java.jdbc :as jdbc]))

(def db-spec {:subprotocol "postgresql"
              :subname "test"
              :user "test"
              :password "test"
              :zeroDateTimeBehaviour "convertToNull"})

(def patient-schema {:date_birth  "2020-11-11"
                     :full_name  "Testing"
                     :address  "Testing 1 1"
                     :oms  123456789
                     :gender 1})

(def update-patient-schema {:date_birth  "2020-12-12"
                            :full_name  "Updated"
                            :address  "Updated"
                            :oms  987654321
                            :gender 0})

(def create-patient-table-ddl
  (jdbc/create-table-ddl :patient
                         [[:id "serial primary key"]
                          [:full_name "varchar(255)"]
                          [:gender "smallint"]
                          [:date_birth "date"]
                          [:address "varchar(1000)"]
                          [:oms "int"]]))

(def drop-patient-table-ddl (jdbc/drop-table-ddl :patient))

(deftest patient-table-test
  (testing "Test for patient table crud actions."
    (is (= (jdbc/db-do-commands db-spec [create-patient-table-ddl]) [0]) "Creating table. Must be return 0.")
    (let [patient (insert-patient db-spec patient-schema)]
      (is (= (get (first patient) :id) 1) "Check new patient entry id=1")
      (is (= patient (get-patient-by-id db-spec (str (:id (first patient))))) "Fetching patient by id")
      (is (= patient (all-patients db-spec))) "Fetching all patient. Is equal `patient by id` when one patient entry.")
    (let [updated-patient (update-patient db-spec "1" update-patient-schema)]
      (is (= updated-patient [1]) "Result update action (must be 1)"))
    (is (= (delete-patient db-spec {:id 1}) [1]) "Deleting patient id=1")
    (is (= (jdbc/db-do-commands db-spec drop-patient-table-ddl) [0]) "Drop table. Must be return 0.")))
