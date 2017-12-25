(ns aoc-2017.day-1
  (:require [aoc-2017.core :refer [read-input]]
            [clojure.string :as str]))

(def input
  (->> (read-input 1)
       ;; remove newline at end of file, ugh!
       str/trim
       seq
       (map #(Character/digit % 10))))

(defn compute [input]
  (loop [xs input
         prior (last input)
         total 0]
    (if (empty? xs)
      total
      (if (= prior (first xs))
        (recur (rest xs) (first xs) (+ total (first xs)))
        (recur (rest xs) (first xs) total)))))

(compute input)
;; => 1102
