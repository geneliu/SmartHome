package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import ml.smart_ideas.smarthome.db.enums.ElementEnum;


@Table(name = "Elementi")
public class Element extends Model{

    //region Constructors

    public Element(){
        super();
    }
    public Element(String naziv, Prostorija prostorija, Kategorija kategorija){
        super();
        this.naziv = naziv;
        this.prostorija = prostorija;
        this.kategorija = kategorija;
    }

    //endregion

    //region Private Fields

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "Prostorija",onDelete = Column.ForeignKeyAction.CASCADE,onUpdate = Column.ForeignKeyAction.CASCADE)
    private Prostorija prostorija;

    @Column(name = "Kategorija",onDelete = Column.ForeignKeyAction.RESTRICT,onUpdate = Column.ForeignKeyAction.CASCADE)
    private Kategorija kategorija;

    //endregion

    //region Public Properties

    public String getNaziv(){
        return naziv;
    }
    public void setNaziv(String naziv){
        this.naziv = naziv;
        save();
    }

    public Prostorija getProstorija(){
        return prostorija;
    }
    public void setProstorija(Prostorija prostorija){
        this.prostorija = prostorija;
        save();
    }

    public Kategorija getKategorija(){
        return kategorija;
    }
    public void setKategorija(Kategorija kategorija){
        this.kategorija = kategorija;
        save();
    }

    public Prekidac getPrekidac(){
        return new Select()
                .from(Prekidac.class)
                .where("Element = ? ",this.getId())
                .executeSingle();
    }
    public Klizac getKlizac(){
        return new Select()
                .from(Klizac.class)
                .where("Element = ? ",this.getId())
                .executeSingle();
    }

    //endregion

    //region Methods

    public ElementEnum checkElement(){
        Prekidac prekidac = getPrekidac();
        Klizac klizac = getKlizac();
        if(prekidac != null && klizac != null)
            return ElementEnum.Both;
        else if(prekidac != null)
            return ElementEnum.Prekidac;
        else  if(klizac != null)
            return ElementEnum.Klizac;
        else return ElementEnum.None;
    }

    //endregion

}
