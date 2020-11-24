package SA_63.Controller;

import SA_63.Connection.DB_historyClass;
import SA_63.Connection.DB_memberClass;
import SA_63.Model.Member;
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

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;

public class popup_closeController implements Initializable {
    private int count_loop;
    private int j;
    @FXML
    Label name,warn,balance,showall;
    @FXML
    TextField idnumber;
    @FXML
    private CheckBox confirm,confirm2 ;
    public void accept(ActionEvent event) throws IOException {
        if(StaticAllmember.getStatic_allmember().get(count_loop).getId().equals(idnumber.getText()) && confirm.isSelected()){
            warn.setVisible(false);
            idnumber.setDisable(true);
            confirm.setDisable(true);
            if(j == 0){

                Member m  = StaticAllmember.getStatic_allmember().get(count_loop);
                long count_date = getDaysCountBetweenDates(m.getOpenDate(),LocalDate.now());
                double money = m.getBalance()+(m.getBalance()*count_date*(m.getInterest()/100)/365);
                String money555 = String.format("%.2f",money);
                showall.setText(showall.getText()+" "+money555);
                showall.setVisible(true);
                confirm2.setVisible(true);
                j+=1;
            }
            if(confirm2.isSelected()) {
                warn.setVisible(false);
                String sql = "UPDATE `table_member` SET `statusAccount` = 'C' WHERE `table_member`.`member_ID` =" + "'" + idnumber.getText() + "'";
                String sql1 = "UPDATE `table_member` SET `balanceAccount` = '0' WHERE `table_member`.`member_ID`=" + "'" + idnumber.getText() + "'";
                try {
                    Connection connection = DB_memberClass.getConnection();
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Connection connection = DB_memberClass.getConnection();
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql1);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                confirm2.setDisable(true);
                balance.setText("ยอดเงินคงเหลือ = 0");
                warn.setText("ทำรายการเสร็จสิ้น");
                warn.setVisible(true);
                StaticAllmember.getStatic_allmember().get(count_loop).setBalance(0);


//                Parent root = FXMLLoader.load(getClass().getResource("OfficerPage.fxml"));
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(new Scene(root, 624, 578));
//                stage.show();
//                stage.setResizable(false);
            }
            else {
                warn.setText("กรุณาเลือกปุ่มทำการถอนทั้งหมด");

            }
        }
        else if(StaticAllmember.getStatic_allmember().get(count_loop).getId().equals(idnumber.getText()) && !confirm.isSelected()){
            warn.setText("กรุณากดปุ่มยืนยันการปิดบัญชี");
            warn.setVisible(true);
        }

        else {
            warn.setText("เลขบัตรประชาชนไม่ถูกต้อง");
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
        j = 0 ;
        warn.setVisible(false);
        this.count_loop = StaticAllmember.getCountloop();
        name.setText(name.getText()+" : "+ StaticAllmember.getStatic_allmember().
                get(StaticAllmember.getCountloop()).getFirstname());
        String money = "ยอดเงินคงเหลือ =  "+StaticAllmember.getStatic_allmember().get(StaticAllmember.getCountloop()).getBalance();
        balance.setText(money);

        DB_memberClass.getConnection();
    }
    public long getDaysCountBetweenDates(LocalDate dateBefore, LocalDate dateAfter) {
        return DAYS.between(dateBefore, dateAfter);
    }
}
