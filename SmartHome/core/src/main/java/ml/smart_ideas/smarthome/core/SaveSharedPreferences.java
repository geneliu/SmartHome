package ml.smart_ideas.smarthome.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by isusec on 19.12.15..
 */
public class SaveSharedPreferences {

    static final String PREF_USER_NAME= "username";
    static final String PREF_KEY_STRINGS = "deletedHouses";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static void clearUserName(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(PREF_USER_NAME).commit();

    }

    public static boolean checkSavedUser(){
        if(getUserName(Globals.getInstance().getContext()).length() == 0)
            return false;
        else
            return true;
    }




    //Deleted Houses

    public static void saveDeletedHouses(Context ctx,String RemoteID)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        List<String> list= null;
        if(readDeletedHouses(Globals.getInstance().getContext()).size() !=0) list= new ArrayList<>(readDeletedHouses(Globals.getInstance().getContext()));
        else list= new ArrayList<>();
        list.add(RemoteID);
        editor.putString(PREF_KEY_STRINGS, TextUtils.join(",", list));
        editor.commit();
        }

    public static List<String> readDeletedHouses(Context ctx)
    {
        List<String> list= new ArrayList<>();
        String serialized = getSharedPreferences(ctx).getString(PREF_KEY_STRINGS, null);
        if(serialized!=null) return Arrays.asList(TextUtils.split(serialized, ","));
        else return list;

    }

    public static void clearDeletedList(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(PREF_KEY_STRINGS).commit();
    }


}
