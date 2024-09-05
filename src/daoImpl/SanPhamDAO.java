package daoimpl;

import daos.DAOs;
import entities.SanPham;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.XJdbc;

public class SanPhamDAO extends DAOs<SanPham, String> {

    @Override
    public List<SanPham> selectBySql(String sql, Object... agrs) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) XJdbc.select(sql, agrs);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setLoaiSP(rs.getString(2));
                sp.setTenSP(rs.getString(3));
                sp.setGiaTien(Double.valueOf(rs.getString(4)));
                sp.setHinh(rs.getString(5));
                list.add(sp);
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<SanPham> selectAll() {
        String sql = "SELECT * FROM SANPHAM ";
        return this.selectBySql(sql);
    }

    @Override
    public SanPham selectByID(String MaSP) {
        String sql = " SELECT * FROM SanPham WHERE MASP = ?";
        List<SanPham> list = this.selectBySql(sql, MaSP);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public List<SanPham> selectByIDs(String val, int maTK) {
        String sql = "";
        if (maTK == 0) {
            sql += " SELECT * FROM SanPham WHERE LOAISP LIKE ?";
        } else {
            sql += " SELECT * FROM SanPham WHERE TENSANPHAM LIKE ? ";
        }
        return this.selectBySql(sql, "%" + val + "%");
    }

    @Override
    public int insert(SanPham Entity) {
        int i = - 1;
        String sql = " INSERT INTO SanPham (LOAISP,TENSANPHAM,GIATIEN,HINH) VALUES (? ,? ,? ,?)";
        Object[] values = {
            Entity.getLoaiSP(),
            Entity.getTenSP(),
            Entity.getGiaTien(),
            Entity.getHinh()
        };
        try {
            i = XJdbc.IUD(sql, values);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public int update(SanPham Entity) {
        int i = -1;
        String Sql = "UPDATE SANPHAM SET LOAISP = ? , TENSANPHAM = ? , GIATIEN = ? , HINH = ? WHERE MASP = ? ";
        Object[] values = {
            Entity.getLoaiSP(),
            Entity.getTenSP(),
            Entity.getGiaTien(),
            Entity.getHinh(),
            Entity.getMaSP()
        };
        try {
            i = XJdbc.IUD(Sql, values);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public int delete(String maSP) {
        int i = -1;
        String sql = "DELETE FROM SANPHAM WHERE MASP = ? ";
        try {
            i = XJdbc.IUD(sql, maSP);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    public String createMaSP() {
        String sql = " EXEC SP_TAOMASP ";
        return XJdbc.getValue(sql);
    }
}
