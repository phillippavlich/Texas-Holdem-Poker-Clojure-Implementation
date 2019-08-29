(ns texas-holdem-poker.core)

(require '[texas-holdem-poker.cards :as cards])

(defn main
  "This is the main function to start to the game."
  (println "Welcome to Phil's Texas Holdem Proker game!")
  
  (let [deck (cards/build-deck)]

    (cards/deal-two-cards deck)
    ;;(cards/deal-two-cards (cards/shuffle-deck deck))


    )




  )

