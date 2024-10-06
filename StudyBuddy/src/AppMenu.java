

import java.util.Scanner;

public class AppMenu {

	Scanner input;
	
	public AppMenu() {
		input = new Scanner(System.in);
	}
	
	public int showLoginMenu() {
		System.out.println("Login or signup: (select 1 or 2)\n");
		System.out.println("\t1. Login");
		System.out.println("\t2. Signup");
		System.out.println("Enter your choice (number) here: ");
		int option = input.nextInt();
		
		//flush out the Scanner buffer
		input.nextLine();
		
		return option;
	}
	public void showHomeMenu(User u) {
		System.out.println("My Buddies\n");
		System.out.println("\nTo-Do\n");
		System.out.println("My Profile: \n");
		System.out.println("\t" + u.getUsername());
		System.out.println("\t" + u.getName());
		System.out.println("\t" + u.getBio() + "\n\n");
		
	}
	
	public int showNavMenu() {
		System.out.println("Choose an option:\n");
		System.out.println("\t1. Find Buddies");
		System.out.println("\t2. Chat");
		System.out.println("\t3. Logout");
		int option = input.nextInt();
		
		//clear buffer scanner
		input.nextLine();
		return option;
	}
}