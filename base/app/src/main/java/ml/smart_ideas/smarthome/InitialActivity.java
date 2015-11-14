package ml.smart_ideas.smarthome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.ButterKnife;
import ml.smart_ideas.smarthome.Fragments.LoginFragment;
import ml.smart_ideas.smarthome.core.EventListener;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.db.Korisnik;


public class InitialActivity extends AppCompatActivity implements EventListener {

    public final static String EXTRA_MESSAGE = "ml.smart_ideas.smarthome";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        ButterKnife.bind(this);

        if (findViewById(R.id.fragment_container) != null) {

            // u slučaju postojanja fragmenta
            if (savedInstanceState != null) {
                return;
            }

            LoginFragment loginFragment = new LoginFragment();

            loginFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, loginFragment).commit();
        }

        Globals.getInstance().addListener(this);
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
        if(getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void ShowFragment(String username) {
        String message = "";

        if (username != null) {
            Globals.getInstance().setKorisnik(new Korisnik(username, "Ime", "Prezime"));

            message = "Pozdrav " + username;

        } else {
             message = "Krivi podaci";
        }
        Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }


}
