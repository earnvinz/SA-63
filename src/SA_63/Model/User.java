package SA_63.Model;

import SA_63.Model.Gender;

public class User {
    private String firstname;
    private String lastname;
    private int age;
    private String id;
    private String phonenumber;
    private Gender gender;

    public User(String firstname, String lastname, int age, String id, String phonenumber, Gender gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.id = id;
        this.phonenumber = phonenumber;
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
