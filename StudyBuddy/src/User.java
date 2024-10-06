import java.util.List;

public class User {

	private String username;
	private String email;
	private String password;
	private String name;
	private String bio;
	private String major;
	private int year;
	private List<Integer> preferences;
	private int meetingPreference;

	public User(String username, String email, String password, String name, String bio, String major, int year,
			List<Integer> preferences, int meetingPreference) {

		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.bio = bio;
		this.major = major;
		this.year = year;
		this.preferences = preferences;
		this.meetingPreference = meetingPreference;
	}

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

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", bio=" + bio + ", major=" + major + ", year=" + year + ", preferences=" + preferences
				+ ", meetingPreference=" + meetingPreference + "]";
	}

	public String getMajor() {
		return major;
	}

	public int getYear() {
		return year;
	}

	public List<Integer> getPreferences() {
		return preferences;
	}

	public int getMeetingPreference() {
		return meetingPreference;
	}

	// Method to check similarity with another StudyBuddy
	public boolean compare(User other) {
		if (!this.major.equals(other.major) || this.year != other.year
				|| !(this.meetingPreference == other.meetingPreference)) {
			return false;
		}

		// Check if there is at least one matching preference
		for (Integer preference : this.preferences) {
			if (other.preferences.contains(preference)) {
				return true; // Found a matching preference
			}
		}
		return false; // No matching preference found
	}

	public boolean checkLogin(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}

	public void displayProfile() {
		System.out.println("Name: " + name);
		System.out.println("Bio: " + bio);
	}

}
