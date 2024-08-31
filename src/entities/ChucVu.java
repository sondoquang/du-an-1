/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author tai30
 */
public class ChucVu {
    private String MaCV;
    private String TenCV;

    public String getMaCV() {
        return MaCV;
    }

    public void setMaCV(String MaCV) {
        this.MaCV = MaCV;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String TenCV) {
        this.TenCV = TenCV;
    }
    
    public boolean equals(ChucVu e){
        return this.MaCV.equals(e.getMaCV())&&
                this.TenCV.equals(e.getTenCV());
    }
    
}
