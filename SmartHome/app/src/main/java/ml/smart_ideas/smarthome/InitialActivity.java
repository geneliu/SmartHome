package ml.smart_ideas.smarthome;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import ml.smart_ideas.smarthome.core.enums.ActivityEnum;
import ml.smart_ideas.smarthome.fragments.LoginFragment;
import ml.smart_ideas.smarthome.fragments.RegistrationFragment;
import ml.smart_ideas.smarthome.core.enums.AppStateEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.NavigationEnum;
import ml.smart_ideas.smarthome.core.EventListener;
import ml.smart_ideas.smarthome.core.fragments.HousesFragment;
import ml.smart_ideas.smarthome.core.Globals;


public class InitialActivity extends AppCompatActivity implements EventListener {

    public final static String EXTRA_MESSAGE = "ml.smart_ideas.smarthome";
    private DrawerLayout mdrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        ButterKnife.bind(this);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mdrawer= (DrawerLayout) findViewById(R.id.drawer_layout);


        mDrawerToggle = setupDrawerToggle();
        mdrawer.setDrawerListener(mDrawerToggle);



        Globals.getInstance().addListener(this);
        Globals.getInstance().addActivityListener(this);
        Globals.getInstance().setContext(getApplicationContext());

        if (findViewById(R.id.fragment_container) != null) {

            // u slučaju postojanja fragmenta
            if (savedInstanceState != null) {
                return;
            }
            Globals.getInstance().ShowFragment(FragmentEnum.LoginFragment,true);
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
          ToogleNavigationDrawer(getBackFragment());
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void ShowActivity(ActivityEnum activityEnum)
    {

        if(Globals.getInstance().getAppStateEnum().name()!="SignedIn") {
            Intent intent = new Intent();
            intent.setClassName(this, "ml.smart_ideas.smarthome." + String.valueOf(activityEnum));
            startActivity(intent);
            finish();
        }


    }
    @Override
    public void ShowFragment(FragmentEnum fragmentEnum, boolean addToBackStack) {
        AppStateEnum appStateEnum = Globals.getInstance().getAppStateEnum();
        Fragment fragment = getFragmentFromEnum(fragmentEnum);
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

    private void ToogleNavigationDrawer(Fragment fragment){ToogleNavigationDrawer(fragment,false);}
    private void ToogleNavigationDrawer(Fragment fragment, boolean forceRefresh){

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
    }
    private Fragment getBackFragment(){
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            String fragmentTag = getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 2).getName();
            Fragment currentFragment = getFragmentManager().findFragmentByTag(fragmentTag);
            return currentFragment;}
        return null;
    }


    public Fragment getFragmentFromEnum(FragmentEnum fragmentEnum){
        switch (fragmentEnum){
            case LoginFragment: return new LoginFragment();
            case RegistrationFragment: return new RegistrationFragment();
            case HousesFragment: return  new HousesFragment();
            default: return new LoginFragment();
        }
    }



    private ActionBarDrawerToggle setupDrawerToggle() {

        return new ActionBarDrawerToggle(this, mdrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);

    }
    @Override
    protected void onResume(){
        super.onResume();
        //toolbar= (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }
    @Override
    protected void onPostResume(){
        super.onPostResume();
        ToogleNavigationDrawer(getFragmentManager().findFragmentById(R.id.fragment_container),true);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

}
