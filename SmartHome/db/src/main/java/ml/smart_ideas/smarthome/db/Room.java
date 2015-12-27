package ml.smart_ideas.smarthome.db;

import android.widget.*;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

import ml.smart_ideas.smarthome.db.enums.Category;
import ml.smart_ideas.smarthome.db.enums.ElementEnum;

@Table(name = "Rooms")
public class Room extends Model {

    //region Constructors

    public Room(){
        super();
    }
    public Room(String name, House house){
        this.name = name;
        this.house = house;
        save();
    }

    //endregion

    //region Private Fields

    @Column(name = "name")
    private String name;

    @Column(name = "House",onDelete = Column.ForeignKeyAction.CASCADE,onUpdate = Column.ForeignKeyAction.CASCADE)
    private House house;

    //endregion

    //region Public Properties

    public void updateRoom(Room updatedRoom)
    {
        this.name= updatedRoom.getName();
        this.house= updatedRoom.getHouse();
        this.save();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        save();
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
        save();
    }

    public List<Element> getElements() {
        return getMany(Element.class, "Room");
    }

    public void addSwitchElement(String name, Category category){

        Element element = new Element(name,this,category);
        Switch aSwitch = new Switch(true,element);
    }
    public void addSliderElement(String name, Category category){

        Element element = new Element(name,this,category);
        Slider slider = new Slider(true,element);
    }


    //endregion


}