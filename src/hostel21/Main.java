package hostel21;
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		LoadData();
		RunApp();
		SaveData();
	}
	
	private static void LoadData() { }
	
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
					
				}
				break;
			case "change":
				if(TestUserChangeArgs(args)) {
					
				}
				break;
			case "view":
				if(TestUserViewArgs(args)) {
					
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
	private static void SaveData() { }
}
