package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Prekidaci")
public class Prekidac extends Model{

    //region Constructors

    public Prekidac(){
        super();
    }
    public Prekidac(boolean stanje,Element element){
        super();
        this.stanje = stanje;
        this.element = element;
        save();
    }

    //endregion


    //region Private Fields

    @Column(name = "stanje")
    private boolean stanje;

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

    public Element getElement(){
        return element;
    }
    public void setElement(Element element){
        this.element = element;
        save();
    }

    //endregion
}
