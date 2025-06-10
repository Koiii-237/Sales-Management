/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class MENU {
    private String maMENU;
    private String tenSP;
    private double donGia;
    private String maCTSP;

    public MENU() {
    }

    public MENU(String maMENU, String tenSP, double donGia, String maCTSP) {
        this.maMENU = maMENU;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.maCTSP = maCTSP;
    }

    public String getMaMENU() {
        return maMENU;
    }

    public void setMaMENU(String maMENU) {
        this.maMENU = maMENU;
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

    public String getMaCTSP() {
        return maCTSP;
    }

    public void setMaCTSP(String maCTSP) {
        this.maCTSP = maCTSP;
    }
    
    
}
