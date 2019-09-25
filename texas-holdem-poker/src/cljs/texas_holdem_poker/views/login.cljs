(ns texas-holdem-poker.views.login
  )

(defn input-element
  "An input element which updates its value on change"
  [id name type value]
  [:div {:class "input-box"}
   [:h2 (str name ": ")]
   [:input {:id id
            :name name
            :type type
            :class "input-box"
            :required ""
            :value @value
            ;;:on-change #(reset! value (-> % .-target .-value))
            }]
   ]
  )

(defn login-page []
  (let [email-address (atom nil)]
    [:div#login-form
     [:h2 "Please login or sign up"]
     [:form {:method :post :action "/login"}
      (input-element "username" "username" "text" email-address)
      (input-element "email" "email" "email" email-address)
      (input-element "password" "password" "password" email-address)
      [:button {:type :submit} "Log in"]]]


    )

  )