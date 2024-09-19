package pack_client;

public class ClientTO {
	private String ct_id;
	private String ct_name;
	private String ct_birth;
	private String ct_gender;
	private String ct_classification;

	public ClientTO () {
		
	}
	
	public String getCt_id() {
		return ct_id;
	}
	public void setCt_id(String ct_id) {
		this.ct_id = ct_id;
	}
	public String getCt_name() {
		return ct_name;
	}
	public void setCt_name(String ct_name) {
		this.ct_name = ct_name;
	}
	public String getCt_birth() {
		return ct_birth;
	}
	public void setCt_birth(String ct_birth) {
		this.ct_birth = ct_birth;
	}
	public String getCt_gender() {
		return ct_gender;
	}
	public void setCt_gender(String ct_gender) {
		this.ct_gender = ct_gender;
	}
	public String getCt_classification() {
		return ct_classification;
	}
	public void setCt_classification(String ct_classification) {
		this.ct_classification = ct_classification;
	}



}
