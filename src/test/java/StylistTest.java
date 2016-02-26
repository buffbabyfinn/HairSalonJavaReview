import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;

public class StylistTest {

	@Rule
	public DatabaseRule database = new DatabaseRule();

	@Test
	public void all_emptyAtFirst() {
		assertEquals(Stylist.all().size(), 0);
	}

	@Test
	public void save_savesIntoDatabase_true() {
		Stylist myStylist = new Stylist("Serah Jones");
		myStylist.save();
		assertTrue(Stylist.all().get(0).equals(myStylist));
	}

	@Test
	public void equals_returnsTrueIfNamesAretheSame() {
		Stylist firstStylist = new Stylist("Serah Jones");
		Stylist secondStylist = new Stylist("Serah Jones");
		assertTrue(firstStylist.equals(secondStylist));
	}

	@Test
	public void update_updatesStylistObject_true(){
		Stylist myStylist = new Stylist("Serah Jones");
		myStylist.save();
		myStylist.update("Serah Smith");
		assertEquals(Stylist.all().get(0).getName(), "Serah Smith");
	}

	@Test
	public void delete_removesStylistObject_true(){
		Stylist myStylist = new Stylist("Serah Jones");
		myStylist.save();
		myStylist.delete();
		assertEquals(Stylist.all().size(), 0);
	}

	@Test
	public void find_findStylistObjectInDatabase_true() {
		Stylist myStylist = new Stylist("Serah Jones");
		myStylist.save();
		Stylist savedStylist = Stylist.find(myStylist.getId());
		assertTrue(myStylist.equals(savedStylist));
	}

	// //implement a test for the getClients method
	// @Test
	// public void getClients_returnsListOfClients_true(){
  //
	// 	//create a cuisine object
	// 	Stylist myStylist = new Stylist("Northern Indian");
	// 	//save the cuisine object
	// 	myStylist.save();
  //
	// 	Client firstClient = new Client("Bollywood Theatre", "111-111-1111", "Portland, OR", myStylist.getId());
  // 		Client secondClient = new Client("Natural Selection", "999-999-9999", "Portland, OR", myStylist.getId());
  //
	// 	//save first restaurant object
	// 	firstClient.save();
	// 	//save second restaurant object
	// 	secondClient.save();
  //
	// 	assertEquals(myStylist.getClients().size(), 2);
	// }
}
