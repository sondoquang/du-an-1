
package daos;

import entities.ChiTietHDNhap;
import java.util.List;

public interface CTHDNhapDAO {
    List <ChiTietHDNhap> selectBySQl(String sql , Object...values);
    List <ChiTietHDNhap> selectAll();
    List <ChiTietHDNhap> selectByID(String maHD);
    ChiTietHDNhap insert(ChiTietHDNhap Entity);
}
