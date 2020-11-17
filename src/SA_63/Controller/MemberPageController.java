package SA_63.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MemberPageController {

    public void back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 408, 400));
        stage.show();
        stage.setResizable(false);
    }
}
