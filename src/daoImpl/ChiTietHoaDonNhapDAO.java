
package daoImpl;


import daos.DAOs;
import entities.ChiTietHDNhap;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ChiTietHoaDonNhapDAO extends DAOs<ChiTietHDNhap,String>{

    @Override
    public List<ChiTietHDNhap> selectBySql(String sql , Object...values) {
       List<ChiTietHDNhap> list = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) XJdbc.select(sql, values);
            while(rs.next()){
                ChiTietHDNhap cthdn = new ChiTietHDNhap();
                cthdn.setSoHDN(rs.getString(1));
                cthdn.setMaNL(rs.getString(2));
                cthdn.setSoLuong(rs.getInt(3));
                cthdn.setGhiChu(rs.getString(4));
                list.add(cthdn);
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e){
           throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<ChiTietHDNhap> selectAll() {
        String sql = " SELECT * FROM CTHDNhap";
        return this.selectBySql(sql);
    }
    
    @Override
    public ChiTietHDNhap selectByID(String maHD) {
        String sql = "SELECT * FROM CTHDNhap";
        List<ChiTietHDNhap> list = this.selectBySql(sql, maHD);
        return list.isEmpty()?null:list.get(0);
    }
    
    public List <ChiTietHDNhap> selectByIDs(String maHD) {
        String sql = "SELECT * FROM CTHDNhap";
        return this.selectBySql(sql, maHD);
    }

    @Override
    public int insert(ChiTietHDNhap Entity) {
        int i = -1;
       String sql = "INSERT INTO CTHDNhap (SOHDN,MANL,SOLUONG,GHICHU)VALUES (?,?,?,?);";
        Object values [] = {
            Entity.getSoHDN(),
            Entity.getMaNL(),
            Entity.getSoLuong(),
            Entity.getGhiChu()
        };
        try {
            i = XJdbc.IUD(sql,values);
        } catch (ClassNotFoundException | NumberFormatException | SQLException e){
           throw new RuntimeException(e);
        }
        return i;
    }

    @Override
    public int update(ChiTietHDNhap e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
