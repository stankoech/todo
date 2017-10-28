package controllers;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(login.render("Login || TODO"));
    }

    public static Result register() {
        return ok(register.render("Register"));
    }

    //public static Result dashboard() {
        //return ok(dashboard.render("Dashboard"));}
}
