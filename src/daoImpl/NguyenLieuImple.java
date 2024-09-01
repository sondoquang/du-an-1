
package daoimpl;

import entities.NguyenLieu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbc;
import daoImpl.NguyenLieuDao;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NguyenLieuImple extends NguyenLieuDao{

    public List<NguyenLieu> selectBySQl(String sql, Object... agrs) {
        List<NguyenLieu> list = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) XJdbc.select(sql, agrs);
            while(rs.next()){
                NguyenLieu nl = new NguyenLieu();
                nl.setMaNL(rs.getString("MaNL"));
                nl.setTenNL(rs.getString("TenNL"));
                nl.setGiaTien(rs.getDouble("giaTien"));
                nl.setTonKho(rs.getInt("tonkho"));
                nl.setToiThieu(rs.getInt("toithieu"));
                nl.setDonVi(rs.getString("donVi"));
                nl.setHinh(rs.getString("hinh"));
                nl.setMinGia(rs.getDouble("minGia"));
                list.add(nl);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(NguyenLieuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<NguyenLieu> selectAll() {
        String sql = " SELECT * FROM NGUYENLIEU ";
        return this.selectBySQl(sql);
    }

    @Override
    public NguyenLieu selectByID(String maNL) {
        String sql = "SELECT * FROM NGUYENLIEU WHERE MANL = ? ";
        List <NguyenLieu> list = this.selectBySQl(sql, maNL);
        return !list.isEmpty()? list.get(0):null;
    }

    public List<NguyenLieu> selectByName(String name) {
        String sql = " SELECT * FROM NGUYENLIEU WHERE TenNL like ? ";
        return this.selectBySQl(sql, "%"+name+"%");
    }

    public Integer checkNLTonKhoByMaNL(String maNL) {
        String sql = "SELECT TONKHO FROM NGUYENLIEU WHERE MANL = ? ";
        return XJdbc.getValue(sql, maNL);
    }

    public void updateTKNguyenLieu(NguyenLieu Entity) {
        String sql = " UPDATE NGUYENLIEU SET TONKHO = TONKHO - ? WHERE MANL = ?";
        try {
            Object [] values ={
                Entity.getTonKho(),
                Entity.getMaNL()
            };
            XJdbc.IUD(sql, values);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(NguyenLieuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateGiaTien(Double giaTien ,String manl) {
        String sql = "UPDATE NGUYENLIEU SET GIATIEN = GIATIEN - ?  where MaNL = ? ";
        Object [] value = {
            giaTien,
            manl
        };
        try {
            XJdbc.IUD(sql, value);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(NguyenLieuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
