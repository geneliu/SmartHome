package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by Admin on 13.11.2015..
 */
@Table(name="Kuce")
public class Kuca extends Model {
    @Column(name = "naziv")
    private String naziv;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "korisnik")
    private Korisnik korisnik;

    public Kuca()
    {
        super();
    }
    public Kuca(String Naziv,String Adresa,Korisnik Korisnik)
    {
        super();
        this.naziv=Naziv;
        this.adresa=Adresa;
        this.korisnik=Korisnik;
    }

    //Upiti
    public List<Kuca> popisSvihKuca()
    {
        return getMany(Kuca.class,"Kuca");
    }


    //Getters and setters

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
