package ml.smart_ideas.smarthome.helpers;

import android.app.Fragment;

import ml.smart_ideas.smarthome.InitialActivity;
import ml.smart_ideas.smarthome.MainActivity;
import ml.smart_ideas.smarthome.core.enums.ActivityEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.fragments.ElementsFragment;
import ml.smart_ideas.smarthome.core.fragments.HousesFragment;
import ml.smart_ideas.smarthome.core.fragments.NewElementFragment;
import ml.smart_ideas.smarthome.core.fragments.NewHouseFragment;
import ml.smart_ideas.smarthome.core.fragments.NewRoomFragment;
import ml.smart_ideas.smarthome.core.fragments.RoomsFragment;
import ml.smart_ideas.smarthome.fragments.LoginFragment;
import ml.smart_ideas.smarthome.fragments.RegistrationFragment;

/**
 * Created by mario on 21.11.2015..
 */
public class Creator {


    public static Fragment getFragmentFromEnum(FragmentEnum fragmentEnum){
        switch (fragmentEnum){
            case LoginFragment: return new LoginFragment();
            case RegistrationFragment: return new RegistrationFragment();
            case HousesFragment: return new HousesFragment();
            case NewHouseFragment: return new NewHouseFragment();
            case RoomsFragment: return new RoomsFragment();
            case NewRoomFragment: return new NewRoomFragment();
            case ElementsFragment: return new ElementsFragment();
            case NewElementFragment: return new NewElementFragment();
            default: return new LoginFragment();
        }
    }

    public static Class getActivityFromEnum(ActivityEnum activityEnum){
        switch (activityEnum){
            case InitialActivity: return InitialActivity.class;
            case MainActivity: return MainActivity.class;
            default: return InitialActivity.class;
        }
    }
}
