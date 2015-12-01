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
    public Element(String name, Room room, Category Category){
        super();
        this.name = name;
        this.room = room;
        this.Category = Category;
    }

    //endregion

    //region Private Fields

    @Column(name = "name")
    private String name;

    @Column(name = "Room",onDelete = Column.ForeignKeyAction.CASCADE,onUpdate = Column.ForeignKeyAction.CASCADE)
    private Room room;

    @Column(name = "Category",onDelete = Column.ForeignKeyAction.RESTRICT,onUpdate = Column.ForeignKeyAction.CASCADE)
    private Category Category;

    //endregion

    //region Public Properties

    public String getNaziv(){
        return name;
    }
    public void setNaziv(String name){
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
        return Category;
    }
    public void setCategory(Category Category){
        this.Category = Category;
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

    //endregion

}
