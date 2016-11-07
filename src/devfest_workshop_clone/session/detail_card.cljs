(ns devfest-workshop-clone.session.detail-card
  (:require [rum.core :as rum]))

(rum/defc detail-card [session on-favorite-click]

  [:div.container
   [:div.card.is-fullwidth
    [:header.card-header
     [:p.card-header-title (:title session)]
     [:a.card-header-icon
      {:on-click (partial on-favorite-click session)}
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