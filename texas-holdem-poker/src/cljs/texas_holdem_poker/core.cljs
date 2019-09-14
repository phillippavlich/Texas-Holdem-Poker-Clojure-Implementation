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

(def img-id "uploaded-image")

(def suit
  {:clubs "clubs.png"
   :spades "spades.png"
   :diamonds "diamonds.png"
   :hearts "hearts.png"
   })

(defn get-suit-url
  "Gets the url for the suit."
  [card]
  (get suit (get (first card) :suit))
  )

(defn get-rank
  "Gets the url for the suit."
  [card]
  (println (get (first card) :rank))
  (get (first card) :rank)
  )



(defn greeting []
  [:div
   [:h1 (:text @app-state)]
   [:p (:text @player-cards)]
   [:div {:class "card"} (:text @flop-view)]
   [:div {:class "card"} (:text @turn-view)
    [:div {:class "left-rank"} (str (get-rank turn))]
    [:img {:id "test" :src (get-suit-url turn) :class "suit"}]
    ]
   [:div {:class "card"} (:text @river-view)
    [:div {:class "left-rank"} (str (get-rank river))]
    [:img {:id "test1" :src (get-suit-url river) :class "suit"}]
    ]
   [:img {:id img-id :src "clubs.png" :class "suit"}]

   [:div {:class "card face-down"}]
   [:p (:text @result)]
   ]
  )

(defn render []
  (reagent/render [greeting] (js/document.getElementById "app"))

  )
