package hostel21;

import java.util.ArrayList;

public class Booking {
	
	private static int count = 0;
	private int id;
	private String hostel_name;
	private long check_in_date;
	private long check_out_date;
	private int beds;
	private String user_name;
	private float price;
	
	public Booking(String hostel_name, long check_in_date, long check_out_date, int beds, String user_name, float price)
	{
		this.setHostel_name(hostel_name);
		this.setCheck_in_date(check_in_date);
		this.setCheck_out_date(check_out_date);
		this.setBeds(beds);
		this.setUser_name(user_name);
		this.setPrice(price);
		this.id = ++count;
	}
	
	public int getID()
	{
		return id;
	}
	
	public static Booking AddNewBooking(int search_id, String user_id)
	{
		SearchResult sr = Search.GetSearchResult(search_id);
		Customer c = Customer.GetCustomer(user_id);
		if(sr != null && c != null)
		{
			String hostel = sr.GetHostelName();
			long checkin = sr.GetBedsCandidates().get(0).get(0).getDate();
			long checkout = sr.GetBedsCandidates().get(0).get(sr.GetBedsCandidates().get(0).size()-1).getDate() + 1;
			int beds = sr.GetBedsCandidates().size();
			float price = sr.GetTotalPrice();
			String name = c.getFirstName() + " " + c.getLastName();
			
			Booking b = new Booking(hostel, checkin, checkout, beds, name, price);
			
			// set beds as unavailable
			Hostel h = Main.GetHostelsByCity(sr.GetHostelName()).get(0);
			for(int i=0; i<sr.GetBedsCandidates().size(); i++)
			{
				ArrayList<Date> dates = sr.GetBedsCandidates().get(i);
				for(Date date : dates)
				{
					Date d = h.GetHostelDate(date.getDate());
					Bed bed = d.GetBedByNum(date.getBeds().get(0).getRoomNum(), date.getBeds().get(0).getNum());
					bed.setAvailable(false);
				}
			}
			
			return b;
		}
		else return null;
	}

	public String getHostel_name() {
		return hostel_name;
	}

	public void setHostel_name(String hostel_name) {
		this.hostel_name = hostel_name;
	}

	public long getCheck_in_date() {
		return check_in_date;
	}

	public void setCheck_in_date(long check_in_date) {
		this.check_in_date = check_in_date;
	}

	public long getCheck_out_date() {
		return check_out_date;
	}

	public void setCheck_out_date(long check_out_date) {
		this.check_out_date = check_out_date;
	}

	public int getBeds() {
		return beds;
	}

	public void setBeds(int beds) {
		this.beds = beds;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
