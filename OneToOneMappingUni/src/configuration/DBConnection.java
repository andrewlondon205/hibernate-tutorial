package configuration;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
        String username = "softstrategy";
        String password = "changeme";

        try {
            System.out.println("Connecting to the database " + url);
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connection Successful");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
