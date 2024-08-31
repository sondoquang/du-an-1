
package daos;


import entities.HoaDonNhap;
import java.util.List;

public interface HoaDonNhapDAO {
    List <HoaDonNhap> selectBySql(String sql, Object...values);
    List <HoaDonNhap> selectAll();
    HoaDonNhap selectByID(String maHD);
    String createIdHDNhap();
    HoaDonNhap insert(HoaDonNhap Entity);
}
