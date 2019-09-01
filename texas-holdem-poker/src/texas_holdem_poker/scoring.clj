(ns texas-holdem-poker.scoring)

;;if tied in score rank look to card rank to be a tie breaker!
;;need a function to look into ties
(def score-rank
  {:high-card 0
   :one-pair 1
   :two-pair 2
   :three-of-a-kind 3
   :straight 4
   :flush 5
   :full-house 6
   :four-of-a-kind 7
   :straight-flush 8
   :royal-flush 9})

(defn flush?
  "Returns a boolean whether a flush is present."
  [hand]
  (> (last (first (sort-by val > (frequencies (map :suit hand))))) 4)
  )

(defn straight?
  "Returns a boolean whether a straight is present."
  [hand]
  (let [all-ranks (set (map :rank hand))
        mod-rank (if (contains? all-ranks 14) (conj all-ranks 1) all-ranks) ;; ace can be low or high
        rank-order (-> mod-rank sort distinct)]

    ;;provides list of all results that have a straight, could be multiple results
    (not (empty?
           (for [x rank-order
                       :let [y (take 5 (range x (+ 5 x)))]
                       :when (= y (take 5 (filter #(> % (dec x)) rank-order)))]
                   y)))
    )
  )

(defn get-pairs
  "Retrieves all pairs for a given hand. Sorted by # freq desc,
  then by Ace to 2. Helps to deal with tie breakers."
  [hand]
  (sort-by last > (sort-by first > (-> (map :rank hand) frequencies )))
  )

(defn calculate-score
  "Calculates the score of the player."
  [hand]
  (cond (-> (get-pairs hand) first last (= 4)) (score-rank :four-of-a-kind)
        (flush? hand) (score-rank :flush)
        (straight? hand) (score-rank :straight)
        (-> (get-pairs hand) first last (= 3)) (score-rank :three-of-a-kind)
        (straight? hand) (score-rank :two-pair)
        (-> (get-pairs hand) first last (= 1)) (score-rank :one-pair)
        :else (score-rank :high-card))
  )

(defn high-cards
  "Returns a sorted high card set."
  [hand]
  (take 5 (-> (map :rank hand) sort reverse))
  )

(defn compare-highs
  "Compares highs for tiebreaker"
  [hand1 hand2]
  (loop [cards-a hand1
         cards-b hand2]

    (cond (not (= (first cards-a) (first cards-b))) (if (> (first cards-a) (first cards-b)) (str "Player A won with a " (first cards-a) " high") (str "Player B won with a " (first cards-b) " high"))
          (empty? cards-a) (println "Tie")
          :else (recur (rest cards-a) (rest cards-b)))
    )
  )

(defn tiebreaker
  "Solves tie breakers."
  [hand1 hand2 score]
  (let []
    (cond (= 0 score) (compare-highs (high-cards hand1) (high-cards hand2))
          :else "Tie Haven't dealt with this case yet")
    )
  )

(defn who-wins
  "Checks which player wins the hand."
  [hand1 hand2]
  (let [score-a (calculate-score hand1)
        score-b (calculate-score hand2)
        result (compare score-a score-b)
        ]

    (cond (= 1 result) (str "Player A wins with a " (get (clojure.set/map-invert score-rank) score-a))
          (= -1 result) (str "Player B wins with a " (get (clojure.set/map-invert score-rank) score-b))
          :else (tiebreaker hand1 hand2 score-a))
    )
  )


