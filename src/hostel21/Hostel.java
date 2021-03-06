package hostel21;

import java.text.DateFormat;
import java.util.ArrayList;

public class Hostel {

	private int id;
	private String name;
	private Address address;
	private Contact contact;
	
	private int numOfRooms;
	private ArrayList<Date> dates = new ArrayList<Date>();
	
	//need verifications on what date format to be used
	private String checkInTime;
	private String checkOutTime;
	
	private String cancellation_deadline;
    private String cancellation_penalty;
	
	private boolean isSmockingAllowed;
	private boolean isAlcoholAllowed;
	
	public Hostel() {
		// TODO Auto-generated constructor stub
	}
	
	/*public Hostel(int id,Address address, int numOfRooms){
		this.id = id;
		this.address = address;
		this.numOfRooms = numOfRooms;
		
	}*/
	
	public Hostel(String name, Address address, Contact contact, String checkIn, String checkOut,
            boolean isSmoking, boolean isAlcohol, String cancelDeadline, String cancelPenalty)
	{
	    id++;
	    this.name = name;
	    this.address = address;
	    this.contact = contact;
	    this.checkInTime = checkIn;
	    this.checkOutTime = checkOut;
	    this.isAlcoholAllowed = isSmoking;
	    this.isAlcoholAllowed = isAlcohol;
	    this.cancellation_deadline = cancelDeadline;
	    this.cancellation_penalty = cancelPenalty;                        
	    
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
	public String getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}
	public String getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(String checkOutTime) {
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
	
	public Date GetHostelDate(long date)
	{
		for(Date d : dates)
			if(d.getDate() == date)
				return d;
		return null;
	}
	
}
