
package daoImpl;

import daos.ThongKeDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbc;

public class ThongKeImple implements ThongKeDAO{

    @Override
    public List<Object[]> getListArray(String sql, String[] cols, Object... values) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = (ResultSet) XJdbc.select(sql, values);
            while(rs.next()){
                Object [] vals = new Object[cols.length]; 
                for(int i=0 ; i<cols.length ; i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
             return list;
        } catch (ClassNotFoundException | SQLException e){
             throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object[]> getThongKe() {
        String sql = " EXEC SP_TKCHART ";
        String cols [] = {"THANG","SUMDT","SUMSP","SUMHD"};
        return this.getListArray(sql, cols);
    }

    @Override
    public List<Object[]> getDTSP() {
        String sql = "EXEC SP_TOPSANPHAM 100";
        String cols [] = {"MASP","TENSANPHAM","TONGSL","DOANHTHU"};
        return this.getListArray(sql, cols);
    }

    @Override
    public List<Object[]> getDTKH() {
        String sql = "exec SP_TOPKHACHHANG 100";
        String cols [] = {"MAKH","HOVATEN","TONGHD","TONGGIATRI"};
        return this.getListArray(sql, cols);
    }

    @Override
    public List<Object[]> getDTNV() {
        String sql = "exec SP_TOPNHANVIEN 100";
        String cols [] = {"MANV","HOVATEN","TONGHD","TONGGIATRI"};
        return this.getListArray(sql, cols);
    }

    @Override
    public List<Object[]> getDTTMonth() {
        String sql = " EXEC SP_DOANHTHUTHEOTHANG ";
        String cols [] = {"THANG","GIAVON","DOANHTHU","TIENLOI","TIENLO"};
        return this.getListArray(sql, cols);
    }
    
    @Override
    public List<Object[]> getDTTDAY() {
        String sql = " EXEC SP_DOANHTHUTHEONGAY";
        String cols [] = {"NGAY","GIAVON","DOANHTHU","TIENLOI","TIENLO"};
        return this.getListArray(sql, cols);
    }
    @Override
    public List<Object[]> getDTTYEAR() {
        String sql = " EXEC SP_DOANHTHUTHEONAM ";
        String cols [] = {"NAM","GIAVON","DOANHTHU","TIENLOI","TIENLO"};
        return this.getListArray(sql, cols);
    }

    @Override
    public List<Object[]> getTop10SP() {
        String sql = "exec SP_TOPSANPHAM 10";
        String cols [] = {"MASP","TENSANPHAM","TONGSL","DOANHTHU"};
        return this.getListArray(sql, cols);
    }
    
    
}
