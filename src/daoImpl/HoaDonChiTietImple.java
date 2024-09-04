
package daoImpl;

import entities.HoaDonChiTiet;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbc;
import java.sql.ResultSet;
import daos.HoaDonChiTietDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HoaDonChiTietImple implements HoaDonChiTietDAO {

    @Override
    public List<HoaDonChiTiet> selectBySql(String sql, Object... values) {
        List <HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) XJdbc.select(sql, values);
            while(rs.next()){
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaHD(rs.getString("mahd"));
                hdct.setMaSP(rs.getString("masp"));
                hdct.setSoLuong(rs.getInt("SoLuong"));
                hdct.setDonGia(rs.getDouble("giaTien"));
                hdct.setKhuyenMai(rs.getDouble("khuyenMai"));
                hdct.setThanhTien(rs.getDouble("Thanhtien"));
                hdct.setGhiChu(rs.getString("ghichu"));
                list.add(hdct);
            } 
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(HoaDonChiTietImple.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    @Override
    public List<HoaDonChiTiet> selectAll() {
        String sql = "SELECT * FROM HOADONCHITIET ";
        return this.selectBySql(sql);
    }

    @Override
    public List<HoaDonChiTiet> selectByID(String maHD) {
        String sql = " SELECT * FROM HOADONCHITIET WHERE MAHD = ? ";
        return this.selectBySql(sql, maHD);
    }
    
    public List<HoaDonChiTiet> selectByMaSP(String maSP){
        String sql = " SELECT * FROM HOADONCHITIET WHERE MASP = ? ";
        return this.selectBySql(sql, maSP);
    }

    @Override
    public void insertCTHoaDon(HoaDonChiTiet entity) {
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
            XJdbc.IUD(sql, values);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HoaDonChiTietImple.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String mahd) {
        String sql = "DELETE FROM HOADONCHITIET WHERE MAHD = ?";
        try {
            XJdbc.IUD(sql,mahd);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HoaDonChiTietImple.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
