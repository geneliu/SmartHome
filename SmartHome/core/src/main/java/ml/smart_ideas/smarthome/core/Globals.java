package ml.smart_ideas.smarthome.core;


import android.content.Context;

import ml.smart_ideas.smarthome.core.enums.ActivityEnum;
import ml.smart_ideas.smarthome.core.enums.AppStateEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.NavigationEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentStateEnum;
import ml.smart_ideas.smarthome.db.User;
import ml.smart_ideas.smarthome.db.House;
import ml.smart_ideas.smarthome.db.Room;


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

    private User _user;
    private NavigationEnum navigationEnum;
    private AppStateEnum appStateEnum;
    private Context context;


    private FragmentStateEnum fragmentState;


    private House currentHouse;
    private Room currentRoom;

    //endregion

    //region Public Properties

    public User getUser() {
        return _user;
    }

    public void setUser(User user) {
        _user = user;
    }

    public void setUser(String username, String password, String ime, String prezime) {
        _user = new User(username, password, ime, prezime);
    }

    public NavigationEnum getNavigationEnum() {
        return navigationEnum;
    }

    public void setNavigationEnum(NavigationEnum navigationEnum) {
        this.navigationEnum = navigationEnum;

    }

    public AppStateEnum getAppStateEnum() {
        return appStateEnum;
    }

    public void setAppStateEnum(AppStateEnum appStateEnum) {
        this.appStateEnum = appStateEnum;

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public House getCurrentHouse() {
        return currentHouse;
    }

    public void setCurrentHouse(House house) {
        currentHouse = house;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public FragmentStateEnum getFragmentState() {
        return fragmentState;
    }

    public void setFragmentState(FragmentStateEnum fragmentState) {
        this.fragmentState = fragmentState;
    }

    //endregion


    //region Events
    private EventListener listener;

    public void addListener(EventListener eventListener) {
        listener = eventListener;
    }


    public void ShowFragment(FragmentEnum fragmentEnum) {
        ShowFragment(fragmentEnum, true);
    }

    public void ShowFragment(FragmentEnum fragmentEnum, boolean addToBackStack) {

        if (listener != null)
            listener.ShowFragment(fragmentEnum, addToBackStack);
    }

    public void ShowActivity(ActivityEnum activityEnum) {

        if (listener != null)
            listener.ShowActivity(activityEnum);
    }

    public void PressBack() {

        if (listener != null)
            listener.PressBack();
    }

    public void ClearBackStack() {
        if (listener != null)
            listener.ClearBackStack();
    }

    public void RefreshNavigation() {
        if (listener != null)
            listener.RefreshNavigation();
    }


    //endregion


    //region Toolbar Title

    public void ShowTitle(String title) {

        if (listener != null)
            listener.ShowTitle(title);
    }

    //endregion

    //region Messages

    private MessageEventListener messageListener;

    public void addMessageListener(MessageEventListener messageEventListener) {
        messageListener = messageEventListener;
    }

    public void ShowMessage(String message) {

        if (messageListener != null)
            messageListener.ShowMessage(message);
    }

    //endregion


}
