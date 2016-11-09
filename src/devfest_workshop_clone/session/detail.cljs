(ns devfest-workshop-clone.session.detail
  (:require [rum.core :as rum]
            [devfest-workshop-clone.router :as router]
            [devfest-workshop-clone.store :as store]
            [devfest-workshop-clone.session.detail-card :as sdc]))

(rum/defc detail < rum/reactive [id]
  (let [sessions (rum/react store/sessions)
        session (get sessions (int id))]

    (if session
      (letfn [(on-favorite-click [session]
                (println "session favorite: ", session))]

        [:div
         (sdc/detail-card session on-favorite-click)
         [:div.back-link.is-pulled-right
          [:a {:href (router/path-for :session-list)} "Zpět"]]]))))

