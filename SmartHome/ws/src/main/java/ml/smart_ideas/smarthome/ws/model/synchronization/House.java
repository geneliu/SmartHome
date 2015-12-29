package ml.smart_ideas.smarthome.ws.model.synchronization;


import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class House {

    @SerializedName("remoteID")
    @Expose
    private String remoteID;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("adress")
    @Expose
    private String adress;
    @SerializedName("rooms")
    @Expose
    private List<Room> rooms = new ArrayList<Room>();

    /**
     *
     * @return
     *     The remoteID
     */
    public String getRemoteID() {
        return remoteID;
    }

    /**
     *
     * @param remoteID
     *     The remoteID
     */
    public void setRemoteID(String remoteID) {
        this.remoteID = remoteID;
    }

    /**
     *
     * @return
     *     The lastModified
     */
    public String getLastModified() {
        return lastModified;
    }

    /**
     *
     * @param lastModified
     *     The last_modified
     */
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     *
     * @param adress
     *     The adress
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     *
     * @return
     *     The rooms
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     *
     * @param rooms
     *     The rooms
     */
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

}