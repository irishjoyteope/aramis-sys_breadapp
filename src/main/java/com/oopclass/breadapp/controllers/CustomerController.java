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

import com.oopclass.breadapp.models.Customer;
import com.oopclass.breadapp.config.StageManager;
import com.oopclass.breadapp.models.Customer;

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
import com.oopclass.breadapp.services.ICustomerService;
import com.oopclass.breadapp.views.FxmlView;

/**
 * OOP Class 20-21
 *
 * @author Gerald Villaran
 */
@Controller
public class CustomerController implements Initializable {

    @FXML
    private Label customerId;
    
    @FXML
    private Button openServices;

    @FXML
    private Button openPayments;

    @FXML
    private TextField lastName;

    @FXML
    private TextField firstName;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField age;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField street;

    @FXML
    private TextField brgy;

    @FXML
    private TextField city;

    @FXML
    private TextField province;

    @FXML
    private DatePicker created;

    @FXML
    private DatePicker updated;

    
     @FXML
    private ToggleGroup gender;
    
     @FXML
    private RadioButton rbMale;

    @FXML
    private RadioButton rbFemale;
    
    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, Long> colId;

    @FXML
    private TableColumn<Customer, String> colLastname;

    @FXML
    private TableColumn<Customer, String> colFirstname;

    @FXML
    private TableColumn<Customer, LocalDate> colBirthdate;

    @FXML
    private TableColumn<Customer, Integer> colAge;

    @FXML
    private TableColumn<Customer, String> colGender;

    @FXML
    private TableColumn<Customer, Integer> colPhone;

    @FXML
    private TableColumn<Customer, String> colStreet;

    @FXML
    private TableColumn<Customer, String> colBrgy;

    @FXML
    private TableColumn<Customer, String> colCity;

    @FXML
    private TableColumn<Customer, String> colProvince;
    
     @FXML
    private TableColumn<Customer, LocalDate> colCreated;
     
      @FXML
    private TableColumn<Customer, LocalDate> colUpdated;

    @FXML
    private TableColumn<Customer, Boolean> colEdit;

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
    private ICustomerService customerService;

    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void reset(ActionEvent event) {
        clearFields();
       
    }

    @FXML
    private void openServices(ActionEvent event) {
        stageManager.switchScene(FxmlView.SERVICE);

    }

    @FXML
    private void openPayments(ActionEvent event) {
        stageManager.switchScene(FxmlView.PAYMENT);

    }

    @FXML
    private void save(ActionEvent event) {

      
            if (customerId.getText() == null || "".equals(customerId.getText())) {
                if (true) {

                    Customer customer = new Customer();
                    customer.setFirstName(getFirstName());
                    customer.setLastName(getLastName());
                    customer.setDob(getDob());
                    customer.setGender(getGender());
                    customer.setAge(getAge());
                    customer.setPhoneNumber(getPhoneNumber());
                    customer.setBrgy(getBrgy());
                    customer.setStreet(getStreet());
                    customer.setCity(getCity());
                    customer.setProvince(getProvince());
                    customer.setCreated(getCreated());
                    customer.setUpdated(getUpdated());
                    customer.setGender(getGender());
                    

                    Customer newCustomer = customerService.save(customer);

                    saveAlert(newCustomer);
                }

            } else {
                Customer customer = customerService.find(Long.parseLong(customerId.getText()));
                customer.setFirstName(getFirstName());
                    customer.setLastName(getLastName());
                    customer.setDob(getDob());
                    customer.setGender(getGender());
                    customer.setAge(getAge());
                    customer.setPhoneNumber(getPhoneNumber());
                    customer.setBrgy(getBrgy());
                    customer.setStreet(getStreet());
                    customer.setCity(getCity());
                    customer.setProvince(getProvince());
                    customer.setCreated(getCreated());
                    customer.setUpdated(getUpdated());
                customer.setGender(getGender());
                Customer updatedCustomer = customerService.update(customer);
                updateAlert(updatedCustomer);
            }

            clearFields();
            loadCustomerDetails();
        

    }

