package ml.smart_ideas.smarthome.ws;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.SaveSharedPreferences;
import ml.smart_ideas.smarthome.db.User;
import ml.smart_ideas.smarthome.ws.connection.ServerCommunication;

public class AutoSyncService extends Service{
    private static long UPDATE_INTERVAL = 1000*60*30;  //1000 = 1sec

    private static Timer timer = new Timer();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("AutoSyncService", "Received start id " + startId + ": " + intent);
        return START_STICKY;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        _startService();

    }

    private void _startService()
    {
        timer.scheduleAtFixedRate(

                new TimerTask() {

                    public void run() {

                        doServiceWork();

                    }
                }, 1000, UPDATE_INTERVAL);
        Log.d("AutoSyncService", "AutoSync Timer started....");
    }

    private void doServiceWork()
    {
        Log.d("AutoSyncService","Sync started...");
        try {
            User user = User.checkExistingUser(SaveSharedPreferences.getUserName(Globals.getInstance().getContext()));
            if(user != null)
                ServerCommunication.getInstance().SynchronizeDataFromServer(user,true);
            else
                Log.d("AutoSyncService", "No user, sync abort...");
        }
        catch (Exception e) {
        }

    }

    private void _shutdownService()
    {
        if (timer != null) timer.cancel();
        Log.d("AutoSyncService", "AutoSync Timer stopped...");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        _shutdownService();
    }

    public static boolean isRunning() {
        ActivityManager manager = (ActivityManager) Globals.getInstance().getContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (AutoSyncService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
