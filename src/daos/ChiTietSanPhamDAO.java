
package daos;

import entities.ChiTietSanPham;
import java.util.List;

public interface ChiTietSanPhamDAO {
    List<ChiTietSanPham> selectBySql(String sql , Object...values);
    List<ChiTietSanPham> selectAll();
    List<ChiTietSanPham> selectAllNguyenLieu(String maSP);
    ChiTietSanPham insert(ChiTietSanPham Entity);
    ChiTietSanPham update(ChiTietSanPham Entity);
    void detele(String masp);
}
