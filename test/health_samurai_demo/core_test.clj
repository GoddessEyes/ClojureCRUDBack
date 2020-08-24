(ns health-samurai-demo.core-test
  (:require [clojure.test :refer :all]
            [health-samurai-demo.core :refer :all]
            [health-samurai-demo.crud :refer [insert-patient get-patient-by-id all-patients delete-patient update-patient]]
            [clojure.java.jdbc :as jdbc]
            [clojure.tools.logging :refer [info]]
            [clj-http.client :as client]))

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
    (jdbc/db-do-commands db-spec [create-patient-table-ddl])
    (info "Create patient")
    (let [patient (insert-patient db-spec patient-schema)]
      (info patient)
      (info "Fetching patient by id:")
      (is patient (get-patient-by-id db-spec (str (:id (first patient)))))
      (info "Fetching all patient")
      (is patient (all-patients db-spec)))
    (let [updated-patient (update-patient db-spec "1" update-patient-schema)]
      (info "Result update action (must be 1): " updated-patient)
      (is updated-patient 1)
      (info (get-patient-by-id db-spec "1")))
    (info "Deleting patient with id=1")
    (delete-patient db-spec "1")
    (jdbc/db-do-commands db-spec drop-patient-table-ddl)
    ))
