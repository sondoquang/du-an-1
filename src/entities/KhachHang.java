/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Date;

/**
 *
 *  Id VARCHAR(20) NOT NULL,
 Name NVARCHAR(50) NOT NULL,
 Price FLOAT NOT NULL,
 CreateDate DATE NOT NULL,
 Image NVARCHAR(50) NOT NULL,
 PRIMARY KEY(Id)
);
 * @author Computer
 */
public class KhachHang {
    private String MaKH;
    private String HoVaTen;
    private String SDT;

    public KhachHang() {
    }

    public KhachHang(String MaKH, String HoVaTen, String SDT) {
        this.MaKH = MaKH;
        this.HoVaTen = HoVaTen;
        this.SDT = SDT;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getHoVaTen() {
        return HoVaTen;
    }

    public void setHoVaTen(String HoVaTen) {
        this.HoVaTen = HoVaTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
    
    @Override
    public String toString() {
        return  this.MaKH + " | " + this.HoVaTen;
    }

    @Override
    public boolean equals(Object obj) {
        KhachHang other = (KhachHang) obj;
        if(other == null){
            return false;
        }
        return other.getMaKH().equals(this.getMaKH());
    }
    
}
