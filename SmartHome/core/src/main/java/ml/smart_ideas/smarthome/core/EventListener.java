package ml.smart_ideas.smarthome.core;


import ml.smart_ideas.smarthome.core.enums.ActivityEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;

/**
 * Created by mario on 14.11.2015..
 */
public interface EventListener {
    void ShowActivity(ActivityEnum activityEnum);
    void PressBack();
    void ShowFragment(FragmentEnum fragmentEnum,boolean addToBackStack);
    void ShowTitle(String title);
    void ClearBackStack();
}