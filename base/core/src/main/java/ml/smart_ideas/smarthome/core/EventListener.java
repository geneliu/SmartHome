package ml.smart_ideas.smarthome.core;


import android.app.Fragment;

/**
 * Created by mario on 14.11.2015..
 */
public interface EventListener {
    void ShowFragment(Fragment fragment,boolean addToBackStack);
    void ShowTitle(String title);
}