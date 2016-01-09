package ml.smart_ideas.smarthome.ws.Helpers;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.db.House;
import ml.smart_ideas.smarthome.db.User;
import ml.smart_ideas.smarthome.ws.model.Diff;
import ml.smart_ideas.smarthome.ws.model.UpdateIdModel;

/**
 * Created by isusec on 08.01.16..
 */
public class DatabaseUpdateHelper {

    public static void updateHouseRemoteIds(UpdateIdModel changes)
    {
        for(Diff diffs: changes.getDiffs())
        {
            User user= Globals.getInstance().getUser();
          House house= user.getHouse(Integer.parseInt(diffs.getOldId()));
           house.setRemoteID(Long.valueOf(diffs.getNewId()));
            house.save();
        }
    }

}
