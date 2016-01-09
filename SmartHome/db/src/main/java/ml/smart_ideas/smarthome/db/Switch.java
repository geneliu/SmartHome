package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Switches")
public class Switch extends Model{

    //region Constructors

    public Switch(){
        super();
    }
    public Switch(boolean state,Element element){
        super();
        this.state = state;
        this.element = element;
        save();
    }

    //endregion


    //region Private Fields

    @Column(name = "state")
    private boolean state;

    @Column(name = "Element",onDelete = Column.ForeignKeyAction.CASCADE,onUpdate = Column.ForeignKeyAction.CASCADE)
    private Element element;

    //endregion

    //Public Properties

    public boolean getState(){
        return state;
    }
    public void setState(boolean state){
        this.state = state;
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