package SA_63.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;

public class SigninController {
    @FXML
    TextField id;
    @FXML
    PasswordField password;

    public void Signin(ActionEvent event) throws IOException {
        if(id.getText().equals("x") && password.getText().equals("x")) {
            Parent root = FXMLLoader.load(getClass().getResource("MemberPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 612, 443));
            stage.show();
            stage.setResizable(false);
        }
        else{
            Parent root = FXMLLoader.load(getClass().getResource("OfficerPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 624, 578));
            stage.show();
            stage.setResizable(false);
        }
    }
    public void close(ActionEvent event){
        System.exit(1);
    }
}
