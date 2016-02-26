import java.util.List;
import java.util.Arrays;
import org.sql2o.*;


public class Client {

  public Client() {

  }

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getDescription().equals(newClient.getDescription()) && this.getDueDate() == newClient.getDueDate() && this.getId() == newClient.getId();
    }
  }
}
