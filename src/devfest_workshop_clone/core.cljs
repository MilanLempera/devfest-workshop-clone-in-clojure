(ns ^:figwheel-always devfest-workshop-clone.core
  (:require [rum.core :as rum]
            [cljsjs.react]
            [bidi.router :as bidi]
            [devfest-workshop-clone.layout :as layout]
            [devfest-workshop-clone.router :as router]
            [devfest-workshop-clone.store :refer [state]]))

(enable-console-print!)

(let [root-element (.getElementById js/document "app-root")
      router (router/create-router state)]

  (rum/mount (layout/app state) root-element))


;;
;; optionally do something on reload
;;
(defn on-js-reload []
  (swap! state update-in [:__figwheel_counter] inc))