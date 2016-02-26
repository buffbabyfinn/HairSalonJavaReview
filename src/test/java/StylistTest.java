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

	@Test
	public void getClients_returnsListOfClients_true(){
		Stylist myStylist = new Stylist("Brittany Stylist");
		myStylist.save();

		Client firstClient = new Client(myStylist.getId(), "Sabrina Balabuszko", "815-404-4500", "SBadass@gmail.com");
  	Client secondClient = new Client(myStylist.getId(), "Client McClienty", "555-555-5555", "sbemail@homestarrunner.com");
		firstClient.save();
		secondClient.save();
		assertEquals(myStylist.getClients().size(), 2);
	}
}
