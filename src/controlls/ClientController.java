package controlls;

import button.ButtonCustom;
import customjtable.CustomJTable;
import daoImpl.HoaDonDAO;
import daoImpl.KhachHangDAO;
import entities.HoaDon;
import entities.KhachHang;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import utils.XMsgBox;
import utils.XValidate;

public class ClientController {
    
    private static final KhachHangDAO dao = new KhachHangDAO();
    private static final HoaDonDAO hddao = new HoaDonDAO();
    public static JFrame frame;
    private static ButtonCustom btnReset;
    private static ButtonCustom btnSua;
    private static ButtonCustom btnThemKhachHang;
    private static ButtonCustom btnXoa;
    private static CustomJTable tblHoaDon1;
    private static CustomJTable tblKhachHang;
    private static JTextField txtMaKH;
    private static JTextField txtSoDT;
    private static JTextField txtTenKH;
    
    public static String makh;
    public static String mahd;

    public static void initialize(JFrame frame, ButtonCustom btnReset, ButtonCustom btnSua, ButtonCustom btnThemKhachHang,ButtonCustom btnXoa,CustomJTable tblHoaDon1,CustomJTable tblKhachHang,JTextField txtMaKH,JTextField txtSoDT,JTextField txtTenKH) {
        ClientController.frame = frame;
        ClientController.btnReset = btnReset;
        ClientController.btnSua = btnSua;
        ClientController.btnThemKhachHang = btnThemKhachHang;
        ClientController.btnXoa = btnXoa;
        ClientController.tblHoaDon1 = tblHoaDon1;
        ClientController.tblKhachHang = tblKhachHang;
        ClientController.txtMaKH = txtMaKH;
        ClientController.txtSoDT = txtSoDT;
        ClientController.txtTenKH = txtTenKH;   
    }

    public static void getComponents(JFrame frame, ButtonCustom btnReset, ButtonCustom btnSua, ButtonCustom btnThemKhachHang,ButtonCustom btnXoa,CustomJTable tblHoaDon1,CustomJTable tblKhachHang,JTextField txtMaKH,JTextField txtSoDT,JTextField txtTenKH) {
        frame = ClientController.frame;
        btnReset = ClientController.btnReset;
        btnSua = ClientController.btnSua;
        btnThemKhachHang = ClientController.btnThemKhachHang;
        btnXoa = ClientController.btnXoa;
        tblHoaDon1 = ClientController.tblHoaDon1;
        tblKhachHang = ClientController.tblKhachHang;
        txtMaKH = ClientController.txtMaKH;
        txtSoDT = ClientController.txtSoDT;
        txtTenKH = ClientController.txtTenKH;
    }

    
    public static void init() {
        fillTableCustomer("", 1);
        fillTableBills();
        if(SellController.soDT != null){
            txtSoDT.setText(SellController.soDT);
            txtMaKH.setText(dao.createIDCustomer());
        }
    }
    
    
    
