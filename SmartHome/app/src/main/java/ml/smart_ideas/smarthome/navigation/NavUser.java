package ml.smart_ideas.smarthome.navigation;


import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.db.User;

public class NavUser implements NavigationItem{

    public User getUser(){
        return Globals.getInstance().getUser();
    }
}
