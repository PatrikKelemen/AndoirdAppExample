public class ClinicTest {
	public Clinic test1 = new Clinic ("testName")
	public Clinic test2;

	public void checkSet() throws Exception {

		test1.setId ("h");

		Assert.assertNotEquals(test1.getId, test2.getId);
		test2.setId ("h")
		Assert.assertEquals(test1.getId,test2.getId);



	}