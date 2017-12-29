(ns aoc-2017.day-3
  "The difficulty of these Advent of Code problems seems to increase
  exponentially.")

;; 1 --> 1
;; 2 --> 8
;; 3 --> 16
;; 4 --> 24

(defn ring [n]
  (when (< n 1) (ex-info "ring must be called with n >= 1"))
  (if (= n 1)
    1
    (* 8 (- n 1))))

;; 1 --> 1
;; 2 --> 2
;; 9 --> 2
;; 10 --> 3

(defn side-length [ring-size]
  (-> ring-size
      (- 4)
      (/ 4)
      (+ 2)))

(defn from-mid [x ring-start side-length]
  (let [start (+ 1 (- x ring-start))
        mid (/ (- side-length 1) 2)]
    (Math/abs
     (- (mod start (dec side-length))
        mid))))

(defn inverse-ring [x]
  (loop [n 1
         ring-end 1
         ring-size 1]
    (if (>= ring-end x)
      (let [ring-start (inc (- ring-end ring-size))
            side-length (side-length ring-size)]
        {:ring-n n
         :ring-size ring-size
         :ring-start ring-start
         :side-length side-length
         :from-mid (from-mid x ring-start side-length)})
      (recur (inc n) (+ ring-end (ring (inc n))) (ring (inc n))))))

(defn solution [x]
  (let [{:keys [ring-n from-mid]} (inverse-ring x)]
    (+ (dec ring-n) from-mid)))

(solution 347991)
;; => 480
