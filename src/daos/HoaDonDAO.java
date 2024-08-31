
package daos;

import entities.HoaDon;
import java.util.List;

public interface HoaDonDAO {
    List <HoaDon> selectBySql(String sql, Object...values);
    List <HoaDon> selectAll();
    List <HoaDon> selectByID(String maKH);
    List <HoaDon> selectByName(String name);
    List <HoaDon> selectHDByDay();
    List <HoaDon> selectHDByMonth();
    List <HoaDon> selectHDByYear();
    void insertHoaDon(HoaDon entity);
    HoaDon update(HoaDon Entity);
    String taoMaHoaDon();
    Double checkCountBills(String sdt);
}
