(ns devfest-workshop-clone.session.detail-card
  (:require [rum.core :as rum]))

(rum/defc detail-card [session]

  [:div.container
   [:div.card.is-fullwidth
    [:header.card-header
     [:p.card-header-title (:title session)]
     [:a.card-header-icon
      [:i.fa.fa-heart]]]
    [:div.card-content
     [:div.content
      [:div.tags
       (for
         [tag (:tags session)]
         [:span.tag.is-primary tag]
         )
       ]
      (:description session)
      [:br]
      [:small (str (:language session) " / " (:complexity session))]
      [:div.speakers
       [:b "Speakers:"]
       (for
         [speaker (:speakers session)]
         (:name speaker))]]]]])