package IIT.PP2.CW2.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class WelcomeController {

    @FXML
    private Button btn_login;

    private Stage primaryStage = new Stage();

    public void goToEmployeeDetails() throws Exception{
//        Gets the current window
        Stage currentStage = (Stage)btn_login.getScene().getWindow();

//        Closes the current window
        currentStage.close();

//        Loads the employee details window
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/EmployeeDetails.fxml"));
        primaryStage.setTitle("BeGOOD Inc. - Employee Details");
        primaryStage.setScene(new Scene(root, 900, 400));

//        Sets an icon for the application
        Image applicationIcon = new Image(getClass().getResourceAsStream("/IIT/PP2/CW2/Images/Logo.png"));
        primaryStage.getIcons().add(applicationIcon);

        primaryStage.show();

    }


}
