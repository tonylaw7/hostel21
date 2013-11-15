package hostel21;

public class Customer {
	
	private static int id;
	private String firstName;
	private String lastName;
	private String email;
	private String facebook;
	private String twitter;
	private String phone;
	
	public Customer(String first, String last, String email, String facebook, String twiiter, String phone){
				
		this.firstName = first;
		this.lastName = last;
		this.email = email;
		this.facebook = facebook;
		this.twitter = twiiter;
		this.phone = phone;
		
		id++;
	}
	
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
