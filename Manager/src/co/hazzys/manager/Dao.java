package co.hazzys.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao { //static Class // 생성자 없이 만들면 알아서 자동으로 생성

	public static Connection getConnection() {
		Connection conn = null; // static 초기값 필요
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hazzys";
		String password = "dhgPwl7#";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
