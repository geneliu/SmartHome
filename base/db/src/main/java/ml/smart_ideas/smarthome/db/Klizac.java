package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Klizaci")
public class Klizac extends Model {

    //region Constructors

    public Klizac(){
        super();
    }
    public Klizac(boolean stanje,short vrijednostKlizaca,Element element){
        super();
        this.stanje = stanje;
        this.vrijednostKlizaca = vrijednostKlizaca;
        this.element = element;
        save();
    }

    //endregion


    //region Private Fields

    @Column(name = "stanje")
    private boolean stanje;

    @Column(name = "vrijednost_klizaca")
    private short vrijednostKlizaca;

    @Column(name = "Element",onDelete = Column.ForeignKeyAction.CASCADE,onUpdate = Column.ForeignKeyAction.CASCADE)
    private Element element;

    //endregion

    //Public Properties

    public boolean getStanje(){
        return stanje;
    }
    public void setStanje(boolean stanje){
        this.stanje = stanje;
        save();
    }

    public short getVrijednostKlizaca(){
        return vrijednostKlizaca;
    }
    public void setVrijednostKlizaca(short vrijednostKlizaca){
        this.vrijednostKlizaca = vrijednostKlizaca;
        save();
    }

    public Element getElement(){
        return element;
    }
    public void setElement(Element element){
        this.element = element;
        save();
    }

    //endregion
}