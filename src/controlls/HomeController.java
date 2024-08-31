
package controlls;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import utils.XJdbc;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.XMsgBox;
import utils.XImage;


public class HomeController {

    private static JFrame frame;
    private static JLabel lblEmpPic;
    private static JLabel lblEmpName;
    private static JLabel lblProdPic;
    private static JLabel lblProdName;
    private static JLabel lblSum;
    private static JTable tblEmp;
    private static JTable tblProd;

    public static void initialize(JFrame frame, JLabel lblEmpPic, JLabel lblEmpName, JLabel lblProdPic, JLabel lblProdName, JLabel lblSum) {
        HomeController.frame = frame;
        HomeController.lblEmpPic = lblEmpPic;
        HomeController.lblEmpName = lblEmpName;
        HomeController.lblProdPic = lblProdPic;
        HomeController.lblProdName = lblProdName;
        HomeController.lblSum = lblSum;
    }

    public static void getComponents(JFrame frame, JLabel lblEmpPic, JLabel lblEmpName, JLabel lblProdPic, JLabel lblProdName, JLabel lblSum) {
        frame = HomeController.frame;
        lblEmpPic = HomeController.lblEmpPic;
        lblEmpName = HomeController.lblEmpName;
        lblProdPic = HomeController.lblProdPic;
        lblProdName = HomeController.lblProdName;
        lblSum = HomeController.lblSum;
    }
    
    public static void init(){
        try {
            setTopEmp();
            setTopProd();
            setSum();
        } catch (SQLException | ClassNotFoundException ex) {
            XMsgBox.alert(frame, "Lỗi truy vấn dữ liệu");
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private static void setTopEmp() throws SQLException, ClassNotFoundException{
        String sql = "exec SP_NHANVIENTIEUBIEU";
        ResultSet rs = (ResultSet)XJdbc.select(sql);
        while (rs.next()){
            lblEmpPic.setIcon(XImage.getResized(XImage.read("EmpImages", rs.getString(6)), lblEmpPic.getWidth(), lblEmpPic.getHeight()));
            lblEmpName.setText(rs.getString(3));
        }
    }
    
    private static void setTopProd() throws SQLException, ClassNotFoundException{
        String sql = "exec SP_SPBANCHAYNHAT";
        ResultSet rs = (ResultSet)XJdbc.select(sql);
        while (rs.next()){
            lblProdPic.setIcon(XImage.getResized(XImage.read("ProdImages", rs.getString(5)), lblProdPic.getWidth(), lblProdPic.getHeight()));
            lblProdName.setText(rs.getString(4));
        }
    }
    
    private static void setSum() throws SQLException, ClassNotFoundException{
        String sql = "exec SP_SUMDONHANGTRONGNGAY";
        int sum = XJdbc.getValue(sql);
        lblSum.setText(sum+"");
    }
}