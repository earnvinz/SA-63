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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class Deposit_WithdrawController implements Initializable {
    private int count_loop;
    @FXML
    Label name,warn;
    @FXML
    MenuButton choice;
    @FXML
    TextField money;
    public void back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("OfficerPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 624, 578));
        stage.show();
        stage.setResizable(false);
    }
    public void setDeposit(ActionEvent event) throws IOException {
        choice.setText("ฝากเงิน");
    }
    public void setWithdraw(ActionEvent event) throws IOException {
        choice.setText("ถอนเงิน");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        warn.setVisible(false);
        count_loop = StaticAllmember.getCountloop();
        name.setText(name.getText()+" : "+StaticAllmember.getStatic_allmember().get(count_loop).getFirstname()+"  "+StaticAllmember.getStatic_allmember().get(count_loop).getLastname());

    }
    public void accept(ActionEvent event) throws IOException {
        if(choice.getText().equals("ฝากเงิน")) {
            if (isNumeric(money.getText())) {
                double total = StaticAllmember.getStatic_allmember().get(count_loop).getBalance();
                double add = Double.parseDouble(money.getText());
                if(add <= 0){
                    warn.setText("ข้อมูลไม่ถูกต้อง");
                    warn.setVisible(true);
                }
                else {
                    total = total + add;
                    String sql = "UPDATE `table_member` SET `balanceAccount` = '" + total + "' WHERE `table_member`.`member_ID` = '" + StaticAllmember.getStatic_allmember().get(count_loop).getId() + "';";
                    try {
                        Connection connection = DB_memberClass.getConnection();
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Count_history_transaction.setCount(Count_history_transaction.getCount() + 1);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                    Date date = new Date(System.currentTimeMillis());
                    String sql1 = "INSERT INTO `history_transaction` (`DepWith_ID`, `date`, `status`, `account_ID`, `amount`) VALUES ('" + Count_history_transaction.getCount() + "', '" + formatter.format(date) + "', '" + "CDP" + "', '" + StaticAllmember.getStatic_allmember().get(count_loop).getId() + "', '" + money.getText() + "');";
                    try {
                        Connection connection = DB_historyClass.getConnection();
                        Statement stm = connection.createStatement();
                        stm.executeUpdate(sql1);

                        Parent root = FXMLLoader.load(getClass().getResource("OfficerPage.fxml"));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(root, 624, 578));
                        stage.show();
                        stage.setResizable(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            else{
                warn.setText("ข้อมูลไม่ถูกต้อง");
                warn.setVisible(true);
            }
        }



        else if(choice.getText().equals("ถอนเงิน")) {
            if (isNumeric(money.getText())) {
                double total = StaticAllmember.getStatic_allmember().get(count_loop).getBalance();
                double minus = Double.parseDouble(money.getText());
                if (minus > total) {
                    warn.setVisible(true);
                    money.setText("");
                }
                else if(minus <= 0){
                    warn.setText("ข้อมูลไม่ถูกต้อง");
                    warn.setVisible(true);

                }
                else {
                    Count_history_transaction.setCount(Count_history_transaction.getCount() + 1);

                    total = total - minus;
                    String sql = "UPDATE `table_member` SET `balanceAccount` = '" + total + "' WHERE `table_member`.`member_ID` = '" + StaticAllmember.getStatic_allmember().get(count_loop).getId() + "';";
                    try {
                        Connection connection = DB_memberClass.getConnection();
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                    Date date = new Date(System.currentTimeMillis());
                    String sql1 = "INSERT INTO `history_transaction` (`DepWith_ID`, `date`, `status`, `account_ID`, `amount`) VALUES ('" + Count_history_transaction.getCount() + "', '" + formatter.format(date) + "', '" + "CWD" + "', '" + StaticAllmember.getStatic_allmember().get(count_loop).getId() + "', '" + money.getText() + "');";
                    try {
                        Connection connection = DB_historyClass.getConnection();
                        Statement stm = connection.createStatement();
                        stm.executeUpdate(sql1);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Parent root = FXMLLoader.load(getClass().getResource("OfficerPage.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root, 624, 578));
                    stage.show();
                    stage.setResizable(false);
                }

            }
            else{
                warn.setText("ข้อมูลไม่ถูกต้อง");
                warn.setVisible(true);
            }
        }




        else{
            warn.setText("เลือกรายการธุรกรรม");
            warn.setVisible(true);
        }
        money.setText("");

    }










    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
