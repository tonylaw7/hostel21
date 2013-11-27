package hostel21;

import java.text.DateFormat;
import java.util.ArrayList;

public class Date {
	
	private long date;
	private ArrayList<Bed> beds;
	
	public Date() {
        this.beds = new ArrayList<Bed>();
	}
	
	public Date(long date) {
        this.date = date;
        this.beds = new ArrayList<Bed>();
	}
	
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

	public String GetBedAvailabilitiesWithPrices()
	{
		int c=0;
		float min = 1000, max = 0;
		for(Bed bed : beds)
			if(bed.isAvailable())
			{
				c++;
				if(bed.getPrice() > max) max = bed.getPrice();
				if(bed.getPrice() < min) min = bed.getPrice();
			}
		if(c!=0)
			return date + " to " + (date+1) + ": " + c + " beds avaialable between " + min + " and " + max;
		else
			return date + " to " + (date+1) + ": " + "No available beds on this date";
	}
}
