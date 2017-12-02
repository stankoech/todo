package models;


import controllers.Application;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Stankoech on 11/4/2017.
 */
@Entity
@Table(name="category")
public class Category extends Model{

    @Id
    public Long Id;

    public String name;

    public String description;

    public Long user_id;


    public static Finder<Long, Category> find=new Finder<Long, Category>(Long.class, Category.class);

    public static List<Category> getAllCategories(){
        User user= Application.getUserBySession();
        return find.where().eq("user_id",user.Id).findList();
        //return find.where().eqfindUnique();
    }

    public static Category finditembyitemName(String catg_id){
        return find.where().eq("Id",Long.valueOf(catg_id)).findUnique();
    }

    public static Category findbyCategoryId(Long id){
        return find.where().eq("Id",id).findUnique();
    }

}
