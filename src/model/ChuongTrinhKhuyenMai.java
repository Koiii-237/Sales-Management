/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class ChuongTrinhKhuyenMai {

    private String maKM;
    private double phanTramKM;
    private String thoiGianBatDau;
    private String thoiGianKetThuc;
    private String maSP;

    public ChuongTrinhKhuyenMai() {
    }

    public ChuongTrinhKhuyenMai(String maKM, double phanTramKM, String thoiGianBatDau, String thoiGianKetThuc, String maSP) {
        this.maKM = maKM;
        this.phanTramKM = phanTramKM;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.maSP = maSP;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public double getPhanTramKM() {
        return phanTramKM;
    }

    public void setPhanTramKM(double phanTramKM) {
        this.phanTramKM = phanTramKM;
    }

    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public String getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(String thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }
    
    
}
