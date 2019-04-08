package IIT.PP2.CW2.Controller;

import IIT.PP2.CW2.DAO.ContractDetailsDAO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;

public class ContractDetailsController implements Initializable {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;

    private String defaultId;
    private int temporaryId;
    private String name;
    private String description;
    private LocalDate creationDate;
    private String jobType;

    @FXML
    private Button btn_employees;
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
    private TextArea txt_description;
    @FXML
    private DatePicker txt_creationDate;
    @FXML
    private TextField txt_jobType;

    @FXML
    private TableView<ContractDetailsDAO> tableView_contractDetails;
    @FXML
    private TableColumn<ContractDetailsDAO, ObjectId> tableCell_contractDefaultId;
    @FXML
    private TableColumn<ContractDetailsDAO, Integer> tableCell_contractId;
    @FXML
    private TableColumn<ContractDetailsDAO, String> tableCell_contractName;
    @FXML
    private TableColumn<ContractDetailsDAO, String> tableCell_contractDescription;
    @FXML
    private TableColumn<ContractDetailsDAO, LocalDate> tableCell_contractCreationDate;
    @FXML
    private TableColumn<ContractDetailsDAO, String> tableCell_contractJobType;

    private Stage primaryStage = new Stage();

    //    Creates an observable list to hold the object in the class
    public ObservableList<ContractDetailsDAO> contractList;

    public List contract = new ArrayList();

    //    Creates a connection to mongodb server
    MongoClient mongoClient = new MongoClient(HOST, PORT);

    //    Creates a database name
    MongoDatabase mongoDatabase = mongoClient.getDatabase("BeGOOD");

    //    Creates a collection
    MongoCollection contractCollection = mongoDatabase.getCollection("Contract");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //    Calls the find all method
        MongoCursor<Document> cursor = contractCollection.find().iterator();

        try {
            for (int i = 0; i< contractCollection.count(); i++) {
                temporaryId = i + 1;

                Document contractDoc = cursor.next();
                Date getDate = contractDoc.getDate("Creation Date");
                LocalDate formattedDate = getDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                defaultId = contractDoc.getObjectId("_id").toString();
                name = contractDoc.getString("Name");
                description = contractDoc.getString("Description");
                creationDate = formattedDate;
                jobType = contractDoc.getString("Job Type");

                contract.add(new ContractDetailsDAO(defaultId, temporaryId, name, description, creationDate, jobType));
            }
            contractList = FXCollections.observableArrayList(contract);
        } finally {
//            Closes the connection
            cursor.close();
        }

        setContractTable();
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

    public void goToCustomerDetails() throws Exception{
//        Gets the current window
        Stage currentStage = (Stage)btn_customers.getScene().getWindow();

//        Closes the current window
        currentStage.close();

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

    public void insertContractDetails(ActionEvent event){
        try{

            if (txt_name.getText().equals("") || txt_jobType.getText().equals("") || txt_creationDate.getValue() == null || txt_description.getText().equals("")) {
                lbl_status.setText("Missing required field inputs !!!");
            } else {
//                Gets the values of the fields
                Document contractDoc = new Document("Name", txt_name.getText())
                        .append("Description", txt_description.getText())
                        .append("Creation Date", txt_creationDate.getValue())
                        .append("Job Type", txt_jobType.getText());

//                Inserts the document
                contractCollection.insertOne(contractDoc);

//                Displays a success message
                lbl_status.setText("Saved Successfully !!!");

                rePopulateContractTable();
                setContractTable();
            }
        }
        catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
//              Displays an error message
            lbl_status.setText("Insert Failed !!!");
        }
    }

    public void viewContractDetails(ActionEvent event) {
        ContractDetailsDAO selectedContract = tableView_contractDetails.getSelectionModel().getSelectedItem();
        if (selectedContract == null) {
            lbl_status.setText("Please select a row to view");
        } else {
            txt_name.setText(selectedContract.getName());
            txt_description.setText(selectedContract.getDescription());
            txt_creationDate.setValue(selectedContract.getCreationDate());
            txt_jobType.setText(selectedContract.getJobType());
        }
    }

