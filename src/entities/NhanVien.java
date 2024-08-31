/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import daoImpl.ChucVuDAO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ndhlt
 */
public class NhanVien {
    private String MaNV = "nvbanhang";
    private String MatKhau;
    private String HoVaTen;
    private ChucVu cv;
    private Date NgaySinh;
    private boolean GioiTinh;
    private String DiaChi;
    private String SDT;
    private String Email;
    private String Hinh;
    
    public static List<ChucVu> list = new ChucVuDAO().selectAll();

    public NhanVien(String MaNV, String MatKhau, String HoVaTen, ChucVu cv, Date NgaySinh, boolean GioiTinh, String DiaChi, String SDT, String Email, String Hinh) {
        this.MaNV = MaNV;
        this.MatKhau = MatKhau;
        this.HoVaTen = HoVaTen;
        this.cv = cv;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.Hinh = Hinh;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getHoVaTen() {
        return HoVaTen;
    }

    public void setHoVaTen(String HoVaTen) {
        this.HoVaTen = HoVaTen;
    }

    public ChucVu getCv() {
        return cv;
    }

    public void setCv(ChucVu cv) {
        this.cv = cv;
    }


    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }
    public NhanVien() {
    }
    
    
    public ChucVu getCVbyName(String tencv){
        for(ChucVu cv: list){
            if(cv.getTenCV().equals(tencv)){
                return cv;
            }
        }
        return null;
    }
    
    public ChucVu getCVbyID(String macv){
        for(ChucVu cv: list){
            if(cv.getMaCV().equals(macv)){
                return cv;
            }
        }
        return null;
    }
}
