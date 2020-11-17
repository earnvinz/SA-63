package SA_63.Controller;

import SA_63.Connection.DB_memberClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class NewMemberPageController {
    @FXML
    TextField fname,lname,cardnumber,age,address,contact,money;
    @FXML
    MenuButton gender;
    @FXML
    PasswordField password;

    public void back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("OfficerPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 624, 578));
        stage.show();
        stage.setResizable(false);
    }
    public void setMale(ActionEvent event) throws IOException {
        gender.setText("ชาย");
    }
    public void setFemale(ActionEvent event) throws IOException {
        gender.setText("หญิง");
    }
    public void SignUp(ActionEvent event) throws IOException, SQLException {
        DB_memberClass db_memberClass = new DB_memberClass();
        Connection connection = DB_memberClass.getConnection();
        String sql = "INSERT INTO `table_member` (`fname`, `lname`, `age`, `idnumber`, `address`, `contact`, `gender`, `balance`, `password`) VALUES("+"'"+fname.getText()+"',"+ "'"+lname.getText()+"',"+"'"+age.getText()+"',"+"'"+cardnumber.getText()+"',"+"'"+address.getText()+"',"+ "'"+contact.getText()+"',"+ "'"+gender.getText()+"',"+ "'"+money.getText()+"',"+"'"+password.getText()+"')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
}
