import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Client.all().size(), 0);
  }

  @Test
  public void all_containsOneClientObject_true() {
  		Client myClient = new Client(1, "Sabrina Balabuszko", "815-404-4500", "SBadass@gmail.com");
  		myClient.save();
      assertEquals(Client.all().size(), 1);
  }

  @Test
  public void equals_returnsTrueIfNameIsSame_true(){
  	Client firstClient = new Client(1, "Sabrina Balabuszko", "815-404-4500", "SBadass@gmail.com");
  	Client secondClient = new Client(1, "Sabrina Balabuszko", "815-404-4500", "SBadass@gmail.com");
  	assertTrue(firstClient.equals(secondClient));
  }

  @Test
	public void save_returnsTrue_true() {
  	Client myClient = new Client(1, "Sabrina Balabuszko", "815-404-4500", "SBadass@gmail.com");
		myClient.save();
		assertTrue(Client.all().get(0).equals(myClient));
	}
	@Test
	public void update_returnsUpdatedClientObject_true(){
		Client myClient = new Client(1, "Sabrina Balabuszko", "815-404-4500", "SBadass@gmail.com");
		myClient.save();
		myClient.update("Sabornad Babblyzoo");
		assertEquals(Client.all().get(0).getClientName(), "Sabornad Babblyzoo");
	}

	@Test
	public void delete_removesClientObjectFromDB_true(){
  	Client myClient = new Client(1, "Sabrina Balabuszko", "815-404-4500", "SBadass@gmail.com");
		myClient.save();
		myClient.delete();
		assertEquals(Client.all().size(), 0);
	}

	@Test
	public void getStylistType_returnsStylistTypeFromClientObject_true(){
		Stylist myStylist = new Stylist("Brittany Stylist");
		myStylist.save();
		int myStylistId = myStylist.getId();
		Client myClient = new Client(myStylistId, "Sabrina Balabuszko", "815-404-4500", "SBadass@gmail.com");
		myClient.save();
		assertEquals(myClient.getStylistName(), "Brittany Stylist");
	}

	@Test
	public void find_returnClientObject_true(){
		Client firstClient = new Client(1, "Sabrina Balabuszko", "815-404-4500", "SBadass@gmail.com");
  	Client secondClient = new Client(2, "Client McClient", "555-555-5555", "e@mail.com");
		firstClient.save();
		secondClient.save();
		Client retrievedClient = Client.find(secondClient.getId());
		assertTrue(retrievedClient.equals(secondClient));
	}

}
