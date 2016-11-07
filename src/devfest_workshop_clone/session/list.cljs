(ns devfest-workshop-clone.session.list
  (:require [rum.core :as rum]
            [clojure.string :refer [join]]))

(defn- speaker-names [session]
  (->> (:speakers session)
       (map :name)
       (join ", ")))

(rum/defc list [sessions]
  [:table.table
   [:thead
    [:tr
     [:th "Title"]
     [:th "Speaker"]
     [:th]]]
   [:tbody
    (for
      [session sessions]
      [:tr
       [:td (:title session)]
       [:td (speaker-names session)]
       [:td.is-icon
        [:a
         [:i.fa.fa-info]]]])]])

