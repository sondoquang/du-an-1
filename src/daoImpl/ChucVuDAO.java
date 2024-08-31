/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import entities.ChucVu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbc;

/**
 *
 * @author tai30
 */
public class ChucVuDAO {

    public List<ChucVu> selectAll() {
        String sql = "SELECT * FROM ChucVu";
        List<ChucVu> list = selectBySql(sql);
        return list;
    }
    
    public ChucVu selectByID(String key) {
        String sql = "SELECT * FROM ChucVu WHERE MaCV = ?";
        List<ChucVu> list = selectBySql(sql, key);
        return !list.isEmpty() ? list.get(0) : null;
    }
    
    public List<ChucVu> selectBySql(String sql, Object... args) {
        List<ChucVu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = (ResultSet) XJdbc.select(sql, args);
                while (rs.next()) {
                    ChucVu entity = new ChucVu();
                    entity.setMaCV(rs.getString(1));
                    entity.setTenCV(rs.getString(2));
                    list.add(entity);
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(ChucVuDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } finally {
                rs.getStatement().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
}

