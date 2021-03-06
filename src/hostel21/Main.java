package hostel21;
import java.util.*;

public class Main {
	
	// Static fields
	static ArrayList<Hostel> hostel21 = new ArrayList<Hostel>();
	static ArrayList<Customer> customers = Customer.customers;
	static ArrayList<String> results = new ArrayList<String>();
	static ArrayList<Bed> bedResults = new ArrayList<Bed>();
	static Hashtable<Integer, Booking> bookingIDs = new Hashtable<Integer, Booking>();
	
	// Main program
	public static void main(String[] args) {
		RunApp();
		SaveData();
	}
	
	// Load/Save data
	private static void LoadData(String path) { 
		XMLParser.parser(path);
	}
	private static void SaveData() { }
	
	// Main app method
	private static void RunApp()
	{
		String command = "";
		while(true)
		{
			Scanner scan = new Scanner(System.in);
			command = scan.nextLine();
			
			String[] args = command.split(" ");
			if(args.length < 1)
				continue;
			
			switch(args[0])
			{
			case "search":
				ExcuteSearchCommand(args);
				break;
			case "book":
				ExcuteBookCommand(args);
				break;
			case "user":
				ExcuteUserCommand(args);
				break;
			case "admin":
				ExcuteAdminCommand(args);
				break;
				default:
					System.out.println(args[0] + " is not recognised as a command! Please try again.");
			}
			System.out.println();
		}
	}
	
	// Search methods
	static void Search(String city, long start_date, long end_date, int beds)
	{
		// clear previous temporary search data
		bedResults.clear();
		results.clear();
		
		ArrayList<Hostel> hostels;
		hostels = (city != "") ? GetHostelsByCity(city):hostel21;
		
		if(hostels.size() == 0)
		{
			System.out.println("No search result found! Try different arguments.");
			return;
		}
		
		for(int i=0; i<hostels.size(); i++)
		{
			System.out.println("Hostel #" + (i+1) + ", " + hostels.get(i).getAddress().getCity());
			ArrayList<Date> dates = hostels.get(i).GetHostelDatesByRange(start_date, end_date);
			
			if(beds == -1)
				for(int j=0; j<dates.size(); j++)
					System.out.println(dates.get(j).GetBedAvailabilitiesWithPrices());
			else
			{
				if(SearchByPriority(dates, beds))
				{
					ArrayList<String> list = ConvertBedArrayListToStringArrayList(bedResults, dates.size(), beds);
					if(bedResults.size() == 0)
					{
						System.out.println("No search result found! Try different arguments.");
						return;
					}
					
					Search.GenerateSearchIDs(list, hostels.get(i).getAddress().getCity(), dates, beds);
				}
				else
				{
					ArrayList<ArrayList<String>> bedsCombinations = new ArrayList<ArrayList<String>>(beds);
					GetSearchResults(bedsCombinations, dates, 0, "", beds);
					if(results.size() == 0)
					{
						System.out.println("No search result found! Try different arguments.");
						return;
					}
					
					Search.GenerateSearchIDs(results, hostels.get(i).getName(), dates, beds);
				}
			}
				
			System.out.println();
		}
	}
	
	static ArrayList<String> ConvertBedArrayListToStringArrayList(ArrayList<Bed> bedResults, int dateSize, int beds)
	{
		ArrayList<String> list = new ArrayList<String>();
		String str = "";
		for(Bed bed : bedResults)
		{
			String tmp = bed.getRoomNum() + "-" + bed.getNum();
			for(int i=1;i<dateSize;i++)
				tmp += "," + bed.getRoomNum() + "-" + bed.getNum();
			str += (str=="") ? tmp : ";" + tmp;
		}
		list.add(str);
		
		return list;
	}
	
	static boolean SearchByPriority(ArrayList<Date> dates, int beds)
	{
		int counter = 0;
		for(Bed bed : dates.get(0).getBeds())
			if(bed.isAvailable())
				if(CheckBedAvailability(bed,dates))
				{
					bedResults.add(bed);
					counter++;
					if(counter >= beds)
						return true;
				}
		
		return false;
	}
	
	static boolean CheckBedAvailability(Bed bed, ArrayList<Date> dates)
	{
		for(int i=1; i<dates.size(); i++)
		{
			Bed b = dates.get(i).GetBedByNum(bed.getRoomNum(), bed.getNum());
			if(b == null) return false;
			if(!b.isAvailable())
				return false;
		}
		return true;
	}
	
