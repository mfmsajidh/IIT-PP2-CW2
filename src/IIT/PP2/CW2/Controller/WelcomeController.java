package IIT.PP2.CW2.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class WelcomeController {

    @FXML
    private Button btn_login;

    Stage primaryStage = new Stage();

    public void goToEmployeeDetails() throws Exception{
//      get the current window
        Stage stage = (Stage)btn_login.getScene().getWindow();

//      close the current window
        stage.close();

//      load the attendance list window
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/EmployeeDetails.fxml"));
        primaryStage.setTitle("Employee Details");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }


}
