(ns santa.core)
(use 'clojure.java.io)
(require '[clojure.string :as str])

(defn handle-function [cmd]
  (cond 
    (not (nil? (re-find #"on" cmd)))
    (fn [light]
      (assoc light :is-lit? true)
      )
    (not (nil? (re-find #"off" cmd)))
    (fn [light]
      (assoc light :is-lit? false))
    (not (nil? (re-find #"toggle" cmd)))
    (fn [light]
      (assoc light :is-lit? (not (:is-lit? light))))))

(defn get-from-coords [line]
  )
(with-open [rdr (reader "data/input6.txt")]
  (doall (map #(fn [line]
                 (let [fun (handle-function line)
                       [from-x from-y to-x to-y] (parse-coords line)])
                 (+ 3 3)
                 ) (take 2 (line-seq rdr)))))

(defn parse-coords [line]
  (map #(Integer. %) (re-seq #"\d+" (apply str line))
  ))

(let [tst (with-open [rdr (reader "data/input6.txt")]
  (doall (take 1  (line-seq rdr))))]
  (let [[from-x from-y to-x to-y] (parse-coords tst)]
    (assert (= from-x 489))
    (assert (= from-y 959))
    (assert (= to-x 759))
    (assert (= to-y 964))
  ))

(defn get-el [grid x y]
  ((grid x) y)
  )
(range 1 3)
(defn test-lights [lights]
  (let [tst (aget lights 0 0)]
    (assert (not (nil? (:x @tst)))
    (assert (not (nil? (:y @tst)))
  ))))

(defn test-instructions [line]
  (assert (not (nil? (first line)))))

(defn build-instruction [line]
  (let [[from-x from-y to-x to-y] (parse-coords line)]
  {
   :func (handle-function line)
   :from-x from-x
   :to-x (inc to-x)
   :from-y from-y
   :to-y (inc to-y)
   }))

(defn build-range [instruction]
  (for [xs (range (:from-x instruction) (inc (:to-x instruction)))
        ys (range (:from-y instruction) (inc (:to-y instruction)))] [xs ys]))

(defrecord Light [x y is-lit?])

(defn build-light-grid [dim]
  (to-array-2d (partition dim (for [x (range dim) y (range dim)] (atom (Light. x y false))))))

(count (flatten (map build-vol-lights input)))

(defn build-vol-lights [line]
  (for [xs (range (:from-x line) (inc (:to-x line)))
        ys (range (:from-y line) (inc (:to-y line)))]
    [xs ys]))
(let [lights [build-light-grid 1000]]
  (doseq [line input]
    (test-line line)
    (let [vol-lights (build-vol-lights line)]
      )))



(let [lights (build-light-grid 1000)]

  (doseq [line (remove nil? input)]
    (let [vol-lights (build-range line)]
      (print (count vol-lights))
        ;;(doseq [light vol-lights]
        ;;(swap! (aget lights (light 0) (light 1)) (:func line))
                 )))
        ;;(aget lights (light 0) (light 1)))))

     

  ;;testing
  (test-lights lights)
  
(first (with-open [rdr (reader "data/input6.txt")]
 (seq [instruction (map build-instruction (line-seq rdr))])))

(def input (with-open [rdr (reader "data/input6.txt")]
  (doall (remove nil? (map build-instruction (line-seq rdr))))))

(first (map build-instruction input))
(first input)

(defn pos-num
  ([] (pos-num 1))
  ([n] (lazy-seq (cons n (pos-num (inc n))))))

(defn positive-numbers
  ([] (positive-numbers 1))
  ([n] (lazy-seq (cons n (positive-numbers (inc n))))))
(take 10000 (positive-numbers))
