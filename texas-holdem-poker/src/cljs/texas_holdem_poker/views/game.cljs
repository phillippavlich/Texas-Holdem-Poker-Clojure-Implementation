(ns texas-holdem-poker.views.game
  (:require [texas-holdem-poker.common :as common]
            )
  )

;;(common/build-db) ;;build database
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
  "Gets the rank of the card for display."
  [card]
  (let [rank (get card :rank)]
    (cond
      (< rank 11) (str rank)
      (= rank 11) (str "J")
      (= rank 12) (str "Q")
      (= rank 13) (str "K")
      (= rank 14) (str "A")
      )
    )
  )

(defn build-card
  "Build the card for the UI."
  [card]
  [:div {:class "card"}
   [:div {:class "rank-card"} (str (get-rank card))]
   [:img {:src (get-suit-url card) :class "suit"}]
   [:div {:class "rank-card right-rank"} (str (get-rank card))]
   ]
  )

;;look at breaking this down into multiple functions
;;then rendering multiple components
(defn game []
  [:div
   [:div {:class "game-board"}
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

   ]
  )