package ml.smart_ideas.smarthome.core.Fragmenti;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ml.smart_ideas.smarthome.core.Adapteri.homeAdapter;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.db.Korisnik;
import ml.smart_ideas.smarthome.db.Kuca;


public class PrikazKucaFragment extends Fragment {
    homeAdapter adapter;

    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.prikaz_kuca_fragment, container, false);

        listView = (ListView) viewInflater.findViewById(R.id.lista_kuca);

        //if(this.isVisible())
            InitializeFragment();



        /*
        Intent intent = getIntent();
        Korisnik korisnik = Globals.getInstance().getKorisnik();
        String message = "Pozdrav " + korisnik.getUsername() + ", " + korisnik.getIme() + " " + korisnik.getPrezime();  //intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.postLoginText);
        textView.setText(message);
        */
        return viewInflater;
    }

    private void AddDummyData()
    {
        Korisnik korisnik=Globals.getInstance().getKorisnik();  //new Korisnik("joooo","dooo","gooo","shooo");
        Kuca dummyKuca= new Kuca();
        Kuca dummyKuca1 = new Kuca();
        Kuca dummyKuca2= new Kuca();
        dummyKuca.setNaziv("Tuđanova šupa");
        dummyKuca.setAdresa("dasdas");
        dummyKuca.setKorisnik(korisnik);
        dummyKuca1.setNaziv("Sušecova štala");
        dummyKuca1.setAdresa("tutu");
        dummyKuca1.setKorisnik(korisnik);
        dummyKuca2.setNaziv("Kišićeva rupa");
        dummyKuca2.setAdresa("hoho");
        dummyKuca2.setKorisnik(korisnik);
        dummyKuca.save();
        dummyKuca1.save();
        dummyKuca2.save();

    }

    public void InitializeFragment(){
        Korisnik korisnik = Globals.getInstance().getKorisnik();
        if(korisnik.getKuce().size() < 3)
        AddDummyData();

        ArrayList<Kuca> kuce= new ArrayList<>();
        adapter=new homeAdapter(getActivity(),kuce);
        List<Kuca> kucice=korisnik.getKuce();
        adapter.addAll(kucice);

        listView.setAdapter(adapter);

    }
}
