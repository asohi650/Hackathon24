package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudyBuddyAppInit {
	// Personal info fields
	private final String FILE_PATH = "UserDatabase.txt"; // where users are stored

	private static List<User> users;
	AppMenu menu;
	Scanner input = new Scanner(System.in);
	User currentUser;

	public StudyBuddyAppInit() throws IOException {
		users = new ArrayList<>();
		menu = new AppMenu();
		loadUsers();
	}

	public List<User> getUsers() {
		return users;
	}

	public User launchApplication() {
		System.out.println("Welcome to the Study Buddy App!");
		System.out.println("login or sign up?");

		int option = menu.showLoginMenu();

		while (true) {
			if (option == 1)
				return login();
			else if (option == 2)
				return signup();
			else
				System.out.println("Not a valid option.  Pick again!");
		}
	}

	private User signup() {
		System.out.println("Create a username: ");
		String inputUser, username = "";

		boolean isNotUnique = true;
		while (isNotUnique) {
			inputUser = input.nextLine().trim();
			for (User u : users) {
				if (u.getUsername().equals(inputUser) || inputUser.equals("")) {
					System.out.println("This username is taken or invalid. Please try again");
					break;
				} else {
					username = inputUser;
					isNotUnique = false;
				}
			}
		}

		System.out.println("Enter your full name: ");
		String name = input.nextLine().trim();

		System.out.println("Create a password: ");
		String password = input.nextLine().trim();

		System.out.println("Enter your email: ");
		String email = input.nextLine().trim();

		System.out.print("Enter a brief bio: ");
		String bio = input.nextLine();

		// Step 2: Collect study details
		int major = selectMajor();
		int year = studyYear();
		List<Integer> preferences = studyPreference();
		int meetingPreference = getMeetingPreference();

		// Step 3: Create a new User object
		User newUser = new User(username, email, password, name, bio, String.valueOf(major), year, preferences,
				meetingPreference);

		// Save new user information to file (optional)
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
			writer.write(newUser.toFile());
			writer.newLine();
			System.out.println("Information saved!");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file: " + e.getMessage());
		}

		// Step 4: Save the new User and StudyBuddy to the list
		users.add(newUser);
		return newUser;
	}

	private User login() {
		while (true) {
			System.out.println("Enter username: ");
			String username = input.nextLine().trim();
			System.out.println("Enter password: ");
			String password = input.nextLine().trim();
			for (User u : users) {
				if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
					System.out.println("Login successful!");
					return u;
				}
			}
			System.out.println("Username or password incorrect");
		}
	}

	// Method to collect the user's major or field of study
	public int selectMajor() {
		int majorChoice = -1; // Variable to store user's choice
		boolean validInput = false;

		System.out.println("Please pick the one closest or most similar to your major:\n");
		System.out.println("\t1. Business");
		System.out.println("\t2. Computer");
		System.out.println("\t3. Psychology");
		System.out.println("\t4. Science");
		System.out.println("\t5. Engineering");
		System.out.println("\t6. Education");
		System.out.println("\t7. Communications");
		System.out.println("\t8. Finance and Accounting");
		System.out.println("\t9. Criminal Justice");
		System.out.println("\t10. Anthropology and Sociology\n");

		while (!validInput) {
			System.out.print("Enter the number: ");
			String input = this.input.nextLine(); // Use existing scanner instance

			try {
				majorChoice = Integer.parseInt(input);
				// Check if the choice is within the valid range
				if (majorChoice >= 1 && majorChoice <= 10) {
					validInput = true; // Valid input
				} else {
					System.out.println("Invalid input. Please select a number between 1 and 10.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}

		return majorChoice; // Return the selected major
	}

	// Method to collect the user's year of study
	public int studyYear() {
		System.out.print("\nYear of Study: ");
		while (!input.hasNextInt()) { // while the input is not an integer
			System.out.println("Error: please enter a valid number");
			input.next(); // discard the invalid input
			System.out.println("\nYear of Study:");
		}
		int studyYear = input.nextInt(); // get the integer input
		input.nextLine(); // Clear the buffer after reading an integer
		return studyYear;
	}

	// Method to collect study preferences
	public List<Integer> studyPreference() {
		List<Integer> preferencesList = new ArrayList<>(); // List to store preferences
		boolean validInput = false;

		System.out.println(
				"\nSelect your preferred study methods (you can select multiple by separating them with commas):\n");
		System.out.println("\t1. Reading");
		System.out.println("\t2. Taking Notes");
		System.out.println("\t3. Discussing Concepts");
		System.out.println("\t4. Practice Problems");
		System.out.println("\t5. Active Recall");
		System.out.println("\t6. Flashcards\n");

		while (!validInput) {
			System.out.print("Enter your choices (e.g., 1,2,3): ");
			String input = this.input.nextLine(); // Use existing scanner instance
			input.replace(" ", "").trim();

			// Split the input by commas and trim spaces
			String[] choices = input.split(",");
			validInput = true; // Assume valid input until proven otherwise
			preferencesList.clear(); // Clear previous entries

			for (String choice : choices) {
				choice = choice.trim(); // Remove any leading or trailing whitespace
				try {
					int choiceNumber = Integer.parseInt(choice);
					// Check if the choice is within the valid range
					if (choiceNumber < 1 || choiceNumber > 6) {
						validInput = false;
						break; // No need to check further if one is invalid
					}
					// Check if the preference is already in the list to avoid duplicates
					if (!preferencesList.contains(choiceNumber)) {
						preferencesList.add(choiceNumber); // Add valid choice to preferencesList
					}
				} catch (NumberFormatException e) {
					validInput = false; // Not a valid integer
					break;
				}
			}

			// If input was not valid, prompt again
			if (!validInput) {
				System.out.println("Invalid input.");
			}
		}

		return preferencesList; // Return the list of preferences
	}

	// Method to get meeting preferences
	private int getMeetingPreference() {
		boolean validInput = false; // Flag to check if input is valid
		int meetingPreference = -1;

		System.out.println("Are you good with in-person, online, or both?");
		System.out.println("\t1. In-person");
		System.out.println("\t2. Online");
		System.out.println("\t3. Both");

		while (!validInput) {
			System.out.print("Enter your choice (1, 2, or 3): ");
			String inputChoice = this.input.nextLine().trim();
			try {
				meetingPreference = Integer.parseInt(inputChoice);
				if (meetingPreference >= 1 && meetingPreference <= 3) {
					validInput = true;
				} else {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Please enter 1, 2, or 3: ");
			}
		}
		return meetingPreference; // Return the meeting preference as an integer
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
			try (Scanner fileReader = new Scanner(db)) {
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
						pref = pref.replace("[", "");
						pref = pref.replace("]", "");
						pref = pref.trim();
						preferences.add(Integer.parseInt(pref));
					}
					int meetingPreference = Integer.parseInt(splittedLine[8]);
					User user = new User(username, email, password, name, bio, major, year, preferences,
							meetingPreference);
					users.add(user);
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}