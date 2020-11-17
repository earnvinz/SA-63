package SA_63.Model;

public class Member extends User {
    private double balance;
    private String address;
    private String password;
    public Member(String firstname, String lastname, int age, String id,String address,
                  String phonenumber, Gender gender , double balance,String password) {
        super(firstname, lastname, age, id, phonenumber, gender);
        this.balance = balance;
        this.address = address;
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
