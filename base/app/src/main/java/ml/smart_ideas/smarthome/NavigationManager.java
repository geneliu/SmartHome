package ml.smart_ideas.smarthome;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import java.util.ArrayList;

import ml.smart_ideas.smarthome.core.NavigationItem;

/**
 * Created by Admin on 15.11.2015..
 */
public class NavigationManager {

    public ArrayList<NavigationItem> navigationItems;
    private static NavigationManager instance;

    private Activity mHandlerActivity;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    public void setDependencies(Activity handlerActivity, DrawerLayout drawerLayout, NavigationView navigationView){
        this.mHandlerActivity = handlerActivity;
        this.mNavigationView = navigationView;
        this.mDrawerLayout = drawerLayout;
    }
    // private constructor
    private NavigationManager(){
        navigationItems = new ArrayList<NavigationItem>();
    }

    public void addItem(NavigationItem newItem){
        newItem.setPosition(navigationItems.size());
        navigationItems.add(newItem);
        mNavigationView.getMenu().add(0,newItem.getPosition(),0,newItem.getItemName());
    }

    public static NavigationManager getInstance(){
        if(instance == null)
            instance = new NavigationManager();
        return instance;
    }
    public ArrayList<String> getNavigationItemsAsStrings(){
        ArrayList<String> navigationItemStrings = new ArrayList<String>();
        for (NavigationItem item : navigationItems) {
            navigationItemStrings.add(item.getItemName());
        }
        return navigationItemStrings;
    }
}