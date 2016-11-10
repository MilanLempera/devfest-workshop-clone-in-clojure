(ns ^:figwheel-always devfest-workshop-clone.core
  (:require [rum.core :as rum]
            [cljsjs.react]
            [matchbox.core :as m]
            [devfest-workshop-clone.layout :as layout]
            [devfest-workshop-clone.router :as router]
            [devfest-workshop-clone.store :refer [state]]))

(enable-console-print!)

(defn- init-firebase [state]
  (let [root (m/connect "https://devfest-angular2-workshop.firebaseio.com")]
    (m/auth-anon root)
    (swap! state assoc :firebase-root root)))

(let [root-element (.getElementById js/document "app-root")
      router (router/create-router state)]

  (init-firebase state)
  (rum/mount (layout/app) root-element))


;;
;; optionally do something on reload
;;
(defn on-js-reload []
  (swap! state update-in [:__figwheel_counter] inc))