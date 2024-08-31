
package controller;

public interface KhachHangController {
    void initialize(String sdt);
    void fillTableCustomer(String values , int maTK);
    void fillTableBills();
    void searchBillCustomer(String MaKH);
    void fillInfoCustomer();
    void insertCustomer();
    void updateInfoCustomer();
    void deleteCustomer(String makh);
    void resetForm();   
}
