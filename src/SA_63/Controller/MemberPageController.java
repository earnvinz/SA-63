package SA_63.Controller;

import SA_63.Static.OnlineUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MemberPageController implements Initializable {
    @FXML
    Label money;
    public void back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 408, 400));
        stage.show();
        stage.setResizable(false);
    }
    public void trans(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Transaction.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 670, 376));
        stage.show();
        stage.setResizable(false);
    }
    public void Profile(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 594, 472));
        stage.show();
        stage.setResizable(false);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        money.setText(money.getText()+" : "+ OnlineUser.getOnline().getBalance()+"  THB");
    }
}
