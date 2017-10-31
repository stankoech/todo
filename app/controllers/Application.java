package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.User;
import play.*;
import play.api.libs.json.Json;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(login.render("Login || TODO"));
    }

    public static Result registerPage() {
        return ok(register.render("Register"));
    }

    //public static Result dashboard() {
        //return ok(dashboard.render("Dashboard"));}
    public static Result register(){
        ObjectNode result= play.libs.Json.newObject();
        DynamicForm form= Form.form().bindFromRequest();
        String username=form.get("username");
        String email=form.get("email");
        String password=form.get("password");
        String mobile=form.get("mobile");

        User myuser =new User();
        myuser.username=username;
        myuser.email=email;
        myuser.password=password;
        myuser.mobile=mobile;
        myuser.save();

        result.put("message", "Users created successful");
        result.put("code","2000");


        return ok(result);
    }
}
