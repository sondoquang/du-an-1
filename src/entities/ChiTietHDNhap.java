
package entities;


public class ChiTietHDNhap {
    private String soHDN;
    private String maNL;
    private int soLuong;
    private String ghiChu;

    public ChiTietHDNhap(String soHDN, String maNL, int soLuong, String ghiChu) {
        this.soHDN = soHDN;
        this.maNL = maNL;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }

    public ChiTietHDNhap() {
    }

    public String getSoHDN() {
        return soHDN;
    }

    public void setSoHDN(String soHDN) {
        this.soHDN = soHDN;
    }

    public String getMaNL() {
        return maNL;
    }

    public void setMaNL(String maNL) {
        this.maNL = maNL;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}