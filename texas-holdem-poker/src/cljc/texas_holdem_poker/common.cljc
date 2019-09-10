(ns texas-holdem-poker.common
  (:require [texas-holdem-poker.cards :as cards]
            [texas-holdem-poker.scoring :as score])
  )

(defn get-deck
  "A function that is shared between clj and cljs"
  []
  (cards/shuffle-deck (cards/build-deck))
  )

(defn get-deck-2
  "A function that is shared between clj and cljs"
  []
  (-> (cards/shuffle-deck (cards/build-deck)) first last)
  )

(defn get-player-cards
  "A function that is shared between clj and cljs"
  [deck]
  (let [playerA (cards/deal-cards deck 2)
        playerB (cards/deal-cards (cards/remove-cards-from-deck deck 2) 2)]

      (str "Player A: " (cards/name-card (first playerA))
           " and " (cards/name-card (last playerA)) " and "
           "Player B: " (cards/name-card (first playerB))
           " and " (cards/name-card (last playerB))
      )
    )

  )

(defn get-player-a
  "A function that is shared between clj and cljs"
  [deck]
  (cards/deal-cards deck 2)
  )

(defn get-player-b
  "A function that is shared between clj and cljs"
  [deck]
  (cards/deal-cards (cards/remove-cards-from-deck deck 2) 2)
  )

(defn get-flop
  "A function that is shared between clj and cljs"
  [deck]
  (cards/deal-cards (cards/remove-cards-from-deck deck 5) 3)
  )

(defn get-turn
  "A function that is shared between clj and cljs"
  [deck]
  (cards/deal-cards (cards/remove-cards-from-deck deck 9) 1)
  )

(defn get-river
  "A function that is shared between clj and cljs"
  [deck]
  (cards/deal-cards (cards/remove-cards-from-deck deck 11) 1)
  )

(defn get-result
  "A function that is shared between clj and cljs"
  [dealt playerA playerB]
  (score/who-wins (concat dealt playerA) (concat dealt playerB))
  )



