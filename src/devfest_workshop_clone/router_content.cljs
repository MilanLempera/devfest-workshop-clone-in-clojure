(ns devfest-workshop-clone.router-content
  (:require [rum.core :as rum]
            [devfest-workshop-clone.router :as router]
            [devfest-workshop-clone.store :as store]
            [devfest-workshop-clone.session.list :as sl]
            [devfest-workshop-clone.session.detail :as sd]))

(rum/defc router-content < rum/reactive []
  (let [{handler :handler params :route-params} (:router (rum/react store/state))]

    (case handler
      :session-list (sl/list)

      :session-detail (sd/detail (:id params))

      [:div
       [:h2 "404 Not Found"]
       [:p
        "Go to "
        [:a {:href (router/path-for :session-list)} "Homepage"]]])))

