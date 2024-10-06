package application;

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
}