package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SellectBook {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mydb";
		String id = "root";
		String pw = "root";
		try {
			// 1.드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 DB 연결
			Connection conn = DriverManager.getConnection(url,id,pw);
			String sql = "select * from book2";
			// 3. SQL문 작성
			Statement st = conn.createStatement(); // sql
			
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				String no = rs.getString("bookno");
				String title = rs.getString("booktitle");
				String author = rs.getString("bookaythor");
				String year = rs.getString("bookyear");
				String price = rs.getString("bookprice");
				String publisher = rs.getString("bookpublisher");
				
				System.out.println(no + "\t" + title + "\t" + author);
			}
			if(rs != null)
				rs.close();
			if(st != null)
				st.close();
			if(conn != null)
				conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("접속불량");
		}

	}

}
