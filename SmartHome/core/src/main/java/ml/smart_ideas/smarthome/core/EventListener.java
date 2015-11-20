package ml.smart_ideas.smarthome.core;


import ml.smart_ideas.smarthome.core.enums.FragmentEnum;

/**
 * Created by mario on 14.11.2015..
 */
public interface EventListener {
    void ShowActivity(String activity);

    void ShowFragment(FragmentEnum fragmentEnum,boolean addToBackStack);
    void ShowTitle(String title);
}