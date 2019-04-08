package IIT.PP2.CW2.Controller;

import IIT.PP2.CW2.DAO.CustomerDetailsDAO;
import IIT.PP2.CW2.DAO.JobRoleDetailsDAO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;

public class JobRoleDetailsController implements Initializable {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;

    private String defaultId;
    private int temporaryId;
    private String name;
    private String hourlyPay;

    @FXML
    private Button btn_employees;
    @FXML
    private Button btn_contracts;
    @FXML
    private Button btn_customers;
    @FXML
    private Button btn_jobTypes;
    @FXML
    private Button btn_logout;

    @FXML
    private Label lbl_status;

    @FXML
    private TextField txt_name;
    @FXML
    private TextField txt_hourlyPay;

    @FXML
    private TableView<JobRoleDetailsDAO> tableView_jobRoleDetails;
    @FXML
    private TableColumn<JobRoleDetailsDAO, ObjectId> tableCell_jobRoleDefaultId;
    @FXML
    private TableColumn<JobRoleDetailsDAO, Integer> tableCell_jobRoleId;
    @FXML
    private TableColumn<JobRoleDetailsDAO, String> tableCell_jobRoleName;
    @FXML
    private TableColumn<JobRoleDetailsDAO, String> tableCell_jobRoleHourlyPay;

    private Stage primaryStage = new Stage();

    //    Creates an observable list to hold the object in the class
    public ObservableList<JobRoleDetailsDAO> jobRoleList;

    public List jobRole = new ArrayList();

    //    Creates a connection to mongodb server
    MongoClient mongoClient = new MongoClient(HOST, PORT);

    //    Creates a database name
    MongoDatabase mongoDatabase = mongoClient.getDatabase("BeGOOD");

    //    Creates a collection
    MongoCollection jobRoleCollection = mongoDatabase.getCollection("Job Role");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //    Calls the find all method
        MongoCursor<Document> cursor = jobRoleCollection.find().iterator();

        try {
            for (int i = 0; i< jobRoleCollection.count(); i++) {
                temporaryId = i + 1;

                Document jobRoleDoc = cursor.next();

                defaultId = jobRoleDoc.getObjectId("_id").toString();
                name = jobRoleDoc.getString("Name");
                hourlyPay = jobRoleDoc.getString("Hourly Pay");

                jobRole.add(new JobRoleDetailsDAO(defaultId, temporaryId, name, hourlyPay));
            }
            jobRoleList = FXCollections.observableArrayList(jobRole);
        } finally {
//            Closes the connection
            cursor.close();
        }

//        Calls the setJobRoleTable method
        setJobRoleTable();
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

//        Loads the jobRole details window
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

//        Loads the jobRole details window
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/CustomerDetailsUI.fxml"));
        primaryStage.setTitle("BeGOOD Inc. - Customer Details");
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

    public void insertJobRoleDetails(ActionEvent event){
        try{

            if (txt_name.getText().equals("") || txt_hourlyPay.getText().equals("")) {
                lbl_status.setText("Missing required field inputs !!!");
            } else {
//                Gets the values of the fields
                Document jobRoleDoc = new Document("Name", txt_name.getText())
                        .append("Hourly Pay", txt_hourlyPay.getText());

//                Inserts the document
                jobRoleCollection.insertOne(jobRoleDoc);

//                Displays a success message
                lbl_status.setText("Saved Successfully !!!");

                rePopulateJobRoleTable();
                setJobRoleTable();
            }
        }
        catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
//              Displays an error message
            lbl_status.setText("Insert Failed !!!");
        }
    }

    public void viewJobRoleDetails(ActionEvent event) {
        JobRoleDetailsDAO selectedJobRole = tableView_jobRoleDetails.getSelectionModel().getSelectedItem();
        if (selectedJobRole == null) {
            lbl_status.setText("Please select a row to view");
        } else {
            txt_name.setText(selectedJobRole.getName());
            txt_hourlyPay.setText(selectedJobRole.getHourlyPay());
        }
    }

    public void updateJobRoleDetails(ActionEvent event){
        boolean notRetrieved = (txt_name.getText().equals("") && txt_hourlyPay.getText().equals(""));
        if (notRetrieved) {
            lbl_status.setText("Please click view before updating a row");
        } else if (txt_name.getText().equals("") || txt_hourlyPay.getText().equals("")) {
            lbl_status.setText("Missing required field inputs !!!");
        } else {
            JobRoleDetailsDAO selectedJobRole = tableView_jobRoleDetails.getSelectionModel().getSelectedItem();

            String id_ = selectedJobRole.getDefaultId();

            jobRoleCollection.updateOne(eq("_id", new ObjectId(id_)), new Document("$set",
                    new Document("Name", txt_name.getText())
                            .append("Hourly Pay", txt_hourlyPay.getText())
            ));

            rePopulateJobRoleTable();;
            setJobRoleTable();
            lbl_status.setText("Updated Successfully !!!");

        }
    }

    public void deleteJobRoleDetails(ActionEvent event){
        JobRoleDetailsDAO selectedJobRole = tableView_jobRoleDetails.getSelectionModel().getSelectedItem();

        if (selectedJobRole == null) {
            lbl_status.setText("Please select a row to delete");
        } else {
            String id_ = selectedJobRole.getDefaultId();
            jobRoleCollection.deleteOne(eq("_id", new ObjectId(id_)));
            rePopulateJobRoleTable();
            setJobRoleTable();
            lbl_status.setText("Deleted Successfully !!!");
        }
    }

    public void setJobRoleTable(){

//        Sets the values of each column to display on the table
        tableCell_jobRoleDefaultId.setCellValueFactory(new PropertyValueFactory<JobRoleDetailsDAO, ObjectId>("defaultId"));
        tableCell_jobRoleId.setCellValueFactory(new PropertyValueFactory<JobRoleDetailsDAO, Integer>("id"));
        tableCell_jobRoleName.setCellValueFactory(new PropertyValueFactory<JobRoleDetailsDAO, String>("name"));
        tableCell_jobRoleHourlyPay.setCellValueFactory(new PropertyValueFactory<JobRoleDetailsDAO, String>("hourlyPay"));

        tableView_jobRoleDetails.setItems(jobRoleList);

    }

    private void rePopulateJobRoleTable() {

//        Sets the fields to null or empty
        txt_name.setText("");
        txt_hourlyPay.setText("");

//        Calls the find all methods from the mongodb database
        MongoCursor<Document> cursor = jobRoleCollection.find().iterator();

//        Clears the list so that the previous data won't be displayed together with this new ones on the table
        jobRole.clear();

        try{
//          loop through the database and then populate the list
            for(int i = 0; i < jobRoleCollection.count(); i++){
                temporaryId = i +1;

                Document jobRoleDoc = cursor.next();

                defaultId = jobRoleDoc.getObjectId("_id").toString();
                name = jobRoleDoc.getString("Name");
                hourlyPay = jobRoleDoc.getString("Hourly Pay");

                jobRole.add(new JobRoleDetailsDAO(defaultId, temporaryId, name, hourlyPay));
            }
            jobRoleList = FXCollections.observableArrayList(jobRole);


        }
        finally {
//          close the connection
            cursor.close();
        }


    }

}
