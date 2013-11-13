package hostel21;

import java.util.ArrayList;

public class Hostel {

	private String address;
	private int id;
	private int numOfRooms;
	private ArrayList<Room> Rooms;
	
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
	public ArrayList<Room> getRooms() {
		return Rooms;
	}
	public void setRooms(ArrayList<Room> rooms) {
		Rooms = rooms;
	}
	
	
	
}
