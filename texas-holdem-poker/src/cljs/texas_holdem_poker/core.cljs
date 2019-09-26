(ns texas-holdem-poker.core
  (:require [reagent.core :as reagent :refer [atom]]
            [texas-holdem-poker.views.login :as login]
            [texas-holdem-poker.views.game :as game]
            )
  )

(enable-console-print!)
(defonce app-state (atom {:text "Welcome to Phil's Texas Holdem Poker game!"}))

(defn greeting []
  [:div
   [:h1 (:text @app-state)]
   ]
  )

(defn screen []
  [:div
   (login/login-page)
   [greeting]
   (game/game)
   [:h1 "adding more parts"]

   ]
  )

(defn render []
  (reagent/render [screen] (js/document.getElementById "app"))

  )
