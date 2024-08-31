
package controlls;

import daoImpl.NhanVienDAO;
import daoImpl.HoaDonImple;
import entities.NhanVien;
import java.awt.Image;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import utils.XMsgBox;
import utils.XDate;
import utils.XImage;
import utils.XTable;
import utils.XValidate;

public class EmployeeController {

    public static int curr = -1;
    private static final NhanVienDAO dao = new NhanVienDAO();
    private static final HoaDonImple hddao = new HoaDonImple();
    public static JFrame frame;
    public static JTable table;
    public static JTextField txtManv;
    public static JTextField txtHoVaTen;
    public static JPasswordField txtmatkhau;
    public static JRadioButton rdoAdmin;
    public static JRadioButton rdoKho;
    public static JRadioButton rdoBan;
    public static JRadioButton rdonam;
    public static JRadioButton rdonu;
    public static ButtonGroup group1;
    public static ButtonGroup group2;
    public static JTextField txtnamsinh;
    public static JTextField txtsodienthoai_NV;
    public static JTextField txtemail;
    public static JTextArea txtdiachi_NV;
    public static JLabel lblhinh;
    public static JButton btncapnhat;
    public static JButton btnThem;
    public static JButton btndatlai;
    public static JButton btnXoa;

    public static void initialize(JFrame frame, JTable table, JTextField txtManv,
            JTextField txtHoVaTen, JPasswordField txtmatkhau,
            JRadioButton rdoAdmin, JRadioButton rdoBan, JRadioButton rdoKho, JRadioButton rdonam, JRadioButton rdonu, ButtonGroup group1, ButtonGroup group2,
            JTextField txtnamsinh, JTextField txtsodienthoai_NV, JTextField txtemail, JTextArea txtdiachi_NV, JLabel lblhinh, JButton btncapnhat, JButton btnThem,
            JButton btndatlai, JButton btnXoa) {
        EmployeeController.frame = frame;
        EmployeeController.table = table;
        EmployeeController.txtManv = txtManv;
        EmployeeController.txtHoVaTen = txtHoVaTen;
        EmployeeController.txtmatkhau = txtmatkhau;
        EmployeeController.rdoAdmin = rdoAdmin;
        EmployeeController.rdoKho = rdoKho;
        EmployeeController.rdoBan = rdoBan;
        EmployeeController.rdonam = rdonam;
        EmployeeController.rdonu = rdonu;
        EmployeeController.group1 = group1;
        EmployeeController.group2 = group2;
        EmployeeController.txtnamsinh = txtnamsinh;
        EmployeeController.txtsodienthoai_NV = txtsodienthoai_NV;
        EmployeeController.txtemail = txtemail;
        EmployeeController.txtdiachi_NV = txtdiachi_NV;
        EmployeeController.lblhinh = lblhinh;
        EmployeeController.btnThem = btnThem;
        EmployeeController.btncapnhat = btncapnhat;
        EmployeeController.btndatlai = btndatlai;
        EmployeeController.btnXoa = btnXoa;
    }

