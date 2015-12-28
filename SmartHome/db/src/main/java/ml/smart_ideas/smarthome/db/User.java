package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;


@Table(name = "Users")
public class User extends Model {

    //region Constructor

    public User() {
        super();
    }

    public User(String username, String password, String name, String surname) {
        super();
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        save();
    }

    //endregion


    //region Private Fields
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    //endregion


    //region Public Properties

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
        save();
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
        save();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        save();
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        save();
    }

    public List<House> getHouses() {
        return getMany(House.class, "User");
    }

    //endregion

    //region Methods

    public static User checkExistingUser(String username) {
        return new Select()
                .from(User.class)
                .where("username = ?", username)
                .executeSingle();
    }


    public void updateUser(User updatedUser) {

        this.username = updatedUser.getUsername();
        this.password = updatedUser.getPassword();
        this.name = updatedUser.getName();
        this.surname = updatedUser.getSurname();

        this.save();
    }

    public void addHouse(String Name, String Address) {
        House house = new House(Name,Address,this);
        save();
    }

    public void deleteAllHouses(){
        new Delete()
                .from(House.class)
                .where(" = ? ", this.getId())
                .execute();
    }


    //endregion


}