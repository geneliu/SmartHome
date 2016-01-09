package ml.smart_ideas.smarthome.ws.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by isusec on 08.01.16..
 */
public class Diff {

    @SerializedName("old_id")
    @Expose
    private String oldId;
    @SerializedName("new_id")
    @Expose
    private String newId;

    /**
     *
     * @return
     * The oldId
     */
    public String getOldId() {
        return oldId;
    }

    /**
     *
     * @param oldId
     * The old_id
     */
    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    /**
     *
     * @return
     * The newId
     */
    public String getNewId() {
        return newId;
    }

    /**
     *
     * @param newId
     * The new_id
     */
    public void setNewId(String newId) {
        this.newId = newId;
    }
}
