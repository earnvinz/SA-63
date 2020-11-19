package SA_63.Controller;

import SA_63.Connection.DB_historyClass;
import SA_63.Connection.DB_memberClass;
import SA_63.Model.*;
import SA_63.Static.Count_history_transaction;
import SA_63.Static.OnlineUser;
import SA_63.Static.StaticAllEmployee;
import SA_63.Static.StaticAllmember;
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
    private ArrayList<Member> allmember = new ArrayList<>(); // Member
    private ArrayList<Deposit_Withdraw> allhistory = new ArrayList<>();
    private ArrayList<Employee> allemployee = new ArrayList<>();
    @FXML
    public void Signin(ActionEvent event) throws IOException {
        if(Checkid()) {

            if(CheckPassword()){
                if(CheckisEmployee()){
                    for(Employee e : allemployee){
                        if(e.getId().equals(id.getText())){
                            StaticAllEmployee.setOnlineEmployee(e);
                        }
                    }
                    Parent root = FXMLLoader.load(getClass().getResource("OfficerPage.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root, 624, 578));
                    stage.show();
                    stage.setResizable(false);
                }
                else{
                    Parent root = FXMLLoader.load(getClass().getResource("MemberPage.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root, 408, 445));
                    stage.show();
                    stage.setResizable(false);
                }

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
                allmember.add(new Member(fname,lname,age,id,address,contact,gender,balance,password));
            }
            StaticAllmember.setStatic_allmember(allmember);
        }
        catch (Exception e){
            e.printStackTrace();
        }




        /* history */
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


        /* employee */
        String sql2 = "SELECT * FROM `table_employee`";
        DB_memberClass.getConnection();
        try {
            Connection connection = DB_memberClass.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql2);
            while (resultSet.next()){
                String name = resultSet.getString(6);
                String lastname = resultSet.getString(7);
                String ag = resultSet.getString(4);
                int age = Integer.parseInt(ag);
                String id = resultSet.getString(1);
                String phonenumber = resultSet.getString(2);


                String gende = resultSet.getString(5);
                Gender gender = Gender.valueOf(gende);
                String education=resultSet.getString(9);
                String department = resultSet.getString(10);
                String address =resultSet.getString(3);
                String password = resultSet.getString(8);
                String email = resultSet.getString(11);


                allemployee.add(new Employee(name,lastname,age,id,phonenumber,gender,education,department,address,password,email));


            }
            StaticAllEmployee.setAllemployee(allemployee);
            System.out.println("Size of allemployee = " + allemployee.size());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public boolean Checkid(){
        for(Member m : allmember){
            if(id.getText().equals(m.getId())){
                return true;
            }
        }
        for(Employee e : allemployee){
            if(id.getText().equals(e.getId())){
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
        for(Employee e : allemployee){
            if(id.getText().equals(e.getId()) && password.getText().equals(e.getPassword())){
                return  true;
            }
        }
        return false;
    }
    public boolean CheckisEmployee(){
        for(Employee e : allemployee){
            if(id.getText().equals(e.getId())){
                return true;
            }
        }
        return false;
    }
}
