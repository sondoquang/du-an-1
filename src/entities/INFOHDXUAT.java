
package entities;

import java.util.Date;

public class INFOHDXUAT {
    private String soHDN;
    private String maNV;
    private Date ngay = new Date();
    private String maNl;
    private String tenNl;
    private Double Gia;
    private int soLuong;
    private String donvi;

    public INFOHDXUAT() {
    }

    public INFOHDXUAT(String soHDN, String maNV, Date ngay, String maNl, String tenNl, Double Gia, int soLuong, String donvi) {
        this.soHDN = soHDN;
        this.maNV = maNV;
        this.ngay = ngay;
        this.maNl = maNl;
        this.tenNl = tenNl;
        this.Gia = Gia;
        this.soLuong = soLuong;
        this.donvi = donvi;
    }

    public String getSoHDN() {
        return soHDN;
    }

    public void setSoHDN(String soHDN) {
        this.soHDN = soHDN;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getMaNl() {
        return maNl;
    }

    public void setMaNl(String maNl) {
        this.maNl = maNl;
    }

    public String getTenNl() {
        return tenNl;
    }

    public void setTenNl(String tenNl) {
        this.tenNl = tenNl;
    }

    public Double getGia() {
        return Gia;
    }

    public void setGia(Double Gia) {
        this.Gia = Gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }
    
}
