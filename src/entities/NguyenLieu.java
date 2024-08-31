
package entities;

public class NguyenLieu {
    private String maNL;
    private String tenNL;
    private Double GiaTien;
    private Integer tonKho;
    private Integer toiThieu;
    private String donVi;
    private String hinh;
    private Double minGia;

    public NguyenLieu(String maNL, String tenNL, Double GiaTien, Integer tonKho, Integer toiThieu, String donVi, String hinh, Double minGia) {
        this.maNL = maNL;
        this.tenNL = tenNL;
        this.GiaTien = GiaTien;
        this.tonKho = tonKho;
        this.toiThieu = toiThieu;
        this.donVi = donVi;
        this.hinh = hinh;
        this.minGia = minGia;
    }

    public NguyenLieu() {
    }

    public String getMaNL() {
        return maNL;
    }

    public void setMaNL(String maNL) {
        this.maNL = maNL;
    }

    public String getTenNL() {
        return tenNL;
    }

    public void setTenNL(String tenNL) {
        this.tenNL = tenNL;
    }

    public Double getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(Double GiaTien) {
        this.GiaTien = GiaTien;
    }

    public Integer getTonKho() {
        return tonKho;
    }

    public void setTonKho(Integer tonKho) {
        this.tonKho = tonKho;
    }

    public Integer getToiThieu() {
        return toiThieu;
    }

    public void setToiThieu(Integer toiThieu) {
        this.toiThieu = toiThieu;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public Double getMinGia() {
        return minGia;
    }

    public void setMinGia(Double minGia) {
        this.minGia = minGia;
    }

    
    
}
