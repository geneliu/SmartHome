package ml.smart_ideas.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import ml.smart_ideas.smarthome.Fragments.LoginFragment;
import ml.smart_ideas.smarthome.core.Adapteri.homeAdapter;
import ml.smart_ideas.smarthome.core.Fragmenti.PrikazKucaFragment;
import ml.smart_ideas.smarthome.db.Korisnik;
import ml.smart_ideas.smarthome.db.Kuca;

public class HomeScreenActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "ml.smart_ideas.smarthome";
    homeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);

        if (findViewById(R.id.fragment_container) != null) {

        if(Kuca.getSveKuce().isEmpty())
            AddDummyData();
        ArrayList<Kuca> kuce= new ArrayList<>();
        adapter=new homeAdapter(this,kuce);
        List<Kuca> kucice=Kuca.getSveKuce();
        adapter.addAll(kucice);
        ListView listView = (ListView) findViewById(R.id.lista_kuca);
        listView.setAdapter(adapter);


            // u slučaju postojanja fragmenta
            if (savedInstanceState != null) {
                return;
            }

            LoginFragment loginFragment = new LoginFragment();

            loginFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,PrikazKucaFragment).commit();
        }
    }


}