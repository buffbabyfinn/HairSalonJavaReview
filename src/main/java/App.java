import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "views/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      model.put("template", "views/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("stylistname");
      Stylist myStylist = new Stylist(name);
      myStylist.save();
      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      model.put("template", "views/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/newclient", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String clientname = request.queryParams("clientname");
      String phone = request.queryParams("phone");
      String email = request.queryParams("email");
      int stylistid = Integer.parseInt(request.queryParams("stylistid"));
      Client myClient = new Client(stylistid, clientname, phone, email);
      myClient.save();
      response.redirect("/");
      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      model.put("template", "views/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist myStylist = Stylist.find(Integer.parseInt(request.params(":id")));
      List<Client> allStylistClients = myStylist.getClients();
      model.put("stylist", myStylist);
      model.put("clients", allStylistClients);
      model.put("template", "views/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/client/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      String stylistid = Integer.toString(client.getStylistId());
      model.put("client", client);
      model.put("stylistid", stylistid);
      model.put("template", "views/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

     post("/client/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client myClient = Client.find(Integer.parseInt(request.params(":id")));
      myClient.delete();
      response.redirect("/");
      model.put("template", "views/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/:id/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      Stylist myStylist = Stylist.find(client.getStylistId());
      String clientname = request.queryParams("clientname");
      client.update(clientname);
      response.redirect("/");
      model.put("template", "views/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:id/delete", (request, response) -> {
     HashMap<String, Object> model = new HashMap<String, Object>();
     Stylist myStylist = Stylist.find(Integer.parseInt(request.params(":id")));
     myStylist.delete();
     response.redirect("/");
     model.put("template", "views/index.vtl");
     return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());
  }
}
