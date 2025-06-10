/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class ChiTietHoaDon {

    private String maCTHD;
    private String maHD;
    private String maKM;
    private String maCTSP;
    private String tenSP;
    private double donGia;
    private String trangThai;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String maCTHD, String maHD, String maKM, String maCTSP, String tenSP, double donGia, String trangThai) {
        this.maCTHD = maCTHD;
        this.maHD = maHD;
        this.maKM = maKM;
        this.maCTSP = maCTSP;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }

    public String getMaCTHD() {
        return maCTHD;
    }

    public void setMaCTHD(String maCTHD) {
        this.maCTHD = maCTHD;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getMaCTSP() {
        return maCTSP;
    }

    public void setMaCTSP(String maCTSP) {
        this.maCTSP = maCTSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
