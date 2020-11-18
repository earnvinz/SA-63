package SA_63.Model;

import java.util.ArrayList;

public class Member extends User {
    private double balance;
    private String address;
    private String password;
    private ArrayList<Deposit_Withdraw> Myhistory;
    private double first_money;
    public Member(String firstname, String lastname, int age, String id,String address,
                  String phonenumber, Gender gender , double balance,String password,double first_money) {
        super(firstname, lastname, age, id, phonenumber, gender);
        this.balance = balance;
        this.address = address;
        this.password = password;
        this.first_money=first_money;

    }

    public double getFirst_money() {
        return first_money;
    }

    public void setFirst_money(double first_money) {
        this.first_money = first_money;
    }

    public ArrayList<Deposit_Withdraw> getMyhistory() {
        return Myhistory;
    }

    public void setMyhistory(ArrayList<Deposit_Withdraw> myhistory) {
        Myhistory = myhistory;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
