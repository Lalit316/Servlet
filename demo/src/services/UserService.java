package services;

import domains.User;
import utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 3/6/2017.
 */
public class UserService {

    public User getUser(String name, String password){
        String query = "select * from user where name=? and password=?";
        PreparedStatement pstm = new DatabaseConnection().getPreparedStatement(query);

        User user = null;
        try {
            pstm.setString(1, name);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
           while (rs.next()) {

                user = new User();
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setId(rs.getInt("id"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getUserList(){
        List<User> userList = new ArrayList<User>();
        String query = "select * from user";
        PreparedStatement pstm = new DatabaseConnection().getPreparedStatement(query);
       try{
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {

           User user = new User();
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setId(rs.getInt("id"));
            userList.add(user);

        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
        return userList;
    }

    public void insert(User user) {
        String query = "insert into user (name, password,role) values(?,?,?)";

        PreparedStatement pstm = new DatabaseConnection().getPreparedStatement(query);
        try {
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getRole());

            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public void deleteUser(int id) {
        String query = "delete from user where id =?";

        PreparedStatement pstm = new DatabaseConnection().getPreparedStatement(query);
        try {
           pstm.setInt(1,id);
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editUser(User user){

        String query ="update user set name=?,password=?,role=? where id=?";

        PreparedStatement pstm = new DatabaseConnection().getPreparedStatement(query);
        try {
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getRole());
            pstm.setInt(4,user.getId());

            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public User getUser(int id){
        String query="select * from user where id=?";
        PreparedStatement pstm = new DatabaseConnection().getPreparedStatement(query);
        User user = null;
        try{
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                user = new User();
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setId(rs.getInt("id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
