package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {
	static Connection con;
	DBCon db;

	private DBCon() throws Exception {

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@70.12.115.51:1521:orcl";
		String user = "BurgerKing";
		String pass = "bk";
		// 1. 드라이버로딩
		Class.forName(driver);
		// 2. Connection 연결객체 얻어오기
		con = DriverManager.getConnection(url, user, pass);
	}

	public static Connection getConnection() throws Exception {
		if (con == null)
			new DBCon();
		return con;
	}
}