	public static ArrayList<Hostel> GetHostelsByCity(String city)
	{
		ArrayList<Hostel> hostels = new ArrayList<Hostel>();
		for(Hostel h : hostel21)
			if(h.getAddress().getCity().equals(city))
				hostels.add(h);
		return hostels;
	}
	static void GetSearchResults(ArrayList<ArrayList<String>> bedsCombinations, ArrayList<Date> dates, int index, String searchStr, int b)
	{
		ArrayList<String> beds = new ArrayList<String>();
		bedsCombinations.add(index, beds);
		GetBedsCombinations(dates, 0, "", beds);
		for(String bed : bedsCombinations.get(index))
		{
			String tmp = searchStr;
			tmp = (searchStr != "") ? ";"+bed : bed;
			if(index < b-1) // if more combinations are available
			{
				SetBedAvailability(bed, dates, false);
				GetSearchResults(bedsCombinations, dates, index+1, tmp, b);
				SetBedAvailability(bed, dates, true);
			}
			else
				results.add(tmp);
		}
	}
	static void GetBedsCombinations(ArrayList<Date> dates, int index, String beds_num, ArrayList<String> results)
	{
		String[] beds_nums = beds_num.split(",");
		if(beds_nums.length == dates.size()){
			//beds_num = beds_num.substring(0, beds_num.length()-1);
			results.add(beds_num);
		}
		else
			for(Bed bed : dates.get(index).getBeds())
				if(bed.isAvailable())
				{
					String tmp = beds_num;
					if(beds_num != "") tmp += ",";
					GetBedsCombinations(dates, index+1, tmp+bed.getRoomNum()+"-"+bed.getNum(), results);
				}
	}
	
	static void SetBedAvailability(String bed, ArrayList<Date> dates, boolean isAvailable)
	{
		String[] beds = bed.split(",");
		
		// test
		if(beds.length != dates.size())
		{
			System.out.println("Error! 1");
			return;
		}
		
		for(int i=0;i<dates.size();i++)
		{
			int num = Integer.parseInt(beds[i].split("-")[1]);
			int room = Integer.parseInt(beds[i].split("-")[0]);
			Bed b = GetBedByNumber(dates.get(i), num, room);
			
			// test
			if(b == null) {
				System.out.println("Error! 2");
				return;
			}
			
			b.setAvailable(isAvailable);
		}
	}
	
	static Bed GetBedByNumber(Date date, int number, int room)
	{
		for(Bed bed : date.getBeds())
			if(bed.getNum() == number && bed.getRoomNum() == room)
				return bed;
		return null;
	}
	
	// Commands excution
	private static void ExcuteAdminCommand(String[] args)
	{
		if(args.length < 3) 
			System.out.println("Missing arguments for 'user' command!");
		else {
			switch(args[1]) {
			case "load":
				if(TestAdminLoadArgs(args)) {
					LoadData(args[3]);
				}
				break;
			case "revenue":
				if(TestAdminOccupancyArgs(args)) {
				
				}
				break;
			case "occupancy":
				if(TestAdminOccupancyArgs(args)) {
				
				}
				break;
			default:
				System.out.println("Wrong 'admin' arguments!");	
			}
		}
	}
	private static void ExcuteUserCommand(String[] args)
	{
		if(args.length < 3) 
			System.out.println("Missing arguments for 'user' command!");
		else {
			switch(args[1])
			{
			case "add":
				if(TestUserAddArgs(args)) {					
					AddNewCustomer(args);					
				}
				break;
			case "change":
				if(TestUserChangeArgs(args)) {
					ChangeCustomer(args);
				}
				break;
			case "view":
				if(TestUserViewArgs(args)) {
					ViewCustomer(args[3]);
				}
				break;
			default:
				System.out.println("Wrong 'user' arguments!");
			}
		}
	}
	private static void ExcuteBookCommand(String[] args)
	{
		if(args.length < 3) 
			System.out.println("Missing arguments for 'book' command!");
		else
		{
			switch(args[1])
			{
			case "add":
				if(TestBookAddArgs(args)) {
					Booking booking = Booking.AddNewBooking(Integer.parseInt(args[3]), args[5]);
					if(booking == null)
						System.out.println("search_id and/or user_id doesn't exist! Please try again.");
					else
					{
						System.out.println("Booking successful! Here's the detail of your booking:");
						System.out.println("Hostel name: " + booking.getHostel_name());
						System.out.println("Check-in date: " + booking.getCheck_in_date());
						System.out.println("Check-out date: " + booking.getCheck_out_date());
						System.out.println("Beds: " + booking.getBeds());
						System.out.println("Booking ID: " + booking.getID());
						System.out.println("Name: " + booking.getUser_name());
						System.out.println("Price: " + booking.getPrice());
					}
				}
				break;
			case "cancel":
				if(TestBookCancelViewArgs(args)) {
					
				}
				break;
			case "view":
				if(TestBookCancelViewArgs(args)) {
					
				}
				break;
			default:
				System.out.println("Wrong book command! 'book' command should be followed by add, cancel, or view");
			}
		}
	}
	private static void ExcuteSearchCommand(String[] args)
	{
		if(args.length > 1)
		{
			if(TestSearchArgs(args))
			{
				args[2] = args[2].substring(1);
				args[2] = args[2].substring(0, args[2].length()-1);
				if(args.length == 3)
					Search(args[2], 20000000, 30000000,-1);
				if(args.length == 5)
					Search(args[2], Long.parseLong(args[4]), 30000000,-1);
				if(args.length == 7)
					Search(args[2], Long.parseLong(args[4]), Long.parseLong(args[6]),-1);
				if(args.length == 9)
					Search(args[2], Long.parseLong(args[4]), Long.parseLong(args[6]), Integer.parseInt(args[8]));
			}
		}
		else
		{
			Search("",20000000,30000000,-1);
		}
	}
	
