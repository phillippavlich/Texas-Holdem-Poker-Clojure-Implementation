(ns texas-holdem-poker.builddb
  (:require [clojure.java.jdbc :as jdbc]
            )
  )

;;lein repl
;;(require '[texas-holdem-poker.builddb :as db])
;;(db/testing)
;;https://github.com/clojure-emacs/cider/issues/2028

(defn testing
  "test connection."
  []
  (+ 1 1))