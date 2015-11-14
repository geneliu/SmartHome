package ml.smart_ideas.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.ButterKnife;
import ml.smart_ideas.smarthome.Fragments.LoginFragment;


public class InitialActivity extends AppCompatActivity {



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




}
