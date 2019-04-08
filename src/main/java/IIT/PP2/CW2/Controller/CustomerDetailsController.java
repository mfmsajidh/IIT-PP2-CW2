package IIT.PP2.CW2.Controller;

import IIT.PP2.CW2.DAO.CustomerDetailsDAO;
import IIT.PP2.CW2.Main;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;

public class CustomerDetailsController implements Initializable {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;

    private String defaultId;
    private int temporaryId;
    private String name;
    private String address;
    private String contactNumber;

    @FXML
    private Button btn_employees;
    @FXML
    private Button btn_contracts;
    @FXML
    private Button btn_jobRoles;
    @FXML
    private Button btn_jobTypes;
    @FXML
    private Button btn_logout;

    @FXML
    private Label lbl_status;

    @FXML
    private TextField txt_name;
    @FXML
    private TextArea txt_address;
    @FXML
    private TextField txt_contactNumber;

    @FXML
    private TableView<CustomerDetailsDAO> tableView_customerDetails;
    @FXML
    private TableColumn<CustomerDetailsDAO, ObjectId> tableCell_customerDefaultId;
    @FXML
    private TableColumn<CustomerDetailsDAO, Integer> tableCell_customerId;
    @FXML
    private TableColumn<CustomerDetailsDAO, String> tableCell_customerName;
    @FXML
    private TableColumn<CustomerDetailsDAO, String> tableCell_customerAddress;
    @FXML
    private TableColumn<CustomerDetailsDAO, String> tableCell_customerContactNumber;

    private Stage primaryStage = new Stage();

    //    Creates an observable list to hold the object in the class
    public ObservableList<CustomerDetailsDAO> customerList;

    public List customer = new ArrayList();

    //    Creates a connection to mongodb server
    MongoClient mongoClient = new MongoClient(HOST, PORT);

    //    Creates a database name
    MongoDatabase mongoDatabase = mongoClient.getDatabase("BeGOOD");

    //    Creates a collection
    MongoCollection customerCollection = mongoDatabase.getCollection("Customer");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //    Calls the find all method
        MongoCursor<Document> cursor = customerCollection.find().iterator();

