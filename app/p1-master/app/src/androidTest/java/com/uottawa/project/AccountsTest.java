package com.uottawa.project;
import org.junit.Assert;
import org.junit.Test;

public class AccountsTest {
	public Account test1 = new Account ("1", "user", "user", "last", "email", "Employee")
	public Account test2;
	
	public void checkSet() throws Exception {

		Assert.assertNotEquals(test1.getUsername(), test2.getUsername());
		Assert.assertNotEquals(test1.getPassword(), test2.getPassword());
		Assert.assertNotEquals(test1.getFirstName(), test2.getFirstName());
		Assert.assertNotEquals(test1.getLastName(), test2.getLastName());
		Assert.assertNotEquals(test1.getEmail(), test2.getEmail());
		Assert.assertNotEquals(test1.getAccountType(), test2.getAccountType());

		test2.setPassword("1");
		test2.setUsername ("user");
		test2.setFirstName ("user");
		test2.setLastName ("last");
		test2.setEmail ("email");
		test2.setAccountType ("Employee");
		
		Assert.assertEquals(test1.getUsername(), test2.getUsername());
		Assert.assertEquals(test1.getPassword(), test2.getPassword());
		Assert.assertEquals(test1.getFirstName(), test2.getFirstName());
		Assert.assertEquals(test1.getLastName(), test2.getLastName());
		Assert.assertEquals(test1.getEmail(), test2.getEmail());
		Assert.assertEquals(test1.getAccountType(), test2.getAccountType());
		
	}
	
}