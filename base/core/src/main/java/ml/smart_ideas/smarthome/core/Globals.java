package ml.smart_ideas.smarthome.core;





import android.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import ml.smart_ideas.smarthome.db.Korisnik;


public class Globals {

    //region Constructor
    private Globals() { }

    private static Globals _instance;

    public static Globals getInstance() {
        if (_instance == null)
            _instance = new Globals();
        return _instance;
    }
    //endregion

    //region Private Fields

    private Korisnik _korisnik;

    //endregion

    //region Public Properties

    public Korisnik getKorisnik(){
        return _korisnik;
    }
    public void setKorisnik(Korisnik korisnik){
        _korisnik = korisnik;
    }

    //endregion

    //region Events


    //region Fragments Changing
    private List<EventListener> listeners = new ArrayList<EventListener>();

    public void addListener(EventListener toAdd) {
        listeners.add(toAdd);
    }


    public void ShowFragment(Fragment fragment,boolean addToBackStack) {

        for (EventListener el : listeners)
            el.ShowFragment(fragment,addToBackStack);
    }
    //endregion

    //region ErrorMessages

    private List<MessageEventListener> messageListeners = new ArrayList<MessageEventListener>();

    public void addErrorListener(MessageEventListener toAdd) {
        messageListeners.add(toAdd);
    }
    public void removeErrorListeners(){messageListeners.clear();}


    public void ShowMessage(String message) {

        for (MessageEventListener ml : messageListeners)
            ml.ShowMessage(message);
    }

    //endregion


    //endregion


}
