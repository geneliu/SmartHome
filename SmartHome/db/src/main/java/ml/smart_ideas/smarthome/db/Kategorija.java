package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Kategorije")
public class Kategorija extends Model {

    //region Constructors

    public Kategorija() {
        super();
    }

    public Kategorija(String naziv) {
        this.naziv = naziv;
        save();
    }

    //endregion

    //region Private Fields

    @Column(name = "naziv")
    private String naziv;


    //TODO
    private static Kategorija rasvjeta;
    private static Kategorija grijanje;
    private static Kategorija klimatizacija;
    private static Kategorija prozori;


    //endregion

    //region Public Properties

    public String getNaziv() {
        return naziv;
    }
    public void setNaziv(String naziv){
        this.naziv = naziv;
        save();
    }


    //endregion
}
