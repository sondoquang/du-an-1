
package daoImpl;



import daos.DAOs;
import entities.ChiTietSanPham;
import java.util.List;
import utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChiTietSanPhamDAO extends DAOs<ChiTietSanPham,String>{

    @Override
    public List<ChiTietSanPham> selectBySql(String sql , Object...values) {
        List<ChiTietSanPham> list = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) XJdbc.select(sql, values);
            while(rs.next()){
                ChiTietSanPham ctsp = new ChiTietSanPham ();
                ctsp.setMaSP(rs.getString(1));
                ctsp.setMaNL(rs.getString(2));
                ctsp.setSoLuong(rs.getInt(3));
                ctsp.setGiaVon(rs.getDouble(4));
                list.add(ctsp);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ChiTietSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<ChiTietSanPham> selectAll() {
        String sql = "SELECT * FROM CHITIETSANPHAM ";
        return this.selectBySql(sql);
    }

    @Override
    public int insert(ChiTietSanPham Entity) {
        int i = -1;
        String sql = "INSERT INTO CHITIETSANPHAM VALUES (?,?,?,?)";
        Object [] values = {
            Entity.getMaSP(),
            Entity.getMaNL(),
            Entity.getSoLuong(),
            Entity.getGiaVon()
        };
        try {
            i = XJdbc.IUD(sql,values);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ChiTietSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public ChiTietSanPham selectByID(String maNL) {
        String sql = "SELECT * FROM CHITIETSANPHAM WHERE MANL = ? ";
        List <ChiTietSanPham> list = this.selectBySql(sql, maNL);
        return !list.isEmpty()?list.get(0):null;
    }
    
    public List<ChiTietSanPham> selectAllApplianceForPro(String maSP) {
        String sql = " SELECT * FROM CHITIETSANPHAM WHERE MASP = ? ";
        return this.selectBySql(sql,maSP);
    }
    
    @Override
    public int update(ChiTietSanPham Entity){
        int i = -1;
        String sql = "Update from CHITIETSANPHAM set soLuong = ? , giaVon = ? where masp = ?";
        Object [] values = {
            Entity.getSoLuong(),
            Entity.getGiaVon(),
            Entity.getMaSP()
        };
        try {
            i = XJdbc.IUD(sql, values);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ChiTietSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public int delete(String maSP) {
        int i = -1;
        String sql = " DELETE FROM CHITIETSANPHAM WHERE MASP = ? ";
        try {
            i = XJdbc.IUD(sql, maSP);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ChiTietSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
}
