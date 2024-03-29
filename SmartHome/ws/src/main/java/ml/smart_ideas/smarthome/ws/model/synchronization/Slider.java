package ml.smart_ideas.smarthome.ws.model.synchronization;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Slider {


    @SerializedName("stanje")
    @Expose
    private boolean stanje;
    @SerializedName("slider_value")
    @Expose
    private String sliderValue;



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

    /**
     *
     * @return
     *     The sliderValue
     */
    public String getSliderValue() {
        return sliderValue;
    }

    /**
     *
     * @param sliderValue
     *     The slider_value
     */
    public void setSliderValue(String sliderValue) {
        this.sliderValue = sliderValue;
    }

}