package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Table(name = "Houses")
public class House extends Model {

    @Column(name="remoteID")
    private Long remoteID;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "User",onDelete = Column.ForeignKeyAction.CASCADE,onUpdate = Column.ForeignKeyAction.CASCADE)
    private User user;




    @Column(name = "last_modified")
    private String last_modified;

    public House() {
        super();
    }

    public House(String name, String address, User user) {
        super();
        this.name = name;
        this.address = address;
        this.user = user;
        this.setLast_modified();
        save();
        this.remoteID= this.getId()+10000;
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

    public void setName(String name) {
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

    public Long getRemoteID() {
        return remoteID;
    }

    public void setRemoteID(Long remoteID) {
        this.remoteID = remoteID;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        this.last_modified = sdf.format(new Date());
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

    public List<Room> getRooms() {
        return getMany(Room.class, "House");
    }
}