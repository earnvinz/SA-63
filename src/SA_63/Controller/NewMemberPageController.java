package SA_63.Controller;

import SA_63.Connection.DB_historyClass;
import SA_63.Connection.DB_memberClass;
import SA_63.Model.Employee;
import SA_63.Model.Member;
import SA_63.Static.Count_history_transaction;
import SA_63.Static.StaticAllEmployee;
import SA_63.Static.StaticAllmember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewMemberPageController {
    @FXML
    TextField fname,lname,cardnumber,age,address,contact,money;
    @FXML
    MenuButton gender;
    @FXML
    PasswordField password;
    @FXML
    Label wname,wlname,wpassword,widnumber,wage,wgender,waddress,wcontact,wmoney;

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
        int count = 0;

        if(Checkname(fname.getText())){
           wname.setVisible(false);
            count += 1;
        }
        else{
            wname.setVisible(true);

        }


        if(Checkname(lname.getText())){
            wlname.setVisible(false);
            count += 1;
        }
        else{
            wlname.setVisible(true);

        }


        if(Checkpassword(password.getText())){
            wpassword.setVisible(false);
            count += 1;
        }
        else{
            wpassword.setVisible(true);

        }

        if(Checkidnumber(cardnumber.getText())){
            widnumber.setVisible(false);
            count += 1;
        }
        else{
            widnumber.setVisible(true);
        }

        if(CheckAge(age.getText())){
            wage.setVisible(false);
            count+=1;
        }
        else{
            wage.setVisible(true);
        }

        if(!gender.getText().equals("เพศ")){
            count+=1;
            wgender.setVisible(false);
        }
        else{
            wgender.setVisible(true);
        }

        if(CheckAddress(address.getText())){
            waddress.setVisible(false);
            count+=1;
        }
        else{
            waddress.setVisible(true);
        }

        if(CheckPhonenumber(contact.getText())){
            wcontact.setVisible(false);
            count +=1;
        }
        else{
            wcontact.setVisible(true);
        }

        if(CheckMoney(money.getText())){
            wmoney.setVisible(false);
            count +=1;
        }
        else{
            wmoney.setVisible(true);
        }

        if(count == 9) {

            DB_memberClass db_memberClass = new DB_memberClass();
            Connection connection = DB_memberClass.getConnection();
            String sql = "INSERT INTO `table_member` (`fname`, `lname`, `age`, `idnumber`, `address`, `contact`, `gender`, `balance`, `password`) VALUES(" + "'" + fname.getText() + "'," + "'" + lname.getText() + "'," + "'" + age.getText() + "'," + "'" + cardnumber.getText() + "'," + "'" + address.getText() + "'," + "'" + contact.getText() + "'," + "'" + gender.getText() + "'," + "'" + money.getText() + "'," + "'" + password.getText() + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            Count_history_transaction.setCount(Count_history_transaction.getCount() + 1);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            Connection connection1 = DB_historyClass.getConnection();
            String sql1 = "INSERT INTO `history_transaction` (`DepWith_ID`, `date`, `status`, `account_ID`, `amount`) VALUES ('" + Count_history_transaction.getCount() + "', '" + formatter.format(date) + "', '" + "CDP" + "', '" + cardnumber.getText() + "', '" + money.getText() + "');";
            Statement statement1 = connection1.createStatement();
            statement1.executeUpdate(sql1);


            Parent root = FXMLLoader.load(getClass().getResource("OfficerPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 624, 578));
            stage.show();
            stage.setResizable(false);
        }
    }
    public boolean Checkname(String name){

        if(name.contains("0") || name.contains("1") || name.contains("2") || name.contains("3") || name.contains("4") || name.contains("5") || name.contains("6") || name.contains("7") || name.contains("8") || name.contains("9") || name.contains(" ")){
            return false;
        }
        if(getSpecialCharacterCount(name) == 1){
            return false;
        }
        if (name.equals("")){
            return false;
        }

        return true;
    }

    public boolean Checkpassword(String password){
        if(password.equals("")){
            return false;
        }
        if(password.contains(" ")){
            return false;
        }
        if(password.length() <4 || password.length() >6){
            return false;
        }
        if(getSpecialCharacterCount(password) == 1){
            return false;
        }
        return true;
    }

    public boolean Checkidnumber(String idnumber){
        if(idnumber.equals("")){
            return false;
        }
        if (idnumber.contains(" ")){
            return false;
        }
        if (idnumber.length() != 13){
            return false;
        }
        if(getSpecialCharacterCount(idnumber) == 1){
            return false;
        }
        if(!CheckAllcharisnumber(idnumber)){
            System.out.println("5555555555");
            return false;

        }
        for(Employee e : StaticAllEmployee.getAllemployee()){
            if(idnumber.equals(e.getId())) {
                widnumber.setText("* มีรหัสบัตรประชาชนนี้แล้วในระบบ");
                return false;
            }
        }
        for(Member m : StaticAllmember.getStatic_allmember()){
            if(idnumber.equals(m.getId()) ){
                widnumber.setText("* มีรหัสบัตรประชาชนนี้แล้วในระบบ");
                return false;
            }
        }
        return true;

    }
    public boolean CheckAge(String age){

        if(age.equals("")){
            return false;
        }
        if(age.charAt(0) == '0'){
            return false;
        }
        if (age.contains(" ")){
            return false;
        }
        if(getSpecialCharacterCount(age) == 1){
            return false;
        }
        if(!CheckAllcharisnumber(age)){
            System.out.println("age is not number");
            return false;

        }
        return true;
    }

    public boolean CheckAllcharisnumber(String number) {
        for (int i = 0; i < number.length(); i++) {
            Boolean flag = Character.isDigit(number.charAt(i));
            if (!flag) {
                return false;
            }

        }
        return true;
    }


    public boolean CheckAddress(String address){
        if(address.equals("") || address.equals(" ") || address.equals("  ")){
            return false;
        }
        if(address.contains("   ")){
            return false;
        }
        return true;
    }
    public boolean CheckPhonenumber(String contact){
        if(getSpecialCharacterCount(contact) == 1){
            return false;
        }
        if(!CheckAllcharisnumber(contact)){
            return false;
        }
        if(contact.length() != 10){
            return false;
        }
        return true;
    }


    public boolean CheckMoney(String money){
        if(!isNumeric(money)){
            return false;
        }
        if(Double.parseDouble(money) < 100){

            return false;
        }
        return true;
    }


















    public int getSpecialCharacterCount(String s) {

        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        // boolean b = m.matches();
        boolean b = m.find();
        if (b) {
//            System.out.println("There is a special character in my string ");
            return 1;
        }
//        System.out.println("There is no special char.");
        return 0;

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
