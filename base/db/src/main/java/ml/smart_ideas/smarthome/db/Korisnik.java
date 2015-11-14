package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name="Korisnik")
public class Korisnik extends Model {

    //region Constructor

    public Korisnik() {
        super();
    }

    public Korisnik(String username,String ime, String prezime) {
        super();
        this.username = username;
        this.ime = ime;
        this.prezime = prezime;
    }

    //endregion


    //region Private Fields
    @Column(name = "username")
    private String username;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    //endregion


    //region queryes




    //endregion
    //region Public Properties

    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getIme(){
        return this.ime;
    }
    public void setIme(String ime){
        this.ime = ime;
    }

    public String getPrezime(){
        return this.prezime;
    }
    public void setPrezime(String prezime){
        this.prezime = prezime;
    }

    //endregion


}
