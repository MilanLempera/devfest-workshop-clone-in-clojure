(ns devfest-workshop-clone.router
  (:require [bidi.router]
            [bidi.bidi]))

(def my-routes ["/" {""         :session-list
                     "session/" {[:id] :session-detail}}])


(defn path-for [& args]                                     ;TODO zbavit se #?
  (str "#" (apply bidi.bidi/path-for my-routes args)))

(defn create-router [state]
  (bidi.router/start-router! my-routes
                             {:on-navigate (fn [route]
                                             (println "on-navigate" route)
                                             (swap! state assoc :router route))}))

