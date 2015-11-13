package ml.smart_ideas.smarthome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.db.Korisnik;

public class HomeScreenActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "ml.smart_ideas.smarthome";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Intent intent = getIntent();
        Korisnik korisnik = Globals.getInstance().getKorisnik();
        String message = "Pozdrav " + korisnik.getUsername() + ", " + korisnik.getIme() + " " + korisnik.getPrezime();  //intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.postLoginText);
        textView.setText(message);

    }
}