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

(rum/defcs notification-panel < (rum/local [] ::likes)
  [state]
  (let [fire-likes (-> (m/get-in (:firebase-root @store/state) [:likes])
                       (m/take-last 5))
        likes (::likes state)]

    (m/deref
      fire-likes
      (fn [values]
        (reset! likes values)))

    [:div.panel
     [:p.panel-heading "Likes:"]
     (reverse
       (map #(rum/with-key (notification %1) %2)
            (vals @likes) (keys @likes)))
     ]))



