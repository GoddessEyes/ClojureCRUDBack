(ns health-samurai-demo.utils
  (:import (java.time LocalDate)))

(defn get-date-from-string [value]
  (LocalDate/parse value))
