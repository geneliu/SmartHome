package ml.smart_ideas.smarthome;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import ml.smart_ideas.smarthome.Fragments.LoginFragment;
import ml.smart_ideas.smarthome.Fragments.RegistrationFragment;
import ml.smart_ideas.smarthome.core.EventListener;
import ml.smart_ideas.smarthome.core.Fragmenti.PrikazKucaFragment;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.db.Korisnik;


public class InitialActivity extends AppCompatActivity implements EventListener {

    public final static String EXTRA_MESSAGE = "ml.smart_ideas.smarthome";
    private DrawerLayout mdrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        ButterKnife.bind(this);

        Globals.getInstance().addListener(this);

        if (findViewById(R.id.fragment_container) != null) {

            // u slučaju postojanja fragmenta
            if (savedInstanceState != null) {
                return;
            }





            /*
            NavigationManager nm = NavigationManager.getInstance();
            nm.setDependencies(this, mdrawer, (NavigationView) findViewById(R.id.drawer_layout));
            */
            toolbar= (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            mdrawer= (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerToggle = setupDrawerToggle();
            mdrawer.setDrawerListener(mDrawerToggle);



            LoginFragment loginFragment = new LoginFragment();
            Globals.getInstance().ShowFragment(loginFragment,false);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_initial, menu);

        getSupportActionBar().setTitle(R.string.app_view_name);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void ShowFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment != null) {

            fragment.setArguments(getIntent().getExtras());

            if (addToBackStack) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
            }
            else {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }

        }

    }
    private ActionBarDrawerToggle setupDrawerToggle() {

        return new ActionBarDrawerToggle(this, mdrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

}
