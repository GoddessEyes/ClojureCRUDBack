(defproject health_samurai_demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
                 [org.clojure/clojure "1.9.0"]
                 [ikitommi/linked "1.3.1-alpha1"]
                 [clj-time "0.12.0"]
                 [ring-cors "0.1.13"]
                 [prismatic/schema "1.1.9"]
                 [metosin/compojure-api "2.0.0-alpha31"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [lynxeyes/dotenv "1.1.0"]
                 [toucan "1.1.9"]
                 [org.postgresql/postgresql "42.2.4"]]

  :plugins [
            [lein-ring "0.8.10"]
            [lein-cljfmt "0.6.8"]
            ]
  :main ^:skip-aot health-samurai-demo.core
  :profiles {:uberjar {:aot :all}}
  :repl-options {:init-ns health-samurai-demo.core})
