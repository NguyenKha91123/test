package DataBase;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

//import com.mysql.jdbc.Driver;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection c=null;
		
		try {
//			Đăng ký MySQL với DriverManager
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			String url="jdbc:mysql://localhost:3333/GAME";
			String user="root";
			String password="123456789";
			
//			Tạo kết nối
			c=DriverManager.getConnection(url, user, password);
//			Connection conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	public static void closeConnection(Connection c) {
		try {
			if(c!=null) {
				c.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printInfo(Connection c) {
		if(c!=null) {
			try {
				DatabaseMetaData mtdt=c.getMetaData();
				System.out.println(mtdt.getDatabaseProductName());
				System.out.println(mtdt.getDatabaseProductVersion());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
