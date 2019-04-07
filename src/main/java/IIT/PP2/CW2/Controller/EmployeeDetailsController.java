package IIT.PP2.CW2.Controller;

import IIT.PP2.CW2.Main;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.bson.Document;

public class EmployeeDetailsController {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;

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

    private Stage primaryStage = new Stage();

    public void goToContractDetails() throws Exception{
//        Gets the current window
        Stage currentStage = (Stage)btn_contracts.getScene().getWindow();

//        Closes the current window
        currentStage.close();

//        Loads the contract details window
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/ContractDetails.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/CustomerDetails.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/JobRoleDetails.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/JobTypeDetails.fxml"));
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

//          Creates a connection to mongodb server
            MongoClient mongoClient = new MongoClient(HOST, PORT);

//          Creates a database name
            MongoDatabase mongoDatabase = mongoClient.getDatabase("BeGOOD");

//          Creates a collection
            MongoCollection employeeCollection = mongoDatabase.getCollection("Employee");

//          Gets the values of the fields
            Document employeeDoc = new Document("Name", txt_name.getText())
                    .append("Date of Birth", txt_dateOfBirth.getValue())
                    .append("Contact Number", txt_contactNumber.getText());

//          Inserts the document
            employeeCollection.insertOne(employeeDoc);

//          Display a success message
            lbl_status.setText("Saved Successfully !!!");

//          set the fields to null or empty
            txt_name.setText("");
            txt_dateOfBirth.setValue(null);
            txt_contactNumber.setText("");
        }
        catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
//          Display the error message
            lbl_status.setText("Insert Failed !!!");
        }
    }

    public void updateEmployeeDetails(ActionEvent event){
        lbl_status.setText("Updated Successfully !!!");
    }

    public void deleteEmployeeDetails(ActionEvent event){
        lbl_status.setText("Deleted Successfully !!!");
    }
}
