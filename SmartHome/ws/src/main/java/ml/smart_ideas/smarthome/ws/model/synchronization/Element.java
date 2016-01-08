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
    private Slider slider;
    @SerializedName("switchers")
    @Expose
    private  Switcher switcher;

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

    public Switcher getSwitcher() {
        return switcher;
    }

    public void setSwitcher(Switcher switcher) {
        this.switcher = switcher;
    }
}