        try {
            for (int i = 0; i< customerCollection.count(); i++) {
                temporaryId = i + 1;

                Document customerDoc = cursor.next();

                defaultId = customerDoc.getObjectId("_id").toString();
                name = customerDoc.getString("Name");
                address = customerDoc.getString("Address");
                contactNumber = customerDoc.getString("Contact Number");

                customer.add(new CustomerDetailsDAO(defaultId, temporaryId, name, address, contactNumber));
            }
            customerList = FXCollections.observableArrayList(customer);
        } finally {
//            Closes the connection
            cursor.close();
        }

//        Calls the setJobRoleTable method
        setCustomerTable();
    }

    public void goToEmployeeDetails() throws Exception{
//        Gets the current window
        Stage currentStage = (Stage)btn_employees.getScene().getWindow();

//        Closes the current window
        currentStage.close();

//        Loads the job type details window
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/EmployeeDetailsUI.fxml"));
        primaryStage.setTitle("BeGOOD Inc. - Employee Details");
        primaryStage.setScene(new Scene(root, 900, 400));

//        Sets an icon for the application
        Image applicationIcon = new Image(getClass().getResourceAsStream("/IIT/PP2/CW2/Images/Logo.png"));
        primaryStage.getIcons().add(applicationIcon);

        primaryStage.show();

    }

    public void goToContractDetails() throws Exception{
//        Gets the current window
        Stage currentStage = (Stage)btn_contracts.getScene().getWindow();

//        Closes the current window
        currentStage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/ContractDetailsUI.fxml"));
        primaryStage.setTitle("BeGOOD Inc. - Contract Details");
        primaryStage.setScene(new Scene(root, 900, 400));

//        Sets an icon for the application
        Image applicationIcon = new Image(getClass().getResourceAsStream("/IIT/PP2/CW2/Images/Logo.png"));
        primaryStage.getIcons().add(applicationIcon);

        primaryStage.show();

    }

    public void goToJobRoleDetails() throws Exception{
//        Gets the current window
        Stage currentStage = (Stage)btn_jobRoles.getScene().getWindow();

//        Closes the current window
        currentStage.close();

//        Loads the job role details window
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/JobRoleDetailsUI.fxml"));
        primaryStage.setTitle("BeGOOD Inc. - Job Role Details");
        primaryStage.setScene(new Scene(root, 900, 400));

//        Sets an icon for the application
        Image applicationIcon = new Image(getClass().getResourceAsStream("/IIT/PP2/CW2/Images/Logo.png"));
        primaryStage.getIcons().add(applicationIcon);

        primaryStage.show();

    }

    public void goToJobTypeDetails() throws Exception{
//        Gets the current window
        Stage currentStage = (Stage)btn_jobTypes.getScene().getWindow();

//        Closes the current window
        currentStage.close();

//        Loads the job type details window
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/JobTypeDetailsUI.fxml"));
        primaryStage.setTitle("BeGOOD Inc. - Job Type Details");
        primaryStage.setScene(new Scene(root, 900, 400));

//        Sets an icon for the application
        Image applicationIcon = new Image(getClass().getResourceAsStream("/IIT/PP2/CW2/Images/Logo.png"));
        primaryStage.getIcons().add(applicationIcon);

        primaryStage.show();

    }

    public void goToWelcomeScreen() throws Exception{
//        Gets the current window
        Stage currentStage = (Stage)btn_logout.getScene().getWindow();

//        Closes the current window
        currentStage.close();

//        Loads the main class window
        Main mainClass = new Main();

        mainClass.start(primaryStage);

    }

    public void insertCustomerDetails(ActionEvent event){
        try{

            if (txt_name.getText().equals("") || txt_contactNumber.getText().equals("") || txt_address.getText().equals("")) {
                lbl_status.setText("Missing required field inputs !!!");
            } else {
//                Gets the values of the fields
                Document customerDoc = new Document("Name", txt_name.getText())
                        .append("Address", txt_address.getText())
                        .append("Contact Number", txt_contactNumber.getText());

//                Inserts the document
                customerCollection.insertOne(customerDoc);

//                Displays a success message
                lbl_status.setText("Saved Successfully !!!");

                rePopulateCustomerTable();
                setCustomerTable();
            }
        }
        catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
//              Displays an error message
            lbl_status.setText("Insert Failed !!!");
        }
    }

    public void viewCustomerDetails(ActionEvent event) {
        CustomerDetailsDAO selectedCustomer = tableView_customerDetails.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            lbl_status.setText("Please select a row to view");
        } else {
            txt_name.setText(selectedCustomer.getName());
            txt_address.setText(selectedCustomer.getAddress());
            txt_contactNumber.setText(selectedCustomer.getContactNumber());
        }
    }

    public void updateCustomerDetails(ActionEvent event){
        boolean notRetrieved = (txt_name.getText().equals("") && txt_contactNumber.getText().equals("") && txt_address.getText().equals(""));
        if (notRetrieved) {
            lbl_status.setText("Please click view to before updating a row");
        } else if (txt_name.getText().equals("") || txt_contactNumber.getText().equals("") || txt_address.getText().equals("")) {
            lbl_status.setText("Missing required field inputs !!!");
        } else {
            CustomerDetailsDAO selectedCustomer = tableView_customerDetails.getSelectionModel().getSelectedItem();

            String id_ = selectedCustomer.getDefaultId();

            customerCollection.updateOne(eq("_id", new ObjectId(id_)), new Document("$set",
                    new Document("Name", txt_name.getText())
                            .append("Address", txt_address.getText())
                            .append("Contact Number", txt_contactNumber.getText())
            ));

            rePopulateCustomerTable();;
            setCustomerTable();
            lbl_status.setText("Updated Successfully !!!");

        }
    }

    public void deleteCustomerDetails(ActionEvent event){
        CustomerDetailsDAO selectedCustomer = tableView_customerDetails.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            lbl_status.setText("Please select a row to delete");
        } else {
            String id_ = selectedCustomer.getDefaultId();
            customerCollection.deleteOne(eq("_id", new ObjectId(id_)));
            rePopulateCustomerTable();
            setCustomerTable();
            lbl_status.setText("Deleted Successfully !!!");
        }
    }

    public void setCustomerTable(){

//        Sets the values of each column to display on the table
        tableCell_customerDefaultId.setCellValueFactory(new PropertyValueFactory<CustomerDetailsDAO, ObjectId>("defaultId"));
        tableCell_customerId.setCellValueFactory(new PropertyValueFactory<CustomerDetailsDAO, Integer>("id"));
        tableCell_customerName.setCellValueFactory(new PropertyValueFactory<CustomerDetailsDAO, String>("name"));
        tableCell_customerAddress.setCellValueFactory(new PropertyValueFactory<CustomerDetailsDAO, String>("address"));
        tableCell_customerContactNumber.setCellValueFactory(new PropertyValueFactory<CustomerDetailsDAO, String>("contactNumber"));

        tableView_customerDetails.setItems(customerList);

    }

    private void rePopulateCustomerTable() {

//        Sets the fields to null or empty
        txt_name.setText("");
        txt_address.setText("");
        txt_contactNumber.setText("");

//        Calls the find all methods from the mongodb database
        MongoCursor<Document> cursor = customerCollection.find().iterator();

//        Clears the list so that the previous data won't be displayed together with this new ones on the table
        customer.clear();

        try{
//          loop through the database and then populate the list
            for(int i = 0; i < customerCollection.count(); i++){
                temporaryId = i +1;

                Document customerDoc = cursor.next();

                defaultId = customerDoc.getObjectId("_id").toString();
                name = customerDoc.getString("Name");
                address = customerDoc.getString("Address");
                contactNumber = customerDoc.getString("Contact Number");

                customer.add(new CustomerDetailsDAO(defaultId, temporaryId, name, address, contactNumber));
            }
            customerList = FXCollections.observableArrayList(customer);


        }
        finally {
//          close the connection
            cursor.close();
        }


    }

}
