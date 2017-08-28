package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	//연결
	public static Connection getConnect(){
		Connection conn=null;
		String url="jdbc:oracle:thin:@70.12.110.73:1521:xe";
		String user="food";
		String password="food";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//해제
	public static void dbClose(Connection conn, 
			Statement st, ResultSet rs){
			try {
				if(rs!=null)rs.close();
				if(st!=null)st.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
