package daoImpl;

import daos.DAOs;
import utils.XJdbc;
import entities.HoaDon;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.XMsgBox;

public class HoaDonDAO extends DAOs<HoaDon,String>{

    @Override
    public int insert(HoaDon entity) {
        String sql = " INSERT INTO HOADON (MaKH ,MaNV ,NgayMua ,TONGTIEN ,GiamGia ,TRIGIA, trangThai) values( ? ,? ,? ,? ,? ,? ,? );";
        Object[] value = {
            entity.getMaKH(),
            entity.getMaNV(),
            entity.getNgayMua(),
            entity.getTongTien(),
            entity.getGiamGia(),
            entity.getTriGia(),
            entity.getTrangThai()
        };

        try {
            XJdbc.IUD(sql, value);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    @Override
    public List<HoaDon> selectBySql(String sql, Object... values) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) XJdbc.select(sql, values);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString(1));
                hd.setMaKH(rs.getString(2));
                hd.setMaNV(rs.getString(3));
                hd.setNgayMua(rs.getDate(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setGiamGia(rs.getDouble(6));
                hd.setTriGia(rs.getDouble(7));
                hd.setTrangThai(rs.getString(8));
                list.add(hd);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<HoaDon> selectAll() {
        String sql = "SELECT * FROM HOADON ";
        return this.selectBySql(sql);
    }

    @Override
    public HoaDon selectByID(String key) {
        String sql = " SELECT * FROM HOADON WHERE MAKH = ? ";
        List<HoaDon> list = this.selectBySql(sql, key);
        return list.isEmpty()?list.get(0):null;
    }
    
    @Override
    public int update(HoaDon Entity) {
        try {
            String sql = "UPDATE HOADON SET MANV =  ? , NgayMua = ? , TONGTIEN = ? ,GiamGia = ? , TRIGIA = ? ,TRANGTHAI = ? WHERE MAHD = ? ";
            Object [] value  = {
                Entity.getMaNV(),
                Entity.getNgayMua(),
                Entity.getTongTien(),
                Entity.getGiamGia(),
                Entity.getTriGia(),
                Entity.getTrangThai(),
                Entity.getMaHD()
            };
            XJdbc.IUD(sql , value);
            return 1;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public int delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<HoaDon> selectByID1(String maKH) {
        String sql = " SELECT * FROM HOADON WHERE MAKH = ? ";
        return this.selectBySql(sql, maKH);
    }
    
    public HoaDon selectByMaHD(String maHD){
        String sql = " SELECT * FROM HOADON WHERE MAHD = ? ";
        List <HoaDon> list = this.selectBySql(sql, maHD);
        return !list.isEmpty()?list.get(0):null;
    }
    
    public HoaDon selectByMaNV(String maNV){
        String sql = " SELECT * FROM HOADON WHERE MANV = ? ";
        List <HoaDon> list = this.selectBySql(sql, maNV);
        return !list.isEmpty()?list.get(0):null;
    }
    
    public Double checkCountBills(String makh) {
        String sql = "SELECT SUM(TRIGIA) FROM HOADON WHERE MAKH = ? AND (NgayMua > (SELECT MAX(NgayMua) FROM HOADON WHERE GiamGia <> 0 AND MAKH = ?) AND NGAYMUA <= GETDATE())";
        return XJdbc.getValue(sql, makh,makh);
    }

    public List<HoaDon> selectHDByDay() {
        String sql = " SELECT * FROM HOADON WHERE  CONVERT(DATE,NGAYMUA) BETWEEN DATEADD( DAY ,-1,CONVERT(date,getdate())) AND CONVERT(DATE,GETDATE()) ";
        return this.selectBySql(sql);
    }

    public List<HoaDon> selectHDByMonth() {
        String sql = " SELECT * FROM HOADON WHERE  CONVERT(DATE,NGAYMUA) BETWEEN DATEADD( month ,-1,CONVERT(date,getdate())) AND CONVERT(DATE,GETDATE()) ";
        return this.selectBySql(sql);
    }

    public List<HoaDon> selectHDByYear() {
        String sql = " SELECT * FROM HOADON WHERE  CONVERT(DATE,NGAYMUA) BETWEEN DATEADD( year ,-1,CONVERT(date,getdate())) AND CONVERT(DATE,GETDATE()) ";
        return this.selectBySql(sql);
    }

    public List<HoaDon> selectByName(String name) {
        String sql = " SELECT * FROM KHACHHANG KH INNER JOIN HOADON HD ON KH.MaKH = HD.MAKH WHERE KH.HOVATEN LIKE ? ";
        return this.selectBySql(sql,"%"+name+"%");
    }
    
    
    
    public HoaDon updateTrangThaiHD(HoaDon Entity) {
        try {
            String sql = "Update HoaDon set TrangThai = ? where maHD = ? ";
            Object[] value = {
                Entity.getTrangThai(),
                Entity.getMaHD()
            };
            XJdbc.IUD(sql, value);
        } catch (ClassNotFoundException | SQLException e) {
            XMsgBox.alert(null, "Lỗi truy vấn dữ liệu !");
        }
        return Entity;
    }
    
    public String taoMaHoaDon() {
        String sql = " EXEC SP_TAOMAHD ";
        return XJdbc.getValue(sql);
    }
}
