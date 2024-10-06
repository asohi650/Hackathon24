import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudyBuddyApp {
	private final String FILE_PATH = "UserDatabase.txt"; // where users are stored

	private static List<User> users;
	AppMenu menu;
	Scanner input = new Scanner(System.in);

	public StudyBuddyApp() throws IOException {
		users = new ArrayList<>();
		menu = new AppMenu();

		loadUsers();

	}

	private void launchApplication() {

		System.out.println("Welcome to the Study Buddy App!");
		System.out.println("login or sign up?");

		boolean flag = true;
		int option = menu.showLoginMenu();

		while (flag) {

			switch (option) {
			// login
			case 1:
				login();

				// signup
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
	 * method to load users from the database file and separate each detail into
	 * variables
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	private void loadUsers() throws IOException {
		File db = new File(FILE_PATH);


		String currentLine;
		String[] splittedLine;

		if (db.exists()) {
			Scanner fileReader = new Scanner(db);

			while (fileReader.hasNextLine()) {

				currentLine = fileReader.nextLine();
				splittedLine = currentLine.split(":");
				String username = splittedLine[0];
				String password = splittedLine[1];
				String email = splittedLine[2];
				String name = splittedLine[3];
				String bio = splittedLine[4];
				String major = splittedLine[5];
				int year = Integer.parseInt(splittedLine[6]);

				// goes through preferences nested array and splits up using comma as a
				// delimeter
				List<Integer> preferences = new ArrayList<>(); // how preferences will load when logging in
				String[] prefArray = splittedLine[7].split(",");
				for (String pref : prefArray) {
					preferences.add(Integer.parseInt(pref));
					int meetingPreference = Integer.parseInt(splittedLine[8]);

					User user = new User(username, password, email, name, bio, major, year, preferences,
							meetingPreference);
					users.add(user);
					
			
					
					}
					
				}
			}

		}

	}


