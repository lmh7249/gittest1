package pack_counselor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CounselorDAO {
	// 상담자의 기본 정보들과 로그인 로직이 들어가 있음. 

	private boolean idCheck = false;
	private Connection conn = null;

	public CounselorDAO() {
		// 생성자, 데이터베이스 연결
		String url = "jdbc:mariadb://localhost:3306/minitest";
		String user = "root";
		String password = "123456";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 1. 상담자 로그인 아이디, 비밀번호 일치하는지 확인하는 메서드 
	public boolean SearchId(String id, String password) throws SQLException {
		String sql = "select counselor_id, counselor_password from counselor";
		PreparedStatement pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			if (rs.getString("counselor_id").equals(id) && rs.getString("counselor_password").equals(password)) {
				idCheck = true;
			}
		}
		if( rs != null ) rs.close();
		if( pstmt != null ) pstmt.close();
		if( conn != null ) conn.close();
		return idCheck;
	}
	

	// 2. 상담자 회원가입 : 아이디 중복인지 확인하는 메서드 
	public boolean checkId(String id) throws SQLException {
		String sql = "select counselor_id from counselor";
		PreparedStatement pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			if(rs.getString("counselor_id").equals(id)) {
				idCheck = true; 
			}
		}
		if( rs != null ) rs.close();
		if( pstmt != null ) pstmt.close();
		if( conn != null ) conn.close();
		return idCheck;
	}
	
	// 3. 상담자 회원가입: 회원가입 메서드
	
	public void createCounselor(String counselor_id, String counselor_name, String counselor_password) throws SQLException {
		String sql = "insert into counselor values (?, ?, ?)";
		PreparedStatement pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, counselor_id);
		pstmt.setString(2, counselor_name);
		pstmt.setString(3, counselor_password);
		pstmt.executeUpdate();
		
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
