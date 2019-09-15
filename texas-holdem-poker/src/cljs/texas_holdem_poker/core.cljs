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
  (get suit (get card :suit))
  )

(defn get-rank
  "Gets the url for the suit."
  [card]
  (get card :rank)
  )

;;build a card component that has all the html/css required.
(defn build-card
  "Build the card for the UI."
  [card]
  [:div {:class "card"}
   [:div {:class "rank-card"} (str (get-rank card))]
   [:img {:id "test1" :src (get-suit-url card) :class "suit"}]
   [:div {:class "rank-card right-rank"} (str (get-rank card))]
   ]
  )

(defn greeting []
  [:div
   [:h1 (:text @app-state)]
   [:p (:text @player-cards)]
   ;;[:div {:class "card"} (:text @flop-view)]
   ;; [:div {:class "card"} (:text @turn-view)
    ;;[:div {:class "rank-card"} (str (get-rank turn))]
    ;;[:img {:id "test" :src (get-suit-url turn) :class "suit"}]
    ;;]




   ;;look at internet explorer
   [:div {:class "card face-down"}]
   [:div {:class "deal-box"}
    (build-card (first flop))
    (build-card (second flop))
    (build-card (last flop))
    [:br]
    (build-card (first turn))
    (build-card (first river))
    ]
   [:br]

   [:div {:class "deal-box"}
    [:h3 "player A has:"]
    [:br]
    (build-card (first playerA))
    (build-card (last playerA))
    ]

   [:div {:class "deal-box"}
    [:h3 "player B has:"]
    [:br]
    (build-card (first playerB))
    (build-card (last playerB))
    ]
    [:p (:text @result)]
    ]


  )

(defn render []
  (reagent/render [greeting] (js/document.getElementById "app"))

  )
