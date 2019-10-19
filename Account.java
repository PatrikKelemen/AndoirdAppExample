import java.security.*;
import java.nio.charset.StandardCharsets;
public class Account {
	
	/*
	* Stores the account's password.
	*/
	private String password;
	
	/*
	*Store the account's Hash password
	*/
	private byte[] hashPassword;
	
	/*
	* Stores the account's username.
	*/
	private String userName;

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
		hashPassword = hashPassword(password);
	}
	
	/*
	*Methode to hash password to SHA-256
	*/
	public byte[] hashPassword(String pass){
	
		try{
			
			//hashing the password to SHA-256 
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] passwordHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			return passwordHash;
		}
		catch(Exception e){
			return null;
		}
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