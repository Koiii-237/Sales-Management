/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.ChuongTrinhKhuyenMai;
import DBPOOL.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author ADMIN
 */
public class ChuongTrinhKMDAO {
    public Object[] getRow(ChuongTrinhKhuyenMai ctkm) {
        String maKM = ctkm.getMaKM();
        double phanTramKM = ctkm.getPhanTramKM();
        String thoiGianBatDau = ctkm.getThoiGianBatDau();
        String thoiGianKetThuc = ctkm.getThoiGianKetThuc();
        String maSP = ctkm.getMaSP();

        return new Object[]{maKM, phanTramKM, thoiGianBatDau, thoiGianKetThuc, maSP};
    }
     public List<ChuongTrinhKhuyenMai> getALL() {
        List<ChuongTrinhKhuyenMai> listCTKM = new ArrayList<>();
        String sql = "SELECT * FROM ChuongTrinhKhuyenMai";
        try (Connection con = DBConnection.getConnection();  
             Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            
            while (rs.next()) {
                String maKM = rs.getString(1);
                double phanTramKM = rs.getDouble(2);
                String thoiGianBatDau = rs.getString(3);
                String thoiGianKetThuc = rs.getString(4);
                String maSP = rs.getString(5);

                ChuongTrinhKhuyenMai ctkm = new ChuongTrinhKhuyenMai(maKM, phanTramKM, thoiGianBatDau, thoiGianKetThuc, maSP);
                listCTKM.add(ctkm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCTKM;
    }
     public int insert(ChuongTrinhKhuyenMai ctkm) {
         String sql = "INSERT INTO ChuongTrinhKhuyenMai (maKM, phanTramKM, thoiGianBatDau, thoiGianKetThuc, maSP) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, ctkm.getMaKM());
            pstmt.setDouble(2, ctkm.getPhanTramKM());
            pstmt.setString(3, ctkm.getThoiGianBatDau());
            pstmt.setString(4, ctkm.getThoiGianKetThuc());
            pstmt.setString(5, ctkm.getMaSP());
            if (pstmt.executeUpdate() > 1) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return 0;
    } 
     public int update(ChuongTrinhKhuyenMai ctkm) {
        String sql = "UPDATE ChuongTrinhKhuyenMai SET phanTramKM=?, thoiGianBatDau=?, thoiGianKetThuc=?, maSP=? WHERE maKM=?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setDouble(1, ctkm.getPhanTramKM());
            pstmt.setString(2, ctkm.getThoiGianBatDau());
            pstmt.setString(3, ctkm.getThoiGianKetThuc());
            pstmt.setString(4, ctkm.getMaSP());
            pstmt.setString(5, ctkm.getMaKM());

             if (pstmt.executeUpdate() > 1) {
                return 1;
            }
            } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
     public boolean delete(String maKM) {
        String sql = "DELETE FROM ChuongTrinhKhuyenMai WHERE maKM=?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, maKM);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
