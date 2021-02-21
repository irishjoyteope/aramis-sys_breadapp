package com.oopclass.breadapp.views;

import java.util.ResourceBundle;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

public enum FxmlView {

    USER {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("user.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/User.fxml";
        }
    }
    ,
     HOME {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("home.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Home.fxml";
        } 
     }           
        ,
             PAYMENT {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("payment.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Payment.fxml";
        }
      
             }
             ,
             
            SERVICE {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("service.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Services.fxml";
        }
                
            }
            ,
            CUSTOMER {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("customer.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Customer.fxml";
        }
};    

    
    public abstract String getTitle();
    public abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}