package ml.smart_ideas.smarthome.core;

import android.app.Fragment;

/**
 * Created by Admin on 15.11.2015..
 */
public interface NavigationItem {
    public String getItemName();
    public int getPosition();
    public void setPosition(int position);
    public Fragment getFragment();
}