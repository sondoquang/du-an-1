package controlls;

import daoImpl.HoaDonImple;
import daoImpl.KhachHangDAO;
import entities.HoaDon;
import entities.KhachHang;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import utils.XMsgBox;
import utils.XValidate;

public class ClientController {

    private static int curr = -1;
    private static final KhachHangDAO dao = new KhachHangDAO();
    public static JFrame frame;
    public static JTable table;
    public static JTextField txtMaKH;
    public static JTextField txtHoVaTen;
    public static JTextField txtSDT;
    public static JButton btnThem;
    public static JButton btnSua;
    public static JButton btnXoa;

    public static void initialize(JFrame frame, JTable table, JTextField txtMaKH, JTextField txtHoVaTen, JTextField txtSDT, JButton btnThem, JButton btnSua, JButton btnXoa) {
        ClientController.frame = frame;
        ClientController.table = table;
        ClientController.txtMaKH = txtMaKH;
        ClientController.txtHoVaTen = txtHoVaTen;
        ClientController.txtSDT = txtSDT;
        ClientController.btnThem = btnThem;
        ClientController.btnSua = btnSua;
        ClientController.btnXoa = btnXoa;
    }

    public static void getComponents(JFrame frame, JTable table, JTextField txtMaKH, JTextField txtHoVaTen, JTextField txtSDT, JButton btnThem, JButton btnSua, JButton btnXoa) {
        frame = ClientController.frame;
        table = ClientController.table;
        txtMaKH = ClientController.txtMaKH;
        txtHoVaTen = ClientController.txtHoVaTen;
        txtSDT = ClientController.txtSDT;
        btnThem = ClientController.btnThem;
        btnSua = ClientController.btnSua;
        btnXoa = ClientController.btnXoa;
    }

    public static void init() {
        fillTable();
        curr = -1;
        updateStatus();
    }

    public static void fillTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
            List<KhachHang> list = dao.selectAll();
            for (KhachHang sp : list) {
                Object[] row = {
                    sp.getMaKH(),
                    sp.getHoVaTen(),
                    sp.getSDT()
                };
                model.addRow(row);
                curr = 0;
                edit();
                table.setColumnSelectionInterval(0, 0);
            }
        } catch (Exception e) {
            XMsgBox.alert(frame, "Lỗi truy vấn dữ liệu!");
        }
    }

    public static void tableClick() {
        curr = table.getSelectedRow();
        edit();
    }

    public static void insert() {
        KhachHang model = getForm();
        if (model == null) {
            return;
        }
        try {
            dao.insert(model);
            fillTable();
            clearForm();
            XMsgBox.alert(frame, "Thêm mới thành công!");
        } catch (Exception e) {
            XMsgBox.alert(frame, "Thêm mới thất bại!");
        }

    }

    public static void update() {
        KhachHang model = getForm();
        if (model == null) {
            return;
        }
        try {
            dao.update(model);
            fillTable();
            XMsgBox.alert(frame, "Cập nhật thành công!");
        } catch (Exception e) {
            XMsgBox.alert(frame, "Cập nhật thất bại!");
        }
    }

    HoaDonImple hddao = new HoaDonImple();

    public static void delete() {
        String maKH = txtMaKH.getText();
        try {
            dao.delete(maKH);
            fillTable();
            clearForm();
            XMsgBox.alert(frame, "Xóa thành công!");
        } catch (Exception e) {
            XMsgBox.alert(frame, "Xóa thất bại!");
        }
    }

    public static void clearForm() {
        String makh = dao.createIDCustomer();
        txtMaKH.setText(makh);
        txtHoVaTen.setText("");
        txtSDT.setText("");
        curr = -1;
        table.clearSelection();
        updateStatus();
    }

    public static void edit() {
        String MaKH = (String) table.getValueAt(curr, 0);
        KhachHang cd = dao.selectByID(MaKH);
        setForm(cd);
        updateStatus();
    }

    public static void setForm(KhachHang kh) {
        txtMaKH.setText(kh.getMaKH());
        txtHoVaTen.setText(kh.getHoVaTen());
        txtSDT.setText(kh.getSDT());
    }

    public static KhachHang getForm() {
        KhachHang kh = new KhachHang();
        kh.setMaKH(txtMaKH.getText());
        kh.setHoVaTen(txtHoVaTen.getText());
        kh.setSDT(txtSDT.getText());
        if (check(kh)) {
            return kh;
        }
        return null;
    }

    public static boolean check(KhachHang kh) {
        if (kh.getMaKH().equals("")
                || kh.getHoVaTen().equals("")
                || kh.getSDT().equals("")) {
            XMsgBox.inform(null, "Vui lòng điền đủ thông tin khách hàng.");
            return false;
        } else {
            if (!XValidate.checkName(kh.getHoVaTen())) {
                XMsgBox.inform(null, "Sai định dạng tên !!");
                return false;
            }
            if (!XValidate.phoneNumber(kh.getSDT())) {
                XMsgBox.inform(null, "Sai định dạng số điện thoại");
                return false;
            }
        }
        if (dao.checkPhoneNumber(kh.getSDT()) != null) {
            XMsgBox.inform(null, "Số điện thoại đã tồn tại !!");
            return false;
        }
        return true;
    }

    private static void updateStatus() {
        boolean edit = (curr >= 0);
        txtMaKH.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);
    }
}
