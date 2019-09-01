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

(defn calculate-score
  "Calculates the score of the player."
  [hand]
  (cond (flush? hand) (score-rank :flush)
        (straight? hand) (score-rank :straight)
        :else (score-rank :high-card))
  )


(defn who-wins
  "Checks which player wins the hand."
  [hand1 hand2]
  (let [result (compare (calculate-score hand1) (calculate-score hand2))]

    (cond (= 1 result) "Player A wins"
          (= -1 result) "Player B wins"
          :else "Tie")

    )


  )


