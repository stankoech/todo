package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Category;
import models.Item;
import models.User;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(login.render("Login || TODO"));
    }

    public static Result addcategoryPage(){
        if(!isLoggedIn())
            return index();
        return ok(addcategory.render("Add Categories"));
    }


    public static Result itemPage(Long catgId){
        if(!isLoggedIn())
            return index();
        return ok(additem.render("ITEMS",catgId));
    }

    public static Result tododetails(){
        if(!isLoggedIn())
            return index();
        return ok(tododetails.render("DASHBOARD"));

    }

    public static Result viewitems(Long categoryId){
        if(!isLoggedIn())
            return index();
        return ok(viewitems.render("VIEITEMS",categoryId));
    }

    public static Result login() {
        ObjectNode result = play.libs.Json.newObject();
        DynamicForm form = Form.form().bindFromRequest();
        Logger.info(form.get("username"));

        String username = form.get("username");
        String password = form.get("password");

        User newuser = User.finduserbyusername(username);

        if (newuser != null) {
            //check password
            if (!newuser.password.equals(password)) {
                result.put("message", "Invalid Password");
                result.put("title", Play.application().configuration().getString("app.login.error"));
                result.put("code", "201");
                return ok(result);
            }
            }
        else {
                result.put("title", Play.application().configuration().getString("app.login.error"));
                result.put("message", "Invalid Username");
                result.put("code", "201");
                return ok(result);
            }

            //creating a session
            session("username", username);
            result.put("title", Play.application().configuration().getString("app.login.success"));
            result.put("message", "Login successful");
            result.put("code", "200");

           return ok(result);
           // return tododetails();
        }

    public static Result registerPage() {
        return ok(register.render("Register"));
    }

    public static Result register() {
        ObjectNode result = play.libs.Json.newObject();
        DynamicForm form = Form.form().bindFromRequest();
        String username = form.get("username");
        String email = form.get("email");
        String password = form.get("password");
        String cpassword = form.get("cpassword");

        if (!password.equals(cpassword)) {
            return ok("Password mismatch");
        }


        String mobile=form.get("mobile");
        Integer pin= Integer.valueOf(form.get("pin"));

        if (User.finduserbyusername(username)!=null){
            result.put("title", "Registration Error");
            result.put("message", "User "+username +"already exists");
            result.put("code","201");
            return ok(result);
        }
        User myuser =new User();
        myuser.username=username.toLowerCase();
        myuser.email=email;
        myuser.password=password;
        myuser.mobile=mobile;
        myuser.pin=pin;
        myuser.save();

        Logger.info("username",username);
        result.put("title", "Registration Success");
        result.put("message", "User has been created successful");
        result.put("code","200");

        return ok(result);
        //return registerPage();
    }

    public static Result addcategory(){
        ObjectNode categ= play.libs.Json.newObject();
        DynamicForm form= Form.form().bindFromRequest();
        //Long Id=form.get("Id");
       // Long user_id= Long.valueOf(form.get("user_id"));
        String name=form.get("name");
        String description=form.get("description");

       /* Category newuser=Category.findbyCategoryId(Id);

        if (Category.findbyCategoryId(Long.valueOf(Id))!=null){
            categ.put("message", "User already exists");
            categ.put("code","201");
            return ok(categ);
        }*/

        Category newcat =new Category ();
        newcat.user_id=getUserBySession().Id;
        newcat.name=name;
        newcat.description=description;

        newcat.save();

        Logger.info("name",name);
        categ.put("title", "Category Success");
        categ.put("message", "New Category created successfully");
        categ.put("code","200");

        //return tododetails();
        return ok(categ);
    }


    public static Result additem(){
        ObjectNode aditem= play.libs.Json.newObject();
        DynamicForm form= Form.form().bindFromRequest();
        String catg_id= form.get("catg_id");
        String name=form.get("name");
        String description=form.get("description");

        Item newitem =new Item();
        newitem.catg_id=catg_id;
        newitem.item_name=name;
        newitem.item_desc=description;

        newitem.save();

        Logger.info("name",name);
        aditem.put("title", "Item Success");
        aditem.put("message", "New Item added successfully");
        aditem.put("code","200");
        aditem.put("catId",catg_id);

        //return viewitems(Long.valueOf(catg_id));
        return ok(aditem);
    }

    public static Result deletecat(Long id) {
        Category del = Category.findbyCategoryId(id);

        if (del!= null) {
            Logger.info("Category Found! Now delete it!");
            del.delete();
        }
        else {
            Logger.info("Category Not Found!");
        }
        return tododetails();
    }

    public static Result editcategoryPage(Long id){
        if(!isLoggedIn())
            return index();
        Category editcat = Category.findbyCategoryId(id);
        return ok(editcategory.render("Edit Category",id,editcat));
    }

    public static Result editcategory() {
        ObjectNode categ= play.libs.Json.newObject();
        DynamicForm form= Form.form().bindFromRequest();
        Long Id= Long.valueOf(form.get("categoryId"));
        String name=form.get("name");
        String description=form.get("description");

        Category newuser=Category.findbyCategoryId(Id);
        if (newuser!=null){
            newuser.name = name;
            newuser.description = description;
            newuser.user_id=getUserBySession().Id;
            newuser.save();
            categ.put("title", "Category Success");
            categ.put("message", "Category edited successfully");
            categ.put("code","200");
            //return tododetails();
            return ok(categ);
        }

        Logger.info("name",name);
        categ.put("message", "New Category created successfully");
        categ.put("code","200");

        //return tododetails();
        return ok(categ);
    }


    public static Result deleteitem(Long id) {
        Item delitem = Item.findbyitemId(id);

        if (delitem!= null) {
            Logger.info("Item Found! Now delete it!");
            delitem.delete();
        }
        else {
            Logger.info("Item Not Found!");
        }
        return tododetails();

    }

    public static Result edititemPage(Long id){
        if(!isLoggedIn())
            return index();
        Item edititem =Item.findbyitemId(id);
        return ok(views.html.edititem.render("Edit Item",id,edititem));
     }

    public static Result edititem(){
        ObjectNode editm = play.libs.Json.newObject();
        DynamicForm form= Form.form().bindFromRequest();
        Long Id= Long.valueOf(form.get("itemID"));
        String catg_id= form.get("catg_id");
        String name=form.get("name");
        String description=form.get("description");

        Item newitem=Item.findbyitemId(Id);
        if (newitem!=null){
            newitem.catg_id=catg_id;
            newitem.item_name = name;
            newitem.item_desc = description;
            newitem.save();
            editm.put("title", "Item Success");
            editm.put("message", "Item edited successfully");
            editm.put("code","200");
            //return tododetails();
            return ok(editm);
        }
        //return tododetails();
        return ok(editm);

    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                routes.Application.index()
        );
    }

    public static Result viewpassword(User user){
        return ok(viewpassword.render("VIEWPASSWORD",user));
    }

    public static Result forgotpasswordpage() {
        return ok(forgotpassword.render("forgot password"));
    }

    public static Result forgotpassword() {
        ObjectNode result=play.libs.Json.newObject();
        DynamicForm form= Form.form().bindFromRequest();
        Logger.info(form.get("username"));

        String username=form.get("username");
        Integer pin= Integer.valueOf(form.get("pin"));


        User newuser=User.finduserbyusername(username);
        if (newuser!=null){
            //check password
            if (!newuser.pin.equals(pin)){
                result.put("title", "PIN Error");
                result.put("message", "Invalid PIN");
                result.put("code","201");
                return ok(result);
            }
        }
        else {
            result.put("title", "Login Error");
            result.put("message", "Invalid Username");
            result.put("code","201");
            return ok(result);

        }
        result.put("title", "Password Success");
        result.put("message", "Password retrieve Successfully");
        result.put("code","200");
        result.put("pass","Your password is "+newuser.password);
        return ok(result);

//        return viewpassword(newuser);
    }

    //session reading

    public static boolean isLoggedIn() {
        String user = session().get("username");
        if(user != null)
            return true;

        return false;
    }
    //Display user in dashboard

    public static String displayUser() {
        String user = session().get("username");
       if(user != null)
           return user;

        return null;
    }

    public static User getUserBySession(){
        String userall = session().get("username");
        return User.finduserbyusername(userall);
    }
}
