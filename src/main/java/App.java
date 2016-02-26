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

  //
  //
  // "/client/$client.getId()"
  //
  // "/stylists"
  //
  // "/newclient"
  //
  // "/stylist/$stylist.getId()"
  }
}
