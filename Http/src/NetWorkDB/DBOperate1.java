/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NetWorkDB;

import com.mysql.jdbc.PreparedStatement;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class DBOperate1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, UnsupportedEncodingException {
        Connection con;
        PreparedStatement stmt;
        ResultSet rs;
        
        Class jdbcDriver = Class.forName("com.mysql.jdbc.Driver");
        java.sql.DriverManager.registerDriver((Driver) jdbcDriver.newInstance());
        
        
        /*
        String dbUrl = "jdbc:mysql://222.201.101.15:3306/STUDENTDB";        
        String dbUser="myuser";
        String dbPwd="mysql";
         */     
        
        String dbUrl = "jdbc:mysql://localhost:3307/s2sh";
        String dbUser="root";
        String dbPwd="123";
      
        con = java.sql.DriverManager.getConnection(dbUrl, dbUser, dbPwd);                       
        
        String aName = new String("张茂盛".getBytes("GB2312"),"ISO-8859-1");
        String aClass = new String("计算机1101".getBytes("GB2312"),"ISO-8859-1");
        //stmt.executeUpdate("delete from students where no='20111003413'");
        //stmt.executeUpdate("insert into STUDENTS(NO,NAME,AGE,CLASS)"
          //      +"VALUES('20111003413','"+aName+"',20,'"+aClass+"');");
        //rs = stmt.executeQuery("select no,name,age,class from students");
        
        String sql = "insert into book(bookName,bookNo,bookPrice) values('搜狗之路','1232-4-23-434',23.4)";
        stmt = (PreparedStatement) con.prepareStatement(sql);
        stmt.executeUpdate(sql);
        //rs = stmt.executeQuery();        
        /*
        while(rs.next()){                   
            String bookName = rs.getString(2);            
            System.out.println(bookName);
        } 
        * */        
        //rs.close();
        stmt.close();
    }
}
