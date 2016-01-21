package ml.smart_ideas.smarthome.ws.Helpers;

import android.content.SharedPreferences;
import android.transition.Slide;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.SaveSharedPreferences;
import ml.smart_ideas.smarthome.db.Element;
import ml.smart_ideas.smarthome.db.House;
import ml.smart_ideas.smarthome.db.Room;
import ml.smart_ideas.smarthome.db.User;
import ml.smart_ideas.smarthome.db.enums.Category;
import ml.smart_ideas.smarthome.db.enums.ElementEnum;
import ml.smart_ideas.smarthome.ws.model.synchronization.Diff;
import ml.smart_ideas.smarthome.ws.model.synchronization.Slider;
import ml.smart_ideas.smarthome.ws.model.synchronization.Switch;
import ml.smart_ideas.smarthome.ws.model.synchronization.UserData;

/**
 * Created by isusec on 08.01.16..
 */
public class DataParser {


    public static UserData userDataToCompleteJsonObject(User user)
    {
        UserData userData= new UserData();
        userData.setError("false");
        userData.setUsername(user.getUsername());

        //dohvaćanje kuća
        for (House house: user.getHouses()) {
            ml.smart_ideas.smarthome.ws.model.synchronization.House houseWS= new ml.smart_ideas.smarthome.ws.model.synchronization.House();
            houseWS.setName(house.getName());
            // houseWS.setAdress(house.getAddress());
            houseWS.setLastModified(house.getLast_modified());
            houseWS.setRemoteID(String.valueOf(house.getRemoteID()));
            houseWS.setAdress(house.getAddress());

            //dohvaćanje soba
            for(Room room: house.getRooms())
            {
                ml.smart_ideas.smarthome.ws.model.synchronization.Room roomWS= new ml.smart_ideas.smarthome.ws.model.synchronization.Room();
                roomWS.setName(room.getName());

                //dohvaćanje elemenata
                for(Element element: room.getElements())
                {
                    ml.smart_ideas.smarthome.ws.model.synchronization.Element elementWS= new ml.smart_ideas.smarthome.ws.model.synchronization.Element();
                    elementWS.setName(element.getName());
                    elementWS.setCategoryID(element.getCategory().toString());

                    //dodavanje switchera i slidera
                    if(element.checkElement()== ElementEnum.Switch)
                    {
                        Switch switchWS= new Switch();

                        switchWS.setStanje(element.getSwitch().getState());
                        elementWS.setaSwitch(switchWS);
                    }
                    else {

                        Slider sliderWS= new Slider();
                        sliderWS.setStanje(element.getSlider().getState());
                        sliderWS.setSliderValue(String.valueOf(element.getSlider().getSliderValue()));
                        elementWS.setSlider(sliderWS);
                    }

                    roomWS.setElement(elementWS);


                }
                houseWS.setRoom(roomWS);

            }




            userData.setHouse(houseWS);
        }
        userData.setDeleted(SaveSharedPreferences.readDeletedHouses(Globals.getInstance().getContext()));
        return userData;
    }

    public static void jsonObjectToDatabase(User user, UserData userData)
    {
        //dohvacanje pristiglih kuca
        for(ml.smart_ideas.smarthome.ws.model.synchronization.House houseWS : userData.getHouses())
        {
            House house= user.getHouse(Integer.parseInt(houseWS.getRemoteID()));
            if(house != null)
                house.delete();
            House newHouse= new House(houseWS.getName(),houseWS.getAdress(),user);
            newHouse.setRemoteID(Long.valueOf(houseWS.getRemoteID()));
            newHouse.setLast_modified();
            newHouse.save();

            //dohvacanje prostorija
            for(ml.smart_ideas.smarthome.ws.model.synchronization.Room roomWS: houseWS.getRooms())
            {
                Room newRoom=new Room(roomWS.getName(),newHouse);


                //dohvacanje elemenata
                for(ml.smart_ideas.smarthome.ws.model.synchronization.Element elementWS: roomWS.getElements())
                {
                    Category category = null;
                    for(Category c: Category.values())
                    {
                        if(c.name().equals(elementWS.getCategoryID())) category=c;
                    }
                    Element newElement= new Element(elementWS.getName(),newRoom,category);
                    if(elementWS.getaSwitch() !=null)
                    {
                        ml.smart_ideas.smarthome.db.Switch aSwitch= new ml.smart_ideas.smarthome.db.Switch(elementWS.getaSwitch().getStanje(),newElement);
                    }
                    else if(elementWS.getSlider() !=null)
                    {
                        ml.smart_ideas.smarthome.db.Slider slider = new ml.smart_ideas.smarthome.db.Slider(elementWS.getSlider().getStanje(),newElement);
                        slider.setSliderValue(Integer.parseInt(elementWS.getSlider().getSliderValue()));
                    }
                }
            }
        }

        for(Diff diffs: userData.getDiffs())
        {
            House house= user.getHouse(Integer.parseInt(diffs.getOldId()));
            house.setRemoteID(Long.valueOf(diffs.getNewId()));
            house.save();
        }



            SaveSharedPreferences.clearDeletedList(Globals.getInstance().getContext());
    }


}
