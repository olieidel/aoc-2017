(ns aoc-2017.day-1
  (:require [aoc-2017.core :refer [read-input]]
            [clojure.spec.alpha :as s]
            [clojure.string :as str]
            [orchestra.spec.test :as st]))

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

(s/fdef part-1
        ;; one arg (input) which is a seq of zero or more integers
        :args (s/cat :input (s/spec (s/* int?)))
        ;; returns an integer
        :ret int?
        ;; the returned integer should be less than or equal to the
        ;; sum of all integers in the input. this is a very simple
        ;; test.
        :fn #(<= (:ret %)
                 (reduce +
                         (get-in % [:args :input]))))

(st/instrument)

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

(s/fdef part-2
        ;; same as `part-1`
        :args (s/cat :input (s/spec (s/* int?)))
        :ret int?
        :fn #(<= (:ret %)
                 (reduce +
                         (get-in % [:args :input]))))

(st/instrument)

(part-2 input)
;; => 1076
