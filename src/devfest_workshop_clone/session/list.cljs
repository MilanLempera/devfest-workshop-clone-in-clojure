(ns devfest-workshop-clone.session.list
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [clojure.string :refer [join]]
            [rum.core :as rum]
            [bidi.bidi]
            [bidi.router]
            [devfest-workshop-clone.router :as router]
            [devfest-workshop-clone.store :as store]
            [cljs.core.async :as a])
  (:refer-clojure :exclude [list]))

(defn- speaker-names [session]
  (->> (:speakers session)
       (map :name)
       (join ", ")))

(def search
  (rum/cursor store/state [:session-search]))

(rum/defc list < rum/reactive []
  (let [search-channel (a/chan nil)
        search-query (rum/react search)
        sessions (store/search-sessions search-query)
        on-search (fn [event]
                    (let [query (.-value (.-target event))]
                      (go
                        (a/>! search-channel query))))]

    (go-loop []
             (let [query (a/<! search-channel)]
               (reset! search query)
               (recur)))

    [:table.table
     [:thead
      [:tr
       [:th
        [:p.control.has-icon
         [:input.input
          {:type        "text"
           :placeholder "Title"
           :on-change   on-search
           :value       search-query}]
         [:i.fa.fa-search]]]
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

