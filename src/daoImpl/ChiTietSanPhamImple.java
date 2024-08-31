
package daoImpl;


import daos.ChiTietSanPhamDAO;
import entities.ChiTietSanPham;
import java.util.List;
import utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChiTietSanPhamImple implements ChiTietSanPhamDAO{

    @Override
    public List<ChiTietSanPham> selectBySql(String sql , Object...values) {
        List<ChiTietSanPham> list = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) XJdbc.select(sql, values);
            while(rs.next()){
                ChiTietSanPham ctsp = new ChiTietSanPham ();
                ctsp.setMaNL(rs.getString("maNl"));
                ctsp.setMaSP(rs.getString("maSP"));
                ctsp.setSoLuong(rs.getInt("soluong"));
                ctsp.setGiaVon(rs.getDouble("giaVon"));
                list.add(ctsp);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ChiTietSanPhamImple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<ChiTietSanPham> selectAll() {
        String sql = "SELECT * FROM CHITIETSANPHAM ";
        return this.selectBySql(sql);
    }

    @Override
    public ChiTietSanPham insert(ChiTietSanPham Entity) {
        String sql = "INSERT INTO CHITIETSANPHAM VALUES (?,?,?,?)";
        Object [] values = {
            Entity.getMaSP(),
            Entity.getMaNL(),
            Entity.getSoLuong(),
            Entity.getGiaVon()
        };
        try {
            XJdbc.IUD(sql,values);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ChiTietSanPhamImple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Entity;
    }

    @Override
    public List<ChiTietSanPham> selectAllNguyenLieu(String maSP) {
        String sql = " SELECT * FROM CHITIETSANPHAM WHERE MASP = ? ";
        return this.selectBySql(sql,maSP);
    }
    
    @Override
    public ChiTietSanPham update(ChiTietSanPham Entity){
        String sql = "Update from CHITIETSANPHAM set soLuong = ? , giaVon = ? where masp = ?";
        Object [] values = {
            Entity.getSoLuong(),
            Entity.getGiaVon(),
            Entity.getMaSP()
        };
        try {
            XJdbc.IUD(sql, values);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ChiTietSanPhamImple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Entity;
    }
    

    @Override
    public void detele(String masp) {
        String sql = " DELETE FROM CHITIETSANPHAM WHERE MASP = ? ";
        try {
            XJdbc.IUD(sql, masp);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ChiTietSanPhamImple.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
