(ns ^:figwheel-always devfest-workshop-clone.core
  (:require [rum.core :as rum]
            [cljsjs.react]
            [devfest-workshop-clone.session.detail-card :as sdc]))

(enable-console-print!)

;;;
;; define game state once so it doesn't re-initialise on reload.
;; figwheel counter is a placeholder for any state affected by figwheel live reload events
;;;
(defonce data (atom {:title    "Hello devfest-workshop-clone"
                     :sessions [{:id          400,
                                 :title       "REST API je mrtvé, ať žije GraphQL",
                                 :description "REST se stal standardem pro API. Poslední dobou se ale ukazuje, že není vhodné pro uživatelské rozhraní. Facebook nám nabídl GraphQL a skvělou knihovnu Relay, která usnadňuje a zrychluje práci s daty v aplikacích. ",
                                 :speakers    [{:tags     ["Backend" "REST API" "GraphQL"],
                                                :socials  [{:icon "twitter", :name "Twitter", :link "https://twitter.com/fersman"}
                                                           {:icon "website", :name "Website", :link "http://www.ferschmann.cz"}
                                                           {:icon "linkedin", :name "LinkedIn", :link "https://linkedin.com/in/fersman"}],
                                                :name     "Petr Ferschmann",
                                                :featured false,
                                                :shortBio "Co-founded and operated cloud based accounting system ABRA @FlexiBee. Loves backend and so he is involved in @fnxio. Now he's starting a new project.",
                                                :bio      "Co-founded and operated cloud based accounting system ABRA @FlexiBee. Loves backend and so he is involved in @fnxio. Now he's starting a new project.",
                                                :id       6,
                                                :badges   [],
                                                :photoUrl "/images/people/petr_fershmann.jpg",
                                                :country  "Pilsen, Czech Republic",
                                                :company  "puzzlette"}],
                                 :language    "Czech",
                                 :complexity  "Advanced",
                                 :tags        ["Backend" "Web" "GraphQL" "REST API"]}
                                {:id          700,
                                 :title       "Angular2.Dart",
                                 :description "How to start developing in Dart and how to build applications with Angular2.",
                                 :speakers    [{:tags        ["Dart" "Angular" "Backend" "App Engine"],
                                                :socials     [{:icon "twitter", :name "Twitter", :link "https://twitter.com/tomucha"}
                                                              {:icon "linkedin", :name "LinkedIn", :link "https://cz.linkedin.com/in/tomucha"}],
                                                :name        "Tomáš Zvěřina",
                                                :featured    true,
                                                :shortBio    "Full stack developer, Google Developer Expert for Cloud Platform, Dart enthusiast, father of kids and OSS libraries.",
                                                :bio         "Full stack developer, Google Developer Expert for Cloud Platform, Dart enthusiast, father of kids and OSS libraries.",
                                                :id          11,
                                                :badges      [{:name        "GDE",
                                                               :description "Cloud Google Developer Expert",
                                                               :link        "https://developers.google.com/experts/people/tom-zv-ina"}],
                                                :photoUrl    "/images/people/tomas_zverina.jpg",
                                                :country     "Prague, Czech Republic",
                                                :company     "FNX.io",
                                                :companyLogo "/images/logos/fnxio.svg"}],
                                 :language    "Czech",
                                 :complexity  "Beginner",
                                 :tags        ["Frontend" "Dart" "Angular2"]}
                                {:id          800,
                                 :title       "Techniky duševní práce",
                                 :description "Pojďme se společně zamyslet co to znamená pracovat jako znalostní pracovník, jako programátor. Jak z této činnosti vydat maximum. Jak efektivně využít náš hlavní nástroj - mozek. Přiblížíme si pojmy jako je hluboká práce, inovace, zen programmer a nastavení mysli.",
                                 :speakers    [{:tags        ["Innovation" "Management"],
                                                :socials     [{:icon "twitter", :name "Twitter", :link "https://twitter.com/JaroslavHolan"}
                                                              {:icon "linkedin", :name "LinkedIn", :link "https://www.linkedin.com/in/jaroslavholan"}
                                                              {:icon "website", :name "website", :link "https://www.jaroslavholan.com"}],
                                                :name        "Jaroslav Holaň",
                                                :featured    true,
                                                :shortBio    "Aktuálně je součástí týmu TopMonks v Praze, kde se podílí na vývoji pro velkou českou banku.",
                                                :bio         "Aktuálně je součástí týmu TopMonks v Praze, kde se podílí na vývoji pro velkou českou banku. Pracovní zkušenosti získal ve firmách Česká Spořitelna, Hewlett Packard, Vendavo, Nokia, Tieto. Jeden z organizátorů konference o budoucnosti práce Wisephora. Primárně programuje v Javě, ale rád zkouší i jiné jazyky. Pravidelný účastník Coderetreatů, celodenních cvičení pro zdokonalování programátorských technik. Zastánce čistého kódu, TDD, BDD. Spoluautor knihy Vaadin 7 Cookbook. IT Geek a Kouzelník.",
                                                :id          8,
                                                :badges      [],
                                                :photoUrl    "/images/people/jaroslav_holan.jpg",
                                                :country     "Prague, Czech Republic",
                                                :company     "TopMonks",
                                                :companyLogo "/images/logos/topmonks.svg"}],
                                 :language    "Czech",
                                 :complexity  "Beginner",
                                 :tags        ["Management" "Innovation"]}]}))



(rum/defc app [data]
  [:div {:class "columns"}
   [:div {:class "column is-two-thirds"}
    (sdc/detail-card (last (:sessions @data)))]
   [:div {:class "column"}
    "app-session-notification-panel"]])


(defn el [id] (.getElementById js/document id))

;;
;; mount main component on html game element
;;
(rum/mount (app data) (el "app-root"))

;;
;; optionally do something on game reload
;;
(defn on-js-reload []
  (swap! data update-in [:__figwheel_counter] inc))
