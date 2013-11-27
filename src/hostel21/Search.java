package hostel21;

import java.util.ArrayList;
import java.util.Hashtable;

public class Search {
	
	private static int id=0;
	private static Hashtable<Integer,SearchResult> searchResults = new Hashtable<Integer,SearchResult>(); 
	
	private static void AddSearchResult(SearchResult sr)
	{
		id++;
		searchResults.put(id, sr);
		PrintResults(id, sr);
	}
	
	private static void PrintResults(int id, SearchResult sr)
	{
		String line = "search_id:";
		line += id;
		line += ", $" + sr.GetTotalPrice();
		line += ", rooms " + GetRoomNums(sr);
		System.out.println(line);
	}
	
	private static String GetRoomNums(SearchResult sr)
	{
		String str = "";
		for(int room : sr.GetDistinctRoomNums())
			str += "#" + room + " ";
		return str;
	}
	
	public static SearchResult GetSearchResult(int id)
	{
		return searchResults.get(id);
	}
	
	public static void GenerateSearchIDs(ArrayList<String> results, String name, ArrayList<Date> dates, int beds)
	{
		for(String s : results)
		{
			SearchResult sr = GetSearchResult(s, name, dates, beds);
			AddSearchResult(sr);
		}
	}
	
	private static SearchResult GetSearchResult(String s, String name, ArrayList<Date> dates, int beds)
	{
		SearchResult sr = new SearchResult(name,beds,dates.size());
		String[] bedArray = s.split(";");
		float totalPrice = 0;
		for(int i=0; i<bedArray.length; i++)
		{
			String[] dateArray = bedArray[i].split(",");
			ArrayList<Date> al = new ArrayList<Date>();
			for(int j=0; j<dateArray.length; j++)
			{
				String[] info = dateArray[j].split("-");
				int roomNum = Integer.parseInt(info[0]);
				int bedNum = Integer.parseInt(info[1]);
				Bed bed = dates.get(j).GetBedByNum(roomNum, bedNum);
				float price = bed.getPrice();
				if(!sr.GetDistinctRoomNums().contains(roomNum))
					sr.GetDistinctRoomNums().add(roomNum);
				
				ArrayList<Bed> tmpBed = new ArrayList<Bed>(1);
				tmpBed.add(new Bed(bedNum, roomNum, price, true));
				Date d = new Date(dates.get(j).getDate());
				d.setBeds(tmpBed);
				al.add(d);
				
				totalPrice += price;
			}
			sr.GetBedsCandidates().add(al);
		}
		sr.SetTotalPrice(totalPrice);
		return sr;
	}
}
