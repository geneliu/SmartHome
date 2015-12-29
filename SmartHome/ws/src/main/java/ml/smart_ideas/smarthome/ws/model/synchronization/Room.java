package ml.smart_ideas.smarthome.ws.model.synchronization;



import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("elements")
    @Expose
    private List<Element> elements = new ArrayList<Element>();

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
     *     The elements
     */
    public List<Element> getElements() {
        return elements;
    }

    /**
     *
     * @param elements
     *     The elements
     */
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

}