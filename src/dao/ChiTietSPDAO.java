/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.ChiTietSanPham;
import DBPOOL.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author ADMIN
 */
public class ChiTietSPDAO {
    public Object[] getRow(ChiTietSanPham ctsp) {
        String maCTSP = ctsp.getMaCTSP();
        String maSP = ctsp.getMaSP();
        String tenSP = ctsp.getTenSP();
        double donGia = ctsp.getDonGia();
        String ngayNhap = ctsp.getNgayNhap();
        int soLuongSanPham = ctsp.getSoLuongSanPham();

        return new Object[]{maCTSP, maSP, tenSP, donGia, ngayNhap, soLuongSanPham};
    }
    
     public List<ChiTietSanPham> getALL() {
        List<ChiTietSanPham> listCTSP = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietSanPham";
        try (Connection con = DBConnection.getConnection();  
             Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            
            while (rs.next()) {
                String maCTSP = rs.getString(1);
                String maSP = rs.getString(2);
                String tenSP = rs.getString(3);
                double donGia = rs.getDouble(4);
                String ngayNhap = rs.getString(5);
                int soLuongSanPham = rs.getInt(6);

                ChiTietSanPham ctsp = new ChiTietSanPham(maCTSP, maSP, tenSP, donGia, ngayNhap, soLuongSanPham);
                listCTSP.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCTSP;
    }
     public int insert(ChiTietSanPham ctsp) {
         String sql = "INSERT INTO ChiTietSanPham (maCTSP, maSP, tenSP, donGia, ngayNhap, soLuongSanPham) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, ctsp.getMaCTSP());
            pstmt.setString(2, ctsp.getMaSP());
            pstmt.setString(3, ctsp.getTenSP());
            pstmt.setDouble(4, ctsp.getDonGia());
            pstmt.setString(5, ctsp.getNgayNhap());
            pstmt.setInt(6, ctsp.getSoLuongSanPham());
            if (pstmt.executeUpdate() > 1) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    } 
     public int update(ChiTietSanPham ctsp) {
        String sql = "UPDATE ChiTietSanPham SET maSP=?, tenSP=?, donGia=?, ngayNhap=?, soLuongSanPham=? WHERE maCTSP=?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, ctsp.getMaSP());
            pstmt.setString(2, ctsp.getTenSP());
            pstmt.setDouble(3, ctsp.getDonGia());
            pstmt.setString(4, ctsp.getNgayNhap());
            pstmt.setInt(5, ctsp.getSoLuongSanPham());
            pstmt.setString(6, ctsp.getMaCTSP());

            if (pstmt.executeUpdate() > 1) {
                return 1;
            }
            } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
     public boolean delete(String maCTSP) {
        String sql = "DELETE FROM ChiTietSanPham WHERE maCTSP=?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, maCTSP);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
