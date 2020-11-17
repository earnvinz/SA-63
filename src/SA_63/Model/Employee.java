package SA_63.Model;


public class Employee extends User {

    private String employee_department;


    public Employee(String firstname, String lastname, int age, String id, String phonenumber, Gender gender,String employee_department) {
        super(firstname, lastname, age, id, phonenumber, gender);
        this.employee_department = employee_department;
    }

    public String getEmployee_department() {
        return employee_department;
    }
}
