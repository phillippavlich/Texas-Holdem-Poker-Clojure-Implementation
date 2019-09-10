(ns texas-holdem-poker.components.ui
  (:require [com.stuartsierra.component :as component]
            [texas-holdem-poker.core :refer [render]]))

(defrecord UIComponent []
  component/Lifecycle
  (start [component]
    (render)
    component)
  (stop [component]
    component))

(defn new-ui-component []
  (map->UIComponent {}))
