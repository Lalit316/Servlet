package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by l on 2/28/2017.
 */
public class DatabaseConnection {

    String url = "jdbc:mysql://localhost:3306/demo";
    String userName = "root";
    String password = "";

    Connection connection = null;

    public DatabaseConnection(){
        try {
           // connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,userName,password);
            System.out.println("database connected!!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getPreparedStatement(String query) {
        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pstm;
    }

}
