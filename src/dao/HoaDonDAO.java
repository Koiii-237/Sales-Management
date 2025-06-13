/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import DBPOOL.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author ADMIN
 */
public class HoaDonDAO {
    public Object[] getRow(HoaDon hd) {
        String maHD = hd.getMaHD();
        String maSP = hd.getMaSP();
        String maNV = hd.getMaNV();
        String tenSP = hd.getTenSP();
        String trangThai = hd.getTrangThai();
        double donGia = hd.getDonGia();
        String thoiGianThanhToan = hd.getThoiGianThanhToan();
        int soLuong = hd.getSoLuong();

        return new Object[]{maHD, maSP, maNV, tenSP, trangThai, donGia, thoiGianThanhToan, soLuong};
    }
    public List<HoaDon> getALL() {
        List<HoaDon> listHD = new ArrayList<>();
        String sql = "SELECT * FROM HOADON";
        try (Connection con = DBConnection.getConnection();  
             Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            
            while (rs.next()) {
                String maHD = rs.getString(1);
                String maSP = rs.getString(2);
                String maNV = rs.getString(3);
                String tenSP = rs.getString(4);
                String trangThai = rs.getString(5);
                double donGia = rs.getDouble(6);
                String thoiGianThanhToan = rs.getString(7);
                int soLuong = rs.getInt(8);

                HoaDon hd = new HoaDon(maHD, maSP, maNV, tenSP, trangThai, donGia, thoiGianThanhToan, soLuong);
                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }
    public boolean insert(HoaDon hd) {
         String sql = "INSERT INTO HOADON (maHD, maSP, maNV, tenSP, trangThai, donGia, thoiGianThanhToan, soLuong) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, hd.getMaHD());
            pstmt.setString(2, hd.getMaSP());
            pstmt.setString(3, hd.getMaNV());
            pstmt.setString(4, hd.getTenSP());
            pstmt.setString(5, hd.getTrangThai());
            pstmt.setDouble(6, hd.getDonGia());
            pstmt.setString(7, hd.getThoiGianThanhToan());
            pstmt.setInt(8, hd.getSoLuong());
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean update(HoaDon hd) {
        String sql = "UPDATE HOADON SET maSP=?, maNV=?, tenSP=?, trangThai=?, donGia=?, thoiGianThanhToan=?, soLuong=? WHERE maHD=?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, hd.getMaSP());
            pstmt.setString(2, hd.getMaNV());
            pstmt.setString(3, hd.getTenSP());
            pstmt.setString(4, hd.getTrangThai());
            pstmt.setDouble(5, hd.getDonGia());
            pstmt.setString(6, hd.getThoiGianThanhToan());
            pstmt.setInt(7, hd.getSoLuong());
            pstmt.setString(8, hd.getMaHD());
            
            return pstmt.executeUpdate() > 0;
            } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean delete(String maHD) {
        String sql = "DELETE FROM HOADON WHERE maHD=?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, maHD);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
