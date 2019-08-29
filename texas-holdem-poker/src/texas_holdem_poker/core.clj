(ns texas-holdem-poker.core)

(require '[texas-holdem-poker.cards :as cards])

(defn main
  "This is the main function to start to the game."
  [x]
  (println "Welcome to Phil's Texas Holdem Poker game!")

  (let [cards-gone 4
        deck (cards/shuffle-deck (cards/build-deck))
        playerA (cards/deal-cards deck 2)
        playerB (cards/deal-cards (cards/remove-cards-from-deck deck 2) 2)
        dealt-cards '()
        ]
    
    (println "Player A: " (cards/name-card (first playerA))
             " and " (cards/name-card (last playerA))  )
    (println "Player B: " (cards/name-card (first playerB))
             " and " (cards/name-card (last playerB))  )

    (println "These are the cards left in the deck: "
             (cards/remove-cards-from-deck deck cards-gone))

    ;;burn
    (inc cards-gone)

    ;;flop
    (println "These are the cards left after burn: " (conj dealt-cards (first deck)))
    (+ cards-gone 3)



    )

  )

