(ns texas-holdem-poker.core)

(require '[texas-holdem-poker.cards :as cards])

(defn main
  "This is the main function to start to the game."
  [x]
  (println "Welcome to Phil's Texas Holdem Poker game!")

  (let [deck (cards/shuffle-deck (cards/build-deck))
        playerA (cards/deal-cards deck 2)
        newDeckA (cards/remove-cards-from-deck deck 2)
        playerB (cards/deal-cards newDeckA 2)
        newDeckB (cards/remove-cards-from-deck newDeckA 2)
        ]

    (println "You were dealt these cards: " (cards/name-card (first playerA)) (cards/name-card (last playerA))  )
    (println "Opponent was dealt these cards: " (cards/name-card (first playerB)) (cards/name-card (last playerB))  )

    (println "These are the cards left in the deck: " newDeckB)

    )

  )

