(ns hello-quil.app
  (:require [quil.core :as q]))

(def width 500)
(def height 500)

(defn setup []
  ; Set frame rate to 60 frames per second.
  (q/frame-rate 10)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state. It contains
  ; the player paddles and the ball
  {:t 0})


(defn update-t [state]
  (update state :t #(+ 1 %)))


(defn update-state [state]
  (-> state
      update-t))

(defn key-press [state {:keys [key]}]
  (if (some #{key} [:a :z :up :down])
    (update-in state [:keys] conj key)
    state))

(defn key-release [state]
  (assoc state :keys #{}))

(defn draw-state [{:keys [t]}]
  ; Clear the sketch by filling it with black color.
  (q/background 200)
  ; Set foreground color.
  (q/fill 255 255 255)
  (let [mid-x (/ width 2)
        mid-y (/ height 2)
        r t]
    (q/ellipse mid-x mid-y r (* 1.5 r))))
