package mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCtest {

	public static void main(String[] args) {
		Connection conn;
		try {
			// 1. 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. 연결
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
				System.out.println("접속 완료");
		} catch (Exception e) {
			System.out.println("접속 오류");
		}
	}

}
