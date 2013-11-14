import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		/*
		 * Here we load data from the XML file
		 */
		
		String command = "";
		while(true)
		{
			Scanner scan = new Scanner(System.in);
			command = scan.nextLine();
			
			args = command.split(" ");
			if(args.length < 1)
				continue;
			
			switch(args[0])
			{
			case "search":
				/*
				 * search functionality goes here
				 */
				break;
			case "book":
				/*
				 * book functionality goes here
				 */
				break;
			case "user":
				/*
				 * user functionality goes here
				 */
				break;
			case "admin":
				/*
				 * admin functionality goes here
				 */
				break;
				default:
					System.out.println(args[0] + " is not recognised as a command! Please try again.");
			}
		}

	}

}
