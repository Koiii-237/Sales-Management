/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class HoaDon {
    private String maHD;
    private String maSP;
    private String maNV;
    private String tenSP;
    private String trangThai;
    private double donGia;
    private String thoiGianThanhToan;
    private int soLuong;

    public HoaDon() {
    }

    public HoaDon(String maHD, String maSP, String maNV, String tenSP, String trangThai, double donGia, String thoiGianThanhToan, int soLuong) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.maNV = maNV;
        this.tenSP = tenSP;
        this.trangThai = trangThai;
        this.donGia = donGia;
        this.thoiGianThanhToan = thoiGianThanhToan;
        this.soLuong = soLuong;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getThoiGianThanhToan() {
        return thoiGianThanhToan;
    }

    public void setThoiGianThanhToan(String thoiGianThanhToan) {
        this.thoiGianThanhToan = thoiGianThanhToan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    
}
