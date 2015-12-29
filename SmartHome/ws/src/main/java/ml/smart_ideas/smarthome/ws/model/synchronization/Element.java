package ml.smart_ideas.smarthome.ws.model.synchronization;


import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Element {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("categoryID")
    @Expose
    private String categoryID;
    @SerializedName("sliders")
    @Expose
    private List<Slider> sliders = new ArrayList<Slider>();
    @SerializedName("switchers")
    @Expose
    private List<Switcher> switchers = new ArrayList<Switcher>();

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
     *     The categoryID
     */
    public String getCategoryID() {
        return categoryID;
    }

    /**
     *
     * @param categoryID
     *     The categoryID
     */
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    /**
     *
     * @return
     *     The sliders
     */
    public List<Slider> getSliders() {
        return sliders;
    }

    /**
     *
     * @param sliders
     *     The sliders
     */
    public void setSliders(List<Slider> sliders) {
        this.sliders = sliders;
    }

    /**
     *
     * @return
     *     The switchers
     */
    public List<Switcher> getSwitchers() {
        return switchers;
    }

    /**
     *
     * @param switchers
     *     The switchers
     */
    public void setSwitchers(List<Switcher> switchers) {
        this.switchers = switchers;
    }

}