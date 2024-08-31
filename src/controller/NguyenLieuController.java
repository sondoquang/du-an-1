
package controller;
public interface NguyenLieuController {
    void initialize();
    void fillTableNguyenLieu();
    void fillTableThanhPhan(String maNL, Integer soLuong);
    void setImageProduct();
    void searchNL();
    void deleteNL();
    void insertSanPham();
    void insertCTSanPham(String masp);
    void resetForm();
    void update();
    void setForm();
    boolean checkEmpty();
    int checkIndexProduct(String manl);
    void updateStatus();
}
