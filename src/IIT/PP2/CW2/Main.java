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
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Welcome.fxml"));
        primaryStage.setTitle("Computer Consultancy Firm");
        primaryStage.setScene(new Scene(root, 600, 400));

        //Sets an icon for the application
        Image applicationIcon = new Image(getClass().getResourceAsStream("Images/Logo.png"));
        primaryStage.getIcons().add(applicationIcon);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
