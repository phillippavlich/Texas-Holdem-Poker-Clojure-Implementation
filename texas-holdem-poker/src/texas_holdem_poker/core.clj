(ns texas-holdem-poker.core)

(require '[texas-holdem-poker.cards :as cards])

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!")
  (cards/build-deck)


  )
