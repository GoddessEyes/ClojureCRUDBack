(ns health-samurai-demo.schemes
  (:require [schema.core :as s]))

(s/defschema patient
  {:full_name s/Str
   :gender s/Int
   :date_birth s/Str
   :address s/Str
   :oms s/Int})

(s/defschema id
  {:id s/Int})
