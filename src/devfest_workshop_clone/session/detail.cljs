(ns devfest-workshop-clone.session.detail
  (:require [rum.core :as rum]
            [devfest-workshop-clone.router :as router]
            [devfest-workshop-clone.store :as store]
            [devfest-workshop-clone.session.detail-card :as sdc]
            [matchbox.core :as m]))

(rum/defc detail < rum/reactive [id]
  (let [int-id (int id)
        session (->> (rum/react store/sessions)
                     (filter #(= (:id %) int-id))
                     first)

        fire-likes (m/get-in (:firebase-root @store/state) [:likes])]

    (if session
      (letfn [(on-favorite-click [session]
                (println "session like: ", (:title session))
                (m/conj! fire-likes {:sessionId    (:id session)
                                     :sessionTitle (:title session)
                                     :user         "Pavel"}))]

        [:div
         (sdc/detail-card session on-favorite-click)
         [:div.back-link.is-pulled-right
          [:a {:href (router/path-for :session-list)} "Zpět"]]]))))

