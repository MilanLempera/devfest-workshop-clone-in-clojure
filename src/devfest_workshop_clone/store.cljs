(ns devfest-workshop-clone.store
  (:require [rum.core :as rum]
            [clojure.string :as string]))

(def initial-state {:title          "Hello devfest-workshop-clone"
                    :sessions       []
                    :session-search ""})

(defonce state
         (atom initial-state))

(def sessions (rum/cursor state :sessions))

(defn search-sessions [query]
  (let [search-query (or query "")
        query-lower (string/lower-case search-query)]
    (->> @sessions
         (filter #(string/includes?
                    (string/lower-case (:title %))
                    query-lower)))))
