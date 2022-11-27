package configuration;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/company?useSSL=false";
        String user = "softstrategy";
        String password = "changeme";

        try {
            System.out.println("Connecting to database : "+ url);
            Connection myConnection = DriverManager.getConnection(url,user,password);
            System.out.println("Connection successful");
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
