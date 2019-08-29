(ns texas-holdem-poker.cards)

(def suits [:clubs :spades :hearts :diamonds])
(def ranks (range 1 14)) ;; range is exclusive

(defn build-deck []
  "Builds the deck for the first time."
  (set
    (for [suit suits
          rank ranks]
      {:suit suit :rank rank})))

(defn shuffle-deck
  "Shuffles the deck."
  [x]
  (shuffle x))

(defn deal-two-cards
  "Selects 2 cards from the deck x."
  [x]
  (println (take 2 x) "These are your two cards!"))
