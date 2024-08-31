/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import utils.XJdbc;
import utils.XTable;

/**
 *
 * @author ndhlt
 */
public class SummaryDAO {

    public DefaultTableModel getTopNV() throws SQLException, ClassNotFoundException {
        String[] headers = new String[3];
        String sql = "{call}";
        ResultSet rs = (ResultSet) XJdbc.callNoOutput(sql);
        return XTable.creatTableModel(headers, rs, false);
    }
    
    public DefaultTableModel getTopProd() throws SQLException, ClassNotFoundException {
        String[] headers = new String[3];
        String sql = "{call}";
        ResultSet rs = (ResultSet) XJdbc.callNoOutput(sql);
        return XTable.creatTableModel(headers, rs, false);
    }
    
    public int getCurrentSalesCount() throws SQLException, ClassNotFoundException{
        String sql = "{call}";
        return (int) XJdbc.callWithOutput(sql, 1, Types.INTEGER);
    }
}
