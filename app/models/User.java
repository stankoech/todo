package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

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
        return find.where().eq("username",username).findUnique();
    }
    public static  User finduserbypassword(String password){
        return find.where().eq("password",password).findUnique();
    }

    public static List<User> getAllUsers(){
        return find.where().findList();
    }

}
