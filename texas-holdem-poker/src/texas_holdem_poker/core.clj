(ns texas-holdem-poker.core)

(require '[texas-holdem-poker.cards :as cards])

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!")
  (let [deck (cards/build-deck)]

    (cards/deal-two-cards deck)
    ;;(cards/deal-two-cards (cards/shuffle-deck deck))


    )




  )

