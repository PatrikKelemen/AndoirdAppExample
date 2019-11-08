public class test{
	private static Account testAccount = new Account("team", "User", "firstname", "lastName");
	
	 public static void main(String[] args) {
		System.out.println(testAccount.getPassword());
		System.out.println(testAccount.hashPassword("team"));
    }
	
	
	
}