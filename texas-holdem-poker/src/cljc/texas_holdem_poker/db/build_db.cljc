(ns texas-holdem-poker.db.build_db
  (:require [clojure.java.jdbc :as jdbc]
            )
  )

;;clojure.java.jdbc
(def db {:dbtype "postgresql"
         :dbname "poker-db"
         :host "localhost"
         :user "poker-phil"
         :password "poker-pass"
         })

(def user-table-sql
  (jdbc/create-table-ddl :users2 [[:user_id :serial "PRIMARY KEY"]
                                 [:email "VARCHAR(20)"]
                                 [:name "VARCHAR(20)"]
                                 [:password "VARCHAR(20)"]
                                 ]))

(def build-all-tables
  (jdbc/execute! db [user-table-sql])

  )
