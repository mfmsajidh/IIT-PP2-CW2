package IIT.PP2.CW2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CustomerDetailsController {

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

    private Stage primaryStage = new Stage();

    public void goToEmployeeDetails() throws Exception{
//        Gets the current window
        Stage currentStage = (Stage)btn_employees.getScene().getWindow();

//        Closes the current window
        currentStage.close();

//        Loads the job type details window
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/EmployeeDetails.fxml"));
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

//        Loads the contract details window
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/ContractDetails.fxml"));
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

//        Loads the job type details window
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/WelcomeScreen.fxml"));
        primaryStage.setTitle("BeGOOD Inc. - Job Type Details");
        primaryStage.setScene(new Scene(root, 500, 300));

//        Sets an icon for the application
        Image applicationIcon = new Image(getClass().getResourceAsStream("/IIT/PP2/CW2/Images/Logo.png"));
        primaryStage.getIcons().add(applicationIcon);

        primaryStage.show();

    }

    public void saveCustomerDetails(ActionEvent event){
        try{
////          create a connection to mongodb server
//            MongoClient mongoClient = new MongoClient(HOST, PORT);
//
////          create a database name
//            MongoDatabase mongoDatabase = mongoClient.getDatabase("Confab");
//
////          create a collection
//            MongoCollection coll = mongoDatabase.getCollection("Attendance");
//
////          get the values of the fields
//            Document doc = new Document("firstname", firstname.getText())
//                    .append("lastname", lastname.getText())
//                    .append("email", email.getText())
//                    .append("gender", gender.getValue())
//                    .append("phone_number", pnumber.getText());
//
////          save the document
//            coll.insertOne(doc);
//
////          display a success message
            lbl_status.setText("Saved Successfully !!!");
//
////          set the fields to null or empty
//            firstname.setText("");
//            lastname.setText("");
//            email.setText("");
//            gender.setValue(null);
//            pnumber.setText("");
        }
        catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
//          display the error message
            lbl_status.setText("Failed to save");
        }
    }

    public void updateCustomerDetails(ActionEvent event){
        lbl_status.setText("Updated Successfully !!!");
    }

    public void deleteCustomerDetails(ActionEvent event){
        lbl_status.setText("Deleted Successfully !!!");
    }


}
