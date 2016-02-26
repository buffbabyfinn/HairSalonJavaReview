import java.util.List;
import org.sql2o.*;

public class Stylist {

  public Stylist() {

  }

  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getDescription().equals(newStylist.getDescription()) && this.getDueDate() == newStylist.getDueDate() && this.getId() == newStylist.getId();
    }
  }
}
