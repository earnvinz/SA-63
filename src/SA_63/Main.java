package SA_63;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Controller/Signin.fxml"));
        primaryStage.setTitle("SA-project-63");
        primaryStage.setScene(new Scene(root, 408, 448));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