	// Command args testing 
	private static boolean TestSearchArgs(String[] args)
	{
		if(args.length > 9) {
			System.out.println("Too many arguments! Please try again");
			return false;
		}
		
		for(int i=1; i<args.length; i+=2) {
			if(args[i].equals("--city") ||
			   args[i].equals("--start_date") ||
			   args[i].equals("--end_date") ||
			   args[i].equals("--beds")) 
				continue;
			else
			{
				System.out.println("Wrong search arguments: --city, --start_date, --end_date, or --beds is missing!");
				return false;
			}
		}
		
		if(args.length % 2 ==0)
		{
			System.out.println("Some arguments are not assigned values! Please try again.");
			return false;
		}
		
		return true;
	}
	private static boolean TestBookAddArgs(String[] args)
	{
		if(args.length > 6) {
			System.out.println("Too many arguments! Please try again");
			return false;
		}
		
		if(args.length != 6) {
			System.out.println("Wrong arguments and/or null values! Please try again");
			return false;
		}
		
		for(int i=2; i<args.length; i+=2) {
			if(args[i].equals("--search_id") ||
			   args[i].equals("--user_id")) 
				continue;
			else
			{
				System.out.println("Wrong 'book add' arguments: --search_id or --user_id is missing!");
				return false;
			}
		}
		return true;
	}
	private static boolean TestUserChangeArgs(String[] args)
	{
		if(args.length > 18) {
			System.out.println("Too many arguments! Please try again");
			return false;
		}
		if(args.length<4 || !args[2].equals("--user_id"))
		{
			System.out.println("Wrong 'user change' arguments!");
			return false;
		}
		if(args.length == 4)
		{
			System.out.println("Please specify what to change! No arguments passed!");
			return false;
		}
		if(args.length % 2 == 1)
		{
			System.out.println("Some arguments or values are not assigned! Please try again.");
			return false;
		}
		for(int i=4; i<args.length; i+=2) {
			if(args[i].equals("--first_name") ||
			   args[i].equals("--last_name") ||
			   args[i].equals("--email") ||
			   args[i].equals("--cc_number") ||
			   args[i].equals("--expiration_date") ||
			   args[i].equals("--security_code") ||
			   args[i].equals("--phone")) 
				continue;
			else
			{
					System.out.println("Wrong 'user change' arguments: --user_id, --first_name, "
							+ "--last_name, --email, --cc_number, --expiration_date, --security_code, or --phone is missing!");
					return false;
			}
		}
		return true;
	}
	private static boolean TestUserViewArgs(String[] args)
	{
		if(args.length != 4 || !args[2].equals("--user_id"))
		{
			System.out.println("Wrong 'user view' argument!");
			return false;
		}
		return true;
	}
	private static boolean TestAdminLoadArgs(String[] args)
	{
		if(args.length != 4 || !args[2].equals("--file"))
		{
			System.out.println("Wrong 'admin load' argument!");
			return false;
		}
		return true;
	}
	private static boolean TestBookCancelViewArgs(String[] args)
	{
		if(args.length > 4)
		{
			System.out.println("Too many arguments! Please try again");
			return false;
		}
		if(!args[2].equals("--book_id"))
		{
			System.out.println("Argument '--book_id' is missing!");
			return false;
		}
		if(args.length != 4)
		{
			System.out.println("Argument '--book_id' doesn't have a value! Please try again.");
			return false;
		}
		return true;
	}
	private static boolean TestUserAddArgs(String[] args)
	{
		if(args.length == 8 || args.length == 16)
		{
			for(int i=2; i<args.length; i+=2){
				if(args[i].equals("--first_name") ||
				   args[i].equals("--last_name") ||
				   args[i].equals("--email")) 
					continue;
				else
				{
					System.out.println("Wrong 'user add' arguments: --first_name, --last_name, and --email are required!");
					return false;
				}
			}
			for(int i=8; i<args.length; i+=2) {
				if(args[i].equals("--cc_number") ||
				   args[i].equals("--expiration_date") ||
				   args[i].equals("--security_code") ||
				   args[1].equals("--phone")) 
					continue;
				else
				{
					System.out.println("Wrong 'user add' optional arguments: "
							+ "--cc_number, --expiration_date, --security_code, or --phone is missing!");
					return false;
				}
			}
			return true;
		}
		else
			System.out.println("Wrong 'user add' arguments! Please try again.");
		return false;
	}
	private static boolean TestAdminOccupancyArgs(String[] args)
	{
		if(args.length > 6) {
			System.out.println("Too many arguments! Please try again");
			return false;
		}
		
		if(args.length != 6) {
			System.out.println("Wrong arguments and/or null values! Please try again");
			return false;
		}
		
		for(int i=2; i<args.length; i+=2) {
			if(args[i].equals("--start_date") ||
			   args[i].equals("--end_date")) 
				continue;
			else
			{
				System.out.println("Wrong 'admin' arguments: --start_date or --end_date is missing!");
				return false;
			}
		}
		return true;
	}
	
		
	//scanning the args array to add customer's information
    public static void AddNewCustomer(String[] args)
    {
            String fname = "";
            String lname = "";
            String email = "";
            String cc_number="";
            String expirationDate = "";
            String securityCode = "";
            String phone ="";
            
            for(int i=2; i<args.length; i+=2){
                    if(args[i].equals("--first_name")){fname = args[i+1];}
                    else if (args[i].equals("--last_name")) {lname = args[i+1];}
                    else if (args[i].equals("--email")) {email = args[i+1];}
                    else if (args[i].equals("--cc_number")) {cc_number = args[i+1];}
                    else if (args[i].equals("--expiration_date")){expirationDate = args[i+1];}
                    else if (args[i].equals("--security_code")){securityCode = args[i+1];}
                    else if (args[i].equals("--phone")){phone = args[i+1];}
            }
            
            Customer newCustomer = new Customer(fname, lname, email, cc_number, expirationDate, securityCode, phone);                
            customers.add(newCustomer);
            
            System.out.println("User has just added successfully! user_id: " + newCustomer.getId());
    }
            
