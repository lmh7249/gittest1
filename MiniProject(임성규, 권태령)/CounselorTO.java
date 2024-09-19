package pack_counselor;

public class CounselorTO {
	private String counselor_id;
	private String counselor_name;
	private String counselor_password;
	
	// 생성자
	public CounselorTO() {
	}
	
	// Setter and Getter
	public String getCounselor_id() {
		return counselor_id;
	}
	public void setCounselor_id(String counselor_id) {
		this.counselor_id = counselor_id;
	}
	public String getCounselor_name() {
		return counselor_name;
	}
	public void setCounselor_name(String counselor_name) {
		this.counselor_name = counselor_name;
	}
	public String getCounselor_password() {
		return counselor_password;
	}
	public void setCounselor_password(String counselor_password) {
		this.counselor_password = counselor_password;
	}
	

}
