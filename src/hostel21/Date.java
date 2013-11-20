package hostel21;

import java.text.DateFormat;
import java.util.ArrayList;

public class Date {
	
	private long date;
	private ArrayList<Bed> beds;
	
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public ArrayList<Bed> getBeds() {
		return beds;
	}
	public void setBeds(ArrayList<Bed> beds) {
		this.beds = beds;
	}
	
	public Bed GetBedByNum(int roomNum, int bedNum)
	{
		for(Bed bed : beds)
			if(bed.getRoomNum() == roomNum && bed.getNum() == bedNum)
				return bed;
		return null;
	}

}
