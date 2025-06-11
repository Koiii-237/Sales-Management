/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import DBPOOL.DBConnection;
import java.util.ArrayList;
import model.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
public class UserDAO {
    ArrayList<User> usList = new ArrayList<>();
    
    public ArrayList<User> read(){
        Connection con = DBConnection.getConnection();  
        String sql = "SELECT * FROM Users";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                User us = new User();
                us.setUserName(rs.getString(1));
                us.setPassWord(rs.getString(2));
                us.setRole(rs.getInt(3));
                usList.add(us);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usList;
    }
}
