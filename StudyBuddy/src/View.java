package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class View {

	Scanner input;
	List<User> userList;

	// Prepare a list to store matching users
	List<User> matchingBuddies = new ArrayList<>();
	List<User> myBuddies = new ArrayList<>();

	public View() {
		userList = new ArrayList<User>(); // Initialize userList
		input = new Scanner(System.in);
	}

	public void collectStudyDetails() throws IOException {
		// Step 1: Collect user information
		StudyBuddyAppInit init = new StudyBuddyAppInit();
		User newUser = init.launchApplication();

		userList = init.getUsers();

		// Step 2: Compare the new user with all existing users in the list
		for (User existingBuddy : userList) {
			if (!existingBuddy.equals(newUser) && existingBuddy.getMajor().equals(newUser.getMajor())) {
				// Match only if the majors are the same
				existingBuddy.matches(newUser);
				if (existingBuddy.getMatchCount() > 0)
					matchingBuddies.add(existingBuddy);
			}
		}

		// Step 3: Sort matching buddies
		Collections.sort(matchingBuddies, new UserCompare());

		// Step 4: Display matched study buddies
		if (matchingBuddies.size() > 0) {
			System.out.println("\nMatched Study Buddies: ");
			for (User buddy : matchingBuddies) {
				System.out
						.println("StudyBuddy: " + buddy.toString() + " | Match Score: " + buddy.getMatchCount() + "%");
			}
		} else {
			System.out.println("There are no matches at the moment. Please try again soon!");
		}

		// Step 5: Add Buddies
		System.out.println();
		addBuddy();
	}

	public void addBuddy() {
		String option;

		System.out.println("Would you like to add a study buddy? (yes/no)");
		option = selectYesNo();

		if (option.equals("yes")) {
			while (true) {
				System.out.println("Please input their username: ");
				option = input.nextLine(); // Get the username input
				boolean found = false;

				for (User match : matchingBuddies) {
					if (option.equals(match.getUsername())) {
						myBuddies.add(match);
						System.out.println("Added!");
						found = true;
						break; // Exit the loop once a buddy is added
					}
				}

				if (!found) {
					System.out.println("Username does not exist. Please try again.");
				}

				System.out.println("Would you like to add another study buddy? (yes/no)");
				option = selectYesNo();
				if (option.equals("no")) {
					break; // Exit if user doesn't want to add more buddies
				}
			}
		} else {
			System.out.println("Come back again!");
		}

		if (myBuddies.size() > 0) {
			System.out.println("My Study Buddies: ");
			for (User buddy : myBuddies) {
				System.out.println("MyBuddy: " + buddy.toString());
			}
		}
	}

	public String selectYesNo() {
		while (true) {
			String option = input.nextLine(); // Use nextLine to avoid input skipping
			option = option.toLowerCase().trim();
			if (option.equals("yes") || option.equals("y")) {
				return "yes";
			} else if (option.equals("no") || option.equals("n")) {
				System.out.println("Exiting function...\n Done!");
				return "no";
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		View view = new View(); // Create an instance of View
		view.collectStudyDetails(); // Call the method to collect study details
	}

}
