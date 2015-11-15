package ml.smart_ideas.smarthome.core;


import android.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import ml.smart_ideas.smarthome.core.Enums.AppStateEnum;
import ml.smart_ideas.smarthome.core.Enums.NavigationEnum;
import ml.smart_ideas.smarthome.db.Korisnik;


public class Globals {

    //region Constructor
    private Globals() {
    }

    private static Globals _instance;

    public static Globals getInstance() {
        if (_instance == null)
            _instance = new Globals();
        return _instance;
    }
    //endregion

    //region Private Fields

    private Korisnik _korisnik;
    private NavigationEnum navigationEnum;
    private AppStateEnum appStateEnum;

    //endregion

    //region Public Properties

    public Korisnik getKorisnik() {
        return _korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        _korisnik = korisnik;
    }

    public void setKorisnik(String username,String password,String ime,String prezime) {
        _korisnik = new Korisnik(username,password,ime,prezime);
    }

    public NavigationEnum getNavigationEnum(){
        return  navigationEnum;
    }
    public void setNavigationEnum(NavigationEnum navigationEnum){
        this.navigationEnum = navigationEnum;

    }
    public AppStateEnum getAppStateEnum(){
        return  appStateEnum;
    }
    public void setAppStateEnum(AppStateEnum appStateEnum){
        this.appStateEnum = appStateEnum;

    }

    //endregion

    //region Events
    private List<EventListener> listeners = new ArrayList<EventListener>();

    public void addListener(EventListener toAdd) {
        listeners.add(toAdd);
    }

    //region Fragments Changing

    public void ShowFragment(Fragment fragment, boolean addToBackStack) {

        for (EventListener el : listeners)
            el.ShowFragment(fragment, addToBackStack);

    }
    //endregion

    //region Toolbar Title

    public void ShowTitle(String title) {

        for (EventListener el : listeners)
            el.ShowTitle(title);

    }

    //endregion

    //region Messages

    private List<MessageEventListener> messageListeners = new ArrayList<MessageEventListener>();

    public void addMessageListener(MessageEventListener toAdd) {
        messageListeners.clear();
        messageListeners.add(toAdd);
    }

    public void ShowMessage(String message) {

        for (MessageEventListener ml : messageListeners)
            ml.ShowMessage(message);
    }

    //endregion


    //endregion


}
