(ns devfest-workshop-clone.store
  (:require [rum.core :as rum]
            [clojure.string :as string]))

(def initial-state {:title          "Hello devfest-workshop-clone"
                    :sessions       []
                    :session-search ""})

(defonce state
         (atom initial-state))

(def sessions (rum/cursor state [:sessions]))

(defn search-sessions [query]
  (let [query-lower (clojure.string/lower-case query)]
    (->> @sessions
         (filter #(clojure.string/includes?
                    (clojure.string/lower-case (:title %))
                    query-lower)))))
