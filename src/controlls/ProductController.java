
package controlls;

import customcellbuttonaction.TableActionCellEditor;
import customcellbuttonaction.TableActionCellRender;
import customcellbuttonaction.TableActionEvent;
import daoImpl.HoaDonChiTietImple;
import daoimpl.SanPhamImple;
import entities.SanPham;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utils.XMsgBox;
import utils.XTable;
import views.CheBienJDialog;

public class ProductController {

    private static JFrame frame;
    private static JTable tblSanPham;

    public static void initialize(JFrame frame, JTable tblSanPham) {
        ProductController.frame = frame;
        ProductController.tblSanPham = tblSanPham;
    }
    
    public static void getComponents(JFrame frame, JTable tblSanPham) {
        frame = ProductController.frame;
        tblSanPham = ProductController.tblSanPham;
    }

    private static final SanPhamImple spdao = new SanPhamImple();
    private static final HoaDonChiTietImple hdctdao = new HoaDonChiTietImple();

    public static void init() {
        setActionTable();
        fillTableSanPham("");
    }
    
    public static void setActionTable(){
        TableActionEvent actionEvent = new TableActionEvent() {
            @Override
            public void onDetete(int row) {
                deleteProduct((String) tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0));
            }

            @Override
            public void onEdit(int row) {
                chuyenTrang();
            }
        };
        tblSanPham.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        tblSanPham.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(actionEvent));
    }
    public static void fillTableSanPham(String tenSP) {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        try {
            List<SanPham> list = spdao.getItemsByID(tenSP);
            model.setRowCount(0);
            for (SanPham sp : list) {
                Object[] row = {
                    sp.getMaSP(),
                    sp.getHinh(),
                    sp.getLoaiSP(),
                    sp.getTenSP(),
                    sp.getGiaTien()
                };
                model.addRow(row);
            }
            XTable.insertImage(tblSanPham, 1, 100, 100, "ProdImages");
        } catch (Exception e) {
            XMsgBox.alert(frame, "Lỗi truy vấn dữ liệu !!");
        }
    }
    
    public static String masp;
    public static void chuyenTrang() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        masp = model.getValueAt(tblSanPham.getSelectedRow(), 0) + "";
        new CheBienJDialog(frame, true).setVisible(true);
        fillTableSanPham("");
    }
    
    public static void taoSanPhamMoi(){
        masp = null;
        new CheBienJDialog(frame, true).setVisible(true);
        fillTableSanPham("");
    }
    
    public static void deleteProduct(String maSP){
        if(hdctdao.selectByMaSP(maSP).isEmpty()){
            if(XMsgBox.confirm(frame, "Bạn muốn xóa sản phẩm này chứ ?")){
                spdao.delete(maSP);
                fillTableSanPham(maSP);
                XMsgBox.inform(frame, "Xóa sản phẩm thành công.");
            }
        }else{
            XMsgBox.inform(frame,"Không thể xóa sản phẩm này !");
        }
    }
}
