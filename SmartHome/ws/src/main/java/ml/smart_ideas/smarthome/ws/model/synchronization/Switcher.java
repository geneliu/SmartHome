package ml.smart_ideas.smarthome.ws.model.synchronization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Switcher {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("stanje")
    @Expose
    private String stanje;

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
     *     The stanje
     */
    public String getStanje() {
        return stanje;
    }

    /**
     *
     * @param stanje
     *     The stanje
     */
    public void setStanje(String stanje) {
        this.stanje = stanje;
    }

}