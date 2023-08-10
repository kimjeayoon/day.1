package Insa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class InsaDAO {
	Connection conn = null;
	Statement st = null; 
	PreparedStatement ps = null; // 런타임할때 대치하는것 (이거더 편하다)
	ResultSet rs = null;
	
	public InsaDAO() {
		try {
			String user = "root";
			String pw = "root";
			String url = "jdbc:mysql://localhost:3306/mydb";
			
			// 1. 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. DB 연결
			conn = DriverManager.getConnection(url,user,pw);
			System.out.println("접속 성공");
		} catch (Exception e) {
			
		} finally {
			
		}
	}
	
	public void dbClose() {
		try {
			if(rs != null)
				rs.close();
			if(st != null)
				st.close();
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
		} catch (Exception e) {
			
		}
	}
	
	//insert
	public void insertData(InsaDTO dto) {
		try {
			String sql = "insert into person values(?,?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.name);
			ps.setInt(2, dto.age);
			ps.executeLargeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}
	
	// update
	public void updateDate(InsaDTO dto) {
		try {
			String sql = "update person set age=? where name=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.age);
			ps.setString(2, dto.name);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}
	
	// select
	
	public ArrayList<InsaDTO> readData() {
		ArrayList<InsaDTO> arr  = new ArrayList<InsaDTO>();
		try {
			st = conn.createStatement();
			String sql = "select * from person order by age asc";
			
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				arr.add(new InsaDTO(rs.getString(1), rs.getInt(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return arr;
	}
	
	// delete
	public void deleteData(String name) {
		try {
			String sql = "delete from person where name =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,name);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}
}
