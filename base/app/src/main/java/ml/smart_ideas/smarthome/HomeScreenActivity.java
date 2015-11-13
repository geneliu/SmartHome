package ml.smart_ideas.smarthome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ml.smart_ideas.smarthome.Adapteri.homeAdapter;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.db.Korisnik;
import ml.smart_ideas.smarthome.db.Kuca;

public class HomeScreenActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "ml.smart_ideas.smarthome";
    homeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        AddDummyData();
        ArrayList<Kuca> kuce= new ArrayList<>();
        adapter=new homeAdapter(this,kuce);
        List<Kuca> kucice=Kuca.getSveKuce();
        adapter.addAll(kucice);
        ListView listView = (ListView) findViewById(R.id.lista_kuca);
        listView.setAdapter(adapter);


        /*
        Intent intent = getIntent();
        Korisnik korisnik = Globals.getInstance().getKorisnik();
        String message = "Pozdrav " + korisnik.getUsername() + ", " + korisnik.getIme() + " " + korisnik.getPrezime();  //intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.postLoginText);
        textView.setText(message);
        */

    }

    private void AddDummyData()
    {
        Korisnik korisnik=new Korisnik("joooo","dooo","gooo");
        Kuca dummyKuca= new Kuca();
        Kuca dummyKuca2= new Kuca();
        dummyKuca.setNaziv("Tuđanova šupa");
        dummyKuca.setAdresa("dasdas");
        dummyKuca.setKorisnik(korisnik);
        dummyKuca2.setNaziv("Kišićeva rupa");
        dummyKuca2.setAdresa("hoho");
        dummyKuca2.setKorisnik(korisnik);
        dummyKuca.save();
        dummyKuca2.save();

    }
}