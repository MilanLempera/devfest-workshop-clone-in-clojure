(ns devfest-workshop-clone.session.notification-panel
  (:require [rum.core :as rum]
            [matchbox.core :as m]
            [devfest-workshop-clone.store :as store]))

(rum/defc notification < rum/static [like]
  [:div.panel-block
   [:span.panel-icon
    [:i.fa.fa-heart]]
   (:user like)
   " likes "
   (:sessionTitle like)]
  )

(def firebase-likes-mixin
  {:will-mount (fn [state]
                 (let [fire-likes (-> (m/get-in (:firebase-root @store/state) [:likes])
                                      (m/take-last 5))
                       likes-atom (::likes state)]

                   (m/listen-children
                     fire-likes
                     (fn [[operation item]]
                       (case operation
                         :child-added (swap! likes-atom conj item)

                         :child-removed (swap! likes-atom drop-last)))))
                 state)})


(rum/defcs notification-panel < (rum/local [] ::likes)
                                firebase-likes-mixin

  [state]
  (let [likes-atom (::likes state)]

    [:div.panel
     [:p.panel-heading "Likes:"]
     (map (fn [[id like]]
            (rum/with-key (notification like) id))
          @likes-atom)]))



