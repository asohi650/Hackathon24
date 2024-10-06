import java.awt.Menu;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudyBuddyApp {
	
	private static List<User> users;
	private static final String FILE_NAME = "/.UserDatabase.txt"; // where users are storedw
	AppMenu menu;
	Scanner input = new Scanner(System.in);
	
	
	 
	 public StudyBuddyApp() throws IOException {
		 users = new ArrayList<>();
		 menu = new AppMenu();
	
		 loadUsers();
		 launchApplication();

	}

	private void launchApplication() {
		
		System.out.println("Welcome to the Study Buddy App!");
		System.out.println("login or sign up?");
		
		boolean flag = true;
		int option = menu.showLoginMenu();		
		
	
		while (flag) {

			switch (option) {
			//login 
			case 1:
				login();
			
			//signup
			case 2:

				break;
			
			default:
				System.out.println("Not a valid option.  Pick again!");
				
				break;
			}

		}

		
	}

	
	private void login() {
		System.out.println("Enter username: ");
		String username = input.nextLine().trim();
		System.out.println("Enter password: ");
		String password = input.nextLine().trim();
		
		
			
	}

	/**
	 * method to load users from the database file and separate each detail into variables
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	private static void loadUsers() throws IOException  {
		BufferedReader br;
			br = new BufferedReader(new FileReader(FILE_NAME));
		
		String line;
		
		while ((line = br.readLine()) != null) {
			String [] userData = line.split(":"); // because fields are separated by a colon in the txt file
			String username = userData[0];
			String password = userData[1];
			String email = userData[2];
			String name = userData[3];
			String bio = userData[4];
			String major = userData[5];
			int year = Integer.parseInt(userData[6]);
			
			
			// goes through preferences nested array and splits up using comma as a delimeter
			List<Integer> preferences = new ArrayList<>(); // how preferences will load when logging in
			String [] prefArray = userData[7].split(",");
			for (String pref : prefArray) {
				preferences.add(Integer.parseInt(pref));
				
			}
			
			int meetingPreference = Integer.parseInt(userData[8]);
			
			User user = new User (username, password, email, name, bio, major, year, preferences, meetingPreference);
			users.add(user);
		}
			
		
		
	}

}
