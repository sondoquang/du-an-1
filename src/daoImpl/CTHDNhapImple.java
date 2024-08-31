
package daoImpl;

import daos.CTHDNhapDAO;
import entities.ChiTietHDNhap;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CTHDNhapImple implements CTHDNhapDAO{

    @Override
    public List<ChiTietHDNhap> selectBySQl(String sql , Object...values) {
       List<ChiTietHDNhap> list = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) XJdbc.select(sql, values);
            while(rs.next()){
                ChiTietHDNhap cthdn = new ChiTietHDNhap();
                cthdn.setSoHDN(rs.getString("sohdn"));
                cthdn.setMaNL(rs.getString("MaNL"));
                cthdn.setSoLuong(rs.getInt("soLuong"));
                cthdn.setGhiChu(rs.getString("ghiChu"));
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
        return this.selectBySQl(sql);
    }

    @Override
    public List <ChiTietHDNhap> selectByID(String maHD) {
        String sql = "SELECT * FROM CTHDNhap";
        return this.selectBySQl(sql, maHD);
    }

    @Override
    public ChiTietHDNhap insert(ChiTietHDNhap Entity) {
       String sql = "INSERT INTO CTHDNhap (SOHDN,MANL,SOLUONG,GHICHU)VALUES (?,?,?,?);";
        Object values [] = {
            Entity.getSoHDN(),
            Entity.getMaNL(),
            Entity.getSoLuong(),
            Entity.getGhiChu()
        };
        try {
            XJdbc.IUD(sql,values);
        } catch (ClassNotFoundException | NumberFormatException | SQLException e){
           throw new RuntimeException(e);
        }
        return Entity;
    }
    
}
