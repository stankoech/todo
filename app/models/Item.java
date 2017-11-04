package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Stankoech on 11/4/2017.
 */
@Entity
@Table (name="item")
public class Item extends Model {

    @Id
    public long Id;

    public String catg_id;

    public String item_name;

    public String item_desc;


    public static Finder<Long, Item> find=new Finder<Long, Item>(Long.class, Item.class);

    public static Item finditembyitemName(String item_name){
        return find.where().eq("name",item_name).findUnique();
    }

}
