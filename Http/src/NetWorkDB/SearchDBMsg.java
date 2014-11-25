/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NetWorkDB;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Administrator
 */
public class SearchDBMsg {

    private static DatabaseMetaData meta;
    private static ResultSetMetaData fields;

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, UnsupportedEncodingException {
        Connection con;
        Statement stmt , stmt1;
        ResultSet rs, rs1;

        Class jdbcDriver = Class.forName("com.mysql.jdbc.Driver");
        java.sql.DriverManager.registerDriver((Driver) jdbcDriver.newInstance());
        /*
         String dbUrl = "jdbc:mysql://222.201.101.15:3306/STUDENTDB";        
         String dbUser="myuser";
         String dbPwd="mysql";
         */
        /*
         jdbc:mysql://222.201.101.15:3306/MYSTUDENTDB
         myroot
         my999
         */
        String dbUrl = "jdbc:mysql://localhost:3307/db";
        String dbUser = "root";
        String dbPwd = "123";

        con = java.sql.DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        stmt = con.createStatement();
        /*
        //获取表单信息
        meta = con.getMetaData();
          
        rs = meta.getTables(null, null, null, new String[]{"TABLE"});
        while(rs.next()){
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
        }
      */
        //boyandgirls courses
        //获取表的字段名
        /*
        rs = stmt.executeQuery("select * from user");
        fields = rs.getMetaData();
        int n = fields.getColumnCount();
        for (int i = 1; i <= n; i++) {
            System.out.println(fields.getColumnLabel(i));
        }
     */
         
        String aName = new String("张茂盛".getBytes("GB2312"),"ISO-8859-1");
        String aClass = new String("计算机1101".getBytes("GB2312"),"ISO-8859-1");
       //stmt.executeUpdate("insert into boyandgirls(YOURNO,YOURNAME,AGE,CLASS) values('20111003413',aName,'21',aClass)");
        stmt.executeUpdate("insert into user(id,name,sex) VALUES('20111003415','zms1','male');");
        
        rs = stmt.executeQuery("select * from user");
        while(rs.next()){
            String sNO = rs.getString(1);
            String sName = rs.getString(2);
            /*int sage = rs.getInt(4);
            String sClass;
            if(rs.getString(5)==null){
                sClass ="";
            }else{
                sClass = rs.getString(5);
            }
            */
            //sName = new String(sName.getBytes("ISO-8859-1"),"GB2312");
            //sClass = new String(sClass.getBytes("ISO-8859-1"),"GB2312");
            
            System.out.println("NO.: "+sNO+",Name: "+sName);
        }              
       
        con.close();
        /*
        
       
         * 
         /*
         stmt = con.createStatement();
         stmt1 = con.createStatement();
        
         String aName = new String("张茂盛".getBytes("GB2312"),"ISO-8859-1");
         String aClass = new String("计算机1101".getBytes("GB2312"),"ISO-8859-1");
         //stmt.executeUpdate("delete from students where no='20111003413'");
         stmt.executeUpdate("insert into STUDENTS(NO,NAME,AGE,CLASS)"
         +"VALUES('20111003413','"+aName+"',20,'"+aClass+"');");
         rs = stmt.executeQuery("select no,name,age,class from students");
        
         while(rs.next()){
         String sNO = rs.getString(1);
         String sName = rs.getString(2);
         int sage = rs.getInt(3);
         String sClass = rs.getString(4);
            
         sName = new String(sName.getBytes("ISO-8859-1"),"GB2312");
         sClass = new String(sClass.getBytes("ISO-8859-1"),"GB2312");
            
         System.out.println("NO.: "+sNO+",Name: "+sName+",Age: "+sage+",Class: "+sClass);
         }      
      
         rs.close();
         stmt.close();  * */
    }
}
