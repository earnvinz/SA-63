package SA_63.Model;

public class Employee extends User{
    private String Education;
    private String Department;
    private String Address;
    private String password;
    private String Email;

    public Employee(String firstname, String lastname, int age, String id, String phonenumber, Gender gender, String education, String department, String address, String password, String email) {
        super(firstname, lastname, age, id, phonenumber, gender);
        Education = education;
        Department = department;
        Address = address;
        this.password = password;
        Email = email;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
