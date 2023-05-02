package TestJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DataBase.JDBCUtil;

public class TestDB {
	
	public static void main(String[] args) {
		
		try {
//		Tạo kết nối
		Connection connection=JDBCUtil.getConnection();

//		Tạo ra đối tượng Statement
			Statement statement=connection.createStatement();
//		Thực thi câu lệnh SQL
//			String swap="select * from game.persons order by point asc";
//			ResultSet Comswap=statement.executeQuery(swap);
			
		String show="SELECT * FROM game.persons order by point desc limit 1 offset 2";
		ResultSet result=statement.executeQuery(show);
		int value=0;
		if (result.next()) {
		    value = result.getInt("point");
		    System.out.println(result.getString("name") + " " + value);
		}
//		while(result.next()) {
//			System.out.println(result.getString("NAME")+" "+result.getInt("POINT"));
//		}
		
		JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
