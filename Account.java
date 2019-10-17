public class Account {
	
	/*
	* Stores the account's password.
	*/
	private String password;

	/*
	* Stores the account's username.
	*/
	private String username;

	/*
	* Stores the account's profile picture.
	*/
	//private image profilePic;

	/*
	* Stores the user's first name.
	*/
	private String firstName;

	/*
	* Stores the user's last name.
	*/
	private String lastName;


	/**
	* Creates a new Account.
	* @param password a string with the password
	* @param username a string with the username
	* @param firstName a string with the first name of the user
	* @param lastName a string with the last name of the user
	*/
	public Account(String password, String username, String firstName, String lastName) {
		this.password = password;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	//Getters & Setters
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}