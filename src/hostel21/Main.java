package hostel21;
import java.util.*;
import java.io.*;

public class Main {

	
	// Static fields
	static ArrayList<Hostel> hostel21;
	static ArrayList<Customer> customers;
	static ArrayList<String> results = new ArrayList<String>();
	
	// Main program
	public static void main(String[] args) {
		LoadData();
		RunApp();
		SaveData();
	}
	
	// Load/Save data
	private static void LoadData() { }
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
		if(beds == -1)
		{
			
		}
		else
		{
			ArrayList<Hostel> hostels;
			hostels = (city != "") ? GetHostelsByCity(city):hostel21;
			
			for(int i=0; i<hostels.size(); i++)
			{
				System.out.println("Hostel #" + (i+1) + ", " + hostels.get(i).getName());
				
				ArrayList<Date> dates = hostels.get(i).GetHostelDatesByRange(start_date, end_date);
				ArrayList<ArrayList<String>> bedsCombinations = new ArrayList<ArrayList<String>>(beds);
				GetSearchResults(bedsCombinations, dates, 0, "");
<<<<<<< HEAD
=======
				
				Search.GenerateSearchIDs(results, hostels.get(i).getName(), dates, beds);
				
				System.out.println();
>>>>>>> malaliwi2/master
			}
		}
	}
	
	static ArrayList<Hostel> GetHostelsByCity(String city)
	{
		ArrayList<Hostel> hostels = new ArrayList<Hostel>();
		for(Hostel h : hostel21)
			if(h.getAddress().getCity().equals(city))
				hostels.add(h);
		return hostels;
	}
	static void GetSearchResults(ArrayList<ArrayList<String>> bedsCombinations, ArrayList<Date> dates, int index, String searchStr)
	{
		GetBedsCombinations(dates, 0, "", bedsCombinations.get(index));
		for(String bed : bedsCombinations.get(index))
		{
			if(index < bedsCombinations.size()-1) // if more combinations are available
			{
				SetBedAvailability(bed, false);
				GetSearchResults(bedsCombinations, dates, index+1, searchStr+bed);
				SetBedAvailability(bed, true);
			}
			else
<<<<<<< HEAD
				AddNewSearchResult(searchStr);
=======
				results.add(tmp);
>>>>>>> malaliwi2/master
		}
	}
	static void GetBedsCombinations(ArrayList<Date> dates, int index, String beds_num, ArrayList<String> results)
	{
		if(beds_num.length() == dates.size())
			results.add(beds_num);
		else
			for(Bed bed : dates.get(index).getBeds())
				if(bed.isAvailable())
					GetBedsCombinations(dates, index+1, beds_num+bed.getNum(), results);
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
					/* ... */
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
				
			}
		}
		else
		{
		
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
		
		for(int i=3; i<args.length; i+=2){
			if(args[i].equals("--first_name")){fname = args[i];}
			else if (args[i].equals("--last_name")) {lname = args[i];}
			else if (args[i].equals("--email")) {email = args[i];}
			else if (args[i].equals("--cc_number")) {cc_number = args[i];}
			else if (args[i].equals("--expiration_date")){expirationDate = args[i];}
			else if (args[i].equals("--security_code")){securityCode = args[i];}
			else if (args[i].equals("--phone")){phone = args[i];}
		}
		
		Customer newCustomer = new Customer(fname, lname, email, cc_number, expirationDate, securityCode, phone);		
		customers.add(newCustomer);
		
	}
	
	public static void ChangeCustomer(String[] args)
	{
		String userID = args[3];		
		Customer existingCustomer = GetCustomer(userID);
		
		//sample command
		//$ h21 user change --user_id [ --first_name --last_name --email [ --cc_number --expiration_date --security_code --phone ]]
		//index should start at 4
		for(int i=4; i<args.length; i+=2){
			if(args[i].equals("--first_name")){existingCustomer.setFirstName(args[i]);}
			else if (args[i].equals("--last_name")) {existingCustomer.setLastName(args[i]);}
			else if (args[i].equals("--email")) {existingCustomer.setEmail(args[i]);}
			else if (args[i].equals("--cc_number")) {existingCustomer.setCcNumber(args[i]);}
			else if (args[i].equals("--expiration_date")){existingCustomer.setExpirationDate(args[i]);}
			else if (args[i].equals("--security_code")){existingCustomer.setSecurityCode(args[i]);}
			else if (args[i].equals("--phone")){existingCustomer.setPhone(args[i]);}
		}										
	}
	
	//getting customer by his/her userID
	public static Customer GetCustomer(String userID ) {
		for(Customer c : customers) {
			if (c.getId() == Integer.parseInt(userID)) {
				return c;
			}			
		}
<<<<<<< HEAD
		return null;
	}	
	
	//view customer information
	public static void ViewCustomer(String userID) {
		Customer tempCustomer  = GetCustomer(userID);
				System.out.println("user_id: " + userID);
				System.out.println("Name: " + tempCustomer.getFirstName() + " " + tempCustomer.getLastName());
				System.out.println("Email: " + tempCustomer.getEmail());				
					
=======
>>>>>>> malaliwi2/master
	}

}
