package ml.smart_ideas.smarthome.core.Fragmenti;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ml.smart_ideas.smarthome.core.Adapteri.HouseAdapter;
import ml.smart_ideas.smarthome.core.Enums.AppStateEnum;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.db.Korisnik;
import ml.smart_ideas.smarthome.db.Kuca;


public class PrikazKucaFragment extends Fragment {
    HouseAdapter adapter;

    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.prikaz_kuca_fragment, container, false);

        listView = (ListView) viewInflater.findViewById(R.id.lista_kuca);

        //if(this.isVisible())
            InitializeFragment();

        Globals.getInstance().ShowTitle("Popis kuća");
        Globals.getInstance().setAppStateEnum(AppStateEnum.Ready);

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
        korisnik.addKuca("Tuđanova šupa","ddasd");
        korisnik.addKuca("Sušecova štala","ddasd");
        korisnik.addKuca("Kišićeva rupa","ddasd");

    }

    public void InitializeFragment(){
        Korisnik korisnik = Globals.getInstance().getKorisnik();
        if(korisnik.getKuce().size() < 3)
        AddDummyData();

        ArrayList<Kuca> kuce= new ArrayList<>();
        adapter=new HouseAdapter(getActivity(),kuce);
        List<Kuca> kucice=korisnik.getKuce();
        adapter.addAll(kucice);

        listView.setAdapter(adapter);

    }
}
