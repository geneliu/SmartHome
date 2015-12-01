package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;


@Table(name = "Houses")
public class House extends Model {
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "User",onDelete = Column.ForeignKeyAction.CASCADE,onUpdate = Column.ForeignKeyAction.CASCADE)
    private User user;

    public House() {
        super();
    }

    public House(String name, String address, User user) {
        super();
        this.name = name;
        this.address = address;
        this.user = user;
        save();
    }

    //Upiti


    //Getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        save();
    }

    public String getName() {
        return name;
    }

    public void setNaziv(String name) {
        this.name = name;
        save();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        save();
    }

    public void updateHouse(House updatedHouse) {
        this.name = updatedHouse.getName();
        this.address = updatedHouse.getAddress();
        this.user = updatedHouse.getUser();
        this.save();
    }

    public void addRoom(String Name) {
        Room room = new Room(Name,this);
        save();
    }

    public List<Room> getProstorije() {
        return getMany(Room.class, "House");
    }
}