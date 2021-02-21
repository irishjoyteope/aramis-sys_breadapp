package com.oopclass.breadapp.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.oopclass.breadapp.models.Payment;
import com.oopclass.breadapp.config.StageManager;
import com.oopclass.breadapp.models.Payment;
import com.oopclass.breadapp.models.Payment;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import com.oopclass.breadapp.services.IPaymentService;
import com.oopclass.breadapp.views.FxmlView;

/**
 * OOP Class 20-21
 *
 * @author Gerald Villaran
 */
@Controller
public class PaymentController implements Initializable {

    @FXML 
    private Label paymentId;
    
    @FXML
    private TextField customerId;

    @FXML
    private DatePicker paymentDate;

    @FXML
    private TextField paymentAmount;
    
    @FXML
    private TextField paymentMethod;
    
    @FXML
    private TextField paymentStatus;
    
    @FXML
    private DatePicker created;

    @FXML
    private DatePicker updated;
    
    @FXML
    private TableView<Payment> paymentTable;

    @FXML
    private TableColumn<Payment, Long> colId;

    @FXML
    private TableColumn<Payment, Long> colCustomerId;

    @FXML
    private TableColumn<Payment, LocalDate> colPaymentDate;
    
     @FXML
    private TableColumn<Payment, Integer> colPaymentAmount;

    @FXML
    private TableColumn<Payment, String> colPaymentMethod;

    @FXML
    private TableColumn<Payment, String> colPaymentStatus;

    @FXML
    private TableColumn<Payment, LocalDate> colCreated;

    @FXML
    private TableColumn<Payment, LocalDate> colUpdated;

    @FXML
    private TableColumn<Payment, Boolean> colEdit;

    @FXML
    private Button customerBtn;

    @FXML
    private Button serviceBtn;
    @FXML
    private Button reset;

    @FXML
    private Button save;

    @FXML
    private Button delete;     
       
    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private IPaymentService paymentService;

    private ObservableList<Payment> paymentList = FXCollections.observableArrayList();

    

    @FXML
    void reset(ActionEvent event) {
        clearFields();
       
    }

    @FXML
    private void customerBtn(ActionEvent event) {
        stageManager.switchScene(FxmlView.CUSTOMER);

    }

    @FXML
    private void serviceBtn(ActionEvent event) {
        stageManager.switchScene(FxmlView.SERVICE);

    }

    @FXML
    private void saves(ActionEvent event) {

      
            if (paymentId.getText() == null || "".equals(paymentId.getText())) {
                if (true) {

                    Payment payment = new Payment();
                    payment.setCustomerId(getCustomerId());
                    payment.setPaymentDate(getPaymentDate());
                    payment.setPaymentAmount(getPaymentAmount());
                    payment.setPaymentMethod(getPaymentMethod());
                    payment.setPaymentStatus(getPaymentStatus());
                    payment.setCreated(getCreated());
                    payment.setUpdated(getUpdated());
                 
                   
                    Payment newPayment = paymentService.save(payment);

                    saveAlert(newPayment);
                }

            } else {
                Payment payment = paymentService.find(Long.parseLong(paymentId.getText()));
                payment.setCustomerId(getCustomerId());
                    payment.setPaymentDate(getPaymentDate());
                    payment.setPaymentAmount(getPaymentAmount());
                    payment.setPaymentMethod(getPaymentMethod());
                    payment.setPaymentStatus(getPaymentStatus());
                    payment.setCreated(getCreated());
                    payment.setUpdated(getUpdated());
                Payment updatedPayment = paymentService.update(payment);
                updateAlert(updatedPayment);
            }

            clearFields();
            loadPaymentDetails();
        

    }

    @FXML
    private void delete(ActionEvent event) {
        List<Payment> payments = paymentTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            paymentService.deleteInBatch(payments);
        }

