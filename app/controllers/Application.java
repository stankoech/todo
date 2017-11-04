package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Category;
import models.Item;
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

    public static Result addcategoryPage(){
        return ok(addcategory.render("Add Catehories"));
    }

    public static Result itemPage(){
        return ok(additem.render("ITEMS"));
    }

    public static Result tododetails(){
        return ok(tododetails.render("DASHBOARD"));
    }

    public static Result login() {
        ObjectNode result=play.libs.Json.newObject();
        DynamicForm form= Form.form().bindFromRequest();
        Logger.info(form.get("username"));

        String username=form.get("username");
        String password=form.get("password");

        User newuser=User.finduserbyusername(username);

        if (newuser!=null){
            //check password
         if (!newuser.password.equals(password)){
             result.put("message", "Invalid Password");
             result.put("code","201");
             return ok(result);
         }
        }
         else {
            result.put("message", "Invalid Username");
            result.put("code","201");
            return ok(result);

        }

        result.put("message", "Login successful");
        result.put("code","203");
        //return ok(result);
        return tododetails();
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

        if (User.finduserbyusername(username)!=null){
            result.put("message", "User "+username +"already exists");
            result.put("code","201");
            return ok(result);
        }


        User myuser =new User();
        myuser.username=username.toLowerCase();
        myuser.email=email;
        myuser.password=password;
        myuser.mobile=mobile;
        myuser.save();

        Logger.info("username",username);
        result.put("message", "Users created successful");
        result.put("code","2000");


        return ok(result);
    }

    public static Result addcategory(){
        ObjectNode categ= play.libs.Json.newObject();
        DynamicForm form= Form.form().bindFromRequest();
        String name=form.get("name");
        String description=form.get("description");

        Category newcat =new Category ();
        newcat.name=name.toLowerCase();
        newcat.description=description;

        newcat.save();

        Logger.info("name",name);
        categ.put("message", "New Category created successfully");
        categ.put("code","2000");


        return ok(categ);
    }


    public static Result additem(){
        ObjectNode aditem= play.libs.Json.newObject();
        DynamicForm form= Form.form().bindFromRequest();
        String catg_id=form.get("catg_id");
        String name=form.get("name");
        String description=form.get("description");

        Item newitem =new Item();
        newitem.catg_id=catg_id;
        newitem.item_name=name.toLowerCase();
        newitem.item_desc=description;

        newitem.save();

        Logger.info("name",name);
        aditem.put("message", "New Item added successfully");
        aditem.put("code","2001");


        return ok(aditem);
    }
}
