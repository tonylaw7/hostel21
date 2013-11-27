package hostel21;

import java.text.DateFormat;
import java.util.ArrayList;

public class Customer {
	
	private static int count = 0;
	public static ArrayList<Customer> customers = new ArrayList<Customer>();
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String ccNumber;
	private String expirationDate;
	private String securityCode;	
	private String phone;
	private DateFormat creatingDate;
	
	private String facebook;
	private String twitter;
	
	
	public Customer(String first, String last, String email, String ccNumber, String expirationDate, String securityCode, String phone){
				
		this.firstName = first;
		this.lastName = last;
		this.email = email;
		this.setCcNumber(ccNumber);
		this.setExpirationDate(expirationDate);
		this.setSecurityCode(securityCode);
		this.phone = phone;
		this.id = ++count;
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

	public DateFormat getCreatingDate() {
		return creatingDate;
	}

	public void setCreatingDate(DateFormat creatingDate) {
		this.creatingDate = creatingDate;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	
	public static Customer GetCustomer(String userID ) {
        for(Customer c : customers) {
                if (c.getId() == Integer.parseInt(userID)) {
                        return c;
                }                        
        }
        return null;
	}
}
