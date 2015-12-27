(ns santa.core)
(use 'clojure.java.io)
(require '[clojure.string :as str])

(count (with-open [rdr (reader "data/input4.txt")]
  (doall
    (filter bad-strings? (filter three-vowels? (filter double-chars? (line-seq rdr)))))))

(defn double-chars? [chars] (not (nil? (re-seq #"(.)\1" chars))))

(double-chars? "stirss")
(defn three-vowels? [chars] (< 2 (count (re-seq #"[aeiou]" chars))))
(three-vowels? "satraneg")

(defn bad-strings? [chars] (nil? (re-seq #"(ab)?(cd)?" chars)))

(bad-strings? "ascedpqx")
(defn bad-strings? [chars] (nil? (re-seq #"(?:ab|cd|pq|xy)" chars)))


