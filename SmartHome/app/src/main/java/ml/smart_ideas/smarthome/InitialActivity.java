package ml.smart_ideas.smarthome;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import ml.smart_ideas.smarthome.core.SaveSharedPreferences;
import ml.smart_ideas.smarthome.core.enums.ActivityEnum;
import ml.smart_ideas.smarthome.core.enums.AppStateEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.NavigationEnum;
import ml.smart_ideas.smarthome.core.eventlisteners.EventListener;
import ml.smart_ideas.smarthome.core.fragments.HousesFragment;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.db.User;
import ml.smart_ideas.smarthome.helpers.Creator;


public class InitialActivity extends AppCompatActivity implements EventListener {

    private Toolbar toolbar;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        ButterKnife.bind(this);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Globals.getInstance().setNavigationEnum(NavigationEnum.HideNavDrawer);



        Globals.getInstance().addListener(this);
        Globals.getInstance().setContext(getApplicationContext());

        if (findViewById(R.id.fragment_container) != null) {

            // u slučaju postojanja fragmenta
            if (savedInstanceState != null) {
                return;
            }
            if(SaveSharedPreferences.checkSavedUser())
            {
                User user = User.checkExistingUser(SaveSharedPreferences.getUserName(Globals.getInstance().getContext()));
                Globals.getInstance().setUser(user);
                Globals.getInstance().ShowActivity(ActivityEnum.MainActivity);
            }
            else
            {
                Globals.getInstance().ShowFragment(FragmentEnum.LoginFragment, true);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_initial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //nema opcija
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1 ) {
          //ToogleNavigationDrawer(getBackFragment());
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void ShowActivity(ActivityEnum activityEnum)
    {

        if(Globals.getInstance().getAppStateEnum() != AppStateEnum.NotSignedIn) {
            Class activityClass = Creator.getActivityFromEnum(activityEnum);
            Intent intent = new Intent(this,activityClass);
            startActivity(intent);
            finish();
        }


    }
    @Override
    public void ShowFragment(FragmentEnum fragmentEnum, boolean addToBackStack) {
        AppStateEnum appStateEnum = Globals.getInstance().getAppStateEnum();
        Fragment fragment = Creator.getFragmentFromEnum(fragmentEnum);
        if(fragment.getClass() == HousesFragment.class && appStateEnum == AppStateEnum.SignedIn)
            return;

        if (fragment != null) {

            fragment.setArguments(getIntent().getExtras());

            if (addToBackStack) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment,fragment.getClass().toString())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(fragment.getClass().toString())
                        .commit();
            }
            else {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment,fragment.getClass().toString())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
            ToogleNavigationDrawer(fragment);
        }
    }
    @Override
    public void ShowTitle(String title){
        getSupportActionBar().setTitle(title);
    }
    @Override
    public void PressBack(){
        onBackPressed();
    }
    @Override
    public void ClearBackStack(){
        for (int i = 1;i<getFragmentManager().getBackStackEntryCount();i++) {
            getFragmentManager().popBackStack();
        }
    }

    private void ToogleNavigationDrawer(Fragment fragment){ToogleNavigationDrawer(fragment,false);}
    private void ToogleNavigationDrawer(Fragment fragment, boolean forceRefresh){
/*
        if(fragment.getClass() == LoginFragment.class || fragment.getClass() == RegistrationFragment.class){
            if(Globals.getInstance().getNavigationEnum() != NavigationEnum.HideNavDrawer || forceRefresh) {
                mdrawer.closeDrawers();
                mdrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                mDrawerToggle.setDrawerIndicatorEnabled(false);
                Globals.getInstance().setNavigationEnum(NavigationEnum.HideNavDrawer);
            }
        }
        else {
            if(Globals.getInstance().getNavigationEnum() != NavigationEnum.ShowNavDrawer || forceRefresh) {
                mDrawerToggle.setDrawerIndicatorEnabled(true);
                mDrawerToggle.syncState();
                mdrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                Globals.getInstance().setNavigationEnum(NavigationEnum.ShowNavDrawer);
            }
        }
        */
    }
    private Fragment getBackFragment(){
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            String fragmentTag = getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 2).getName();
            Fragment currentFragment = getFragmentManager().findFragmentByTag(fragmentTag);
            return currentFragment;}
        return null;
    }

    /*
    private ActionBarDrawerToggle setupDrawerToggle() {

        return new ActionBarDrawerToggle(this, mdrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);

    }*/
    @Override
    protected void onResume(){
        super.onResume();
        //toolbar= (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }
    @Override
    protected void onPostResume(){
        super.onPostResume();
        //ToogleNavigationDrawer(getFragmentManager().findFragmentById(R.id.fragment_container),true);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    public void RefreshNavigation(){}

    @Override
    public void Logout() {

    }

}
