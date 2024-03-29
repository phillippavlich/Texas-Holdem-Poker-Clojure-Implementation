(ns texas-holdem-poker.system
  (:require [com.stuartsierra.component :as component]
            [texas-holdem-poker.components.ui :refer [new-ui-component]]))

(declare system)

(defn new-system []
  (component/system-map
   :app-root (new-ui-component)))

(defn init []
  (set! system (new-system)))

(defn start []
  (set! system (component/start system)))

(defn stop []
  (set! system (component/stop system)))

(defn ^:export go []
  (init)
  (start))

(defn reset []
  (stop)
  (go))
