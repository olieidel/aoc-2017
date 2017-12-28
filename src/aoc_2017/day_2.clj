(ns aoc-2017.day-2
  (:require [aoc-2017.core :refer [read-input]]
            [clojure.spec.alpha :as s]
            [clojure.string :as str]
            [orchestra.spec.test :as st]))

(def input
  "There's definitely a better way to do this with regular expressions."
  (-> (slurp "./resources/day-2.txt")
      (str/split #"\n")
      (->> (map #(str/split % #"\t"))
           (map (fn [row] (map (fn [col] (Integer/parseInt col)) row))))))

(defn min-max-diff
  "Subtract the minimum from the maximum value of `xs` and call
  `Math.abs()` on it to return a positive number."
  [xs]
  (-> (- (apply min xs) (apply max xs))
      Math/abs))

(defn part-1 [input]
  (->> (map min-max-diff input)
       (reduce +)))

(part-1 input)
;; => 39126

(defn divide-even
  "Brute-force divides `x` by all numbers in `xs`, where `xs` will be
  removed from xs as it doesn't make much sense dividing `x` by itself
  in this context. If the division yields an integer (= the numbers
  are evenly divisible), return that number. If the division yields
  something else, it will be filtered out. As there will only be one
  number due to the nature of the AoC problem, we can simply return
  the first."
  [x xs]
  (let [candidates (-> xs
                       set
                       (disj x))]
    (->> (map #(/ x %) candidates)
         (filter int?)
         first)))

(defn part-2-row
  "Apply `divide-even` to a whole row and filter out the `nil` values,
  yielding only the correct answer (there is only one)."
  [xs]
  (->> xs
       (map #(divide-even % xs))
       (filter some?)
       first))

(defn part-2
  "Map `part-2-row` to all rows and return the sum of the results."
  [input]
  (->> input
       (map part-2-row)
       (reduce +)))

(part-2 input)
;; => 258
