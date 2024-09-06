
package controlls;

import button.ButtonCustom;
import customjtable.CustomJTable;
import daoImpl.ChiTietSanPhamDAO;
import daoImpl.NguyenLieuDAO;
import daoimpl.SanPhamDAO;
import entities.ChiTietSanPham;
import entities.NguyenLieu;
import entities.SanPham;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import utils.XAuth;
import utils.XImage;
import utils.XMsgBox;
import utils.XTable;
import utils.XValidate;

public class ProcessController {
    private static JFrame frame;
    private static ButtonCustom btnThemSP;
    private static ButtonCustom btnUpdate;
    private static ButtonCustom btnreset;
    private static JComboBox cboLoaiSP;
    private static JLabel lblImage;
    private static JTextField txtGiaBan;
    private static JTextField txtGiaVon;
    private static JTextPane txtMaSP;
    private static JTextField txtSearch;
    private static JTextField txtTenSP;
    private static CustomJTable tblNguyenLieu;
    private static CustomJTable tblThanhPhan;
    
    private static final NguyenLieuDAO nldao = new NguyenLieuDAO();
    private static final SanPhamDAO spdao = new SanPhamDAO();
    private static final ChiTietSanPhamDAO ctspdao = new ChiTietSanPhamDAO();
    private static String maSPUD;
    private static String maSP;
    
    public static void initialize(JFrame frame,ButtonCustom btnThemSP,ButtonCustom btnUpdate,ButtonCustom btnreset, JComboBox cboLoaiSP, JLabel lblImage,JTextField txtGiaBan,JTextField txtGiaVon,JTextPane txtMaSP, JTextField txtSearch,JTextField txtTenSP,CustomJTable tblNguyenLieu,CustomJTable tblThanhPhan) {
        ProcessController.frame = frame;
        ProcessController.btnThemSP = btnThemSP;
        ProcessController.btnUpdate = btnUpdate;
        ProcessController.btnreset = btnreset;
        ProcessController.cboLoaiSP = cboLoaiSP;
        ProcessController.lblImage = lblImage;
        ProcessController.lblImage = lblImage;
        ProcessController.txtGiaBan = txtGiaBan;
        ProcessController.txtGiaVon = txtGiaVon;
        ProcessController.txtMaSP = txtMaSP;  
        ProcessController.txtSearch = txtSearch;
        ProcessController.txtTenSP = txtTenSP; 
        ProcessController.tblNguyenLieu = tblNguyenLieu;
        ProcessController.tblThanhPhan = tblThanhPhan; 
    }

