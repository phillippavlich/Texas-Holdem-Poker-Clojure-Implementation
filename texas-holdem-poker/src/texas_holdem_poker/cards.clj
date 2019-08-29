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
  [deck]
  (shuffle deck))

(defn remove-cards-from-deck
  "Removes dealt cards from deck."
  [deck num]
  (drop num deck)
  )

(defn deal-cards
  "Selects and deals cards from the deck."
  [deck num]
  (take num deck))
