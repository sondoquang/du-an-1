
package daoImpl;

import daos.DAOs;
import entities.NguyenLieu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.XJdbc;

public class NguyenLieuDao extends DAOs<NguyenLieu, String> {
 @Override
     public List<NguyenLieu> selectAll() {
        String sql = "SELECT * FROM NGUYENLIEU";
        return selectBySql(sql);
    }

    @Override
    public List<NguyenLieu> selectBySql(String sql, Object... args) {
        List<NguyenLieu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = (ResultSet) XJdbc.select(sql, args);
                while (rs.next()) {
                    NguyenLieu entity = new NguyenLieu(
                    rs.getString("MaNL"), 
                    rs.getString("TenNL"),
                    rs.getDouble("GIATIEN"),
                    rs.getInt("TonKho"),
                    rs.getInt("ToiThieu"),
                    rs.getString("DONVI"),
                    rs.getString("Hinh"),
                    rs.getDouble("minGia")
                    );
                    list.add(entity);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NguyenLieuDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                rs.getStatement().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

    @Override
    public NguyenLieu selectByID(String key) {
        String sql = "SELECT * FROM NGUYENLIEU WHERE MaNL = ?";
        List<NguyenLieu> list = selectBySql(sql, key);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public int insert(NguyenLieu e) {
        String sql = "INSERT INTO NGUYENLIEU (TenNL, GIATIEN, TonKho, ToiThieu, Hinh, DONVI) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            return XJdbc.IUD(
                sql,
                e.getTenNL(),
                e.getGiaTien(),
                e.getTonKho(),
                e.getToiThieu(),
                e.getHinh(),
                e.getDonVi()
            );
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(NguyenLieuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int update(NguyenLieu e) {
        String sql = "UPDATE NGUYENLIEU SET TenNL = ?, ToiThieu = ?, Hinh = ?, DONVI = ? WHERE MaNL = ?";
        try {
            return XJdbc.IUD(
                sql,
                e.getTenNL(),
                e.getToiThieu(),
                e.getHinh(),
                e.getDonVi(),
                e.getMaNL()
            );
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(NguyenLieuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int delete(String key) {
        String sql = "DELETE FROM NGUYENLIEU WHERE MaNL = ?";
        try {
            XJdbc.IUD(sql, key);
            return 1;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(NguyenLieuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public String createIDIngre(){
        String sql = " EXEC SP_TAOMANL ";
        return XJdbc.getValue(sql);
    }
    
    public NguyenLieu datThem(NguyenLieu Entity) {
        String sql = "UPDATE NGUYENLIEU SET TONKHO = TONKHO + ? , GIATIEN = GIATIEN + ? WHERE MANL = ? ";
        Object values [] = {
            Entity.getTonKho(),
            Entity.getGiaTien(),
            Entity.getMaNL()
        };
        try {
            XJdbc.IUD(sql, values);
        } catch(SQLException | ClassNotFoundException ex) {
            Logger.getLogger(NguyenLieuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Entity;
    }
}
