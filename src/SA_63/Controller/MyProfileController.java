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

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyProfileController implements Initializable {
    @FXML
    Label name,surname,idnumber,age,gender,address,contact;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contact.setText(OnlineUser.getOnline().getPhonenumber());
        name.setText(OnlineUser.getOnline().getFirstname());
        surname.setText(OnlineUser.getOnline().getLastname());
        idnumber.setText(OnlineUser.getOnline().getId());
        age.setText(""+OnlineUser.getOnline().getAge());
        gender.setText(""+OnlineUser.getOnline().getGender());
        address.setText(OnlineUser.getOnline().getAddress());

    }
    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MemberPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 408, 445));
        stage.show();
        stage.setResizable(false);
    }
}
