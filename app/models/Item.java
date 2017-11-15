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
@Table (name="item")
public class Item extends Model {

    @Id
    public Long Id;

    public String catg_id;

    public String item_name;

    public String item_desc;


    public static Finder<Long, Item> find=new Finder<Long, Item>(Long.class, Item.class);

    public static Item finditembyitemName(String catg_id){
        return find.where().eq("id",catg_id).findUnique();
    }
    public static List<Item> getCategoryWise(Long catg_id){
        return find.where().eq("catg_id",catg_id).findList();
    }

    public static Item findbyitemId(Long id){
        return find.where().eq("Id",id).findUnique();
    }

    public static List<Item> getAllItems(){
        return find.where().findList();
    }

}
