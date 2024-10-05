import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudyBuddy {
	
	private static List<User> users = new ArrayList<>();
	private static final String FILE_NAME = "UserDatabase.txt"; //where users are storedw

	public static void main(String[] args) {
		loadUsers();
		int option;
		
		
		
		Scanner input = new Scanner(System.in);
		

	}

	/**
	 * method to load users from the database file and separate each detail into variables
	 */
	private static void loadUsers()  {
		BufferedReader br;
		try {
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
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error reading user database: " + e.getMessage());
		}
		
	}

}
