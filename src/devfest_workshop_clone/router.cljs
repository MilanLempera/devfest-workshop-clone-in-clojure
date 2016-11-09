(ns devfest-workshop-clone.router
  (:require [rum.core :as rum]
            [devfest-workshop-clone.store :as store]
            [devfest-workshop-clone.session.list :as sl]
            [devfest-workshop-clone.session.detail :as sd]))

(def my-routes ["/" {""         :session-list
                     "session/" {[:id] :session-detail}}])

(rum/defc router-content < rum/reactive []
  (let [{handler :handler
         params  :route-params} (::router (rum/react store/state))]

    (case handler
      :session-list (sl/list)

      :session-detail (sd/detail (:id params))

      [:.error "404 Not Found"])))

(defn create-router [state]
  (bidi.router/start-router! my-routes
                             {:on-navigate      (fn [route]
                                                  (println "on-navigate" route)
                                                  (swap! state assoc ::router route))
                              :default-location {:handler :session-list}}))

(defn path-for [& args] ;TODO zbavit se #?
  (str "#" (apply bidi.bidi/path-for my-routes args)))



