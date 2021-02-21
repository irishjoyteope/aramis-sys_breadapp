/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopclass.breadapp.controllers;


import com.oopclass.breadapp.config.StageManager;
import com.oopclass.breadapp.views.FxmlView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Admin
 */
@Controller
public class HomeController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;
    
   @FXML
   private void Customerbtn(ActionEvent event)throws IOException {
       stageManager.switchScene(FxmlView.CUSTOMER);
   }
           
   @FXML        
  private void Paymentsbtn(ActionEvent event)throws IOException {
       stageManager.switchScene(FxmlView.PAYMENT);
       
   }
           
   @FXML        
   private void Servicesbtn(ActionEvent event)throws IOException {
       stageManager.switchScene(FxmlView.SERVICE);
  }
       
       
     @Override
     public void initialize (URL url, ResourceBundle rb){
           
       }
    
   }
    
     

