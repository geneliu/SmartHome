package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;


@Table(name = "Kuce")
public class Kuca extends Model {
    @Column(name = "naziv")
    private String naziv;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "Korisnik")
    private Korisnik korisnik;

    public Kuca() {
        super();
    }

    public Kuca(String Naziv, String Adresa, Korisnik Korisnik) {
        super();
        this.naziv = Naziv;
        this.adresa = Adresa;
        this.korisnik = Korisnik;
        save();
    }

    //Upiti


    //Getters and setters

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
        save();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
        save();
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
        save();
    }

    public void updateKuca(Kuca updatedKuca) {
        this.naziv = updatedKuca.getNaziv();
        this.adresa = updatedKuca.getAdresa();
        this.korisnik = updatedKuca.getKorisnik(); //????
        this.save();
    }

    public List<Prostorija> getProstorije() {
        return getMany(Prostorija.class, "Kuca");
    }
}
