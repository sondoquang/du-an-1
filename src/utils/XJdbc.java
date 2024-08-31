/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ndhlt
 */
public final class XJdbc {

    public static final String username = "sa";
    public static final String password = "Son15@2";
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection con = null;

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String setURL(String database) {
        return "jdbc:sqlserver://localhost:1433;database=" + database + "; encrypt=false;";
    }

    private static String setURL(int port, String database) {
        return "jdbc:sqlserver://localhost:" + port + ";database=" + database + "; encrypt=false;";
    }

    public static void openConnection(String database) throws ClassNotFoundException, SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(setURL(database), username, password);
//            XJdbc.database = database;
        }
    }

    public static void openCustomConnection(String customUrl, String user, String pass) throws ClassNotFoundException, SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(customUrl, user, pass);
//            String[] parts = customUrl.split(";");
//            for(String part: parts){
//                if(part.startsWith("database=")){
//                    XJdbc.database = part.substring(9);
//                    break;
//                }
//            }

        }
    }

    public static void openConnection(int port, String database) throws ClassNotFoundException, SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(setURL(port, database), username, password);
//            XJdbc.database = database;
        }
    }

    public static void openConnection(int port, String database, String user, String pass) throws ClassNotFoundException, SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(setURL(port, database), user, pass);
//            XJdbc.database = database;
        }
    }

    public static void closeConnection() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
//            XJdbc.database = null;
        }
    }

    public static final Object select(String sql, Object... args) throws SQLException, ClassNotFoundException {
        ResultSet rs;
        Object packedRs;
        if (args.length > 0) {
            PreparedStatement pst = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pst.setObject(i + 1, args[i]);
            }
            rs = pst.executeQuery();
        } else {
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
        }
        packedRs = rs;
        return packedRs;
    }

    public static final int IUD(String sql, Object... args) throws SQLException, ClassNotFoundException {
        int result;
        try (PreparedStatement prst = con.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
//                if (args[i] instanceof Date date) {
//                    prst.setDate(i + 1, date);
//                } else if (args[i] instanceof String string) {
//                    prst.setString(i + 1, string);
//                } else if(args[i] instanceof Double){
//                    prst.setDouble(i + 1, (double) args[i]);
//                } else if(args[i] instanceof Integer){
//                    prst.setInt(i + 1, (int) args[i]);
//                }
//                else{
                    prst.setObject(i + 1, args[i]);
//                }
            }
            result = prst.executeUpdate();
        }
        return result;
    }

    public static final Object callNoOutput(String sql, Object... args) throws SQLException, ClassNotFoundException {
        boolean result;
        CallableStatement cst = con.prepareCall(sql);
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                cst.setObject(i + 1, args[i]);
            }
        }
        result = cst.execute();
        Object packedRs = cst.getResultSet();
        Integer updateCount = cst.getUpdateCount();
        return result ? packedRs : updateCount;
    }

    public static final Object callWithOutput(String sql, int output, int sqlType, Object... args) throws SQLException, ClassNotFoundException {
//        boolean result;
        CallableStatement cst = con.prepareCall(sql);
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                cst.setObject(i + 1, args[i]);
            }
            cst.registerOutParameter(output, sqlType);
        }
        cst.execute();
//        Object packedRs = cst.getResultSet();
//        Integer updateCount = cst.getUpdateCount();
        return cst.getObject(output);
    }

    public static <T> T getValue(String sql, Object... values) {
        try {
            ResultSet rs = (ResultSet) XJdbc.select(sql, values);
            rs.next();
            return (T) rs.getObject(1);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(XJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
