import java.util.List;
import java.util.Arrays;
import org.sql2o.*;


public class Client {
  private int id;
  private int stylistid;
  private String clientname;
  private String phone;
  private String email;

  public Client(int stylistid, String clientname, String phone, String email) {
    this.stylistid = stylistid;
    this.clientname = clientname;
    this.phone = phone;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public int getStylistId() {
    return stylistid;
  }

  public String getClientName() {
    return clientname;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getClientName().equals(newClient.getClientName()) && this.getId() == newClient.getId() && this.getPhone().equals(newClient.getPhone()) && this.getEmail().equals(newClient.getEmail());
    }
  }

  public List<Client> sortClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where stylistid = (:stylistid) ORDER BY clientname =(:clientname) [ASC] [NULLS {FIRST}]";
      return con.createQuery(sql)
        .addParameter("stylistid", stylistid)
        .addParameter("clientname", clientname)
        .executeAndFetch(Client.class);
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (stylistid, clientname, phone, email) VALUES (:stylistid, :clientname, :phone, :email)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("stylistid", this.stylistid)
      .addParameter("clientname", this.clientname)
      .addParameter("phone", this.phone)
      .addParameter("email", this.email)
      .executeUpdate().getKey();
    }
  }

  public static List<Client> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }


    public static Client find(int id){
      try(Connection con = DB.sql2o.open()){
        String sql = "SELECT * FROM clients WHERE id = (:id)";
        Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
        return client;
      }
    }

  public void update(String newClientName) {
    this.clientname = newClientName;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET clientname = :newClientName WHERE id = (:id)";
      con.createQuery(sql)
      .addParameter("newClientName", newClientName)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = (:id)";
      con.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  public String getStylistName(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id = :stylistid";
      Stylist stylist = con.createQuery(sql)
      .addParameter("stylistid", this.getStylistId())
      .executeAndFetchFirst(Stylist.class);
    return stylist.getName();
    }
  }
}
