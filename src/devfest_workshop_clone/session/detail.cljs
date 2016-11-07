(ns devfest-workshop-clone.session.detail
  (:require [rum.core :as rum]
            [devfest-workshop-clone.session.detail-card :as sdc]))

(rum/defc detail [session]
  (if session
    (letfn [(on-favorite-click [session]
              (println "session favorite: ", session))]

      [:div
       (sdc/detail-card session on-favorite-click)
       [:div.back-link.is-pulled-right
        [:a "Zpět"]]])))

