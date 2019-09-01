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
  (let [rank-order (-> (map :rank hand) sort distinct)]

    (println rank-order)
    ;;provides list of all results that have a straight, could be multiple results
    (not (empty?
           (for [x rank-order
                       :let [y (take 5 (range x (+ 5 x)))]
                       :when (= y (take 5 (filter #(> % (dec x)) rank-order)))]
                   y)))
    )
  )