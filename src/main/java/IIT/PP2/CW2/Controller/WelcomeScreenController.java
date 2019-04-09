package IIT.PP2.CW2.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class WelcomeScreenController {

    private final static String username = "admin";
    private final static String password = "admin";

    @FXML
    private Label lbl_status;
    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_exit;

    private Stage primaryStage = new Stage();

    public void goToEmployeeDetails() throws Exception{

        String enteredUsername = txt_username.getText();
        String enteredPassword = txt_password.getText();

        if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
//            Gets the current window
            Stage currentStage = (Stage)btn_login.getScene().getWindow();

//            Closes the current window
            currentStage.close();

//            Loads the jobRole details window
            Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/EmployeeDetailsUI.fxml"));
            primaryStage.setTitle("BeGOOD Inc. - Employee Details");
            primaryStage.setScene(new Scene(root, 900, 400));

//            Sets an icon for the application
            Image applicationIcon = new Image(getClass().getResourceAsStream("/IIT/PP2/CW2/Images/Logo.png"));
            primaryStage.getIcons().add(applicationIcon);

            primaryStage.show();
        } else {
            lbl_status.setText("Incorrect Username / Password !!!");
        }

    }

    public void exitWelcomeScreen() {
        Stage currentStage = (Stage)btn_exit.getScene().getWindow();
        currentStage.close();
    }

}
