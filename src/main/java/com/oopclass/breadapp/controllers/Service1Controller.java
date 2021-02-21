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

import com.oopclass.breadapp.models.Service1;
import com.oopclass.breadapp.config.StageManager;
import com.oopclass.breadapp.models.Service1;
import com.oopclass.breadapp.models.Service1;

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

import com.oopclass.breadapp.services.IService1Service;
import com.oopclass.breadapp.views.FxmlView;

/**
 * OOP Class 20-21
 *
 * @author Gerald Villaran
 */
@Controller
public class Service1Controller implements Initializable {

@FXML
    private Button paymentBtn;

    @FXML
    private Button customerBtn;

    @FXML
    private TextField customerId;

    @FXML
    private DatePicker created;

    @FXML
    private DatePicker updated;

    @FXML
    private Label serviceId;

    @FXML
    private DatePicker nextServiceDate;

    @FXML
    private Button reset;

    @FXML
    private Button save;

    @FXML
    private Button delete;

    @FXML
    private TextField category;

    @FXML
    private TextField status;

    @FXML
    private TextField estimatedHours;

    @FXML
    private TextField spentHours;

    @FXML
    private TableView<Service1> serviceTable;
    
    @FXML
    private TableColumn<Service1, Long> colServiceId;

    @FXML
    private TableColumn<Service1, Long> colCustomerId;

    @FXML
    private TableColumn<Service1, String> colCategory;

    @FXML
    private TableColumn<Service1, String> colServiceStatus;

    @FXML
    private TableColumn<Service1, Integer> colEstimatedHours;

    @FXML
    private TableColumn<Service1,Integer> colSpentHours;

    @FXML
    private TableColumn<Service1, LocalDate> colNextServiceDate;

    @FXML
    private TableColumn<Service1, LocalDate > colCreated;

    @FXML
    private TableColumn<Service1, LocalDate> colUpdated;

    @FXML
    private TableColumn<Service1, Boolean> colEdit;
    
       
    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private IService1Service servicesService1;

    private ObservableList<Service1> service1sList = FXCollections.observableArrayList();

    

    @FXML
    void reset(ActionEvent event) {
        clearFields();
       
    }

    @FXML
    private void customerBtn(ActionEvent event) {
        stageManager.switchScene(FxmlView.CUSTOMER);

    }

    @FXML
    private void paymentBtn(ActionEvent event) {
        stageManager.switchScene(FxmlView.PAYMENT);

    }

    @FXML
    private void save1(ActionEvent event) {

      
            if (serviceId.getText() == null || "".equals(serviceId.getText())) {
                if (true) {

                    Service1 service1 = new Service1();
                    service1.setCustomerId(getCustomerId());
                    service1.setCategory(getCategory());
                    service1.setStatus(getStatus());
                    service1.setEstimatedHours(getEstimatedHours());
                    service1.setSpentHours(getSpentHours());
                    service1.setNextServiceDate(getNextService1Date());
                    service1.setCreated(getCreated());
                    service1.setUpdated(getUpdated());
                    

                    Service1 newService1 = servicesService1.save(service1);

                    saveAlert(newService1);
                }

            } else {
                Service1 service1s = servicesService1.find(Long.parseLong(serviceId.getText()));
                service1s.setCustomerId(getCustomerId());
                    service1s.setCategory(getCategory());
                    service1s.setStatus(getStatus());
                    service1s.setEstimatedHours(getEstimatedHours());
                    service1s.setSpentHours(getSpentHours());
                    service1s.setNextServiceDate(getNextService1Date());
                    service1s.setCreated(getCreated());
                    service1s.setUpdated(getUpdated());
                Service1 updatedService1 = servicesService1.update(service1s);
                updateAlert(updatedService1);
            }

            clearFields();
            loadService1Details();
        

    }

    @FXML
    private void delete(ActionEvent event) {
        List<Service1> service1ss = serviceTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            servicesService1.deleteInBatch(service1ss);
        }

        loadService1Details();
    }

    private void clearFields() {
        serviceId.setText(null);
        customerId.setText(null);
        category.clear();
        status.clear();
        spentHours.clear();
        estimatedHours.clear();
        nextServiceDate.getEditor().clear();
        created.getEditor().clear();
        updated.getEditor().clear();
       
    }

    private void saveAlert(Service1 service1s) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Service1 saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The service1s " + service1s.getCategory() + " " +" id is " + service1s.getId() + ".");
        alert.showAndWait();
    }

    private void updateAlert(Service1 service1s) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Service1 updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The service1s " + service1s.getCategory() + " " + service1s.getStatus() + " has been updated.");
        alert.showAndWait();
    }

    

    public Long getCustomerId() {
        return Long.parseLong(customerId.getText());
    }
    
    public String getCategory(){
        return category.getText();
    }
    
    public String getStatus(){
        return status.getText();
    }
    
    public Integer getEstimatedHours() {
        return Integer.parseInt(estimatedHours.getText());
        
    }
    
    public Integer getSpentHours() {
        return Integer.parseInt(spentHours.getText());
        
    }
       
    public LocalDate getNextService1Date() {
        return nextServiceDate.getValue();
    }
    
    public LocalDate getCreated() {
        return created.getValue();
    }
    
    public LocalDate getUpdated() {
        return updated.getValue();
    }

   

    
    

    /*
	 *  Set All service1sTable column properties
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

        colServiceId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colServiceStatus.setCellValueFactory(new PropertyValueFactory<>("serviceStatus"));
        colEstimatedHours.setCellValueFactory(new PropertyValueFactory<>("estimatedHours"));
        colSpentHours.setCellValueFactory(new PropertyValueFactory<>("spentHours"));
        colNextServiceDate.setCellValueFactory(new PropertyValueFactory<>("nextService1Date"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("created"));
        colUpdated.setCellValueFactory(new PropertyValueFactory<>("updated"));
        colEdit.setCellFactory(cellFactory);
    }

    Callback<TableColumn<Service1, Boolean>, TableCell<Service1, Boolean>> cellFactory
            = new Callback<TableColumn<Service1, Boolean>, TableCell<Service1, Boolean>>() {
        @Override
        public TableCell<Service1, Boolean> call(final TableColumn<Service1, Boolean> param) {
            final TableCell<Service1, Boolean> cell = new TableCell<Service1, Boolean>() {
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
                            Service1 service1s = getTableView().getItems().get(getIndex());
                            updateService1(service1s);
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

                private void updateService1(Service1 service1s) {
                    serviceId.setText(Long.toString(service1s.getId()));
                    customerId.setText(Long.toString(service1s.getCustomerId()));
                    category.setText(service1s.getCategory());
                    status.setText(service1s.getStatus());
                    estimatedHours.setText(Integer.toString(service1s.getEstimatedHours()));
                    spentHours.setText(Integer.toString(service1s.getSpentHours()));
                    nextServiceDate.setValue(service1s.getNextServiceDate());
                    created.setValue(service1s.getCreated());
                    updated.setValue(service1s.getUpdated());
                    
                    
                }
            };
            return cell;
        }
    };

    /*
	 *  Add All service1ss to observable list and update table
     */
    private void loadService1Details() {
        service1sList.clear();
        service1sList.addAll(servicesService1.findAll());

        serviceTable.setItems(service1sList);
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

        serviceTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();

        // Add all service1ss into table
        loadService1Details();
    }
}
