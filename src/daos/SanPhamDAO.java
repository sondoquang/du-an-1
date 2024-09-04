
package daos;

import entities.SanPham;
import java.util.List;

public interface SanPhamDAO {
    List <SanPham> selectBySQl(String sql ,Object...agrs);
    List <SanPham> getItems(String loaisp);
    List <SanPham> getItemsByID(String tensp);
    SanPham getItemsByMaSP(String MaSP);
    void insertSanPham(SanPham Entity);
    String createMaSP();
    SanPham update(SanPham Entity);
    void delete(String maSP);
}
