package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Sliders")
public class Slider extends Model {

    //region Constructors

    public Slider(){
        super();
    }
    public Slider(boolean state,Element element){
        super();
        this.state = state;
        this.sliderValue = 20;
        this.element = element;
        save();
    }

    //endregion


    //region Private Fields

    @Column(name = "state")
    private boolean state;

    @Column(name = "slider_value")
    private int sliderValue;

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

    public int getSliderValue(){
        return sliderValue;
    }
    public void setSliderValue(int sliderValue){
        this.sliderValue = sliderValue;
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