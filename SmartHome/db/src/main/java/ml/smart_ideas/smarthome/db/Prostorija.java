package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Prostorije")
public class Prostorija extends Model {

    //region Constructors

    public Prostorija(){
        super();
    }
    public Prostorija(String naziv, Kuca kuca){
        this.naziv = naziv;
        this.kuca = kuca;
        save();
    }

    //endregion

    //region Private Fields

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "Kuca",onDelete = Column.ForeignKeyAction.CASCADE,onUpdate = Column.ForeignKeyAction.CASCADE)
    private Kuca kuca;

    //endregion

    //region Public Properties

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
        save();
    }

    public Kuca getKuca() {
        return kuca;
    }

    public void setKuca(Kuca kuca) {
        this.kuca = kuca;
        save();
    }

    public List<Element> getElemente() {
        return getMany(Element.class, "Prostorija");
    }


    //endregion


}