    public static void getComponents(JFrame frame, JTable table, JTextField txtManv,
            JTextField txtHoVaTen, JPasswordField txtmatkhau,
            JRadioButton rdoAdmin, JRadioButton rdoBan, JRadioButton rdoKho, JRadioButton rdonam, JRadioButton rdonu, ButtonGroup group1, ButtonGroup group2,
            JTextField txtnamsinh, JTextField txtsodienthoai_NV, JTextField txtemail, JTextArea txtdiachi_NV, JLabel lblhinh, JButton btncapnhat, JButton btnThem,
            JButton btndatlai, JButton btnXoa) {
        frame = EmployeeController.frame;
        table = EmployeeController.table;
        txtManv = EmployeeController.txtManv;
        txtHoVaTen = EmployeeController.txtHoVaTen;
        txtmatkhau = EmployeeController.txtmatkhau;
        rdoAdmin = EmployeeController.rdoAdmin;
        rdoKho = EmployeeController.rdoKho;
        rdoBan = EmployeeController.rdoBan;
        rdonam = EmployeeController.rdonam;
        rdonu = EmployeeController.rdonu;
        group1 = EmployeeController.group1;
        group2 = EmployeeController.group2;
        txtnamsinh = EmployeeController.txtnamsinh;
        txtsodienthoai_NV = EmployeeController.txtsodienthoai_NV;
        txtemail = EmployeeController.txtemail;
        txtdiachi_NV = EmployeeController.txtdiachi_NV;
        lblhinh = EmployeeController.lblhinh;
        btnThem = EmployeeController.btnThem;
        btncapnhat = EmployeeController.btncapnhat;
        btnXoa = EmployeeController.btnXoa;
        btndatlai = EmployeeController.btndatlai;
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
            List<NhanVien> list = dao.selectAll();
            for (NhanVien sp : list) {
                Object[] row = {
                    sp.getHinh(),
                    sp.getMaNV(),
                    sp.getHoVaTen(),
                    sp.getMatKhau().replaceAll(".", "*"),
                    sp.getCv().getTenCV(),
                    sp.isGioiTinh()== true?"Nam":"Nữ",
                    sp.getNgaySinh(),
                    sp.getSDT(),
                    sp.getEmail(),
                    sp.getDiaChi()
                };
                model.addRow(row);
            }
            table.setModel(model);
            curr = 0;
            edit();
            table.setRowSelectionInterval(0, 0);
            XTable.insertImage(table, 0, 100, 100, "EmpImages");
        } catch (Exception e) {
            XMsgBox.alert(frame, "Lỗi truy vấn dữ liệu!");
        }
    }

    public static void tableClick() {
        curr = table.getSelectedRow();
        edit();
    }

    public static void insert() {
        NhanVien model = getForm();
        if(model != null){
            dao.insert(model);
            fillTable();
            clearForm();
            XMsgBox.inform(frame, "Thêm mới thành công!");
        }
    }

    public static void update() {
        NhanVien model = getForm();
        if(model != null){
            dao.update(model);
            fillTable();
            XMsgBox.alert(frame, "Cập nhật thành công!");
        }
    }

    public static void delete() {
        String MaNV = txtManv.getText();
        if(hddao.selectByMaNV(MaNV)!= null){
            XMsgBox.inform(null, "Không thể xóa nhân viên này !!");
            return;
        }
        if(XMsgBox.confirm(null, "Bạn có muốn xóa nhân viên này ?")){
            
            File file = new File("EmpImages", lblhinh.getToolTipText());
            if (file.exists()) {
                file.delete();
            }
            dao.delete(MaNV);
            fillTable();
            clearForm();
            XMsgBox.alert(frame, "Xóa thành công!");
            }
    }

    public static void clearForm() {
        txtManv.setText(dao.createIDEmployees());
        txtHoVaTen.setText("");
        txtmatkhau.setText("");
        rdonam.setSelected(true);
        rdoBan.setSelected(true);
        txtnamsinh.setText("");
        txtsodienthoai_NV.setText("");
        txtemail.setText("");
        txtdiachi_NV.setText("");
        lblhinh.setText("");
        lblhinh.setToolTipText("");
        lblhinh.setIcon(null);
        curr = -1;
        //table.clearSelection();
        updateStatus();
    }

    public static void edit() {
        if (curr >= 0) {
            String MaNV = (String) table.getValueAt(curr, 1);
            NhanVien cd = dao.selectByID(MaNV);
            setForm(cd);
            updateStatus();
        }
    }

    public static void setForm(NhanVien kh) {
        txtManv.setText(kh.getMaNV());
        txtHoVaTen.setText(kh.getHoVaTen());
        txtmatkhau.setText(kh.getMatKhau());
        if (NhanVien.list.get(0).equals(kh.getCv())) {
            rdoAdmin.setSelected(true);
        } else if (NhanVien.list.get(1).equals(kh.getCv())) {
            rdoBan.setSelected(true);
        } else if (NhanVien.list.get(2).equals(kh.getCv())) {
            rdoKho.setSelected(true);
        }
        if (kh.isGioiTinh()) {
            rdonam.setSelected(true);
        } else {
            rdonu.setSelected(true);
        }
        txtnamsinh.setText(XDate.toString(kh.getNgaySinh(), "dd/MM/yyyy"));
        txtsodienthoai_NV.setText(kh.getSDT());
        txtemail.setText(kh.getEmail());
        txtdiachi_NV.setText(kh.getDiaChi());
        File file = new File("EmpImages", kh.getHinh());
        if (!file.exists()) {
            lblhinh.setText(kh.getHinh());
            lblhinh.setIcon(null);
        } else {
            lblhinh.setIcon(XImage.getResized(XImage.read("EmpImages", kh.getHinh()), lblhinh.getWidth(), lblhinh.getHeight()));
            lblhinh.setToolTipText(kh.getHinh());
        }
    }

    public static NhanVien getForm() {
        NhanVien sp = new NhanVien();
        sp.setMaNV(txtManv.getText());
        sp.setMatKhau(new String(txtmatkhau.getPassword()));
        sp.setHoVaTen(txtHoVaTen.getText());
        int i = -1;
        if (rdoAdmin.isSelected()) {
            i = 0;
        } else if (rdoBan.isSelected()) {
            i = 1;
        } else if (rdoKho.isSelected()) {
            i = 2;
        }
        sp.setCv(NhanVien.list.get(i));
        sp.setGioiTinh(rdonam.isSelected());
        try {
            sp.setNgaySinh(txtnamsinh.getText().equals("")?XDate.addDays(new Date(), -6570):XDate.toDate(txtnamsinh.getText(), "dd/MM/yyyy"));
        } catch (Exception e) {
            XMsgBox.inform(null, "Ngày sinh không hợp lệ !");
            return null;
        }
        
        sp.setSDT(txtsodienthoai_NV.getText());
        sp.setEmail(txtemail.getText());
        sp.setDiaChi(txtdiachi_NV.getText());
        sp.setHinh(lblhinh.getToolTipText());
        if(check(sp)){
            return sp;
        }
        return null;
    }
    
    private static boolean check(NhanVien nv){
        if(nv.getHoVaTen().equals("") ||
                nv.getMatKhau().equals("") ||
                nv.getDiaChi().equals("") ||
                nv.getSDT().equals("")||
                nv.getEmail().equals("") ||
                nv.getHinh().equals("")){
            XMsgBox.inform(null, "Vui lòng điền đủ thông tin !!");
            return false;
        }
        if(!XDate.check(XDate.toString(nv.getNgaySinh(), "MM/dd/yyyy"),XDate.toString(new Date(), "MM/dd/yyyy") , "MM/dd/yyyy")){
            XMsgBox.inform(null, "Ngày sinh không hợp lệ !!");
            return false;
        }
        if(!XValidate.email(nv.getEmail())){
            XMsgBox.inform(null, "Sai định dạng email !!");
            return false;
        }
        if(!XValidate.phoneNumber(nv.getSDT())){
            XMsgBox.inform(null, "Sai định dạng số điện thoại !!");
            return false;
        }
        if(!XValidate.checkName(nv.getHoVaTen())){
            XMsgBox.inform(null, "Sai định dạng tên !!");
            return false;
        }
        return true;
    }

    private static void updateStatus() {
        boolean edit = (curr >= 0);

        // Trạng thái form
        txtManv.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btncapnhat.setEnabled(edit);
        btnXoa.setEnabled(edit);
    }

   public static void setImageProduct() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            XImage.save("EmpImages", file);
            EmployeeController.prepareSetImage(file.getName());
        }
    }
   public static void prepareSetImage(String nameFile) {
        try {
            ImageIcon Icon = XImage.read("EmpImages", nameFile);
            lblhinh.setIcon(Icon);
            Image im = Icon.getImage().getScaledInstance(lblhinh.getWidth(), lblhinh.getHeight(), Image.SCALE_SMOOTH);
            Icon.setImage(im);
            lblhinh.setToolTipText(nameFile);
        } catch (Exception e) {
            XMsgBox.confirm(null, "Ảnh không có sẵn .");
        }
    }
}
