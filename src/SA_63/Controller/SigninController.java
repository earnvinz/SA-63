package SA_63.Controller;

import SA_63.Connection.DB_historyClass;
import SA_63.Connection.DB_memberClass;
import SA_63.Model.Deposit_Withdraw;
import SA_63.Model.Gender;
import SA_63.Model.Member;
import SA_63.Static.Count_history_transaction;
import SA_63.Static.OnlineUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SigninController implements Initializable {
    private ResultSet resultSet;
    @FXML
    Label warn;
    @FXML
    TextField id;
    @FXML
    PasswordField password;
    private ArrayList<Member> allmember = new ArrayList<>();
    private ArrayList<Deposit_Withdraw> allhistory = new ArrayList<>();
    @FXML
    public void Signin(ActionEvent event) throws IOException {
        if(Checkid()) {

            if(CheckPassword()){

                Parent root = FXMLLoader.load(getClass().getResource("MemberPage.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root, 408, 445));
                stage.show();
                stage.setResizable(false);

            }
            else if(password.getText().equals("")){
                warn.setText("กรุณากรอกรหัสผ่าน");
                warn.setVisible(true);
            }

            else{
                warn.setText("รหัสผ่านไม่ถูกต้อง โปรดลองใหม่อีกครั้ง");
                warn.setVisible(true);
            }

        }
        else {
            warn.setText("รหัสบัตรประชาชนไม่ถูกต้อง กรุณาลองอีกครั้ง");
            warn.setVisible(true);
        }
        if (id.getText().equals("admin") && password.getText().equals("123")){
            Parent root = FXMLLoader.load(getClass().getResource("OfficerPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 624, 578));
            stage.show();
            stage.setResizable(false);
        }
        else if (id.getText().equals("admin") && !password.getText().equals("123")){
            warn.setText("รหัสผ่านผู้ดูแลระบบ/พนักงาน ไม่ถูกต้อง");
            warn.setVisible(true);
        }
    }
    public void close(ActionEvent event){
        System.exit(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        warn.setVisible(false);


        DB_memberClass.getConnection();
        String sql = "SELECT * FROM `table_member`";
        try {
            Connection connection = DB_memberClass.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String fname = resultSet.getString(1);
                String lname = resultSet.getString(2);
                int age = Integer.parseInt(resultSet.getString(3));
                String id = resultSet.getString(4);
                String address = resultSet.getString(5);
                String contact = resultSet.getString(6);
                String g = resultSet.getString(7);
                Gender gender = Gender.valueOf(g);
                Double balance = Double.parseDouble(resultSet.getString(8));
                String password = resultSet.getString(9);
                Double firstDepo = Double.parseDouble(resultSet.getString(10));
                allmember.add(new Member(fname,lname,age,id,address,contact,gender,balance,password,firstDepo));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }





        DB_historyClass.getConnection();
        String sql1 = "SELECT * FROM `history_transaction`";
        try {
            Connection connection = DB_memberClass.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql1);
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String date = resultSet.getString(2);
                String amount = resultSet.getString(5);
                String account_id =resultSet.getString(4);
                String status = resultSet.getString(3);
                allhistory.add(new Deposit_Withdraw(id,date,amount,account_id,status));

            }
            Count_history_transaction.setCount(allhistory.size());
            Count_history_transaction.setHistory(allhistory);
            System.out.println(Count_history_transaction.getCount());
        }
        catch (Exception e){
            e.printStackTrace();
        }











    }
    public boolean Checkid(){
        for(Member m : allmember){
            if(id.getText().equals(m.getId())){
                return true;
            }
        }
        return false;
    }
    public boolean CheckPassword(){
        for(Member m : allmember){
            if(id.getText().equals(m.getId()) && password.getText().equals(m.getPassword())){
                OnlineUser.setOnline(m);
                return true;
            }

        }
        return false;
    }
}
