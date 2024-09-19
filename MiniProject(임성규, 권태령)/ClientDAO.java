package pack_client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDAO {
	private Connection conn = null;
	private ArrayList<ClientTO> clients = new ArrayList<>();
	
	public ClientDAO() {
		String url = "jdbc:mariadb://localhost:3306/minitest";
		String user = "root";
		String password = "123456";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 1. 이용자 회원가입 메서드
	public void createClient(String ct_name, String ct_birth, String ct_gender, String ct_classification) throws SQLException {
		String sql = "insert into client (ct_name, ct_birth, ct_gender, ct_classification) values (?, ?, ?, ?)";
		PreparedStatement pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, ct_name);
		pstmt.setString(2, ct_birth);
		pstmt.setString(3, ct_gender);
		pstmt.setString(4, ct_classification);
		pstmt.executeUpdate();
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
	}
	
	// 2. 이용자 조회 메서드
	public ArrayList<ClientTO> searchClient(String ct_name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 아래 finally try catch문에 데이터베이스를 닫기 위해서 멤버 변수로 pstmt와 rs를 선언함. 
		String sql = "select ct_id, ct_name, ct_birth, ct_gender, ct_classification from client where ct_name = ?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, ct_name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ClientTO to = new ClientTO();
				to.setCt_id(rs.getString("ct_id"));
				to.setCt_name(rs.getString("ct_name"));
				to.setCt_birth(rs.getString("ct_birth"));
				to.setCt_gender(rs.getString("ct_gender"));
				to.setCt_classification(rs.getString("ct_classification"));
				clients.add(to);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			try {
				if( rs != null ) rs.close();
				if( pstmt != null ) pstmt.close();
				if( conn != null ) conn.close();
			} catch (Exception e2) {
				 e2.printStackTrace();
			}
		}
		return clients ;
	}
	
	
	// 3. 이용자 수정 
	// 4. 이용자 삭제 메서드
	// 시간이 된다면 만들기.
	
}
