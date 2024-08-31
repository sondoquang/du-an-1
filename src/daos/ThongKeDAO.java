    
package daos;

import java.util.List;

public interface ThongKeDAO {
    List<Object[]> getListArray(String sql, String[] cols, Object... values);
    List<Object[]> getThongKe();
    List<Object[]> getDTSP();
    List<Object[]> getDTKH();
    List<Object[]> getDTNV();
    List<Object[]> getDTTMonth();
    List<Object[]> getDTTDAY();
    List<Object[]> getDTTYEAR();
    List<Object[]> getTop10SP();
    
}
