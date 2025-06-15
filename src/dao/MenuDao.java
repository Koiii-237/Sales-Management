/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.MENU;
import java.sql.Connection;
import DBPOOL.DBConnection;
import java.sql.*;
import javax.management.MBeanInfo;
/**
 *
 * @author ADMIN
 */
public class MenuDao {
    public List<MENU> getAll() {
        List<MENU> list = new ArrayList<>();
        String sql = "SELECT * FROM MENU";
        try {
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                double donGia = rs.getDouble(3);
                String maCTSP = rs.getString(4);

                MENU mn = new MENU(ma, ten, donGia, maCTSP);
                list.add(mn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Object[] getRow(MENU mn) {
        String maMENU = mn.getMaMENU();
        String tenSP = mn.getTenSP();
        double donGia = mn.getDonGia();
        String maCTSP = mn.getMaCTSP();

        return new Object[]{maMENU, tenSP, donGia, maCTSP};
    }
    public MENU getByMaSP(String maMENU) {
    String sql = "SELECT * FROM MENU WHERE MaMENU = ?";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, maMENU);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String ma = rs.getString("MaMENU");
            String ten = rs.getString("TenSP");
            double donGia = rs.getDouble("DonGia");
            String maCTSP = rs.getString("MaCTSP");
            return new MENU(ma, ten, donGia, maCTSP);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
}
