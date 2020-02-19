import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import models.*;

import static spark.Spark.*;
import static spark.route.HttpMethod.post;

public class App {
    Sightings sightings = new Sightings();
    public static void main(String[] args){
        staticFileLocation("/public");

        get("/", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "wildlife.hbs");
        }, new HandlebarsTemplateEngine());

        get("/ranger", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "ranger.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animal", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "animal.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangeredAnimal", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "endangeredAnimal.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sighting", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "sighting.hbs");
        }, new HandlebarsTemplateEngine());

        get("/choose", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "choose.hbs");
        }, new HandlebarsTemplateEngine());

        get("/success", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/tracking" , (request,response) ->{
            Map<String, Object> model = new HashMap<String, Object>();

                    String name = request.queryParams("name");
                    String crew = request.queryParams("crew");
                    Ranger newRanger = new Ranger(name,crew);
                    newRanger.save();
                    String aName = request.queryParams("aName");
                    Animal newAnimal = new Animal(newRanger.getId(),aName);
                    newAnimal.save();
                    String sName = request.queryParams("sName");
                    String location = request.queryParams("location");
                    Sightings newSighting = new Sightings(sName,location);
                    newSighting.save();
                    return new ModelAndView(model, "tracking.hbs");
                },new HandlebarsTemplateEngine());


        post("/tracking" , (request,response) ->{
            Map<String, Object> model = new HashMap<String, Object>();

            String name = request.queryParams("name");
            String crew = request.queryParams("crew");
            Ranger newRanger = new Ranger(name,crew);
            newRanger.save();
            return new ModelAndView(model, "tracking.hbs");
        },new HandlebarsTemplateEngine());


        post("/tracking" , (request,response) ->{
            Map<String, Object> model = new HashMap<String, Object>();


            Object rangerId = request.queryParams("rangerId");
            String name = request.queryParams("name");
            Animal newAnimal = new Animal((Integer) rangerId, "name");
            newAnimal.save();
            return new ModelAndView(model, "tracking.hbs");
        },new HandlebarsTemplateEngine());

    }
}
