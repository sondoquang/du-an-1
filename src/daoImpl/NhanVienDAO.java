
package daoImpl;

import daos.DAOs;
import entities.NhanVien;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import utils.XJdbc;

public class NhanVienDAO extends DAOs<NhanVien, String> {

    
    
    @Override
    public int insert(NhanVien e) {
        int i = -1;
        String sql = "INSERT INTO NhanVien (MatKhau, HoVaTen, MaCV,NgaySinh,GioiTinh,DiaChi, SDT, Email, Hinh) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            i = XJdbc.IUD(
                    sql,
                    e.getMatKhau(), e.getHoVaTen(), e.getCv().getMaCV(), e.getNgaySinh(), e.isGioiTinh(), e.getDiaChi(),
                    e.getSDT(), e.getEmail(), e.getHinh());
        } catch (SQLException | ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KhachHangDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return i;
    }
    @Override
    public int update(NhanVien e) {
        int i = -1;
        String sql = "UPDATE NhanVien SET MatKhau=?, HoVaTen=?, MaCV=?, NgaySinh=?,GioiTinh=?,DiaChi=?,SDT =?,Email=?,Hinh=?  WHERE MaNV=?";
        try {
            i = XJdbc.IUD(sql,
                    e.getMatKhau(),e.getHoVaTen(),e.getCv().getMaCV(),e.getNgaySinh(), e.isGioiTinh(), e.getDiaChi(),
                e.getSDT(), e.getEmail(), e.getHinh(),e.getMaNV());
        } catch (SQLException | ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KhachHangDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return i;
    }
    @Override
    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM NhanVien";
        return selectBySql(sql);
    }

        @Override
    public List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = (ResultSet) XJdbc.select(sql, args);
                while (rs.next()) {
                    NhanVien entity = new NhanVien();
                    entity.setMaNV(rs.getString(1));
                    entity.setMatKhau(rs.getString(2));
                    entity.setHoVaTen(rs.getString(3));
                    entity.setCv(entity.getCVbyID(rs.getString(4)));
                    entity.setNgaySinh(rs.getDate(5));
                    entity.setGioiTinh(rs.getBoolean(6));
                    entity.setDiaChi(rs.getString(7));
                    entity.setSDT(rs.getString(8));
                    entity.setEmail(rs.getString(9));
                    entity.setHinh(rs.getString(10));
                    list.add(entity);
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(NhanVienDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } finally {
                rs.getStatement().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

    @Override
    public int delete(String key) {
        int i = -1;
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        try {
            i = XJdbc.IUD(sql, key);
        } catch (SQLException | ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KhachHangDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return i;
    }


    @Override
    public NhanVien selectByID(String key) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV = ?";
        List<NhanVien> list = selectBySql(sql, key);
        return !list.isEmpty() ? list.get(0) : null;
    }
    
    public String createIDEmployees(){
        String sql = "EXEC SP_TAOMANV";
        return XJdbc.getValue(sql);
    }
    
    
}
