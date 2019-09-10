(ns texas-holdem-poker.core
  (:require [reagent.core :as reagent :refer [atom]]
            [texas-holdem-poker.common :as common])
  )

(enable-console-print!)

(defonce app-state (atom {:text "Welcome to Phil's Texas Holdem Poker game!"}))
(defonce deck (common/get-deck))
(defonce player-cards (atom {:text (common/get-player-cards deck)}))
(defonce playerA (common/get-player-a deck))
(defonce playerB (common/get-player-b deck))
(defonce flop (common/get-flop deck))
(defonce turn (common/get-turn deck))
(defonce river (common/get-river deck))
(defonce flop-view (atom {:text (common/get-flop deck)}))
(defonce turn-view (atom {:text (common/get-turn deck)}))
(defonce river-view (atom {:text (common/get-river deck)}))
(defonce result (atom {:text (common/get-result (concat flop turn river) playerA playerB)}))

(defn greeting []
  [:div
   [:h1 (:text @app-state)]
   [:h4 (:text @player-cards)]
   [:p (:text @flop-view)]
   [:p (:text @turn-view)]
   [:p (:text @river-view)]
   [:p (:text @result)]
   ]
  )

(defn render []
  (reagent/render [greeting] (js/document.getElementById "app"))

  )
