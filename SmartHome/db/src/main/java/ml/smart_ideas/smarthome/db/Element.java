package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import ml.smart_ideas.smarthome.db.enums.Category;
import ml.smart_ideas.smarthome.db.enums.ElementEnum;


@Table(name = "Elements")
public class Element extends Model{

    //region Constructors

    public Element(){
        super();
    }
    public Element(String name, Room room, Category category){
        super();
        this.name = name;
        this.room = room;
        this.category = category;
        save();
    }

    //endregion

    //region Private Fields

    @Column(name = "name")
    private String name;

    @Column(name = "Room",onDelete = Column.ForeignKeyAction.CASCADE,onUpdate = Column.ForeignKeyAction.CASCADE)
    private Room room;

    @Column(name = "category")
    private Category category;

    //endregion

    //region Public Properties

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
        save();
    }

    public Room getRoom(){
        return room;
    }
    public void setRoom(Room room){
        this.room = room;
        save();
    }

    public Category getCategory(){
        return category;
    }
    public void setCategory(Category category){
        this.category = category;
        save();
    }

    public Switch getSwitch(){
        return new Select()
                .from(Switch.class)
                .where("Element = ? ",this.getId())
                .executeSingle();
    }
    public Slider getSlider(){
        return new Select()
                .from(Slider.class)
                .where("Element = ? ",this.getId())
                .executeSingle();
    }

    //endregion

    //region Methods

    public ElementEnum checkElement(){
        Switch aswitch = getSwitch();
        Slider slider = getSlider();
        if(aswitch != null && slider != null)
            return ElementEnum.Both;
        else if(aswitch != null)
            return ElementEnum.Switch;
        else  if(slider != null)
            return ElementEnum.Slider;
        else return ElementEnum.None;
    }

    public void clearElement(){
        ElementEnum elementEnum = checkElement();
        switch (elementEnum){
            case Both:
                Switch aSwitch = getSwitch();
                Slider slider = getSlider();
                aSwitch.delete();
                slider.delete();
                break;
            case Switch:
                Switch aaSwitch = getSwitch();
                aaSwitch.delete();
                break;
            case Slider:
                Slider aSlider = getSlider();
                aSlider.delete();
                break;
        }
    }

    public void addSwitch(){
        clearElement();
        Switch aSwitch = new Switch(false,this);
    }
    public void addSlider(){
        clearElement();
        Slider slider = new Slider(false,this);
    }

    //endregion

}
