
package controlls;

import daoImpl.HoaDonChiTietImple;
import daoImpl.HoaDonImple;
import daoImpl.KhachHangImple;
import daoimpl.SanPhamImple;
import entities.HoaDon;
import entities.HoaDonChiTiet;
import entities.KhachHang;
import entities.SanPham;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import utils.XMsgBox;

public class ReceiptController {

    private static final HoaDonImple hddao = new HoaDonImple();
    private static final HoaDonChiTietImple hdctdao = new HoaDonChiTietImple();
    private static final SanPhamImple spdao = new SanPhamImple();
    private static final KhachHangImple khdao = new KhachHangImple();

    public static JFrame frame;
    public static JTable tblHoaDon;
    public static JTable tblCTHoaDon;
    public static JTextField txtTimHD;
    public static JComboBox cboTime;

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
    }

    public static void search() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        try {
            List<HoaDon> list = hddao.selectByName(txtTimHD.getText());
            KhachHang kh = null;
            model.setRowCount(0);
            for (HoaDon hd : list) {
                if (hd.getMaKH() != null) {
                    kh = khdao.selectByMaKH(hd.getMaKH());
                }
                Object[] row = {
                    hd.getMaHD(),
                    hd.getMaKH() == null ? "Khách vãng lai" : kh.getHoVaTen(),
                    hd.getMaNV(),
                    hd.getTriGia(),
                    hd.getGiamGia(),
                    hd.getNgayMua(),
                    hd.getTrangThai()
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
            List<HoaDonChiTiet> list = hdctdao.selectByID(model1.getValueAt(tblHoaDon.getSelectedRow(), 0) + "");
            model.setRowCount(0);
            for (HoaDonChiTiet hdct : list) {
                SanPham sp = spdao.getItemsByMaSP(hdct.getMaSP());
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
                    kh = khdao.selectByMaKH(hd.getMaKH());
                }
                Object[] row = {
                    hd.getMaHD(),
                    hd.getMaKH() == null ? "Khách vãng lai" : kh.getHoVaTen(),
                    hd.getMaNV(),
                    hd.getTriGia(),
                    hd.getGiamGia(),
                    hd.getNgayMua(),
                    hd.getTrangThai()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            XMsgBox.alert(frame, "Lỗi truy vấn dữ liệu !!");
        }
    }
}
