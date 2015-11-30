package ml.smart_ideas.smarthome.navigation;


import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.db.Korisnik;

public class NavUser implements NavigationItem{

    public Korisnik getUser(){
        return Globals.getInstance().getKorisnik();
    }
}
