package SA_63.Controller;

import SA_63.Connection.DB_historyClass;
import SA_63.Connection.DB_memberClass;
import SA_63.Static.Count_history_transaction;
import SA_63.Static.StaticAllmember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class popup_closeController implements Initializable {
    private int count_loop;
    @FXML
    Label name,warn;
    @FXML
    TextField idnumber;
    public void accept(ActionEvent event) throws IOException {
        if(StaticAllmember.getStatic_allmember().get(count_loop).getId().equals(idnumber.getText())){
            warn.setVisible(false);
            String sql = "DELETE FROM `table_member` WHERE `table_member`.`idnumber` ="+"'"+idnumber.getText()+"'";
            try {
                Connection connection = DB_memberClass.getConnection();
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);

                idnumber.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }


            Parent root = FXMLLoader.load(getClass().getResource("OfficerPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 624, 578));
            stage.show();
            stage.setResizable(false);
        }
        else {
            warn.setVisible(true);
        }
    }
    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OfficerPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 624, 578));
        stage.show();
        stage.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        warn.setVisible(false);
        this.count_loop = StaticAllmember.getCountloop();
        name.setText(name.getText()+" : "+ StaticAllmember.getStatic_allmember().
                get(StaticAllmember.getCountloop()).getFirstname());

        DB_memberClass.getConnection();
    }
}
