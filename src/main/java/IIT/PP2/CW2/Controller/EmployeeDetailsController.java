package IIT.PP2.CW2.Controller;

import IIT.PP2.CW2.DAO.EmployeeDetailsDAO;
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
import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class EmployeeDetailsController implements Initializable {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;

    private String defaultId;
    private int temporaryId;
    private String name;
    private LocalDate dateOfBirth;
    private String contactNumber;

    @FXML
    private Button btn_contracts;
    @FXML
    private Button btn_customers;
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
    private DatePicker txt_dateOfBirth;
    @FXML
    private TextField txt_contactNumber;

    @FXML
    private TableView<EmployeeDetailsDAO> tableView_employeeDetails;
    @FXML
    private TableColumn<EmployeeDetailsDAO, ObjectId> tableCell_employeeDefaultId;
    @FXML
    private TableColumn<EmployeeDetailsDAO, Integer> tableCell_employeeId;
    @FXML
    private TableColumn<EmployeeDetailsDAO, String> tableCell_employeeName;
    @FXML
    private TableColumn<EmployeeDetailsDAO, LocalDate> tableCell_employeeDateOfBirth;
    @FXML
    private TableColumn<EmployeeDetailsDAO, String> tableCell_employeeContactNumber;

    private Stage primaryStage = new Stage();

//    Creates an observable list to hold the Employees object in the Employees class
    public ObservableList<EmployeeDetailsDAO> employeeList;

    public List employee = new ArrayList();

//    Creates a connection to mongodb server
    MongoClient mongoClient = new MongoClient(HOST, PORT);

//    Creates a database name
    MongoDatabase mongoDatabase = mongoClient.getDatabase("BeGOOD");

//    Creates a collection
    MongoCollection employeeCollection = mongoDatabase.getCollection("Employee");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //    Calls the find all method
        MongoCursor<Document> cursor = employeeCollection.find().iterator();

        try {
            for (int i=0; i<employeeCollection.count(); i++) {
                temporaryId = i + 1;

                Document employeeDoc = cursor.next();
                Date getDate = employeeDoc.getDate("Date of Birth");
                LocalDate formattedDate = getDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                defaultId = employeeDoc.getObjectId("_id").toString();
                name = employeeDoc.getString("Name");
                dateOfBirth = formattedDate;
                contactNumber = employeeDoc.getString("Contact Number");

                employee.add(new EmployeeDetailsDAO(defaultId, temporaryId, name, dateOfBirth, contactNumber));
            }
            employeeList = FXCollections.observableArrayList(employee);
        } finally {
//            Closes the connection
            cursor.close();
        }

//        Calls the setEmployeeTable method
        setEmployeeTable();
    }

    public void goToContractDetails() throws Exception{
//        Gets the current window
        Stage currentStage = (Stage)btn_contracts.getScene().getWindow();

//        Closes the current window
        currentStage.close();

//        Loads the contract details window
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/ContractDetailsUI.fxml"));
        primaryStage.setTitle("BeGOOD Inc. - Contract Details");
        primaryStage.setScene(new Scene(root, 900, 400));

//        Sets an icon for the application
        Image applicationIcon = new Image(getClass().getResourceAsStream("/IIT/PP2/CW2/Images/Logo.png"));
        primaryStage.getIcons().add(applicationIcon);

        primaryStage.show();

    }

    public void goToCustomerDetails() throws Exception{
//        Gets the current window
        Stage currentStage = (Stage)btn_customers.getScene().getWindow();

//        Closes the current window
        currentStage.close();

//        Loads the customer details window
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/CustomerDetailsUI.fxml"));
        primaryStage.setTitle("BeGOOD Inc. - Customer Details");
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

    public void insertEmployeeDetails(ActionEvent event){
        try{

            if (txt_name.getText().equals("") || txt_contactNumber.getText().equals("") || txt_dateOfBirth.getValue() == null) {
                lbl_status.setText("Missing required field inputs !!!");
            } else {
//                Gets the values of the fields
                Document employeeDoc = new Document("Name", txt_name.getText())
                        .append("Date of Birth", txt_dateOfBirth.getValue())
                        .append("Contact Number", txt_contactNumber.getText());

//                Inserts the document
                employeeCollection.insertOne(employeeDoc);

//                Displays a success message
                lbl_status.setText("Saved Successfully !!!");

                rePopulateEmployeeTable();
                setEmployeeTable();
            }
        }
        catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
//              Displays an error message
            lbl_status.setText("Insert Failed !!!");
        }
    }

    public void viewEmployeeDetails(ActionEvent event) {
        EmployeeDetailsDAO selectedEmployee = tableView_employeeDetails.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            lbl_status.setText("Please select an employee to view");
        } else {
            txt_name.setText(selectedEmployee.getName());
            txt_dateOfBirth.setValue(selectedEmployee.getDateOfBirth());
            txt_contactNumber.setText(selectedEmployee.getContactNumber());
        }
    }

    public void updateEmployeeDetails(ActionEvent event){
        boolean notRetrieved = (txt_name.getText().equals("") && txt_contactNumber.getText().equals("") && txt_dateOfBirth.getValue() == null);
        if (notRetrieved) {
            lbl_status.setText("Please click view to update an employee");
        } else if (txt_name.getText().equals("") || txt_contactNumber.getText().equals("") || txt_dateOfBirth.getValue() == null) {
            lbl_status.setText("Missing required field inputs !!!");
        } else {
            EmployeeDetailsDAO selectedEmployee = tableView_employeeDetails.getSelectionModel().getSelectedItem();

            String id_ = selectedEmployee.getDefaultId();

            employeeCollection.updateOne(eq("_id", new ObjectId(id_)), new Document("$set",
                    new Document("Name", txt_name.getText())
                            .append("Date of Birth", txt_dateOfBirth.getValue())
                            .append("Contact Number", txt_contactNumber.getText())
            ));

            rePopulateEmployeeTable();;
            setEmployeeTable();
            lbl_status.setText("Updated Successfully !!!");

        }
    }

    public void deleteEmployeeDetails(ActionEvent event){
        EmployeeDetailsDAO selectedEmployee = tableView_employeeDetails.getSelectionModel().getSelectedItem();

        if (selectedEmployee == null) {
            lbl_status.setText("Please select an employee to delete");
        } else {
            String id_ = selectedEmployee.getDefaultId();
            employeeCollection.deleteOne(eq("_id", new ObjectId(id_)));
            rePopulateEmployeeTable();
            setEmployeeTable();
            lbl_status.setText("Deleted Successfully !!!");
        }

    }

    public void setEmployeeTable(){

//        Sets the values of each column to display on the table
        tableCell_employeeDefaultId.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsDAO, ObjectId>("defaultId"));
        tableCell_employeeId.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsDAO, Integer>("id"));
        tableCell_employeeName.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsDAO, String>("name"));
        tableCell_employeeDateOfBirth.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsDAO, LocalDate>("dateOfBirth"));
        tableCell_employeeContactNumber.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsDAO, String>("contactNumber"));

        tableView_employeeDetails.setItems(employeeList);

    }

    private void rePopulateEmployeeTable() {

//        Sets the fields to null or empty
        txt_name.setText("");
        txt_dateOfBirth.setValue(null);
        txt_contactNumber.setText("");

//        Calls the find all methods from the mongodb database
        MongoCursor<Document> cursor = employeeCollection.find().iterator();

//        Clears the employee list so that the previous data won't be displayed together with this new ones on the table
        employee.clear();

        try{
//          loop through the database and then populate the list
            for(int i = 0; i < employeeCollection.count(); i++){
                temporaryId = i +1;

                Document employeeDoc = cursor.next();
                Date getDate = employeeDoc.getDate("Date of Birth");
                LocalDate formattedDate = getDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                defaultId = employeeDoc.getObjectId("_id").toString();
                name = employeeDoc.getString("Name");
                dateOfBirth = formattedDate;
                contactNumber = employeeDoc.getString("Contact Number");

                employee.add(new EmployeeDetailsDAO(defaultId, temporaryId, name, dateOfBirth, contactNumber ));
            }
            employeeList = FXCollections.observableArrayList(employee);


        }
        finally {
//          close the connection
            cursor.close();
        }


    }

}
