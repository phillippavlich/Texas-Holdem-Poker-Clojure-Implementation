(ns texas-holdem-poker.core)

(require '[texas-holdem-poker.cards :as cards])

(defn main
  "This is the main function to start to the game."
  [x]
  (println "Welcome to Phil's Texas Holdem Poker game!")

  (let [deck (cards/shuffle-deck (cards/build-deck))]

    (println "You were dealt these cards: " (cards/deal-two-cards deck))
    (println "These are the cards left in the deck: " (cards/remove-cards-from-deck deck 2))

    )

  )

