package Servlet;

import java.sql.*;

import com.mysql.cj.exceptions.StreamingNotifiable;
import com.mysql.cj.jdbc.Driver;

import javax.servlet.RequestDispatcher;

public class JDBCdemo {
    public static void selectAll(){
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/studentlogin?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            String sql="select * from studentid";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getInt("account")+","+rs.getString("studentname"));
            }
            close(rs,stmt,con);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static boolean login(String studentID,String password){
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            String sql="select * from stulogin where stuID='"+studentID+"'and password='"+password+"';";
            rs=stmt.executeQuery(sql);
            if (rs.next()){
                return true;
            }
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs,stmt,con);
        }
        return false;
    }
    public static String selectName(String studentID){
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            String sql="select * from stulogin where stuID='"+studentID+"';";
            rs=stmt.executeQuery(sql);
            if (rs.next()){
                return rs.getString("stuName");
            }
            else
                return "";
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs,stmt,con);
        }
        return "";
    }
    public static void register(String studentID,String password,String stuName,String teleNum,String email){
        Connection con=null;
        Statement stmt=null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            String sql="insert into stulogin values ('"+studentID+"','"+password+"','"+stuName+"','"+teleNum+"','"+email+"');";
            stmt.execute(sql);
            close(null,stmt,con);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(null,stmt,con);
        }
    }
    public static void info(String studentID,String [] infoData){
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            String sql="select * from stulogin where stuID='"+studentID+"';";
            rs=stmt.executeQuery(sql);
            if(rs.next()){
                for(int n=0;n<5;n++){
                    infoData[n]=rs.getString(n+1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs,stmt,con);
        }
    }
    public static boolean changeInfo(String studentID,String newTele,String newEmail){
        Connection con=null;
        Statement stmt=null;
        String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String sql="update stulogin SET teleNum='"+newTele+"',email='"+newEmail+"' WHERE stuID='"+studentID+"';";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            int columns=stmt.executeUpdate(sql);
            return columns != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            close(null,stmt,con);
        }
    }
    public static String selectPassword(String studentID){
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String sql="select * from stulogin where stuID='"+studentID+"';";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs,stmt,con);
        }
        return "";
    }
    public static boolean changePassword(String studentID,String oldPassword,String newPassword){
        Connection con=null;
        Statement stmt=null;
        String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String sql="update stulogin SET password='"+newPassword+"' WHERE stuID='"+studentID+"' and password='"+oldPassword+"';";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            int columns=stmt.executeUpdate(sql);
            return columns != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            close(null,stmt,con);
        }
    }
    public static void displayCourse(String[][] data){
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String sql="select * from selectCourse";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            int i=0;
            while(rs.next()){
                for(int j=0;j<5;j++){
                    data[i][j]=rs.getString(j+1);
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs,stmt,con);
        }
    }
    public static boolean checkCourse(String studentID,String courseID){
        if(courseID==null)
            return true;
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String sql="select * from stucourse where stuID='"+studentID+"' and courseID='"+courseID+"';";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs,stmt,con);
        }
        return false;
    }
    public static boolean checkCourseForDisSelect(String studentID,String courseID){
        if(courseID==null)
            return false;
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String sql="select * from stucourse where stuID='"+studentID+"' and courseID='"+courseID+"';";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs,stmt,con);
        }
        return false;
    }
    public static void selectCourse(String studentID,String courseID){
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String sql1;
        String sql2;
        int temp=0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            if(courseID==null)
                return;
            sql1="insert into stucourse(stuID,courseID) values ('"+studentID+"','"+courseID+"')";
            stmt.execute(sql1);
            sql2="select * from selectcourse where courseID='"+courseID+"';";
            rs=stmt.executeQuery(sql2);
            if(rs.next())
                temp=rs.getInt("amount");
            sql2="update selectcourse set amount='"+(temp-1)+"' where courseID='"+courseID+"';";
            stmt.execute(sql2);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs,stmt,con);
        }
    }
    public static void disSelectCourse(String studentID,String courseID){
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String sql1;
        String sql2;
        int temp=0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            if(courseID==null)
                return;
            sql1="delete from stucourse where stuID='"+studentID+"' and courseID='"+courseID+"';";
            stmt.execute(sql1);
            sql2="select * from selectcourse where courseID='"+courseID+"';";
            rs=stmt.executeQuery(sql2);
            if(rs.next())
                temp=rs.getInt("amount");
            sql2="update selectcourse set amount='"+(temp+1)+"' where courseID='"+courseID+"';";
            stmt.execute(sql2);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs,stmt,con);
        }
    }
    public static void displaySelectCourse(String studentID,String[][] course){
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String sql1="select * from stucourse where stuID='"+studentID+"';";
        String sql2="";
        String[][] temp=new String[5][2];
        for(int i=0;i<5;i++){
            for(int j=0;j<2;j++){
                temp[i][j]= "";
            }
        }
        int count=0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql1);
            while(rs.next()){
                for(int j=0;j<2;j++){
                    temp[count][j]=rs.getString(j+2);
                }
                count++;
            }
            for(int i=0;i<5;i++){
                if(temp[i][1]==null)
                    continue;
                else{
                    sql2="select * from selectCourse where courseID='"+temp[i][1]+"';";
                    rs=stmt.executeQuery(sql2);
                    if(rs.next()){
                        for(int j=0;j<4;j++){
                            course[i][j]=rs.getString(j+1);
                        }
                    }
                }
            }
            close(rs,stmt,con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void displayScore(String studentID,String[][] course){
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String sql="select * from stuScore where stuID='"+studentID+"';";
        int count=0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                for(int i=0;i<3;i++){
                    course[count][i]=rs.getString(i+3);
                }
                count++;
            }
            close(rs,stmt,con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void displaySocialWork(String studentID,String[][] data){
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        String url="jdbc:mysql://localhost:3306/studentdata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String sql="select * from stuSocialWork where stuID='"+studentID+"';";
        int count=0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,"root","123456");
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                for(int i=0;i<2;i++){
                    data[count][i]=rs.getString(i+3);
                }
                count++;
            }
            close(rs,stmt,con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rs,Statement stmt,Connection con){
        try{
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
