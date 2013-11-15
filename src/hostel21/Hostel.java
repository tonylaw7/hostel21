package hostel21;

import java.text.DateFormat;
import java.util.ArrayList;

public class Hostel {

	private int id;
	private String name;
	private String address;
	private Contact contact;
	
	private int numOfRooms;
	private ArrayList<Bed> Beds;
	
	//need verifications on what date format to be used
	private DateFormat checkInTime;
	private DateFormat checkOutTime;
	
	private boolean isSmockingAllowed;
	private boolean isAlcoholAllowed;
	
	public Hostel(int id,String address, int numOfRooms){
		this.id = id;
		this.address = address;
		this.numOfRooms = numOfRooms;
		
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
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
	public ArrayList<Bed> getBeds() {
		return Beds;
	}
	public void setRooms(ArrayList<Bed> beds) {
		Beds = beds;
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
	
	
	
}
