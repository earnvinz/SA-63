package SA_63.Controller;

import SA_63.Connection.DB_memberClass;
import SA_63.Model.Gender;
import SA_63.Model.Member;
import SA_63.Static.StaticAllEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;


import javafx.stage.Stage;
import SA_63.Static.StaticAllmember;
import java.net.URL;
import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OfficerController implements Initializable {
    int count_loop = 0;
    private ResultSet resultSet;
    private ArrayList<Member> allmember = new ArrayList<>();
    @FXML
    Label name,surname,idnumber,age,address,contact,gender,money,warn;
    @FXML
    Button next,back,close,open,depo_with;
    @FXML
    TextField find;

    public void back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 408, 448));
        stage.show();
        stage.setResizable(false);
    }
    public void newaccount(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("NewMemberPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 559, 505));
        stage.show();
        stage.setResizable(false);
    }


    public void closeaccount(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("popup_closemember.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 377, 179));

        stage.setResizable(false);
        stage.show();



    }
    public void depo_withdraw(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Deposit_Withdraw.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 418, 271));

        stage.setResizable(false);
        stage.show();



    }













    @Override
    public void initialize(URL location, ResourceBundle resources) {
        warn.setVisible(false);
        DB_memberClass.getConnection(); /*เชื่อมต่อไปยัง database */
        ShowData();
        back.setDisable(true);
        System.out.println(allmember.size());
        if(allmember.size()>0) {
            name.setText(allmember.get(count_loop).getFirstname());
            surname.setText(allmember.get(count_loop).getLastname());
            idnumber.setText(allmember.get(count_loop).getId());
            age.setText("" + allmember.get(count_loop).getAge());
            address.setText(allmember.get(count_loop).getAddress());
            contact.setText(allmember.get(count_loop).getPhonenumber());
            gender.setText("" + allmember.get(count_loop).getGender());
            money.setText("" + allmember.get(count_loop).getBalance());
        }
        else{
            next.setDisable(true);
            back.setDisable(true);
        }

        if(count_loop+1 == allmember.size()){
            next.setDisable(true);
        }
        if(StaticAllEmployee.getOnlineEmployee().getDepartment().equals("พนักงานการเงิน")){
            depo_with.setDisable(true);
            open.setDisable(true);
            close.setDisable(true);
        }
        StaticAllmember.setCountloop(count_loop);




    }
    public void NextchangeData(ActionEvent event){
        if(count_loop != allmember.size()) {
            this.count_loop += 1 ;
            name.setText(allmember.get(count_loop).getFirstname());
            surname.setText(allmember.get(count_loop).getLastname());
            idnumber.setText(allmember.get(count_loop).getId());
            age.setText("" + allmember.get(count_loop).getAge());
            address.setText(allmember.get(count_loop).getAddress());
            contact.setText(allmember.get(count_loop).getPhonenumber());
            gender.setText("" + allmember.get(count_loop).getGender());
            money.setText("" + allmember.get(count_loop).getBalance());
        }
        if(count_loop+1 == allmember.size()){
            next.setDisable(true);
        }
        else{
            next.setDisable(false);
        }
        if(count_loop != 0){
            back.setDisable(false);
        }
        StaticAllmember.setCountloop(count_loop);

    }
    public void BackchangeData(ActionEvent event){

        if(count_loop != 0) {
            this.count_loop -= 1 ;
            name.setText(allmember.get(count_loop).getFirstname());
            surname.setText(allmember.get(count_loop).getLastname());
            idnumber.setText(allmember.get(count_loop).getId());
            age.setText("" + allmember.get(count_loop).getAge());
            address.setText(allmember.get(count_loop).getAddress());
            contact.setText(allmember.get(count_loop).getPhonenumber());
            gender.setText("" + allmember.get(count_loop).getGender());
            money.setText("" + allmember.get(count_loop).getBalance());
        }
        if(count_loop == 0){
            back.setDisable(true);
            next.setDisable(false);
        }
        if(count_loop!=allmember.size() && count_loop != 0){
            next.setDisable(false);
        }
        StaticAllmember.setCountloop(count_loop);



    }
    public void ShowData(){ /* ดึงข้อมูลจาก database */
        String sql = "SELECT * FROM `table_member`"; /* copy from phpmyadmin */
        try {
            Connection connection = DB_memberClass.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
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
        catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void btnfindMember(ActionEvent event) throws IOException {
        int pos = findPositionMember();
        if(pos >= 0){
            warn.setVisible(false);
            count_loop = pos;
            StaticAllmember.setCountloop(count_loop);
            if(count_loop == 0){
                back.setDisable(true);
                next.setDisable(false);
            }
            else if (count_loop == allmember.size() - 1){
                next.setDisable(true);
                back.setDisable(false);
            }
            else{
                next.setDisable(false);
                back.setDisable(false);
            }
            name.setText(allmember.get(count_loop).getFirstname());
            surname.setText(allmember.get(count_loop).getLastname());
            idnumber.setText(allmember.get(count_loop).getId());
            age.setText("" + allmember.get(count_loop).getAge());
            address.setText(allmember.get(count_loop).getAddress());
            contact.setText(allmember.get(count_loop).getPhonenumber());
            gender.setText("" + allmember.get(count_loop).getGender());
            money.setText("" + allmember.get(count_loop).getBalance());

        }
        if(pos < 0){
            warn.setVisible(true);
            warn.setText("ไม่พบผุู้ใช้ในระบบ");
        }


    }
    public int findPositionMember(){
        int i = 0;
        for(;i < allmember.size();i++){
            if(find.getText().equals(allmember.get(i).getId())){
                return i;
            }
        }
        return -1;
    }
}
