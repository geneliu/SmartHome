package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;


@Table(name = "Korisnici")
public class Korisnik extends Model {

    //region Constructor

    public Korisnik() {
        super();
    }

    public Korisnik(String username, String password, String ime, String prezime) {
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

    public List<Kuca> getKuce() {
        return //new Select().from(Kuca.class)
                //.where("Korisnik = ?", this.getId())
                //.execute();
        getMany(Kuca.class, "Korisnik");
    }

    //endregion

    //region Methods

    public static Korisnik checkExistingKorisnik(String username) {
        return new Select()
                .from(Korisnik.class)
                .where("username = ?", username)
                .executeSingle();
    }


    public void updateKorisnik(Korisnik updatedKorisnik) {

        this.username = updatedKorisnik.getUsername();
        this.password = updatedKorisnik.getPassword();
        this.ime = updatedKorisnik.getIme();
        this.prezime = updatedKorisnik.getPrezime();

        this.save();
    }


    //endregion


}
