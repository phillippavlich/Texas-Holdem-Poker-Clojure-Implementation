(ns texas-holdem-poker.scoring)

(require '[texas-holdem-poker.cards :as cards])
;;if tied in score rank look to card rank to be a tie breaker!
;;need a function to look into ties
(def score-rank
  {:high 0
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
  (->> (-> (map :rank hand) frequencies ) (sort-by first > ) (sort-by last >))
  )

(defn calculate-score
  "Calculates the score of the player."
  [hand]
  (println "test1")
  (println (-> (get-pairs hand) first last (= 1)))
  (println (-> (get-pairs hand) first last ))
  (println (-> (get-pairs hand) ))
  (cond (-> (get-pairs hand) first last (= 4)) (score-rank :four-of-a-kind)
        (->> (get-pairs hand) (take 2) (map last) (= '(3 2) )) (score-rank :full-house)
        (flush? hand) (score-rank :flush)
        (straight? hand) (score-rank :straight)
        (-> (get-pairs hand) first last (= 3)) (score-rank :three-of-a-kind)
        (->> (get-pairs hand) (take 2) (map last) (every? #{2})) (score-rank :two-pair)
        (-> (get-pairs hand) first last (= 2)) (score-rank :one-pair)
        :else (score-rank :high))
  )

(defn high-cards
  "Returns a sorted high card set."
  [hand]
  (take 5 (-> (map :rank hand) sort reverse))
  )

(defn compare-highs
  "Compares highs for tiebreaker"
  [hand1 hand2]
  (println "compare high")
  (println hand1)
  (println hand2)
  (loop [cards-a hand1
         cards-b hand2]

    (cond (not (= (first cards-a) (first cards-b))) (if (> (first cards-a) (first cards-b)) (result-description "A" " wins " 0 (first cards-a)) (result-description "B" " wins " 0 (first cards-b)))
          (empty? cards-a) (result-description "A and B" " tie and split the pot " 0 (first hand1))
          :else (recur (rest cards-a) (rest cards-b)))
    )
  )

(defn compare-pairs
  "Solves tie breakers for pairs."
  [hand1 hand2 num-cards score]
  (println "compare pair")
  (println hand1)
  (println hand2)

  (let [pairs-a (->> (get-pairs hand1) (take num-cards) (map first))
        pairs-b (->> (get-pairs hand2) (take num-cards) (map first))
        ]
    (println pairs-a)
    (println pairs-b)
    (loop [cards-a pairs-a
           cards-b pairs-b]

      (cond (not (= (first cards-a) (first cards-b))) (if (> (first cards-a) (first cards-b)) (result-description "A" " wins " score (first cards-a)) (result-description "B" " wins " score (first cards-b)))
            (empty? cards-a) (result-description "A and B" " tie and split the pot " score (first pairs-a))
            :else (recur (rest cards-a) (rest cards-b)))
      )

    )
  )

(defn tiebreaker
  "Solves tie breakers."
  [hand1 hand2 score]
  (let []
    (cond (= 3 score) (compare-pairs hand1 hand2 1 score)
      (= 2 score) (compare-pairs hand1 hand2 2 score)
      (= 1 score) (compare-pairs hand1 hand2 1 score)
      (= 0 score) (compare-highs (high-cards hand1) (high-cards hand2))
          :else "Tie Haven't dealt with this case yet")
    )
  )

(defn result-description
  "Returns a string announcing who won and how."
  [player result score-cat card]
  (let [ winner (str "Player " player)
        reason (get (clojure.set/map-invert score-rank) score-cat)
        ]
    (str winner result "with a " (cards/rank-name card) reason)
    )
  )

(defn who-wins
  "Checks which player wins the hand."
  [hand1 hand2]
  (let [score-a (calculate-score hand1)
        score-b (calculate-score hand2)
        result (compare score-a score-b)
        ]

    (println "Score a: " score-a)
    (println "Score b: " score-b)
    (cond (= 1 result) (result-description "A" " wins " score-a 0)
          (= -1 result) (result-description "B" " wins " score-b 0)
          :else (tiebreaker hand1 hand2 score-a))
    )
  )