    public static void getComponents(JFrame frame,ButtonCustom btnThemSP,ButtonCustom btnUpdate,ButtonCustom btnreset, JComboBox cboLoaiSP, JLabel lblImage,JTextField txtGiaBan,JTextField txtGiaVon,JTextPane txtMaSP, JTextField txtSearch,JTextField txtTenSP,CustomJTable tblNguyenLieu,CustomJTable tblThanhPhan) {
        frame = ProcessController.frame;
        btnThemSP = ProcessController.btnThemSP;
        btnUpdate = ProcessController.btnUpdate;
        btnreset = ProcessController.btnreset;
        cboLoaiSP = ProcessController.cboLoaiSP;
        lblImage = ProcessController.lblImage;
        lblImage = ProcessController.lblImage;
        txtGiaBan = ProcessController.txtGiaBan;
        txtGiaVon = ProcessController.txtGiaVon;
        txtMaSP = ProcessController.txtMaSP;  
        txtSearch = ProcessController.txtSearch;
        txtTenSP = ProcessController.txtTenSP;
        tblNguyenLieu = ProcessController.tblNguyenLieu;
        tblThanhPhan = ProcessController.tblThanhPhan;
    }
    
    
    public static void init() {
        frame.setLocationRelativeTo(frame);
        frame.setTitle("Chế Biến");
        maSPUD = ProductController.maSPUD;
        fillTableNguyenLieu();
        txtMaSP.setText(spdao.createMaSP());
        if(maSPUD != null){
            setForm();
        }else
            resetForm();
        updateStatus();
    }

    
    public static void fillTableNguyenLieu() {
        DefaultTableModel model = (DefaultTableModel) tblNguyenLieu.getModel();
        try {
            List<NguyenLieu> list = nldao.selectAll();
            model.setRowCount(0);
            for (NguyenLieu nl : list) {
                Object[] row = {
                    nl.getMaNL(),
                    nl.getTenNL(),
                    nl.getHinh()
                };
                model.addRow(row);
            }
            XTable.insertImage(tblNguyenLieu, 2, 100, 100, "IngriImages");
        } catch (Exception e) {
            XMsgBox.alert(frame, "Lỗi truy vấn dữ liệu !!");
        }
    }

    
    public static void setImageProduct() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            XImage.save("ProdImages", file);
            setImage(file.getName());
        }
    }

    public static void setImage(String nameFile) {
        try {
            ImageIcon Icon = XImage.read("ProdImages", nameFile);
            lblImage.setIcon(XImage.getResized(XImage.read("ProdImages", nameFile), lblImage.getWidth(), lblImage.getHeight()));
            lblImage.setToolTipText(nameFile);
        } catch (Exception e) {
            XMsgBox.alert(frame, "Ảnh không có sẵn .");
        }

    }

    
    public static void fillTableThanhPhan(String maNL, Integer soLuong) {
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        NguyenLieu nl = nldao.selectByID(maNL);
        Object[] row = {
            nl.getMaNL(),
            nl.getTenNL(),
            soLuong,
            nl.getDonVi()
        };
        model.addRow(row);
    }

    
    public static void searchNL() {
        DefaultTableModel model = (DefaultTableModel) tblNguyenLieu.getModel();
        try {
            List<NguyenLieu> list = nldao.selectByName(txtSearch.getText());
            model.setRowCount(0);
            for (NguyenLieu nl : list) {
                Object[] row = {
                    nl.getMaNL(),
                    nl.getTenNL(),
                    nl.getHinh()};
                model.addRow(row);
            }
            XTable.insertImage(tblNguyenLieu, 2, 100, 100, "IngriImages");
        } catch (Exception e) {
            XMsgBox.alert(frame, "Lỗi kết nối database !!");
        }
    }

    
    public static void deleteNL() {
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        int row = tblThanhPhan.getSelectedRow();
        if (model.getRowCount() >= 0) {
            model.removeRow(row);
        }
    }
    
    public static void insertSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        maSP = spdao.createMaSP();
        if (XValidate.number(txtGiaBan.getText())) {
            if (checkGiaban()) {
                if (txtTenSP.getText() != null && lblImage.getToolTipText() != null) {
                    if (model.getRowCount() > 0) {
                        SanPham sp = new SanPham();
                        sp.setLoaiSP(cboLoaiSP.getSelectedItem() + "");
                        sp.setTenSP(txtTenSP.getText());
                        sp.setGiaTien(Double.valueOf(txtGiaBan.getText()));
                        sp.setHinh(lblImage.getToolTipText());
                        spdao.insert(sp);
                        insertCTSanPham(maSP);
                        XMsgBox.alert(frame, "Thêm sản phẩm thành công");
                    } else {
                        XMsgBox.alert(frame, "Thành phần không được để trống !!");
                    }
                } else {
                    XMsgBox.alert(frame, "Tên và hình không được để trống !!");
                }
            } else {
                XMsgBox.alert(frame, "Giá bán phải lớn hơn giá vốn !!");
            }
        } else {
            XMsgBox.alert(frame, "Sai định dạng giá bán !!");
        }
    }

    
    public static void insertCTSanPham(String masp) {
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        ChiTietSanPham ctsp = new ChiTietSanPham();
        for (int i = 0; i < tblThanhPhan.getRowCount(); i++) {
            NguyenLieu nl = nldao.selectByID(model.getValueAt(i, 0)+"");
            int soLuong = Integer.parseInt(model.getValueAt(i, 2) + "");
            ctsp.setMaSP(masp);
            ctsp.setMaNL(model.getValueAt(i, 0) + "");
            ctsp.setSoLuong(soLuong);
            ctsp.setGiaVon(nl.getMinGia()*soLuong);
            ctspdao.insert(ctsp);
        }
        resetForm();
    }

    
    public static void resetForm() {
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        txtTenSP.setText("");
        txtGiaBan.setText("");
        lblImage.setIcon(null);
        lblImage.setToolTipText("");
        txtGiaVon.setText("0");
        txtMaSP.setText(spdao.createMaSP());
        btnThemSP.setEnabled(true);
        updateStatus();
        model.setRowCount(0);
    }

    public static void chonSoLuong() {
        DefaultTableModel model = (DefaultTableModel) tblNguyenLieu.getModel();
        Integer soLuong;
        String sl = XMsgBox.prompt(frame, "Nhập số lượng");
        if (XValidate.positiveInt(sl) == true && !sl.equals("0")) {
            soLuong = Integer.valueOf(sl);
            String manl  = model.getValueAt(tblNguyenLieu.getSelectedRow(), 0)+"";
            if(checkIndexProduct(manl) == -1){
                 fillTableThanhPhan(model.getValueAt(tblNguyenLieu.getSelectedRow(), 0) + "", soLuong);
            }else{
                DefaultTableModel modeltp = (DefaultTableModel) tblThanhPhan.getModel();
                modeltp.setValueAt(soLuong, checkIndexProduct(manl), 2);
            }
        } else {
            XMsgBox.alert(frame, "Số lượng phải là nguyên dương !!");
        }
        txtGiaVon.setText(giaVon()+"");
    }

    public static void checkSLThanhPhan() {
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        Integer soLuong;
        String sl = XMsgBox.prompt(frame, "Nhập số lượng");
        if (XValidate.positiveInt(sl)) {
            soLuong = Integer.valueOf(sl);
            model.setValueAt(soLuong + "", tblThanhPhan.getSelectedRow(), 2);
            if (soLuong == 0) {
                model.removeRow(tblThanhPhan.getSelectedRow());
            }
        } else {
            XMsgBox.alert(frame, "Số lượng phải là nguyên dương !!");
        }
        txtGiaVon.setText(giaVon()+"");
    }

    private static Double giaVon() {
        Double giaVon = 0.0;
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        for (int i = 0; i < tblThanhPhan.getRowCount(); i++) {
            NguyenLieu nl = nldao.selectByID(model.getValueAt(i, 0) + "");
            Double minGia = nl.getMinGia();
            giaVon += minGia * (Integer.valueOf(model.getValueAt(i, 2) + ""));
        }
        return giaVon;
    }

    private static boolean checkGiaban() {
        Double giaBan = Double.valueOf(txtGiaBan.getText());
        Double giaVon = Double.valueOf(txtGiaVon.getText());
        return (giaBan - giaVon) > 0;
    }
    
    public static void update(){
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        try {
            SanPham sp = new SanPham();
            sp.setMaSP(txtMaSP.getText());
            sp.setTenSP(txtTenSP.getText());
            sp.setLoaiSP(cboLoaiSP.getSelectedItem()+"");
            sp.setGiaTien(Double.valueOf(txtGiaBan.getText()));
            sp.setHinh(lblImage.getToolTipText());
            spdao.update(sp);
            ctspdao.delete(maSPUD);
            insertCTSanPham(maSPUD);
            XMsgBox.inform(frame, "Cập nhật thành công.");
        } catch (Exception e) {
            XMsgBox.inform(frame, "Cập nhật thất bại.");
        }
    }
    
    
    public static void setForm(){
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        try {
            SanPham sp = (SanPham) spdao.selectByID(maSPUD);
            txtMaSP.setText(sp.getMaSP());
            txtGiaBan.setText(sp.getGiaTien()+"");
            txtTenSP.setText(sp.getTenSP());
            if(sp.getLoaiSP().equals("Trà Sữa")){
                cboLoaiSP.setSelectedIndex(0);
            }else if(sp.getLoaiSP().equals("Cà Phê")){
                cboLoaiSP.setSelectedIndex(1);
            }else{
                cboLoaiSP.setSelectedIndex(2);
            }
            setImage(sp.getHinh());
            List <ChiTietSanPham> list = ctspdao.selectAllApplianceForPro(maSPUD);
            for(ChiTietSanPham ctsp: list){
                NguyenLieu nl = nldao.selectByID(ctsp.getMaNL());
                Object [] row ={
                    ctsp.getMaNL(),
                    nl.getTenNL(),
                    ctsp.getSoLuong(),
                    nl.getDonVi()
                };
                model.addRow(row);
            }
            txtGiaVon.setText(giaVon()+"");
        } catch (Exception e) {
        }
    }
    
    
    public static boolean checkEmpty(){
        if(txtGiaBan.getText().equals(""))
            return false;
        if(txtTenSP.getText().equals(""))
            return false;
        if((cboLoaiSP.getSelectedItem()+"").equals(""))
            return false;
        if(lblImage.getToolTipText() == null)
            return false;
        return true;
    }
    
    
    
    public static int checkIndexProduct(String manl){
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        for(int i=0 ; i<model.getRowCount() ; i++){
            String maNL = model.getValueAt(i, 0)+"";
            if(manl.equals(maNL))
                return i;
        }
        return -1;
    }
    
    
    public static void updateStatus(){
        if(!txtMaSP.getText().equals(spdao.createMaSP())){
            btnThemSP.setEnabled(false);
        }else{
            btnUpdate.setEnabled(false);
        }
        if(!XAuth.isAdmin()){
            btnThemSP.setEnabled(false);
            btnreset.setEnabled(false);
            btnUpdate.setEnabled(false);
        }
    }
}
