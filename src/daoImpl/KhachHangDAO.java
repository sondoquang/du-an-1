package daoImpl;

import daos.DAOs;
import entities.KhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.XJdbc;

public class KhachHangDAO extends DAOs<KhachHang, String> {

//    @Override
//    public void insert(KhachHang model) {
//        String sql = "INSERT INTO PRODUCT VALUES (? , ?, ?, ?, ?)";
//        XJdbc.update(sql,
//                model.getHoVaTen(),
//                model.getSDT(),
//                model.getMaKH());
//    }
//
//    @Override
//    public void update(KhachHang model) {
//        String sql = "UPDATE KhachHang SET HoVaTen = ?, SDT = ? WHERE MaKH = ?";
//        XJdbc.update(sql,
//                model.getHoVaTen(),
//                model.getSDT(),
//                model.getMaKH());
//    }
//
//    @Override
//    public void delete(String key) {
//        String sql = "DELETE FROM KhachHang WHERE MaKH=?";
//        XJdbc.update(sql, MaKH);
//    }
    @Override
    public List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) XJdbc.select(sql, args);
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getString(1));
                entity.setHoVaTen(rs.getString(2));
                entity.setSDT(rs.getString(3));
                list.add(entity);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<KhachHang> selectAll() {
        String sql = "SELECT * FROM KhachHang";
        return selectBySql(sql);
    }

    @Override
    public KhachHang selectByID(String key) {
        String sql = "SELECT * FROM KhachHang WHERE MaKH = ?";
        List<KhachHang> list = selectBySql(sql, key);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public int insert(KhachHang e) {
        int i = -1;
        String sql = "INSERT INTO KhachHang(HoVaTen, SDT) VALUES (? , ?)";
        try {
            i = XJdbc.IUD(
                    sql,
                    e.getHoVaTen(),
                    e.getSDT()
            );
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public int update(KhachHang e) {
        int i = -1;
        String sql = "UPDATE KhachHang SET HoVaTen = ?, SDT = ? WHERE MaKH = ?";
        try {
            i = XJdbc.IUD(sql,
                    e.getHoVaTen(),
                    e.getSDT(),
                    e.getMaKH());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public int delete(String key) {
        int i = -1;
        String sql = "DELETE FROM KhachHang WHERE MaKH=?";
        try {
            i = XJdbc.IUD(sql, key);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
    public List<KhachHang> selectByIDs(String values, int maTK) {
        String sql;
        if (maTK == 0) {
            sql = " SELECT * FROM KHACHHANG WHERE SDT LIKE ? ";
        } else {
            sql = " SELECT * FROM KHACHHANG WHERE HOVATEN LIKE ?";
        }
        values = "%" + values + "%";
        return selectBySql(sql, values);
    }
    
    public KhachHang selectBySDT(String soDT){
        String sql = " SELECT * FROM KHACHHANG WHERE SDT LIKE ? ";
        List<KhachHang> list = this.selectBySql(sql,soDT);
        return list.isEmpty()?null:list.get(0);
    }

    public String createIDCustomer() {
        String sql = " EXEC SP_TAOMAKH ";
        return XJdbc.getValue(sql);
    }

    public KhachHang checkPhoneNumber(String SDT) {
        String sql = "SELECT * FROM KHACHHANG WHERE SDT = ? ";
        List<KhachHang> list = this.selectBySql(sql, SDT);
        return !list.isEmpty()? list.get(0) : null;
    }
}
