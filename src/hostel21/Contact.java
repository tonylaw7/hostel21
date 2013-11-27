package hostel21;

public class Contact {
	
	private String phone;
	private String email;
	private String facebook;
	private String website;
	
	public Contact(String phone, String email, String facebook, String website) {
		this.phone = phone;
		this.email = email;
		this.facebook = facebook;
		this.website = website;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
}
