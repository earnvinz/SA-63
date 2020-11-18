package SA_63.Controller;

import SA_63.Connection.DB_historyClass;
import SA_63.Connection.DB_memberClass;
import SA_63.Static.Count_history_transaction;
import SA_63.Static.StaticAllmember;
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
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String sql = "INSERT INTO `table_member` (`fname`, `lname`, `age`, `idnumber`, `address`, `contact`, `gender`, `balance`, `password`, `firstDeposit`) VALUES("+"'"+fname.getText()+"',"+ "'"+lname.getText()+"',"+"'"+age.getText()+"',"+"'"+cardnumber.getText()+"',"+"'"+address.getText()+"',"+ "'"+contact.getText()+"',"+ "'"+gender.getText()+"',"+ "'"+money.getText()+"',"+"'"+password.getText()+"',"+"'"+money.getText()+"')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        Count_history_transaction.setCount(Count_history_transaction.getCount()+1);

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        Connection connection1 = DB_historyClass.getConnection();
        String sql1 = "INSERT INTO `history_transaction` (`DepWith_ID`, `date`, `status`, `account_ID`, `amount`) VALUES ('"+ Count_history_transaction.getCount() +"', '"+formatter.format(date)+"', '"+"CDP"+"', '"+ cardnumber.getText()+"', '"+money.getText()+"');";
        Statement statement1 = connection1.createStatement();
        statement1.executeUpdate(sql1);
    }
}
