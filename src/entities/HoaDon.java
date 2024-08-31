
package entities;

import java.util.Date;

public class HoaDon {
    private String maHD ;
    private String maKH;
    private String maNV;
    private Date ngayMua = new Date();
    private Double tongTien;
    private Double giamGia;
    private Double triGia;
    private String trangThai;

    public HoaDon() {
    }

    public HoaDon(String maHD, String maKH, String maNV, Double tongTien, Double giamGia, Double triGia, String trangThai) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.tongTien = tongTien;
        this.giamGia = giamGia;
        this.triGia = triGia;
        this.trangThai = trangThai;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public Double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(Double giamGia) {
        this.giamGia = giamGia;
    }

    public Double getTriGia() {
        return triGia;
    }

    public void setTriGia(Double triGia) {
        this.triGia = triGia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    
    
    
}