        loadPaymentDetails();
    }

    private void clearFields() {
        paymentId.setText(null);
        customerId.clear();
        paymentDate.getEditor().clear();
        paymentAmount.clear();
        paymentMethod.clear();
        paymentStatus.clear();
        created.getEditor().clear();
        updated.getEditor().clear();
        
    }

    private void saveAlert(Payment payment) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Payment saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The payment " + payment.getCustomerId() + " " + payment.getPaymentDate() + " has been created and \n" + " id is " + payment.getId() + ".");
        alert.showAndWait();
    }

    private void updateAlert(Payment payment) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Payment updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The payment " + payment.getPaymentAmount() + " " + payment.getPaymentStatus() + " has been updated.");
        alert.showAndWait();
    }

    
    public Long getCustomerId() {
        return Long.parseLong(customerId.getText());
    }
    
    public LocalDate getPaymentDate() {
        return paymentDate.getValue();
    }
        
    
    public Integer getPaymentAmount() {
        return Integer.parseInt(paymentAmount.getText());
        
    }
    
    public String getPaymentMethod() {
        return paymentMethod.getText();
    }
    
    public String getPaymentStatus() {
        return paymentStatus.getText();
    }
            
    public LocalDate getCreated() {
        return created.getValue();
    }
    
    public LocalDate getUpdated() {
        return updated.getValue();
    }
        

    /*
	 *  Set All paymentTable column properties
     */
    private void setColumnProperties() {
        /* Override date format in table
		 * colDOB.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
			 String pattern = "dd/MM/yyyy";
			 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 }));*/

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        colPaymentAmount.setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("created"));
        colUpdated.setCellValueFactory(new PropertyValueFactory<>("updated"));
        colEdit.setCellFactory(cellFactory);
    }

    Callback<TableColumn<Payment, Boolean>, TableCell<Payment, Boolean>> cellFactory
            = new Callback<TableColumn<Payment, Boolean>, TableCell<Payment, Boolean>>() {
        @Override
        public TableCell<Payment, Boolean> call(final TableColumn<Payment, Boolean> param) {
            final TableCell<Payment, Boolean> cell = new TableCell<Payment, Boolean>() {
                Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                final Button btnEdit = new Button();

                @Override
                public void updateItem(Boolean check, boolean empty) {
                    super.updateItem(check, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btnEdit.setOnAction(e -> {
                            Payment payment = getTableView().getItems().get(getIndex());
                            updatePayment(payment);
                        });

                        btnEdit.setStyle("-fx-background-color: transparent;");
                        ImageView iv = new ImageView();
                        iv.setImage(imgEdit);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnEdit.setGraphic(iv);

                        setGraphic(btnEdit);
                        setAlignment(Pos.CENTER);
                        setText(null);
                    }
                }

                private void updatePayment(Payment payment) {
                    paymentId.setText(Long.toString(payment.getId()));
                    customerId.setText(Long.toString(payment.getCustomerId()));
                    paymentDate.setValue(payment.getPaymentDate());
                    paymentAmount.setText(Integer.toString(payment.getPaymentAmount()));
                    paymentMethod.setText(payment.getPaymentMethod());
                    paymentStatus.setText(payment.getPaymentStatus());
                    created.setValue(payment.getCreated());
                    updated.setValue(payment.getUpdated());
                    
                    
                }
            };
            return cell;
        }
    };

    /*
	 *  Add All payments to observable list and update table
     */
    private void loadPaymentDetails() {
        paymentList.clear();
        paymentList.addAll(paymentService.findAll());

        paymentTable.setItems(paymentList);
    }

    /*
	 * Validations
     */
    private boolean validate(String field, String value, String pattern) {
        if (!value.isEmpty()) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            if (m.find() && m.group().equals(value)) {
                return true;
            } else {
                validationAlert(field, false);
                return false;
            }
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    private boolean emptyValidation(String field, boolean empty) {
        if (!empty) {
            return true;
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    private void validationAlert(String field, boolean empty) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if (field.equals("Role")) {
            alert.setContentText("Please Select " + field);
        } else {
            if (empty) {
                alert.setContentText("Please Enter " + field);
            } else {
                alert.setContentText("Please Enter Valid " + field);
            }
        }
        alert.showAndWait();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        paymentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();

        // Add all payments into table
        loadPaymentDetails();
    }
}
