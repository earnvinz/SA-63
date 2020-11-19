package SA_63.Static;

import SA_63.Model.Employee;

import java.util.ArrayList;

public class StaticAllEmployee {
    private static ArrayList<Employee> Allemployee;
    private static  Employee onlineEmployee;

    public static ArrayList<Employee> getAllemployee() {
        return Allemployee;
    }

    public static void setAllemployee(ArrayList<Employee> allemployee) {
        Allemployee = allemployee;
    }

    public static Employee getOnlineEmployee() {
        return onlineEmployee;
    }

    public static void setOnlineEmployee(Employee onlineEmployee) {
        StaticAllEmployee.onlineEmployee = onlineEmployee;
    }
}
