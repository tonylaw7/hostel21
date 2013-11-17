package hostel21;

import java.text.DateFormat;
import java.util.ArrayList;

public class Hostel {

	private int id;
	private String name;
	private Address address;
	private Contact contact;
	
	private int numOfRooms;
	private ArrayList<Date> dates;
	
	//need verifications on what date format to be used
	private DateFormat checkInTime;
	private DateFormat checkOutTime;
	
	private boolean isSmockingAllowed;
	private boolean isAlcoholAllowed;
	
	public Hostel(int id,Address address, int numOfRooms){
		this.id = id;
		this.address = address;
		this.numOfRooms = numOfRooms;
		
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumOfRooms() {
		return numOfRooms;
	}
	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public DateFormat getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(DateFormat checkInTime) {
		this.checkInTime = checkInTime;
	}
	public DateFormat getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(DateFormat checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	public boolean isSmockingAllowed() {
		return isSmockingAllowed;
	}
	public void setSmockingAllowed(boolean isSmockingAllowed) {
		this.isSmockingAllowed = isSmockingAllowed;
	}
	public boolean isAlcoholAllowed() {
		return isAlcoholAllowed;
	}
	public void setAlcoholAllowed(boolean isAlcoholAllowed) {
		this.isAlcoholAllowed = isAlcoholAllowed;
	}
	public ArrayList<Date> getDates() {
		return dates;
	}
	public void setDates(ArrayList<Date> dates) {
		this.dates = dates;
	}
	
	// methods
	ArrayList<Date> GetHostelDatesByRange(long start_date, long end_date)
	{
		ArrayList<Date> hostel_dates = new ArrayList<Date>();
		for(Date d : dates)
			if(d.getDate() >= start_date && d.getDate() < end_date)
				hostel_dates.add(d);
		return hostel_dates;
	}
	
}
