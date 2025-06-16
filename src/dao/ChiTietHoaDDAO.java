/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.ChiTietHoaDon;
import DBPOOL.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author ADMIN
 */
public class ChiTietHoaDDAO {
    public Object[] getRow(ChiTietHoaDon cthd) {
        String maCTHD = cthd.getMaCTHD();
        String maHD = cthd.getMaHD();
        String maKM = cthd.getMaKM();
        String maCTSP = cthd.getMaCTSP();
        String tenSP = cthd.getTenSP();
        double donGia = cthd.getDonGia();
        String trangThai = cthd.getTrangThai();

        return new Object[]{maCTHD, maHD, maKM, maCTSP, tenSP, donGia, trangThai};
    }
    
    public List<ChiTietHoaDon> read() {
        List<ChiTietHoaDon> listCTHD = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietHoaDon";
        try (Connection con = DBConnection.getConnection();  
             Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            
            while (rs.next()) {
                String maCTHD = rs.getString(1);
                String maHD = rs.getString(2);
                String maKM = rs.getString(3);
                String maCTSP = rs.getString(4);
                String tenSP = rs.getString(5);
                double donGia = rs.getDouble(6);
                String trangThai = rs.getString(7);

                ChiTietHoaDon cthd = new ChiTietHoaDon(maCTHD, maHD, maKM, maCTSP, tenSP, donGia, trangThai);
                listCTHD.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCTHD;
    }
    
    public int create(ChiTietHoaDon cthd) {
         String sql = "INSERT INTO ChiTietHoaDon (maCTHD, maHD, maKM, maCTSP, tenSP, donGia, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, cthd.getMaCTHD());
            pstmt.setString(2, cthd.getMaHD());
            pstmt.setString(3, cthd.getMaKM());
            pstmt.setString(4, cthd.getMaCTSP());
            pstmt.setString(5, cthd.getTenSP());
            pstmt.setDouble(6, cthd.getDonGia());
            pstmt.setString(7, cthd.getTrangThai());
            
            if(pstmt.executeUpdate() > 1){
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return 0;
    }
    public boolean update(ChiTietHoaDon cthd) {
        String sql = "UPDATE ChiTietHoaDon SET maHD=?, maKM=?, maCTSP=?, tenSP=?, donGia=?, trangThai=? WHERE maCTHD=?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, cthd.getMaHD());
            pstmt.setString(2, cthd.getMaKM());
            pstmt.setString(3, cthd.getMaCTSP());
            pstmt.setString(4, cthd.getTenSP());
            pstmt.setDouble(5, cthd.getDonGia());
            pstmt.setString(6, cthd.getTrangThai());
            pstmt.setString(7, cthd.getMaCTHD());
            
            return pstmt.executeUpdate() > 0;
            } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean delete(String maCTHD) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE maCTHD=?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, maCTHD);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
