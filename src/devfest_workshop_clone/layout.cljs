(ns devfest-workshop-clone.layout
  (:require [rum.core :as rum]
            [cljsjs.react]
            [devfest-workshop-clone.router-content :as rc]
            [devfest-workshop-clone.session.notification-panel :as np]))

(rum/defc app < rum/static []
  [:div.columns
   [:div.column.is-two-thirds

    (rc/router-content)]

   [:div.column
    (np/notification-panel)]])
