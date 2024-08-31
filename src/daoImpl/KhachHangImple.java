package daoImpl;

import entities.KhachHang;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import utils.XJdbc;
import java.sql.SQLException;

public class KhachHangImple extends KhachHangDAO {

    public List<KhachHang> selectBySQl(String sql, Object... agrs) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) XJdbc.select(sql, agrs);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("MaKh"));
                kh.setHoVaTen(rs.getString("hovaten"));
                kh.setSDT(rs.getString("sdt"));
                list.add(kh);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<KhachHang> selectByID(String values, int maTK) {
        String sql;
        if (maTK == 0) {
            sql = " SELECT * FROM KHACHHANG WHERE sdt like ? ";
        } else {
            sql = " SELECT * FROM KHACHHANG WHERE HOVATEN LIKE ?";
        }
        values = "%" + values + "%";
        return selectBySQl(sql, values);
    }

    public void insertCustomer(KhachHang Entity) throws SQLException, ClassNotFoundException {
        String sql = " INSERT INTO KHACHHANG ( hovaten ,sdt) values ( ?, ?) ";
        Object[] values = {
            Entity.getHoVaTen(),
            Entity.getSDT()
        };
        XJdbc.IUD(sql, values);
    }

    public void UpdateInfoCustomer(KhachHang Entity) throws SQLException, ClassNotFoundException {
        String sql = " UPDATE KHACHHANG SET HOVATEN = ? , SDT = ? WHERE MAKH = ? ";
        Object[] values = {
            Entity.getHoVaTen(),
            Entity.getSDT(),
            Entity.getMaKH()
        };
        XJdbc.IUD(sql, values);
    }

    public void deleteCustomer(String makh) throws SQLException, ClassNotFoundException {
        String sql = " DELETE FROM KHACHHANG WHERE MAKH = ? ";
        XJdbc.IUD(sql, makh);
    }

    public KhachHang selectByMaKH(String maKH) {
        String sql = " SELECT * FROM KHACHHANG WHERE MAKH = ?";
        List<KhachHang> list = selectBySQl(sql, maKH);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public KhachHang selectBySDT(String sdt) {
        String sql = " SELECT * FROM KHACHHANG WHERE SDT = ? ";
        List<KhachHang> list = this.selectBySQl(sql, sdt);
        return !list.isEmpty() ? list.get(0) : null;
    }
    
    public String createIDCustomer(){
        String sql = " EXEC SP_TAOMAKH ";
        return XJdbc.getValue(sql);
    }

}
