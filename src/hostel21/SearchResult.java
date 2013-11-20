package hostel21;
import java.util.*;

public class SearchResult {
	private String hostelName;
	private ArrayList<ArrayList<Date>> bedsCandidates;
	private float totalPrice;
	private ArrayList<Integer> distinctRoomNums = new ArrayList<Integer>();
	
	public SearchResult(String name, int beds, int dates)
	{
		hostelName = name;
		bedsCandidates = new ArrayList<ArrayList<Date>>(beds);
		for(ArrayList<Date> list : bedsCandidates)
		{
			list = new ArrayList<Date>(dates);
		}
	}
	
	public String GetHostelName()
	{
		return this.hostelName;
	}
	
	public ArrayList<ArrayList<Date>> GetBedsCandidates()
	{
		return this.bedsCandidates;
	}
	
	public float GetTotalPrice()
	{
		return totalPrice;
	}
	
	public ArrayList<Integer> GetDistinctRoomNums()
	{
		return distinctRoomNums;
	}
	
	public void SetTotalPrice(float price)
	{
		totalPrice = price;
	}
}
