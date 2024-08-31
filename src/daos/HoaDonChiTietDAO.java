
package daos;

import entities.HoaDonChiTiet;
import java.util.List;

public interface HoaDonChiTietDAO {
    List <HoaDonChiTiet> selectBySql(String sql, Object...values);
    List <HoaDonChiTiet> selectAll();
    List <HoaDonChiTiet> selectByID(String maHD);
    void insertCTHoaDon(HoaDonChiTiet entity);
    void delete(String mahd);
}

