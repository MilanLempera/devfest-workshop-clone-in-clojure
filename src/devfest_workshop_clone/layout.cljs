(ns devfest-workshop-clone.layout
  (:require [rum.core :as rum]
            [cljsjs.react]
            [devfest-workshop-clone.router :as router]))

(rum/defc app [] < rum/static
  [:div.columns
   [:div.column.is-two-thirds

    (router/router-content)]

   [:div.column
    "app-session-notification-panel"]])
