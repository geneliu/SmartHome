package ml.smart_ideas.smarthome.ws.model.synchronization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Switch {


    @SerializedName("stanje")
    @Expose
    private boolean stanje;


    /**
     *
     * @return
     *     The stanje
     */
    public boolean getStanje() {
        return stanje;
    }

    /**
     *
     * @param stanje
     *     The stanje
     */
    public void setStanje(boolean stanje) {
        this.stanje = stanje;
    }

}