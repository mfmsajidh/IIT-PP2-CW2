package IIT.PP2.CW2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/IIT/PP2/CW2/FXML/WelcomeScreenUI.fxml"));
        primaryStage.setTitle("BeGOOD Inc. - Login");
        primaryStage.setScene(new Scene(root, 500, 300));

        //Sets an icon for the application
        Image applicationIcon = new Image(getClass().getResourceAsStream("/IIT/PP2/CW2/Images/Logo.png"));
        primaryStage.getIcons().add(applicationIcon);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
