
package entities;


public class SanPham {
    private String maSP;
    private String loaiSP;
    private String tenSP;
    private Double giaTien;
    private String hinh;

    public SanPham() {
    }

    public SanPham(String maSP, String loaiSP, String tenSP, Double giaTien, int soLuong, String hinh) {
        this.maSP = maSP;
        this.loaiSP = loaiSP;
        this.tenSP = tenSP;
        this.giaTien = giaTien;
        this.hinh = hinh;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public Double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Double giaTien) {
        this.giaTien = giaTien;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }


    @Override
    public String toString() {
        return this.tenSP + " | " + this.giaTien;
    }

    @Override
    public boolean equals(Object obj) {
        SanPham sp = (SanPham) obj;
        if(sp == null){
            return false;
        }
        return sp.maSP.equals(this.maSP);
    }
    
    
}
