package hostel21;

import java.text.DateFormat;
import java.util.ArrayList;

public class Date {
	
	private DateFormat date;
	private ArrayList<Bed> beds;
	
	public DateFormat getDate() {
		return date;
	}
	public void setDate(DateFormat date) {
		this.date = date;
	}
	public ArrayList<Bed> getBeds() {
		return beds;
	}
	public void setBeds(ArrayList<Bed> beds) {
		this.beds = beds;
	}
	
	

}
