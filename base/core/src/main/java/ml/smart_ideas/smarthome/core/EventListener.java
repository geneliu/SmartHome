package ml.smart_ideas.smarthome.core;


import android.app.Fragment;

import ml.smart_ideas.smarthome.core.Enums.FragmentEnum;

/**
 * Created by mario on 14.11.2015..
 */
public interface EventListener {
    void ShowFragment(FragmentEnum fragmentEnum,boolean addToBackStack);
    void ShowTitle(String title);
}