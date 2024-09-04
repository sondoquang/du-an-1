
package controlls;

import button.ButtonCustom;
import customjtable.CustomJTable;
import daoImpl.HoaDonImple;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SellController {
    private static JFrame frame;
    private static ButtonCustom btnAll;
    private static ButtonCustom btnCafe;
    private static ButtonCustom btnInHoaDon;
    private static ButtonCustom btnMilkTea;
    private static ButtonCustom btnTaoKH;
    private static ButtonCustom btnTea;
    private static ButtonCustom btnTimKiemKH;
    private static ButtonCustom btnXoa;
    private static JButton btnTimKiem;
    private static JComboBox cboKhachHang;
    private static JRadioButton rdoChuaThanhToan;
    private static JRadioButton rdoThanhToan;
    private static CustomJTable tblOder;
    private static JTextField txtDiscount;
    private static JTextField txtGiamGia;
    private static JTextField txtPrice;
    private static JTextField txtSDT;
    private static JTextField txtTienNhan;
    private static JTextField txtTienThoi;
    private static JTextField txtTimKiem;
    
    private static Double giaGoc = 0.0;
    private static Double discountGoc = 0.0;
    HoaDonImple hdndao = new HoaDonImple();
    String maHD;
    String maHDUD;
    
    public static void initialize(JFrame frame, 
            ButtonCustom btnAll, ButtonCustom btnCafe, ButtonCustom btnInHoaDon, ButtonCustom btnMilkTea, ButtonCustom btnTaoKH, ButtonCustom btnTea,
            ButtonCustom btnTimKiemKH,ButtonCustom btnXoa, JButton btnTimKiem, JComboBox cboKhachHang ,JRadioButton rdoChuaThanhToan, JRadioButton rdoThanhToan,
            CustomJTable tblOder , JTextField txtDiscount, JTextField txtGiamGia,JTextField txtPrice, JTextField txtSDT, JTextField txtTienNhan,
            JTextField txtTienThoi, JTextField txtTimKiem) {
        SellController.frame = frame;
        SellController.btnAll = btnAll;
        SellController.btnCafe = btnCafe;
        SellController.btnInHoaDon = btnInHoaDon;
        SellController.btnMilkTea = btnMilkTea;
        SellController.btnTaoKH = btnTaoKH;
        SellController.btnTea = btnTea;
        SellController.btnTimKiem = btnTimKiem;
        SellController.btnTimKiemKH = btnTimKiemKH;
        SellController.btnXoa = btnXoa;
        SellController.cboKhachHang = cboKhachHang;
        SellController.rdoChuaThanhToan = rdoChuaThanhToan;
        SellController.rdoThanhToan = rdoThanhToan;
        SellController.tblOder = tblOder;
        SellController.btnMilkTea = btnMilkTea;
        SellController.btnTaoKH = btnTaoKH;
        SellController.txtDiscount = txtDiscount;
        SellController.txtGiamGia = txtGiamGia;
        SellController.txtPrice = txtPrice;
        SellController.txtSDT = txtSDT;
        SellController.txtTienNhan = txtTienNhan;
        SellController.txtTienThoi = txtTienThoi;
        SellController.txtTimKiem = txtTimKiem;
        
    }

    public static void getComponents(JFrame frame, 
            ButtonCustom btnAll, ButtonCustom btnCafe, ButtonCustom btnInHoaDon, ButtonCustom btnMilkTea, ButtonCustom btnTaoKH, ButtonCustom btnTea,
            ButtonCustom btnTimKiemKH,ButtonCustom btnXoa, JButton btnTimKiem, JComboBox cboKhachHang ,JRadioButton rdoChuaThanhToan, JRadioButton rdoThanhToan,
            CustomJTable tblOder , JTextField txtDiscount, JTextField txtGiamGia,JTextField txtPrice, JTextField txtSDT, JTextField txtTienNhan,
            JTextField txtTienThoi, JTextField txtTimKiem) {
        frame = SellController.frame;
        btnAll = SellController.btnAll;
        btnCafe = SellController.btnCafe;
        btnInHoaDon = SellController.btnInHoaDon;
        btnMilkTea = SellController.btnMilkTea;
        btnTaoKH = SellController.btnTaoKH;
        btnTea = SellController.btnTea;
        btnTimKiem = SellController.btnTimKiem;
        btnTimKiemKH = SellController.btnTimKiemKH;
        btnXoa = SellController.btnXoa;
        cboKhachHang = SellController.cboKhachHang;
        rdoChuaThanhToan = SellController.rdoChuaThanhToan;
        rdoThanhToan = SellController.rdoThanhToan;
        tblOder = SellController.tblOder;
        btnMilkTea = SellController.btnMilkTea;
        btnTaoKH = SellController.btnTaoKH;
        txtDiscount = SellController.txtDiscount;
        txtGiamGia = SellController.txtGiamGia;
        txtPrice = SellController.txtPrice;
        txtSDT = SellController.txtSDT;
        txtTienNhan = SellController.txtTienNhan;
        txtTienThoi = SellController.txtTienThoi;
        txtTimKiem = SellController.txtTimKiem;
    }
    
}
