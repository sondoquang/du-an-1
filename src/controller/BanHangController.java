
package controller;

import javax.swing.JLabel;


public interface BanHangController {
     void initialize(String mahd);
     void fillProductDetail(String loaisp);
     void clearForm();
     void resetBill();
     void createBill();
     void printBill();
     void insertCTHD(String mahd);
     void eventClickAddProduct();
     Double checkDiscount();
     void fillCustomerByID(String makh);
     void xuatBillKhachHang();
     void searchCustomer();
     void updateStatus(String mahd);
     void setForm(String mahd);
     void updateBills(String mahd);
     jpanelrounded.PanelRound createPanelProduct();
     JLabel createLabelProduct();
     void fillTableOrder();
     void updateNguyenLieu(String maSP, int heSo);
}
