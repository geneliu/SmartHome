package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

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

    public void setName(String naziv) {
        this.name = naziv;
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


    //endregion


}