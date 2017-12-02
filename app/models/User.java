package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Constraint;
import java.util.List;

/**
 * Created by Stankoech on 10/31/2017.
 */
@Entity
@Table(name="tbl_users")
public class User extends Model {

    @Id
    public Long Id;

    @Constraints.Required
    @Constraints.MinLength(value = 4)
    public String username;

    @Constraints.Required
    @Column(unique = true,nullable = false)
    public  String email;

    @Constraints.Required
    @Constraints.MinLength(value = 6)
    public String password;

    @Constraints.Required
    public String mobile;

    @Constraints.Required
    public Integer pin;

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
