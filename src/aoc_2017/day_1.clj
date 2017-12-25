(ns aoc-2017.day-1
  (:require [aoc-2017.core :refer [read-input]]
            [clojure.string :as str]))

(def input
  (->> (read-input 1)
       ;; remove newline at end of file, ugh!
       str/trim
       seq
       (map #(Character/digit % 10))))

(defn part-1 [input]
  (loop [xs input
         prior (last input)
         total 0]
    (if (empty? xs)
      total
      (if (= prior (first xs))
        (recur (rest xs) (first xs) (+ total (first xs)))
        (recur (rest xs) (first xs) total)))))

(part-1 input)
;; => 1102

(defn part-2 [input]
  (let [half-length (/ (count input) 2)]
    (loop [xs-left (take half-length input)
           xs-right (drop half-length input)
           total 0]
      (if (empty? xs-left)
        total
        (if (= (first xs-left) (first xs-right))
          ;; note the * 2 in the total as we add each number which has
          ;; the same number half way around the list twice.
          (recur (rest xs-left) (rest xs-right) (+ total (* 2 (first xs-left))))
          (recur (rest xs-left) (rest xs-right) total))))))

(part-2 input)
;; => 1076
