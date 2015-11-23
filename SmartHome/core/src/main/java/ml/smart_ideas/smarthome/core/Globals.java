package ml.smart_ideas.smarthome.core;


import android.app.FragmentManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ml.smart_ideas.smarthome.core.enums.ActivityEnum;
import ml.smart_ideas.smarthome.core.enums.AppStateEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.NavigationEnum;
import ml.smart_ideas.smarthome.core.enums.StanjeFragmentaEnum;
import ml.smart_ideas.smarthome.db.Korisnik;
import ml.smart_ideas.smarthome.db.Kuca;
import ml.smart_ideas.smarthome.db.Prostorija;


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
    private Context context;


    private StanjeFragmentaEnum stanjeFragmenta;


    private Kuca currentHouse;
    private Prostorija currentRoom;

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

    public Context getContext(){
        return context;
    }
    public void setContext(Context context){
        this.context = context;
    }

    public Kuca getCurrentHouse(){
        return currentHouse;
    }
    public void setCurrentHouse(Kuca house){
        currentHouse = house;
    }

    public Prostorija getCurrentRoom(){
        return currentRoom;
    }
    public void setCurrentRoom(Prostorija room){
        currentRoom = room;
    }

    public StanjeFragmentaEnum getStanjeFragmenta() {
        return stanjeFragmenta;
    }

    public void setStanjeFragmenta(StanjeFragmentaEnum stanjeFragmenta) {
        this.stanjeFragmenta = stanjeFragmenta;
    }

    //endregion


    //region Events
    private EventListener listener;

    public void addListener(EventListener eventListener) {
        listener = eventListener;
    }



    public void ShowFragment(FragmentEnum fragmentEnum){ ShowFragment(fragmentEnum,true);}
    public void ShowFragment(FragmentEnum fragmentEnum, boolean addToBackStack) {

        if(listener != null)
            listener.ShowFragment(fragmentEnum, addToBackStack);
    }

    public void ShowActivity(ActivityEnum activityEnum) {

        if(listener !=null)
            listener.ShowActivity(activityEnum);
    }

    public void PressBack() {

        if(listener !=null)
            listener.PressBack();
    }

    public void ClearBackStack(){
        if(listener !=null)
            listener.ClearBackStack();
    }


    //endregion



    //region Toolbar Title

    public void ShowTitle(String title) {

        if(listener != null)
            listener.ShowTitle(title);
    }

    //endregion

    //region Messages

    private MessageEventListener messageListener;

    public void addMessageListener(MessageEventListener messageEventListener) {
        messageListener = messageEventListener;
    }

    public void ShowMessage(String message) {

        if(messageListener != null)
            messageListener.ShowMessage(message);
    }

    //endregion





}
