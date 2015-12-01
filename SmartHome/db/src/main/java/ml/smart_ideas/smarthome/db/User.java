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

    public User(String username, String password, String ime, String prezime) {
        super();
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        save();
    }

    //endregion


    //region Private Fields
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

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

    public String getIme() {
        return this.ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
        save();
    }

    public String getPrezime() {
        return this.prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
        save();
    }

    public List<House> getKuce() {
        return //new Select().from(House.class)
                //.where("User = ?", this.getId())
                //.execute();
                getMany(House.class, "User");
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
        this.ime = updatedUser.getIme();
        this.prezime = updatedUser.getPrezime();

        this.save();
    }

    public void addHouse(String Naziv, String Adresa) {
        House house = new House(Naziv,Adresa,this);
        save();
    }

    public void deleteSveKuce(){
        new Delete()
                .from(House.class)
                .where(" = ? ", this.getId())
                .execute();
    }


    //endregion


}