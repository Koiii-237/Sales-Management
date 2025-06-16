/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.SanPham;
import DBPOOL.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author ADMIN
 */
public class QuanLySPDAO {
    public Object[] getRow(SanPham sp) {
        String maSP = sp.getMaSP();
        String tenSP = sp.getTenSP();
        double donGia = sp.getDonGia();
        String ngayNhap = sp.getNgayNhap();
        String maMENU = sp.getMaMENU();

        return new Object[]{maSP, tenSP, donGia, ngayNhap, maMENU};
    }
    
    public List<SanPham> getALL() {
        List<SanPham> listSP = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try (Connection con = DBConnection.getConnection();  
             Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            
            while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                double donGia = rs.getDouble(4);
                String ngayNhap = rs.getString(5);
                String maMENU = rs.getString(6);

                SanPham sp = new SanPham(maSP, tenSP, donGia, ngayNhap, maMENU);
                listSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSP;
    }
    
    public boolean insert(SanPham sp) {
         String sql = "INSERT INTO SanPham (maSP, tenSP, donGia, ngayNhap, maMENU) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, sp.getMaSP());
            pstmt.setString(2, sp.getTenSP());
            pstmt.setDouble(3, sp.getDonGia());
            pstmt.setString(4, sp.getNgayNhap());
            pstmt.setString(5, sp.getMaMENU());
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(SanPham sp) {
        String sql = "UPDATE SanPham SET tenSP=?, donGia=?, ngayNhap=?, maMENU=? WHERE maSP=?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, sp.getTenSP());
            pstmt.setDouble(2, sp.getDonGia());
            pstmt.setString(3, sp.getNgayNhap());
            pstmt.setString(4, sp.getMaMENU());
            pstmt.setString(5, sp.getMaSP());
            
            return pstmt.executeUpdate() > 0;
            } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean delete(String maSP) {
        String sql = "DELETE FROM SanPham WHERE maSP=?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, maSP);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
