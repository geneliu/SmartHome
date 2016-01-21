package ml.smart_ideas.smarthome;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import ml.smart_ideas.smarthome.core.eventlisteners.EventListener;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.enums.ActivityEnum;
import ml.smart_ideas.smarthome.core.enums.AppStateEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.NavigationEnum;
import ml.smart_ideas.smarthome.core.eventlisteners.SyncEventListener;
import ml.smart_ideas.smarthome.core.fragments.HousesFragment;
import ml.smart_ideas.smarthome.db.House;
import ml.smart_ideas.smarthome.helpers.Creator;
import ml.smart_ideas.smarthome.navigation.NavigationAdapter;
import ml.smart_ideas.smarthome.ws.AutoSyncService;
import ml.smart_ideas.smarthome.ws.connection.ServerCommunication;


public class MainActivity extends AppCompatActivity implements EventListener,SyncEventListener {
    private DrawerLayout mdrawer;
    private ListView mDrawerList;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationAdapter adapter;
    private List<House> houses;
    private Fragment currentFragment;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mdrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


        adapter = new NavigationAdapter(this,mDrawerList);

        mDrawerList.setAdapter(adapter);


        mDrawerToggle = setupDrawerToggle();
        mdrawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();
        mdrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        Globals.getInstance().setNavigationEnum(NavigationEnum.ShowNavDrawer);


        Globals.getInstance().addListener(this);
        Globals.getInstance().setContext(getApplicationContext());
        Globals.getInstance().addSyncEventListener(this);

        if (findViewById(R.id.fragment_container) != null) {

            // u slučaju postojanja fragmenta
            if (savedInstanceState != null) {
                return;
            }
            Globals.getInstance().ShowFragment(FragmentEnum.HousesFragment, true);
        }
        if(AutoSyncService.isRunning() == false)
            startService(new Intent(this,AutoSyncService.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_sync, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        int id = item.getItemId();
        if(id== R.id.synchronizeData) {
            ServerCommunication.getInstance().SynchronizeDataFromServer();
        }
        //nema opcija
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ShowActivity(ActivityEnum activityEnum) {
        if (Globals.getInstance().getAppStateEnum() != AppStateEnum.NotSignedIn && activityEnum != ActivityEnum.MainActivity) {
            Class activityClass = Creator.getActivityFromEnum(activityEnum);
            Intent intent = new Intent(this, activityClass);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (mdrawer.isDrawerOpen(GravityCompat.START)) {
            mdrawer.closeDrawers();
        } else if (getFragmentManager().getBackStackEntryCount() > 1) {
            //ToogleNavigationDrawer(getBackFragment());
            getFragmentManager().popBackStack();
        } else {

            if (doubleBackToExitPressedOnce) {
                Logout();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, R.string.press_back_again_logout, Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() { doubleBackToExitPressedOnce = false; }
            }, 2000);
        }
    }


    @Override
    public void ShowFragment(FragmentEnum fragmentEnum, boolean addToBackStack) {
        AppStateEnum appStateEnum = Globals.getInstance().getAppStateEnum();
        Fragment fragment = Creator.getFragmentFromEnum(fragmentEnum);
        if (fragment.getClass() == HousesFragment.class && appStateEnum == AppStateEnum.SignedIn)
            return;

        if (fragment != null) {

            fragment.setArguments(getIntent().getExtras());

            if (addToBackStack) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, fragment.getClass().toString())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(fragment.getClass().toString())
                        .commit();
            } else {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, fragment.getClass().toString())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
            ToogleNavigationDrawer(fragment);
        }

    }

    @Override
    public void ShowTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void PressBack() {
        onBackPressed();
    }

    @Override
    public void ClearBackStack() {
        for (int i = 1; i < getFragmentManager().getBackStackEntryCount(); i++) {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public void ClearBackStack(int levelsIgnored) {
        for (int i = 1 + levelsIgnored; i < getFragmentManager().getBackStackEntryCount(); i++) {
            getFragmentManager().popBackStack();
        }
    }


    @Override
    public void RefreshNavigation() {
        adapter = new NavigationAdapter(this,mDrawerList);
        mDrawerList.setAdapter(adapter);
    }

    @Override
    public void Logout() {

        Globals.getInstance().setUser(null);
        //SaveSharedPreferences.setUserName(Globals.getInstance().getContext(),"");
    //    ClearBackStack();
       // clear();

        Globals.getInstance().ShowActivity(ActivityEnum.InitialActivity);
      //  Globals.getInstance().ShowFragment(FragmentEnum.LoginFragment);
        finish();


    }

    private void ToogleNavigationDrawer(Fragment fragment) {
/*
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();
        mdrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        Globals.getInstance().setNavigationEnum(NavigationEnum.ShowNavDrawer);
*/
    }

    private Fragment getBackFragment() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            String fragmentTag = getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 2).getName();
            Fragment currentFragment = getFragmentManager().findFragmentByTag(fragmentTag);
            return currentFragment;
        }
        return null;
    }

    private ActionBarDrawerToggle setupDrawerToggle() {

        return new ActionBarDrawerToggle(this, mdrawer, toolbar, R.string.drawer_open, R.string.drawer_close);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //toolbar= (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //ToogleNavigationDrawer(getFragmentManager().findFragmentById(R.id.fragment_container));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    public void syncData() {

        //Globals.getInstance().setAppStateEnum(AppStateEnum.Here);
        Globals.getInstance().Refresh();
        Toast.makeText(this, R.string.successful_sync,
                Toast.LENGTH_LONG).show();
        //Globals.getInstance().ShowFragment(FragmentEnum.HousesFragment);
        //Globals.getInstance().setAppStateEnum(AppStateEnum.SignedIn);


    }
}
