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
import java.util.List;
import model.ChiTietHoaDon;
/**
 *
 * @author ADMIN
 */
public class ChiTietHDDAO {
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
    public List<ChiTietHoaDon> getALL() {
        List<ChiTietHoaDon> listCTHD = new ArrayList<>();
        String sql = "SELECT * FROM CHITIETHOADON";
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
    public boolean insert(ChiTietHoaDon cthd) {
        String sql = "INSERT INTO CHITIETHOADON (maCTHD, maHD, maKM, maCTSP, tenSP, donGia, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();  
             PreparedStatemen
            
            
}