    @FXML
    private void delete(ActionEvent event) {
        List<Customer> customers = customerTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            customerService.deleteInBatch(customers);
        }

        loadCustomerDetails();
    }

    private void clearFields() {
        customerId.setText(null);
        firstName.clear();
        lastName.clear();
        brgy.clear();
        city.clear();
        province.clear();
        age.clear();
        street.clear();
        phoneNumber.clear();
        created.getEditor().clear();
        updated.getEditor().clear();
        dob.getEditor().clear();
        rbMale.setSelected(true);
        rbFemale.setSelected(false);
    }

    private void saveAlert(Customer customer) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Customer saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The customer " + customer.getFirstName() + " " + customer.getLastName() + " has been created and \n" + getGenderTitle(customer.getGender()) + " id is " + customer.getId() + ".");
        alert.showAndWait();
    }

    private void updateAlert(Customer customer) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Customer updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The customer " + customer.getFirstName() + " " + customer.getLastName() + " has been updated.");
        alert.showAndWait();
    }

    private String getGenderTitle(String gender) {
        return (gender.equals("Male")) ? "his" : "her";
    }

    public String getLastName() {
        return lastName.getText();
    }
    
    public String getFirstName() {
        return firstName.getText();
    }
    
    public LocalDate getDob() {
        return dob.getValue();
    }
    
    public Integer getAge() {
        return Integer.parseInt(age.getText());
    }
    
    public Integer getPhoneNumber() {
        return Integer.parseInt(phoneNumber.getText());
    }
    
    public String getStreet() {
        return street.getText();
    }

    public String getBrgy() {
        return brgy.getText();
    }
    
    public String getCity() {
        return city.getText();
    }
    
    public String getProvince() {
        return province.getText();
    }
    
    public LocalDate getCreated() {
        return created.getValue();
    }
    
    public LocalDate getUpdated() {
        return updated.getValue();
    }

   

    public String getGender() {
        return rbMale.isSelected() ? "Male" : "Female";
    }


    

    /*
	 *  Set All customerTable column properties
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
        colLastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colBirthdate.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        colBrgy.setCellValueFactory(new PropertyValueFactory<>("brgy"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("created"));
        colUpdated.setCellValueFactory(new PropertyValueFactory<>("updated"));
        colEdit.setCellFactory(cellFactory);
    }

    Callback<TableColumn<Customer, Boolean>, TableCell<Customer, Boolean>> cellFactory
            = new Callback<TableColumn<Customer, Boolean>, TableCell<Customer, Boolean>>() {
        @Override
        public TableCell<Customer, Boolean> call(final TableColumn<Customer, Boolean> param) {
            final TableCell<Customer, Boolean> cell = new TableCell<Customer, Boolean>() {
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
                            Customer customer = getTableView().getItems().get(getIndex());
                            updateCustomer(customer);
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

                private void updateCustomer(Customer customer) {
                    customerId.setText(Long.toString(customer.getId()));
                    firstName.setText(customer.getFirstName());
                    lastName.setText(customer.getLastName());
                    dob.setValue(customer.getDob());
                    city.setText(customer.getCity());
                    brgy.setText(customer.getBrgy());
                    province.setText(customer.getProvince());
                    age.setText(Integer.toString(customer.getAge()));
                    phoneNumber.setText(Integer.toString(customer.getPhoneNumber()));
                    created.setValue(customer.getCreated());
                    updated.setValue(customer.getUpdated());
                    
                    if (customer.getGender().equals("Male")) {
                        rbMale.setSelected(true);
                    } else {
                        rbFemale.setSelected(true);
                    }
                }
            };
            return cell;
        }
    };

    /*
	 *  Add All customers to observable list and update table
     */
    private void loadCustomerDetails() {
        customerList.clear();
        customerList.addAll(customerService.findAll());

        customerTable.setItems(customerList);
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

        customerTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();

        // Add all customers into table
        loadCustomerDetails();
    }
}
