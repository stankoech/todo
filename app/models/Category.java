package models;

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
    public long Id;

    public String name;

    public String description;


    public static Finder<Long, Category> find=new Finder<Long, Category>(Long.class, Category.class);

    public static List<Category> getAllCategories(){
        return find.where().findList();
        //return find.where().eqfindUnique();
    }

    public static Category finditembyitemName(String catg_id){
        return find.where().eq("Id",Long.valueOf(catg_id)).findUnique();
    }

    public static Category deleteCat(String catg_id){
        return find.where().eq("Id",Long.valueOf(catg_id)).findUnique();
    }

}
