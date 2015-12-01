package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Kategorije")
public class Category extends Model {

    //region Constructors

    public Category() {
        super();
    }

    public Category(String naziv) {
        this.naziv = naziv;
        save();
    }

    //endregion

    //region Private Fields

    @Column(name = "naziv")
    private String naziv;


    //TODO
    private static Category rasvjeta;
    private static Category grijanje;
    private static Category klimatizacija;
    private static Category prozori;


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