    public void updateContractDetails(ActionEvent event){
        boolean notRetrieved = (txt_name.getText().equals("") && txt_jobType.getText().equals("") && txt_creationDate.getValue() == null && txt_description.getText().equals(""));
        if (notRetrieved) {
            lbl_status.setText("Please click view before updating a row");
        } else if (txt_name.getText().equals("") || txt_jobType.getText().equals("") || txt_creationDate.getValue() == null || txt_description.getText().equals("")) {
            lbl_status.setText("Missing required field inputs !!!");
        } else {
            ContractDetailsDAO selectedContract = tableView_contractDetails.getSelectionModel().getSelectedItem();

            String id_ = selectedContract.getDefaultId();

            contractCollection.updateOne(eq("_id", new ObjectId(id_)), new Document("$set",
                    new Document("Name", txt_name.getText())
                            .append("Description", txt_description.getText())
                            .append("Creation Date", txt_creationDate.getValue())
                            .append("Job Type", txt_jobType.getText())
            ));

            rePopulateContractTable();;
            setContractTable();
            lbl_status.setText("Updated Successfully !!!");

        }
    }

    public void deleteContractDetails(ActionEvent event){
        ContractDetailsDAO selectedContract = tableView_contractDetails.getSelectionModel().getSelectedItem();

        if (selectedContract == null) {
            lbl_status.setText("Please select a row to delete");
        } else {
            String id_ = selectedContract.getDefaultId();
            contractCollection.deleteOne(eq("_id", new ObjectId(id_)));
            rePopulateContractTable();
            setContractTable();
            lbl_status.setText("Deleted Successfully !!!");
        }
    }

    public void setContractTable(){

//        Sets the values of each column to display on the table
        tableCell_contractDefaultId.setCellValueFactory(new PropertyValueFactory<ContractDetailsDAO, ObjectId>("defaultId"));
        tableCell_contractId.setCellValueFactory(new PropertyValueFactory<ContractDetailsDAO, Integer>("id"));
        tableCell_contractName.setCellValueFactory(new PropertyValueFactory<ContractDetailsDAO, String>("name"));
        tableCell_contractDescription.setCellValueFactory(new PropertyValueFactory<ContractDetailsDAO, String>("description"));
        tableCell_contractCreationDate.setCellValueFactory(new PropertyValueFactory<ContractDetailsDAO, LocalDate>("creationDate"));
        tableCell_contractJobType.setCellValueFactory(new PropertyValueFactory<ContractDetailsDAO, String>("jobType"));

        tableView_contractDetails.setItems(contractList);

    }

    private void rePopulateContractTable() {

//        Sets the fields to null or empty
        txt_name.setText("");
        txt_description.setText("");
        txt_creationDate.setValue(null);
        txt_jobType.setText("");

//        Calls the find all methods from the mongodb database
        MongoCursor<Document> cursor = contractCollection.find().iterator();

//        Clears the list so that the previous data won't be displayed together with this new ones on the table
        contract.clear();

        try{
//          loop through the database and then populate the list
            for(int i = 0; i < contractCollection.count(); i++){
                temporaryId = i +1;

                Document contractDoc = cursor.next();
                Date getDate = contractDoc.getDate("Creation Date");
                LocalDate formattedDate = getDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                defaultId = contractDoc.getObjectId("_id").toString();
                name = contractDoc.getString("Name");
                description = contractDoc.getString("Description");
                creationDate = formattedDate;
                jobType = contractDoc.getString("Job Type");

                contract.add(new ContractDetailsDAO(defaultId, temporaryId, name, description, creationDate, jobType));
            }
            contractList = FXCollections.observableArrayList(contract);


        }
        finally {
//          close the connection
            cursor.close();
        }


    }


}
