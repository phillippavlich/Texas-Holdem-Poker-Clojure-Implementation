(ns texas-holdem-poker.common
  (:require [texas-holdem-poker.cards :as cards]
            [texas-holdem-poker.scoring :as score])
  )

(defn get-deck
  "Builds and shuffles the deck to play with."
  []
  (cards/shuffle-deck (cards/build-deck))
  )

(defn get-player-cards
  "Gets the card names of each player."
  [deck]
  (let [playerA (get-player-a deck)
        playerB (get-player-b deck)]

      (str "Player A: " (cards/name-card (first playerA))
           " and " (cards/name-card (last playerA)) " and "
           "Player B: " (cards/name-card (first playerB))
           " and " (cards/name-card (last playerB))
      )
    )

  )

(defn get-player-a
  "Gets Player A's cards."
  [deck]
  (cards/deal-cards deck 2)
  )

(defn get-player-b
  "Gets Player B's cards."
  [deck]
  (cards/deal-cards (cards/remove-cards-from-deck deck 2) 2)
  )

(defn get-flop
  "Gets the flop, 3 cards flipped."
  [deck]
  (cards/deal-cards (cards/remove-cards-from-deck deck 5) 3)
  )

(defn get-turn
  "Gets the turn, 1 cards flipped."
  [deck]
  (cards/deal-cards (cards/remove-cards-from-deck deck 9) 1)
  )

(defn get-river
  "Gets the river, 1 cards flipped."
  [deck]
  (cards/deal-cards (cards/remove-cards-from-deck deck 11) 1)
  )

(defn get-result
  "Returns the result of the hand."
  [dealt playerA playerB]
  (score/who-wins (concat dealt playerA) (concat dealt playerB))
  )



