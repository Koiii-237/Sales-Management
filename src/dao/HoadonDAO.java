/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DBPOOL.DBConnection;
import model.HoaDon;

public class HoadonDAO {
    private static final Logger LOGGER = Logger.getLogger(HoadonDAO.class.getName());

    public boolean add(HoaDon hd) {
    if (hd == null) {
        LOGGER.log(Level.WARNING, "Hóa đơn đầu vào là null, không thể thêm.");
        return false;
    }

    String sql = "INSERT INTO hoadon (maHD, maSP, maNV, tenSP, trangThai, donGia, thoiGianThanhToan, soLuong) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, hd.getMaHD());
        stmt.setString(2, hd.getMaSP());
        stmt.setString(3, hd.getMaNV());
        stmt.setString(4, hd.getTenSP());
        stmt.setString(5, hd.getTrangThai());
        stmt.setDouble(6, hd.getDonGia());
        stmt.setString(7, hd.getThoiGianThanhToan());
        stmt.setInt(8, hd.getSoLuong());

        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, "Lỗi khi thêm hóa đơn vào cơ sở dữ liệu", e);
    }
    return false;
}


    public boolean delete(String maHD) {
        String sql = "DELETE FROM hoadon WHERE maHD=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maHD);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<HoaDon> getAll() {
        List<HoaDon> invoices = new ArrayList<>();
        String sql = "SELECT * FROM hoadon";
        try (Connection conn = DBPOOL.DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                HoaDon hd = new HoaDon(
                    rs.getString("maHD"),
                    rs.getString("maSP"),
                    rs.getString("maNV"),
                    rs.getString("tenSP"),
                    rs.getString("trangThai"),
                    rs.getDouble("donGia"),
                    rs.getString("thoiGianThanhToan"),
                    rs.getInt("soLuong")
                );
                invoices.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
    public boolean update(HoaDon hd) {
    if (hd == null) {
        LOGGER.log(Level.WARNING, "Hóa đơn đầu vào là null, không thể cập nhật.");
        return false;
    }

    String sql = "UPDATE hoadon SET maSP=?, maNV=?, tenSP=?, trangThai=?, donGia=?, thoiGianThanhToan=?, soLuong=? WHERE maHD=?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, hd.getMaSP());
        stmt.setString(2, hd.getMaNV());
        stmt.setString(3, hd.getTenSP());
        stmt.setString(4, hd.getTrangThai());
        stmt.setDouble(5, hd.getDonGia());
        stmt.setString(6, hd.getThoiGianThanhToan());
        stmt.setInt(7, hd.getSoLuong());
        stmt.setString(8, hd.getMaHD());

        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, "Lỗi khi cập nhật hóa đơn trong cơ sở dữ liệu", e);
    }
    return false;
}
    public List<HoaDon> refresh() {
    return getAll();
}
}

