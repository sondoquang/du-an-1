
package daoImpl;

import daos.HoaDonNhapDAO;
import entities.HoaDonNhap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.XJdbc;


public class HoaDonNhapImple implements HoaDonNhapDAO{

    @Override
    public List<HoaDonNhap> selectBySql(String sql, Object... values) {
        List<HoaDonNhap> list = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) XJdbc.select(sql, values);
            while(rs.next()){
                HoaDonNhap hdn = new HoaDonNhap();
                hdn.setSoHD(rs.getString("Sohdn"));
                hdn.setMaNV("MaNV");
                hdn.setNgayNH(rs.getDate("ngayNH"));
                list.add(hdn);
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e){
           throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<HoaDonNhap> selectAll() {
        String sql = "SELECT * FROM HDNHAP";
        return this.selectBySql(sql);
    }

    @Override
    public HoaDonNhap selectByID(String MAHD) {
        String sql = "SELECT * FROM HDNHAP WHERE MAHD = ? ";
        List<HoaDonNhap> list = this.selectBySql( sql);
        return list.size()>0 ? list.get(0): null;
    }

    @Override
    public String createIdHDNhap() {
        String sql = "EXEC SP_TAOIDHDNHAP";
        return XJdbc.getValue(sql);
    }

    @Override
    public HoaDonNhap insert(HoaDonNhap Entity) {
        String sql= "INSERT INTO HDNHAP VALUES(?,?,?)";
        Object [] values = {
            Entity.getSoHD(),
            Entity.getMaNV(),
            Entity.getNgayNH()
        };
        try {
            XJdbc.IUD(sql, values);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonNhapImple.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HoaDonNhapImple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Entity;
    }
}
