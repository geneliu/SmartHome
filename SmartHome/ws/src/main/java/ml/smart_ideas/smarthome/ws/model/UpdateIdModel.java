package ml.smart_ideas.smarthome.ws.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isusec on 08.01.16..
 */
public class UpdateIdModel {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("changes")
    @Expose
    private String changes;
    @SerializedName("diffs")
    @Expose
    private List<Diff> diffs = new ArrayList<Diff>();

    /**
     *
     * @return
     * The error
     */
    public String getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     *
     * @return
     * The changes
     */
    public String getChanges() {
        return changes;
    }

    /**
     *
     * @param changes
     * The changes
     */
    public void setChanges(String changes) {
        this.changes = changes;
    }

    /**
     *
     * @return
     * The diffs
     */
    public List<Diff> getDiffs() {
        return diffs;
    }

    /**
     *
     * @param diffs
     * The diffs
     */
    public void setDiffs(List<Diff> diffs) {
        this.diffs = diffs;
    }



}
