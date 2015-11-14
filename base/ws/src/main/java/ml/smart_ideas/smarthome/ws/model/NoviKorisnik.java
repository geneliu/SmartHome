package ml.smart_ideas.smarthome.ws.model;

/**
 * Created by dudo on 11/14/15.
 */
public class NoviKorisnik {
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public NoviKorisnik() {
    }

    public NoviKorisnik(String name, String surname, String username, String password) {
        this.ime = name;
        this.prezime = surname;
        this.username = username;
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
