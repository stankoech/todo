package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Created by Stankoech on 10/31/2017.
 */
@Entity
@Table(name="tbl_users")
public class User extends Model {

    @Id
    public Long Id;

    public String username;

    public  String email;

    public String password;

    public String mobile;

    public static Finder<Long, User> find=new Finder<Long, User>(Long.class, User.class);
    //Helps us to find items.


    //Check for a unique user with given username
    //@Param Username
    public static User finduserbyusername(String username){
        //select 1 from tbl_users where username=username
        return find.where().eq("username",username).findUnique();

    }

}
