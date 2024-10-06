

import java.util.List;

public class User implements Comparable<User> {

	// Personal info fields
	private String username;
	private String email;
	private String password;
	private String name;
	private String bio;


	// Matching criteria fields
	private String major;
	private int yearOfStudy; // Renamed to match consistency
	private List<Integer> preferences; // Assuming these are study preferences like time of day, subject, etc.
	private int meetingPreference; // in-person, online, both

	// Variable field for accounting for matching with new users
	private int matchCount;
	private List<String> matchedUsers;

	// Constructor for initializing all fields
	public User(String username, String email, String password, String name, String bio, String major, int yearOfStudy,
			List<Integer> preferences, int meetingPreference) {

		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.bio = bio;
		this.major = major;
		this.yearOfStudy = yearOfStudy;
		this.preferences = preferences;
		this.meetingPreference = meetingPreference;
		
	}

	// Getters and setters for all fields (You can generate them with IDE shortcuts)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public List<Integer> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<Integer> preferences) {
		this.preferences = preferences;
	}

	public int getMeetingPreference() {
		return meetingPreference;
	}

	public void setMeetingPreference(int meetingPreference) {
		this.meetingPreference = meetingPreference;
	}

	public int getMatchCount() {
		return matchCount;
	}

	@Override
	public int compareTo(User other) {
		// Use Integer.compare() for primitive int comparison
		return Integer.compare(this.matchCount, other.matchCount); // Change this to compare count
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (other == null || !(other instanceof User)) {
			return false;
		}
		User us = (User) other;
		return this.getMajor() == us.getMajor(); // Compare int with ==
	}

	// Method to check similarity with another StudyBuddy
	public void matches(User other) {
		// Reset the rating based on each comparison cycle
		this.matchCount = 1;

		// Check for matching study year
		if (this.yearOfStudy == other.yearOfStudy) {
			this.matchCount++;
		}

		// Check for matching meeting preference
		if (this.meetingPreference == 3 || other.getMeetingPreference() == 3
				|| this.meetingPreference == other.getMeetingPreference()) {
			this.matchCount++;
		}

		// Check matching preferences (study methods)
		for (Integer preference : this.preferences) {
			if (other.preferences.contains(preference)) {
				this.matchCount++;
			}
		}

		this.matchCount = this.matchCount * 100 / (3 + other.getPreferences().size());
	}
	

	// Method to check login credentials
	public boolean checkLogin(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}

	// Display user profile
	public void displayProfile() {
		System.out.println("Name: " + name);
		System.out.println("Bio: " + bio);
		System.out.println("Major: " + major);
		System.out.println("Year: " + yearOfStudy);
	}
	



	// Compare two users' compatibility (returns true if there are any matching
	// criteria)
	public boolean compare(User otherUser) {
		// Checks major, year of study, meeting preferences (in-person/online)
		if (this.major.equals(otherUser.major) && this.yearOfStudy == otherUser.yearOfStudy
				&& this.meetingPreference == otherUser.meetingPreference) {
			return true;
		}

		// Check if there is at least one matching preference
		for (Integer pref : this.preferences) {
			if (otherUser.preferences.contains(pref)) {
				return true; // Found a matching preference
			}
		}

		return false; // No matching preferences found
	}

	// Format method to create a string representation
	public String toFile() {
		return this.username + ":" + this.password + ":" + this.email + ":" + this.name + ":" + this.bio + ":"
				+ this.major + ":" + this.yearOfStudy + ":" + this.preferences + ":" + this.meetingPreference;

	}

	@Override
	public String toString() {
		return this.name + " Username: " + this.username + " Year of Study:  " + this.yearOfStudy
				+ " Preferred Study Methods: " + this.preferences + " Meething Preference: " + this.meetingPreference;
	}
}