    public static void fillTableCustomer(String values ,int maTK) {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        try {
            List<KhachHang> list = dao.selectByIDs(values, maTK);
            for(KhachHang kh:list){
                Object [] row= {
                    kh.getMaKH(),
                    kh.getHoVaTen(),
                    kh.getSDT()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            XMsgBox.alert(frame, "lỗi truy vấn dữ liệu !!");
        }
    }
    
    
    
    public static void fillTableBills() {
        DefaultTableModel model  = (DefaultTableModel) tblHoaDon1.getModel();
        model.setRowCount(0);
        List<HoaDon> list  = hddao.selectAll();
        try {
            for(HoaDon hd: list){
                Object [] row  ={
                    hd.getMaHD(),
                    hd.getMaKH() == null?"Khách vãn lai":hd.getMaKH(),
                    hd.getMaNV(),
                    hd.getNgayMua(),
                    hd.getTriGia()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            XMsgBox.alert(frame, "lỗi truy vấn dữ liệu !!");
        }
    }

    
    public static void searchBillCustomer(String MaKH) {
        DefaultTableModel model  = (DefaultTableModel) tblHoaDon1.getModel();
        model.setRowCount(0);
        try {
            List<HoaDon> list = hddao.selectByID1(MaKH);
            for(HoaDon hd: list){
                Object [] row  ={
                    hd.getMaHD(),
                    hd.getMaKH() == null?"Khách vãng lai":hd.getMaKH(),
                    hd.getMaNV(),
                    hd.getNgayMua(),
                    hd.getTriGia()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            XMsgBox.alert(frame, "Lỗi truy vấn dữ liệu !");
        }
    }

    
    public static void fillInfoCustomer() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        String maKH = model.getValueAt(tblKhachHang.getSelectedRow(), 0)+"";
        String tenKH = model.getValueAt(tblKhachHang.getSelectedRow(), 1)+"";
        String soDT = model.getValueAt(tblKhachHang.getSelectedRow(), 2)+"";
        txtSoDT.setText(soDT);
        txtMaKH.setText(maKH);
        txtTenKH.setText(tenKH);
        searchBillCustomer(maKH);
    }

    
    public static void insertCustomer() {
        KhachHang kh = new KhachHang();
        kh.setHoVaTen(txtTenKH.getText());
        kh.setSDT(txtSoDT.getText());
        if(XValidate.checkName(kh.getHoVaTen()) != true){
            XMsgBox.alert(frame, "Tên không đúng tiêu chuẩn.");
        } else {
            if(XValidate.phoneNumber(kh.getSDT())){
                if(dao.selectByIDs(kh.getSDT(),0) == null){
                    try {
                        dao.insert(kh);
                        makh = txtMaKH.getText();
                        resetForm();
                        fillTableCustomer("", 1);
                        XMsgBox.alert(frame, "Thêm khách hàng thành công.");
                        frame.dispose();
                    } catch (Exception e) {
                        XMsgBox.alert(frame, "Thêm khách hàng thất bại !!");
                    }
                }else{
                    XMsgBox.alert(frame, "Só điện thoại đã tồn tại !!");
                }
            }else{
                XMsgBox.alert(frame, "Sai định dạng số điện thoại !!");
            }
        }
    }

    
    public static void updateInfoCustomer() {
        KhachHang kh = new KhachHang();
        kh.setMaKH(txtMaKH.getText());
        kh.setHoVaTen(txtTenKH.getText());
        kh.setSDT(txtSoDT.getText());
        if(XValidate.checkName(kh.getHoVaTen()) != true){
            XMsgBox.alert(frame, "Tên không đúng tiêu chuẩn.");
        } else {
            if(XValidate.phoneNumber(kh.getSDT())){
                if(dao.selectByIDs(kh.getSDT(),0) == null){
                    try {
                        dao.update(kh);
                        resetForm();
                        fillTableCustomer("", 1);
                        XMsgBox.alert(frame, "Cập nhật khách hàng thành công.");
                    } catch (Exception e) {
                        XMsgBox.alert(frame, "Cập nhật khách hàng thất bại !!");
                    }
                }else{
                    XMsgBox.alert(frame, "Só điện thoại đã tồn tại !!");
                }
            }else{
                XMsgBox.alert(frame, "Sai định dạng số điện thoại !!");
            }
        }
    }

    
    public static void deleteCustomer(String makh) {
        try {
            dao.delete(makh);
            resetForm();
            fillTableCustomer("", 1);
            XMsgBox.alert(frame, "Xóa khách hàng thành công.");
        } catch (Exception e) {
            XMsgBox.alert(frame, "Xóa khách hàng thất bại !!");
        }
    }

    
    public static void resetForm() {
        txtSoDT.setText("");
        txtTenKH.setText("");
        txtMaKH.setText(dao.createIDCustomer());
        txtSoDT.requestFocus();
        fillTableBills();
        fillTableCustomer("", 1);
    }
}
