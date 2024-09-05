
package daoImpl;

import daos.DAOs;
import entities.HoaDonChiTiet;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HoaDonChiTietDAO extends DAOs <HoaDonChiTiet,String> {

    @Override
    public List<HoaDonChiTiet> selectBySql(String sql, Object... values) {
        List <HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) XJdbc.select(sql, values);
            while(rs.next()){
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaHD(rs.getString(1));
                hdct.setMaSP(rs.getString(2));
                hdct.setSoLuong(rs.getInt(3));
                hdct.setDonGia(rs.getDouble(4));
                hdct.setKhuyenMai(rs.getDouble(5));
                hdct.setThanhTien(rs.getDouble(6));
                hdct.setGhiChu(rs.getString(7));
                list.add(hdct);
            } 
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(HoaDonChiTietDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    @Override
    public List<HoaDonChiTiet> selectAll() {
        String sql = "SELECT * FROM HOADONCHITIET ";
        return this.selectBySql(sql);
    }

    @Override
    public HoaDonChiTiet selectByID(String maHD) {
        String sql = " SELECT * FROM HOADONCHITIET WHERE MAHD = ? ";
        List <HoaDonChiTiet> list = this.selectBySql(sql,maHD);
        return list.isEmpty()?list.get(0):null;
    }
    
    public List<HoaDonChiTiet> selectByIDs(String maSP, int maTK){
        String sql = "";
        if(maTK == 0)
            sql += " SELECT * FROM HOADONCHITIET WHERE MASP = ? ";
        else
            sql += " SELECT * FROM HOADONCHITIET WHERE MAHD = ? ";
        return this.selectBySql(sql, maSP);
    }

    @Override
    public int insert(HoaDonChiTiet entity) {
        int i = -1;
        String sql = " INSERT INTO HOADONCHITIET values (?,?,?,?,?,?,?)";
        Object [] values = {
            entity.getMaHD(),
            entity.getMaSP(),
            entity.getSoLuong(),
            entity.getDonGia(),
            entity.getKhuyenMai(),
            entity.getThanhTien(),
            entity.getGhiChu()
        };
        try {
            i = XJdbc.IUD(sql, values);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HoaDonChiTietDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public int delete(String maHD) {
        int i = -1;
        String sql = "DELETE FROM HOADONCHITIET WHERE MAHD = ?";
        try {
            i = XJdbc.IUD(sql,maHD);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HoaDonChiTietDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public int update(HoaDonChiTiet e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
