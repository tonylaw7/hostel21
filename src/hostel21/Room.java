package hostel21;

import java.util.ArrayList;

public class Room {
	
	private int roomNumber;
	private int numberOfBeds;
	private ArrayList<Bed> Beds;
	
	public Room(int roomNumber, int numberOfBed){
		this.roomNumber = roomNumber;
		this.numberOfBeds = numberOfBed;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public ArrayList<Bed> getBeds() {
		return Beds;
	}

	public void setBeds(ArrayList<Bed> beds) {
		Beds = beds;
	}
	
	

}
