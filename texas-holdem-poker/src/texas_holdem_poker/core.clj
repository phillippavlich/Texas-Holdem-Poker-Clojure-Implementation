(ns texas-holdem-poker.core)

(require '[texas-holdem-poker.cards :as cards])
(require '[texas-holdem-poker.scoring :as score])

(defn main
  "This is the main function to start to the game."
  [x]
  (println "Welcome to Phil's Texas Holdem Poker game!")

  (let [deck (cards/shuffle-deck (cards/build-deck))
        playerA (cards/deal-cards deck 2)
        playerB (cards/deal-cards (cards/remove-cards-from-deck deck 2) 2)
        ]

    (println "Player A: " (cards/name-card (first playerA))
             " and " (cards/name-card (last playerA))  )
    (println "Player B: " (cards/name-card (first playerB))
             " and " (cards/name-card (last playerB))  )

    (println "These are the cards left in the deck: "
             (cards/remove-cards-from-deck deck 4))

    ;;flop
    (def flop (cards/deal-cards
                (cards/remove-cards-from-deck deck 5) 3))
    (println "Flop: " flop)

    ;;turn
    (def turn (concat flop (cards/deal-cards (cards/remove-cards-from-deck deck 9) 1)))
    (println "Turn: " turn)

    ;river
    (def river (concat turn (cards/deal-cards (cards/remove-cards-from-deck deck 11) 1)))
    (println "River: " river)

    ;;scoring player A
    (println (concat river playerA))
    (println (score/flush? (concat river playerA)))
    (println (score/straight? (concat river playerA)))

    ;;scoring player B
    (println (concat river playerB))
    (println (score/flush? (concat river playerB)))
    (println (score/straight? (concat river playerB)))

    ;;check who wins
    (println (score/who-wins (concat river playerA) (concat river playerB)))

    )

  )

