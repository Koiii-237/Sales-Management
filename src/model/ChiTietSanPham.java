/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class ChiTietSanPham {

    private String maCTSP;
    private String maSP;
    private String tenSP;
    private String ngayNhap;
    private double donGia;
    private int soLuongSanPham;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(String maCTSP, String maSP, String tenSP, double donGia, String ngayNhap, int soLuongSanPham) {
        this.maCTSP = maCTSP;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.ngayNhap = ngayNhap;
        this.soLuongSanPham = soLuongSanPham;
    }

    public String getMaCTSP() {
        return maCTSP;
    }

    public void setMaCTSP(String maCTSP) {
        this.maCTSP = maCTSP;
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

    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }
    
    
}
