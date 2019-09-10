(ns texas-holdem-poker.cards)

(def suits [:clubs :spades :hearts :diamonds])
(def ranks (range 2 15)) ;; range is exclusive
(def card-names [:jack :queen :king :ace])

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

(defn rank-name
  "This returns the string of the rank of the card"
  [rank]
  (cond (> (int rank) 10) (str (nth card-names (mod rank 11)) " ")
        (= 0 rank) ""
        :else (str rank " ")
        )
  )

(defn name-card
  "This returns the view name of the card"
  [card]
  (apply str (rank-name (get card :rank)) "of " (str (get card :suit)))
  )
