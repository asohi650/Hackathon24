
public class User {

	private String username;
	private String email;
	private String password;
	private String name;
	private String bio;
	
	public User(String username, String email, String password, String name, String bio) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.bio = bio;
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
	public boolean checkLogin(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}
	public void displayProfile() {
		System.out.println("Name: " + name);
		System.out.println("Bio: " + bio);
	}
		
	
}
