package ml.smart_ideas.smarthome.ws.model.synchronization;


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
    private Slider slider;
    @SerializedName("switchers")
    @Expose
    private Switch aSwitch;

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

    public Slider getSlider() {
        return slider;
    }

    public void setSlider(Slider slider) {
        this.slider = slider;
    }

    public Switch getaSwitch() {
        return aSwitch;
    }

    public void setaSwitch(Switch aSwitch) {
        this.aSwitch = aSwitch;
    }
}