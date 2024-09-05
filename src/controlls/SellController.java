package controlls;

import button.ButtonCustom;
import customcellbuttonaction.TableActionCellEditor;
import customcellbuttonaction.TableActionCellRender;
import customcellbuttonaction.TableActionEvent;
import customjtable.CustomJTable;
import daoImpl.ChiTietSanPhamDAO;
import daoImpl.HoaDonChiTietDAO;
import daoImpl.HoaDonDAO;
import daoImpl.KhachHangDAO;
import daoImpl.NguyenLieuDAO;
import daoimpl.SanPhamDAO;
import entities.ChiTietSanPham;
import entities.HoaDon;
import entities.HoaDonChiTiet;
import entities.KhachHang;
import entities.NguyenLieu;
import entities.SanPham;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import jpanelrounded.PanelRound;
import utils.XAuth;
import utils.XImage;
import utils.XJdbc;
import utils.XMsgBox;
import utils.XValidate;
import views.CreateOrdersJFrame;
import views.SaveOderCustomerJDialog;

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
    private static JPanel pnlMain;

    
    private static final HoaDonDAO hdndao = new HoaDonDAO();
    private static final SanPhamDAO spdao = new SanPhamDAO();
    private static final HoaDonChiTietDAO hdctdao = new HoaDonChiTietDAO();
    private static final KhachHangDAO khdao = new KhachHangDAO();
    private static final HoaDonDAO hddao = new HoaDonDAO();
    private static final ChiTietSanPhamDAO ctspdao = new ChiTietSanPhamDAO();
    private static final NguyenLieuDAO nldao = new NguyenLieuDAO();
    private static Double giaGoc = 0.0;
    private static Double discountGoc = 0.0;
    private static String maHD;
    public static String maHDUD;
    private static Map<String, Integer> listProOrder = new LinkedHashMap();
    private static List<SanPham> list;
    private static JLabel[][] lblImg = new JLabel[100][3];
    static PanelRound[][] pnlProduct = new PanelRound[100][3];

    public static void initialize(JFrame frame,
            ButtonCustom btnAll, ButtonCustom btnCafe, ButtonCustom btnInHoaDon, ButtonCustom btnMilkTea, ButtonCustom btnTaoKH, ButtonCustom btnTea,
            ButtonCustom btnTimKiemKH, ButtonCustom btnXoa, JButton btnTimKiem, JComboBox cboKhachHang, JRadioButton rdoChuaThanhToan, JRadioButton rdoThanhToan,
            CustomJTable tblOder, JTextField txtDiscount, JTextField txtGiamGia, JTextField txtPrice, JTextField txtSDT, JTextField txtTienNhan,
            JTextField txtTienThoi, JTextField txtTimKiem, JPanel pnlMain) {
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
        SellController.pnlMain = pnlMain;

    }

    public static void getComponents(JFrame frame,
            ButtonCustom btnAll, ButtonCustom btnCafe, ButtonCustom btnInHoaDon, ButtonCustom btnMilkTea, ButtonCustom btnTaoKH, ButtonCustom btnTea,
            ButtonCustom btnTimKiemKH, ButtonCustom btnXoa, JButton btnTimKiem, JComboBox cboKhachHang, JRadioButton rdoChuaThanhToan, JRadioButton rdoThanhToan,
            CustomJTable tblOder, JTextField txtDiscount, JTextField txtGiamGia, JTextField txtPrice, JTextField txtSDT, JTextField txtTienNhan,
            JTextField txtTienThoi, JTextField txtTimKiem, JPanel pnlMain) {
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
        pnlMain = SellController.pnlMain;
    }

    public static void init(String maHD) {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        maHDUD = maHD;
        rdoThanhToan.setSelected(true);
        if (maHD != null) {
            setForm();
            hoanTraNguyenLieu(-1);
        }
        fillProductDetail("");
        updateStatus();
        TableActionEvent event = new TableActionEvent() {
            DefaultTableModel model = (DefaultTableModel) tblOder.getModel();

            @Override
            public void onDetete(int row) {
                if (tblOder.isEditing()) {
                    tblOder.getCellEditor().stopCellEditing();
                }
                if (row >= 0) {
                    listProOrder.remove(model.getValueAt(row, 0) + "");
                    model.removeRow(row);
                    resetBill();
                    updatePrice();
                }
            }

            @Override
            public void onEdit(int row) {
                doclickProduct(model.getValueAt(row, 0) + "");
            }
        };
        tblOder.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        tblOder.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
    }

    private static JLabel createNameProduct(String nameProduct, String IDColor) {
        JLabel lblName = new JLabel();
        lblName.setText(nameProduct);
        lblName.setFont(new Font("Arial", Font.BOLD, 18));
        lblName.setForeground(Color.decode(IDColor));
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        return lblName;
    }

    private static void setImageProduct(String nameFile, JLabel nameLabel) {
        try {
            ImageIcon imageProduct = XImage.getResized(XImage.read("ProdImages", nameFile), nameLabel.getWidth(), nameLabel.getHeight());
            nameLabel.setIcon(imageProduct);
        } catch (Exception e) {
            XMsgBox.alert(frame, "Ảnh không có sẵn .");
        }

    }

    private static PanelRound createPanelProduct() {
        PanelRound pnl = new PanelRound();
        pnl.setSize(250, 250);
        pnl.setLayout(new BorderLayout());
        pnl.setBackground(Color.decode("#FFEFD5"));
        pnl.setRoundButtomLeft(30);
        pnl.setRoundButtomRight(30);
        pnl.setRoundTopLeft(30);
        pnl.setRoundTopRight(30);
        return pnl;
    }

    private static JLabel createLabelProduct() {
        JLabel lbl = new JLabel();
        lbl.setSize(245, 245);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return lbl;
    }

    public static void fillProductDetail(String loaisp) {
        int cnt = -1;
        list = spdao.selectByIDs(loaisp,0);
        int size = list.size();
        pnlMain.removeAll();
        pnlMain.setLayout(new GridLayout(size / 3 + 1, 3, 10, 10));
        for (int i = 0; i < size / 3 + 1; i++) {
            for (int j = 0; j < 3; j++) {
                ++cnt;
                if (cnt < size) {
                    SanPham sp = list.get(cnt);
                    pnlProduct[i][j] = createPanelProduct();
                    lblImg[i][j] = createLabelProduct();
                    lblImg[i][j].setToolTipText(sp.getMaSP());
                    setImageProduct(sp.getHinh(), lblImg[i][j]);
                    pnlProduct[i][j].add(lblImg[i][j], BorderLayout.NORTH);
                    pnlProduct[i][j].add(createNameProduct(sp.getTenSP(), "#8B4513"), BorderLayout.CENTER);
                    pnlProduct[i][j].add(createNameProduct(sp.getGiaTien() + " vnđ", "#8B4513"), BorderLayout.SOUTH);
                    pnlMain.add(pnlProduct[i][j]);
                    pnlMain.scrollRectToVisible(new Rectangle(pnlMain.getSize()));
                }
            }
        }
        eventClickAddProduct();
    }

    public static void clearForm() {
        DefaultTableModel model = (DefaultTableModel) tblOder.getModel();
        model.setRowCount(0);
        giaGoc = 0.0;
        discountGoc = 0.0;
        txtSDT.setText("");
        txtPrice.setText(giaGoc + "");
        txtGiamGia.setText(giaGoc * discountGoc + "");
        txtDiscount.setText(discountGoc + "");
        txtTienNhan.setText("");
        txtTienThoi.setText("");
        cboKhachHang.removeAllItems();
        listProOrder.clear();
    }

    private static void resetBill() {
        DefaultTableModel model = (DefaultTableModel) tblOder.getModel();
        giaGoc = 0.0;
        for (int i = 0; i < model.getRowCount(); i++) {
            Double donGia = Double.valueOf(model.getValueAt(i, 2) + "");
            Integer SLSP = Integer.valueOf(model.getValueAt(i, 3) + "");
            model.setValueAt(donGia * SLSP, i, 4);
            giaGoc += Double.parseDouble(model.getValueAt(i, 4) + "");
        }
        txtPrice.setText(giaGoc + "");
    }

    public static void printBill() {
        // Hệ số 1 : Trừ nguyên liệu làm ra sản phẩm trong kho 
        hoanTraNguyenLieu(1);
        try {
            if (tblOder.getRowCount() != 0) {
                maHD = hdndao.taoMaHoaDon();
                KhachHang kh = (KhachHang) cboKhachHang.getSelectedItem();
                HoaDon hd = new HoaDon();
                hd.setMaHD(maHD);
                hd.setMaKH(kh == null ? null : kh.getMaKH());
                hd.setMaNV(XAuth.getUser().getMaNV());
                hd.setNgayMua(new Date());
                hd.setTongTien(Double.parseDouble(txtPrice.getText()) + Double.parseDouble(txtGiamGia.getText()));
                hd.setGiamGia(Double.valueOf(txtGiamGia.getText()));
                hd.setTriGia(Double.valueOf(txtPrice.getText()));
                hd.setTrangThai(rdoThanhToan.isSelected() ? "Đã thanh toán" : "Chưa thanh toán");
                hdndao.insert(hd);
                insertCTHD(maHD);
                clearForm();
                XMsgBox.inform(null, "In hoá đơn thành công.");
            } else {
                XMsgBox.alert(frame, "Hóa đơn trống.");
            }
        } catch (NumberFormatException e) {
            XMsgBox.alert(frame, "In hóa đơn thất bại !!");
        }

    }

    private static void createBill() {
        String sql = " EXEC SP_TAOMAHD ";
        try {
            XJdbc.select(sql);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CreateOrdersJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static HoaDonChiTiet getValueHDCT(int address, String mahd) {
        DefaultTableModel model = (DefaultTableModel) tblOder.getModel();
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setMaHD(mahd);
        hdct.setMaSP(model.getValueAt(address, 0) + "");
        hdct.setSoLuong(Integer.valueOf(model.getValueAt(address, 3) + ""));
        hdct.setDonGia(Double.valueOf(model.getValueAt(address, 2) + ""));
        hdct.setThanhTien(Double.valueOf(model.getValueAt(address, 4) + ""));
        hdct.setKhuyenMai(Double.valueOf(txtDiscount.getText()));
        hdct.setGhiChu(model.getValueAt(address, 1) + "");
        return hdct;
    }

    private static void insertCTHD(String mahd) {
        DefaultTableModel model = (DefaultTableModel) tblOder.getModel();
        try {
            for (int i = 0; i < model.getRowCount(); i++) {
                hdctdao.insert(getValueHDCT(i, mahd));
            }
            updateGiaTienNL();
            xuatBillKhachHang();
        } catch (NumberFormatException e) {
            XMsgBox.alert(frame, "Thêm hóa đơn thất bại !");
        }
    }

    public static void eventClickAddProduct() {
        int dem = -1;
        for (int row = 0; row < list.size() / 3 + 1; row++) {
            for (int col = 0; col < 3; col++) {
                ++dem;
                if (dem < list.size()) {
                    String masp = lblImg[row][col].getToolTipText();
                    lblImg[row][col].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            doclickProduct(masp);
                        }
                    });
                }
            }
        }
    }

    private static void doclickProduct(String maSP) {
        if (checkMaxCount(maSP) < 0) {
            XMsgBox.inform(null, "Nguyên liệu không còn đủ !");
            return;
        }
        int maxCount = checkMaxCount(maSP);
        int count = chonSoLuong();
        if (count == -1) {
            return;
        }
        if (maxCount < count) {
            XMsgBox.inform(null, "Nguyên liệu chỉ đủ cho " + maxCount + "sản phẩm.");
            return;
        }
        if (!listProOrder.containsKey(maSP)) {
            listProOrder.put(maSP, count);
        } else {
            listProOrder.replace(maSP, count);
        }
        fillTableOrder();
        resetBill();
        updatePrice();
    }

    public static void fillTableOrder() {
        DefaultTableModel model = (DefaultTableModel) tblOder.getModel();
        model.setRowCount(0);
        for (String key : listProOrder.keySet()) {
            SanPham sp = (SanPham) spdao.selectByIDs(key,0);
            Object[] row = {
                sp.getMaSP(),
                sp.getTenSP(),
                sp.getGiaTien(),
                listProOrder.get(key),
                sp.getGiaTien() * listProOrder.get(key)
            };
            model.addRow(row);
        }
    }

    private static Double checkDiscount() {
        Double sumMoney = 0.0;
        Double giamGia = 0.0;
        try {
            KhachHang kh = (KhachHang) cboKhachHang.getSelectedItem();
            if (kh != null) {
                sumMoney = hddao.checkCountBills(kh.getMaKH());
                if (sumMoney == null) {
                    sumMoney = 0.0;
                }
            }
            if (sumMoney + giaGoc >= 10000000) {
                giamGia = 0.25;
            } else if (sumMoney + giaGoc >= 5000000) {
                giamGia = 0.15;
            } else if (sumMoney + giaGoc >= 2000000) {
                giamGia = 0.1;
            } else if (sumMoney + giaGoc >= 1000000) {
                giamGia = 0.05;
            } else {
                giamGia = 0.0;
            }
        } catch (Exception e) {
        }
        return giamGia;
    }

    public static void fillCustomerByID(String maKH) {
        try {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboKhachHang.getModel();
            model.removeAllElements();
            KhachHang kh = khdao.selectByID(maKH);
            model.addElement(kh);
            txtSDT.setText(kh.getSDT());
            updatePrice();
        } catch (Exception e) {
            XMsgBox.alert(frame, "Lỗi truy vấn dữ liệu !!");
        }
    }

    private static void updatePrice() {
        discountGoc = checkDiscount();
        String pattern = "#.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        txtDiscount.setText(discountGoc + "");
        Double giamGia = Double.valueOf(txtDiscount.getText()) * giaGoc;
        Double triGia = giaGoc - giamGia;
        txtGiamGia.setText(decimalFormat.format(giamGia));
        txtPrice.setText(decimalFormat.format(triGia));
    }

    private static void updateGiaTienNL() {
        Double giaTien = 0.0;
        DefaultTableModel model = (DefaultTableModel) tblOder.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String masp = model.getValueAt(i, 0) + "";
            int soLuong = Integer.parseInt(model.getValueAt(i, 3) + "");
            List<ChiTietSanPham> list = ctspdao.selectAllApplianceForPro(masp);
            for (ChiTietSanPham ctsp : list) {
                giaTien = ctsp.getGiaVon();
                nldao.updateGiaTien(giaTien * soLuong, ctsp.getMaNL());
            }
        }
    }

    public static int checkMaxCount(String maSP) {
        List<ChiTietSanPham> list = ctspdao.selectAllApplianceForPro(maSP);
        int maxCount = 100000;
        for (ChiTietSanPham ctsp : list) {
            // Kiểm tra nguyên liệu tồn kho bao nhiêu //
            int tonKho = nldao.checkNLTonKhoByMaNL(ctsp.getMaNL());
            // Đơn vị nhỏ nhất để tạo nên sản phẩm //
            int soLuong = ctsp.getSoLuong();
            if ((tonKho / soLuong) < maxCount) {
                maxCount = tonKho / soLuong;
            }
        }
        return maxCount;
    }

    private static int chonSoLuong() {
        String soLuong = XMsgBox.prompt(null, "Nhập số lượng:");
        if (XValidate.positiveInt(soLuong) && Integer.parseInt(soLuong) > 0) {
            return Integer.parseInt(soLuong);
        }
        XMsgBox.alert(null, "Số lượng phải nguyên dương !");
        return -1;
    }

    private static void updateNguyenLieu(String maSP, int heSo) {
        List<ChiTietSanPham> list = ctspdao.selectAllApplianceForPro(maSP);
        for (ChiTietSanPham ctsp : list) {
            try {
                NguyenLieu nl = new NguyenLieu();
                nl.setMaNL(ctsp.getMaNL());
                nl.setTonKho(ctsp.getSoLuong() * heSo);
                nldao.updateTKNguyenLieu(nl);
            } catch (Exception e) {
                XMsgBox.alert(frame, "Lỗi truy vấn dữ liệu!");
            }
        }
    }

    public static void hoanTraNguyenLieu(int heSo) {
        DefaultTableModel model = (DefaultTableModel) tblOder.getModel();
        if (model.getRowCount() > 0) {
            for (int i = 0; i < tblOder.getRowCount(); i++) {
                String masp = model.getValueAt(i, 0) + "";
                int soLuong = Integer.parseInt(model.getValueAt(i, 3) + "");
                updateNguyenLieu(masp, heSo * soLuong);
            }
        }
    }

    public static void searchCustomer() {
        try {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboKhachHang.getModel();
            model.removeAllElements();
            List <KhachHang> list = khdao.selectByIDs(txtSDT.getText(),1);
            if (list != null) {
                model.addElement(list.get(0).toString());
            } else {
                if (XMsgBox.confirm(frame, "Khách hàng mới\nBạn có muốn thêm mới khách hàng ?")) {
                    ProductController.masp = null;
                    new SaveOderCustomerJDialog(frame, true, txtSDT.getText()).setVisible(true);
                    if (SaveOderCustomerJDialog.makh != null) {
                        fillCustomerByID(SaveOderCustomerJDialog.makh);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public static void returnNguyenLieu() {
        if(tblOder.getRowCount() > 0){
            List<HoaDonChiTiet> list = hdctdao.selectByIDs(maHDUD,1);
            for (HoaDonChiTiet hdct : list) {
                updateNguyenLieu(hdct.getMaSP(), 1 * hdct.getSoLuong());
            }
        }
    }

    private static void updateStatus() {
        if (maHDUD != null) {
            btnTimKiemKH.setEnabled(false);
            btnTaoKH.setEnabled(false);
        }
    }

    private static void setForm() {
        List<HoaDonChiTiet> list = hdctdao.selectByIDs(maHDUD,1);
        for (HoaDonChiTiet hdct : list) {
            listProOrder.put(hdct.getMaSP(), hdct.getSoLuong());
        }
        HoaDon hoaDon = hddao.selectByMaHD(maHDUD);
        if (hoaDon.getMaKH() != null) {
            fillCustomerByID(hoaDon.getMaKH());
        }
        if (hoaDon.getTrangThai().equals("Đã thanh toán")) {
            rdoThanhToan.setSelected(true);
        } else {
            rdoChuaThanhToan.setSelected(true);
        }
        fillTableOrder();
        resetBill();
        updatePrice();
    }

    public static void updateBills() {
        hoanTraNguyenLieu(1);
        HoaDon hd = new HoaDon();
        hd.setMaHD(maHDUD);
        if (hddao.selectByMaHD(maHDUD) != null) {
            hd.setMaKH(hddao.selectByMaHD(maHDUD).getMaKH());
        }
        hd.setMaNV(XAuth.getUser().getMaNV());
        hd.setTongTien(Double.valueOf(txtPrice.getText()));
        hd.setGiamGia(Double.valueOf(txtGiamGia.getText()));
        hd.setTriGia(Double.parseDouble(txtPrice.getText()) - Double.parseDouble(txtGiamGia.getText()));
        hd.setTrangThai(rdoThanhToan.isSelected() ? "Đã thanh toán" : "Chưa thanh toán");
        hddao.update(hd);
        hdctdao.delete(maHDUD);
        insertCTHD(maHDUD);
        clearForm();
        XMsgBox.inform(null, "In hóa đơn thành công.");
    }

    private static void xuatBillKhachHang() {
        // chờ xét duyệt mới có code nhé //  
        //        License.setLicenseKey("IRONSUITE.SONDQPS41027.GMAIL.COM.4975-A90B79BBB2-A7A64PLUNOQAEWFP-IE6YVLBAWBSL-NJMYNLOKSNL4-RGO5F57P4DGX-DU6EYU26LZKU-4GYJFRZI5BQ5-S7YUBZ-TLDTIUCDCOSNEA-DEPLOYMENT.TRIAL-UVFH6H.TRIAL.EXPIRES.20.AUG.2024");
//        // Set a log path
//        Settings.setLogPath(Paths.get("C:\\Users\\acer\\Documents"));
//        String head
//                = "<head>\n"
//                + "    <style>\n"
//                + "        div.hoadon{\n"
//                + "            width: 500px;\n"
//                + "            margin: auto;\n"
//                + "            text-align: center;\n"
//                + "        }\n"
//                + "        p.title{\n"
//                + "            font-weight: bolder;\n"
//                + "            font-size: 25px;\n"
//                + "            padding: 10px;\n"
//                + "            border-bottom:  2px dashed   black;\n"
//                + "        }\n"
//                + "        div.hoadon> div.info{\n"
//                + "            margin-top: -10px;\n"
//                + "            font-size: 16px;\n"
//                + "            padding-bottom: 10px;\n"
//                + "        }\n"
//                + "        table.sanpham{\n"
//                + "            border-collapse: collapse;\n"
//                + "            width: 100%;\n"
//                + "        }\n"
//                + "        table.sanpham th{\n"
//                + "            padding: 10px;\n"
//                + "            background-color: #33CCFF;\n"
//                + "        }\n"
//                + "        table.sanpham tr{\n"
//                + "            text-align: center;\n"
//                + "        }\n"
//                + "        div.left{\n"
//                + "            float: left; \n"
//                + "            width: 50%; \n"
//                + "        }\n"
//                + "        div.left ,div.right{\n"
//                + "            padding: 10px; \n"
//                + "            font-size: 18px; font-weight: bold;\n"
//                + "        }\n"
//                + "\n"
//                + "        table.thanhtoan{\n"
//                + "            width: 100%;\n"
//                + "            padding: 10px;\n"
//                + "            border-top: 2px dashed black;\n"
//                + "            border-bottom: 2px dashed black;\n"
//                + "        }\n"
//                + "    </style>\n"
//                + "</head>";
//        String body = "<body>\n"
//                + "    <div class=\"hoadon\">\n"
//                + "        <p class=\"title\">PHIẾU THANH TOÁN CINNAMON</p>\n"
//                + "        <div class=\"info\">\n"
//                + "            <span>MaHD : " + maHD + " - Ngày: " + XDate.toString(new Date(), "HH:mm:ss MM/dd/yyyy") + " - NV001 </span>\n"
//                + "        </div>\n"
//                + "        <table border=\"\" class=\"sanpham\" >\n"
//                + "            <thead>\n"
//                + "                <tr>\n"
//                + "                    <th>Số lượng</th>\n"
//                + "                    <th>Giá bán</th>\n"
//                + "                    <th>Thành tiền</th>\n"
//                + "                </tr>\n"
//                + "            </thead>\n"
//                + // Thực hiện vòng for() tại đây //
//                "            <tbody>\n";
//        DefaultTableModel model = (DefaultTableModel) tblOder.getModel();
//        for (int i = 0; i < tblOder.getRowCount(); i++) {
//
//            body += "               <tr>\n";
//            body += "                    <td>" + model.getValueAt(i, 1) + " <br>" + model.getValueAt(i, 3) + "</td>\n";
//            body += "                    <td>" + model.getValueAt(i, 2) + "</td>\n";
//            body += "                    <td>" + model.getValueAt(i, 4) + "</td>\n";
//            body += "                </tr>\n";
//        }
//
//        body
//                += "            </tbody>\n"
//                + "        </table>\n"
//                + "        <table class=\"thanhtoan\">\n"
//                + "            <tfoot style=\"margin-top: 10px;\">\n"
//                + "                <tr>\n"
//                + "                    <th>Tổng tiền:</th>\n"
//                + "                    <th></th>\n"
//                + "                    <th>" + giaGoc + "</th>\n"
//                + "                </tr>\n"
//                + "                <tr>\n"
//                + "                    <th>Giảm giá:</th>\n"
//                + "                    <th>" + txtDiscount.getText() + " <span>%</span></th>\n"
//                + "                    <th>" + txtGiamGia.getText() + "</th>\n"
//                + "                </tr>\n"
//                + "                <tr>\n"
//                + "                    <th>Phải thanh toán:</th>:</th>\n"
//                + "                    <th></th>\n"
//                + "                    <th>" + txtPrice.getText() + "</th>\n"
//                + "                </tr>\n"
//                + "        </table>\n"
//                +"<p style=\"text-align: right; margin-right: 50px; font-weight: bolder;\">"+cboTrangThai.getSelectedItem()+"</p>"
//                + "        <p>CẢM ƠN QUÝ KHÁCH</p>\n"
//                + "        <P>Đã đồng hàng cùng cửa hàng của chúng tôi</P>\n"
//                + "    </div>\n"
//                + "</body>";
//        License.setLicenseKey("IRONSUITE.SONDQPS41027.GMAIL.COM.4975-A90B79BBB2-A7A64PLUNOQAEWFP-IE6YVLBAWBSL-NJMYNLOKSNL4-RGO5F57P4DGX-DU6EYU26LZKU-4GYJFRZI5BQ5-S7YUBZ-TLDTIUCDCOSNEA-DEPLOYMENT.TRIAL-UVFH6H.TRIAL.EXPIRES.20.AUG.2024");
//        Settings.setLogPath(Paths.get("C:/tmp/IronPdfEngine.log"));
//        PdfDocument myPdf = PdfDocument.renderHtmlAsPdf(head + body);
//        try {
//            myPdf.saveAs(Paths.get(maHD+".pdf"));
//            File file = new File("D:\\Java\\DuAn1\\"+maHD+".pdf");
//            if (file.toString().endsWith(".pdf")) 
//            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
//        else {
//            Desktop desktop = Desktop.getDesktop();
//            desktop.open(file);
//        }
//        } catch (IOException ex) {
//            Logger.getLogger(CreateOrdersJFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        XMsgBox.inform(null, "In hóa đơn thành công");
    }

    public static void txtDiscountKeyPressed() {
        Double giamGia = giaGoc * Double.valueOf(txtDiscount.getText());
        Double triGia = giaGoc - giamGia;
        txtGiamGia.setText(giamGia + "");
        txtPrice.setText(triGia + "");
    }

}
