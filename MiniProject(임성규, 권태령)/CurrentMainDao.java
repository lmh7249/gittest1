package pack_current;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pack_login.LoginDialog;

public class CurrentMainDao {
	private Connection conn = null;

	
	public CurrentMainDao() {
		// 생성자, 데이터베이스 연결
		String url = "jdbc:mariadb://localhost:3306/minitest";
		String user = "root";
		String password = "123456";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] 드라이버 로드 실패" + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] 데이터베이스 연결 실패" + e.getMessage());
		}
	}

	// 1. CounselingRecord 데이터 조회 기능(월, 일, 이용자명으로 조회 가능) -> CurrentMainUI에서 구현
	public List<Object[]> getDataFromDB(int selectedYear, int selectedMonth, String searchName ) {
		List<Object[]> data = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			String query = "SELECT counselingrecord.cr_id, counselingrecord.cr_date, counselingrecord.cr_type, " +
					"counselingrecord.cr_method, client.ct_name, client.ct_classification, " +
					"counselor.counselor_name, counselingrecord.cr_note " +
					"FROM counselingrecord " +
					"JOIN client ON counselingrecord.ct_id = client.ct_id " +
					"JOIN counselor ON counselingrecord.counselor_id = counselor.counselor_id " +
					"WHERE 1=1 ";  
					
			if(selectedYear != 0) {
				query += " AND YEAR(counselingrecord.cr_date) = " + selectedYear;
			}
			if(selectedMonth != 0) {
				query += " AND MONTH(counselingrecord.cr_date) = " + selectedMonth;
			}
			if(!searchName.isEmpty() ) {
				query += " AND client.ct_name LIKE '%" + searchName + "%'";
			}
			
			query += " ORDER BY counselingrecord.cr_id ASC";
			rs = stmt.executeQuery(query);

			while (rs.next() ) {
				Object[] row = new Object[8];
				row[0] = rs.getInt("cr_id");
				row[1] = rs.getDate("cr_date");
				row[2] = rs.getString("cr_type");
				row[3] = rs.getString("cr_method");
				row[4] = rs.getString("counselor_name");
				row[5] = rs.getString("ct_classification");
				row[6] = rs.getString("ct_name");
				row[7] = rs.getString("cr_note");
				data.add(row);
			}
		} catch (SQLException e) {
			System.out.println("[에러] 데이터베이스 쿼리 실패" + e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				System.out.println("[에러] 자원 해제 실패(rs.close / stmt.close 객체 닫는 과정 오류)" + e.getMessage());
			}
		}
		return data;
	}
	
	public List<Object[]> getAllDataFromDB() {
		return getDataFromDB(0, 0, "");
	}
	
	// 2. 상담등록 다이얼로그에서 입력된 이용자 성명을 -> ct_id(기본키)로 변환하는 메서드 / counselingrecord 테이블에 데이터를 삽입하려면 필요한 과정
	public int getCtIdByName(String ctName) {
		int ctId = 0;
		try {
			String sql = "SELECT ct_id FROM client WHERE ct_name = ?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, ctName);
			ResultSet rs = pstmt.executeQuery(); 
			// 1개만 구할 예정이니 while문 사용 x 
			if (rs.next()) {
				ctId = rs.getInt("ct_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return ctId;
	}
	
	// 3. 상담 등록 메서드(= counselingrecord 테이블 데이터 추가)
	public void insertCounselingRecord(String counselor_id, int ct_id, String cr_date, String cr_type, String ct_method, String cr_note) {
		PreparedStatement pstmt = null;
		String sql = "insert into counselingrecord " +
					" (counselor_id, ct_id, cr_date, cr_type, cr_method, cr_note)" +
					" values (?, ?, ?, ?, ?, ?)";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, counselor_id);
			pstmt.setInt(2, ct_id);
			pstmt.setString(3, cr_date);
			pstmt.setString(4, cr_type);
			pstmt.setString(5, ct_method);
			pstmt.setString(6, cr_note);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();}catch(Exception e2){};
			if(pstmt != null) try {pstmt.close();}catch(Exception e2){};
		}
			
	}
	
	// 4. currentMainUi 오른쪽 상단 counselor_id를 name으로 변환
	public String counselorChange (String counselor_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String counselor_name = null;
		try {
			String sql = "select counselor_name from counselor where counselor_id = ?"; 
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, counselor_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				counselor_name = rs.getString("counselor_name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();}catch(Exception e2){};
			if(pstmt != null) try {pstmt.close();}catch(Exception e2){};
			if(rs != null) try {rs.close();}catch(Exception e2){};
		}
		return counselor_name;
	}
}

