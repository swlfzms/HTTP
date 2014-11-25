/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NetWorkDB;

import java.sql.*;
import model.User;

/**
 *
 * @author Administrator
 */
public class ConnectionProvider {

    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private String dbUrl = "jdbc:mysql://localhost:3307/kdwb";
    private String dbUser = "root";
    private String dbPwd = "123";

    public ConnectionProvider() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class jdbcDriver = Class.forName("com.mysql.jdbc.Driver");
        java.sql.DriverManager.registerDriver((Driver) jdbcDriver.newInstance());
        con = java.sql.DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        stmt = con.createStatement();
    }

    public String login(User user) throws SQLException {
        String sql = "select u_name from user where u_name='" + user.getUsername() + "'";
        
        rs = stmt.executeQuery(sql);        
        if (rs == null||!rs.next()) {
            return "<not exist>";
        } else {
            
            sql = "select * from user where u_name='" + user.getUsername() + "' and u_pwd='" + user.getPassword() + "'";
            rs = stmt.executeQuery(sql);
            if (rs == null||!rs.next()) {
                return "<pass wrong>";
            } else {
                while (rs.next()) {
                    String username = rs.getString(1);
                    String password = rs.getString(2);
                    System.out.println(username + " " + password);
                }
                return "<success>";
            }
        }
    }

    public void close() throws SQLException {
        stmt.close();
        rs.close();
    }
}
