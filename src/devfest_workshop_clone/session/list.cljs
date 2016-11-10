(ns devfest-workshop-clone.session.list
  (:require [clojure.string :refer [join]]
            [rum.core :as rum]
            [bidi.bidi]
            [bidi.router]
            [devfest-workshop-clone.router :as router]
            [devfest-workshop-clone.store :as store])
  (:refer-clojure :exclude [list]))

(defn- speaker-names [session]
  (->> (:speakers session)
       (map :name)
       (join ", ")))

(rum/defc list < rum/reactive []
  (let [sessions (rum/react store/sessions)]

    [:table.table
     [:thead
      [:tr
       [:th "Title"]
       [:th "Speaker"]
       [:th]]]
     [:tbody
      (for
        [session sessions]
        [:tr {:key (:id session)}
         [:td (:title session)]
         [:td (speaker-names session)]
         [:td.is-icon
          [:a
           {:href  (router/path-for :session-detail :id (:id session))
            :title "go to detail"}
           [:i.fa.fa-info]]]])]]))

