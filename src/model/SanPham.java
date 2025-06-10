/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class SanPham {
    private String maSP;
    private String tenSP;
    private double donGia;
    private String ngayNhap;
    private String maMENU;

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, double donGia, String ngayNhap, String maMENU) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.ngayNhap = ngayNhap;
        this.maMENU = maMENU;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
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

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getMaMENU() {
        return maMENU;
    }

    public void setMaMENU(String maMENU) {
        this.maMENU = maMENU;
    }
    
    
}