    public static void ChangeCustomer(String[] args)
    {
            String userID = args[3];                
            Customer existingCustomer = GetCustomer(userID);
            
            if(existingCustomer == null)
            {
            	System.out.println("No user has ID: " + args[3] + "! Please try again!");
            	return;
            }
            
            //sample command
            //$ h21 user change --user_id [ --first_name --last_name --email [ --cc_number --expiration_date --security_code --phone ]]
            //index should start at 4
            for(int i=4; i<args.length; i+=2){
                    if(args[i].equals("--first_name")){existingCustomer.setFirstName(args[i+1]);}
                    else if (args[i].equals("--last_name")) {existingCustomer.setLastName(args[i+1]);}
                    else if (args[i].equals("--email")) {existingCustomer.setEmail(args[i+1]);}
                    else if (args[i].equals("--cc_number")) {existingCustomer.setCcNumber(args[i+1]);}
                    else if (args[i].equals("--expiration_date")){existingCustomer.setExpirationDate(args[i+1]);}
                    else if (args[i].equals("--security_code")){existingCustomer.setSecurityCode(args[i+1]);}
                    else if (args[i].equals("--phone")){existingCustomer.setPhone(args[i+1]);}
            }                                                                                
    }
    
    //getting customer by his/her userID
    public static Customer GetCustomer(String userID ) {
            for(Customer c : customers) {
                    if (c.getId() == Integer.parseInt(userID)) {
                            return c;
                    }                        
            }
            return null;
    }        
    
    //view customer information
    public static void ViewCustomer(String userID) {
            Customer tempCustomer  = GetCustomer(userID);
            if(tempCustomer != null)
            {
	            System.out.println("user_id: " + userID);
	            System.out.println("Name: " + tempCustomer.getFirstName() + " " + tempCustomer.getLastName());
	            System.out.println("Email: " + tempCustomer.getEmail());  
            }
            else
            	System.out.println("No user has the ID: " + userID);
                                    
    }

}
