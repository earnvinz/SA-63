package SA_63.Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_historyClass {
    public static Connection getConnection(){
        String db_name = "db_member";
        String user = "root";
        String password = "root";
        String host = "localhost";
        String driver = "com.mysql.jdbc.Driver";
        try {

            Class.forName(driver);
            String url = "jdbc:mysql://"+host+"/"+db_name;
            Connection con = DriverManager.getConnection(url);
            System.out.println("DB_history is pass");
            return con;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
