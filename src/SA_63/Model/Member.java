package SA_63.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Member extends User {
    private double balance;
    private String address;
    private String password;
    private ArrayList<Deposit_Withdraw> Myhistory;



    private String statusAccount;
    private LocalDate openDate;
    private float interest;

    public Member(String firstname, String lastname, int age, String id,String address,
                  String phonenumber, Gender gender , double balance,String password,String statusAccount
    ,LocalDate openDate ,float interest) {
        super(firstname, lastname, age, id, phonenumber, gender);
        this.balance = balance;
        this.address = address;
        this.password = password;
        this.statusAccount = statusAccount;
        this.openDate = openDate;
        this.interest = interest;

    }

    public String getStatusAccount() {
        return statusAccount;
    }

    public void setStatusAccount(String statusAccount) {
        this.statusAccount = statusAccount;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
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
