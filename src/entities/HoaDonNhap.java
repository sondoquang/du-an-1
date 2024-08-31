
package entities;

import java.util.Date;


public class HoaDonNhap {
    private String soHD;
    private String maNV;
    private Date ngayNH = new Date() ;

    public HoaDonNhap() {
    }

    public HoaDonNhap(String soHD, String maNV, Date ngayNH) {
        this.soHD = soHD;
        this.maNV = maNV;
        this.ngayNH = ngayNH;
    }

    public String getSoHD() {
        return soHD;
    }

    public void setSoHD(String soHD) {
        this.soHD = soHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayNH() {
        return ngayNH;
    }

    public void setNgayNH(Date ngayNH) {
        this.ngayNH = ngayNH;
    }

    
    
    
}
