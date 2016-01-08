package ml.smart_ideas.smarthome.ws.Helpers;

import ml.smart_ideas.smarthome.db.House;
import ml.smart_ideas.smarthome.db.User;
import ml.smart_ideas.smarthome.ws.model.synchronization.UserData;

/**
 * Created by isusec on 08.01.16..
 */
public class DataParser {

    public static UserData userDataToSimpleJsonObject(User user)
    {
        UserData userData= new UserData();
        userData.setError("false");
        userData.setUsername(user.getUsername());
        for (House house: user.getHouses()) {
            ml.smart_ideas.smarthome.ws.model.synchronization.House houseWS= new ml.smart_ideas.smarthome.ws.model.synchronization.House();
            houseWS.setName(house.getName());
           // houseWS.setAdress(house.getAddress());
            houseWS.setLastModified(house.getLast_modified());
            houseWS.setRemoteID(String.valueOf(house.getRemoteID()));
            userData.setHouse(houseWS);
        }
        return userData;
    }

}
