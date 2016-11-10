(ns ^:figwheel-always devfest-workshop-clone.core
  (:require [rum.core :as rum]
            [cljsjs.react]
            [matchbox.core :as m]
            [devfest-workshop-clone.layout :as layout]
            [devfest-workshop-clone.router :as router]
            [devfest-workshop-clone.store :refer [state]]
            [ajax.core :refer [GET]]))

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

(defn handler [response]
  (swap! state assoc :sessions response))

(defn error-handler [{:keys [status status-text]}]
  (.error js/console (str "something bad happened: " status " " status-text)))

(GET "/data/session.json" {:response-format (ajax.core/json-response-format {:keywords? true})
                                    :handler         handler
                                    :error-handler   error-handler})