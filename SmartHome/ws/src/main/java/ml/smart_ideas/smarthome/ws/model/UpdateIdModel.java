package ml.smart_ideas.smarthome.ws.model;

import java.util.ArrayList;

/**
 * Created by isusec on 08.01.16..
 */
public class UpdateIdModel {

    String error;
    String changes;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getChanges() {
        return changes;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }

    public ArrayList<OldNewIds> getDiffs() {
        return diffs;
    }

    public void setDiffs(ArrayList<OldNewIds> diffs) {
        this.diffs = diffs;
    }

    ArrayList<OldNewIds> diffs;

}
