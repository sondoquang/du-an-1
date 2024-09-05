
package controlls;

import daoImpl.HoaDonChiTietDAO;
import daoImpl.HoaDonDAO;
import daoImpl.KhachHangDAO;
import daoimpl.SanPhamDAO;
import entities.HoaDon;
import entities.HoaDonChiTiet;
import entities.KhachHang;
import entities.SanPham;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import utils.XMsgBox;
import utils.XTable;

public class ReceiptController {

    private static final HoaDonDAO hddao = new HoaDonDAO();
    private static final HoaDonChiTietDAO hdctdao = new HoaDonChiTietDAO();
    private static final SanPhamDAO spdao = new SanPhamDAO();
    private static final KhachHangDAO khdao = new KhachHangDAO();

    public static JFrame frame;
    public static JTable tblHoaDon;
    public static JTable tblCTHoaDon;
    public static JTextField txtTimHD;
    public static JComboBox cboTime;
    
    public static Set<Integer> historyClick = new LinkedHashSet<>();

    public static void initialize(JFrame frame, JTable tblHoaDon, JTable tblCTHoaDon, JTextField txtTimHD, JComboBox cboTime) {
        ReceiptController.frame = frame;
        ReceiptController.tblHoaDon = tblHoaDon;
        ReceiptController.tblCTHoaDon = tblCTHoaDon;
        ReceiptController.txtTimHD = txtTimHD;
        ReceiptController.cboTime = cboTime;
    }

    public static void getComponents(JFrame frame, JTable tblHoaDon, JTable tblCTHoaDon, JTextField txtTimHD, JComboBox cboTime) {
        frame = ReceiptController.frame;
        tblHoaDon = ReceiptController.tblHoaDon;
        tblCTHoaDon = ReceiptController.tblCTHoaDon;
        txtTimHD = ReceiptController.txtTimHD;
        cboTime = ReceiptController.cboTime;
    }

    public static void init() {
        fillTableBills();
        TableColumnModel columnModel = tblHoaDon.getColumnModel();
        columnModel.getColumn(6).setCellRenderer(new XTable.CheckBoxRenderer());
        columnModel.getColumn(6).setCellEditor(new XTable.checkBoxEditor());
    }

    public static void search() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        try {
            List<HoaDon> list = hddao.selectByName(txtTimHD.getText());
            KhachHang kh = null;
            model.setRowCount(0);
            for (HoaDon hd : list) {
                if (hd.getMaKH() != null) {
                    kh = khdao.selectByID(hd.getMaKH());
                }
                Object[] row = {
                    hd.getMaHD(),
                    hd.getMaKH() == null ? "Khách vãng lai" : kh.getHoVaTen(),
                    hd.getMaNV(),
                    hd.getTriGia(),
                    hd.getGiamGia(),
                    hd.getNgayMua(),
                    hd.getTrangThai().equals("Đã thanh toán")?true:false
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            XMsgBox.alert(frame, "Lỗi truy vấn dữ liệu !!");
        }
    }
    
    public static void fillDetailBills() {
        DefaultTableModel model = (DefaultTableModel) tblCTHoaDon.getModel();
        DefaultTableModel model1 = (DefaultTableModel) tblHoaDon.getModel();
        try {
            List<HoaDonChiTiet> list = hdctdao.selectByIDs(model1.getValueAt(tblHoaDon.getSelectedRow(), 0) + "",1);
            model.setRowCount(0);
            for (HoaDonChiTiet hdct : list) {
                SanPham sp = (SanPham) spdao.selectByIDs(hdct.getMaSP(),0);
                Object[] row = {
                    hdct.getMaSP(),
                    sp.getTenSP(),
                    hdct.getSoLuong(),
                    hdct.getDonGia(),
                    hdct.getThanhTien()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            XMsgBox.alert(frame, "Lỗi truy vấn dữ liệu !!");
        }
    }
    
    public static Set<Integer> doClickTable(){
        historyClick.add(tblHoaDon.getSelectedRow());
        return historyClick;
    }
    
    public static void updateTrangThaiHD(){
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        for(Integer setHis : historyClick){
            HoaDon hd = new HoaDon();
            hd.setMaHD(model.getValueAt(setHis, 0)+"");
            hd.setTrangThai(((boolean)model.getValueAt(setHis, 6))?"Đã thanh toán":"Chưa thanh toán");
            hddao.updateTrangThaiHD(hd);
        }
        historyClick.clear();
        fillTableBills();
    }
    
    public static void fillTableBills() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        List<HoaDon> list = null;
        int loai = cboTime.getSelectedIndex();
        if (loai == 0) {
            list = hddao.selectHDByDay();
        } else if (loai == 1) {
            list = hddao.selectHDByMonth();
        } else {
            list = hddao.selectHDByYear();
        }
        try {
            KhachHang kh = null;
            model.setRowCount(0);
            for (HoaDon hd : list) {
                if (hd.getMaKH() != null) {
                    kh = khdao.selectByID(hd.getMaKH());
                }
                Object[] row = {
                    hd.getMaHD(),
                    hd.getMaKH() == null ? "Khách vãng lai" : kh.getHoVaTen(),
                    hd.getMaNV(),
                    hd.getTriGia(),
                    hd.getGiamGia(),
                    hd.getNgayMua(),
                    hd.getTrangThai().equals("Đã thanh toán")== true?true:false
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            XMsgBox.alert(frame, "Lỗi truy vấn dữ liệu !!");
        }
    }
}
