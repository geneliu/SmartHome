package ml.smart_ideas.smarthome.ws.model.synchronization;




import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("houses")
    @Expose
    private List<House> houses = new ArrayList<House>();

    @SerializedName("deleted")
    @Expose
    private List<String> deleted = new ArrayList<String>();

    public List<String> getDeleted() {
        return deleted;
    }

    public void setDeleted(List<String> deleted) {
        this.deleted = deleted;
    }

    /**
     *
     * @return
     *     The error
     */
    public String getError() {
        return error;
    }

    /**
     *
     * @param error
     *     The error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     *
     * @return
     *     The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     *     The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     *     The houses
     */
    public List<House> getHouses() {
        return houses;
    }

    /**
     *
     * @param houses
     *     The houses
     */
    public void setHouses(List<House> houses) {
        this.houses = houses;
    }
    public void setHouse(House house) { this.houses.add(house);}

}