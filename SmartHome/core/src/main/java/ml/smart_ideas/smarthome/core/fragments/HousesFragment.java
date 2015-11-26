package ml.smart_ideas.smarthome.core.fragments;

import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ml.smart_ideas.smarthome.core.adapters.HouseAdapter;
import ml.smart_ideas.smarthome.core.enums.AppStateEnum;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentStateEnum;
import ml.smart_ideas.smarthome.db.Korisnik;
import ml.smart_ideas.smarthome.db.Kuca;


public class HousesFragment extends Fragment {
    HouseAdapter adapter;

    ListView listView;
    ImageView imageView;
    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.houses_fragment, container, false);

        listView = (ListView) viewInflater.findViewById(R.id.lista_kuca);
        floatingActionButton = (FloatingActionButton)viewInflater.findViewById(R.id.fab_add_house);

         imageView= (ImageView) viewInflater.findViewById(R.id.img_uredi_kucu);


        //if(this.isVisible())
            InitializeFragment();

        Globals.getInstance().ShowTitle(Globals.getInstance().getContext().getString(R.string.houses_fragment_title));
        Globals.getInstance().setAppStateEnum(AppStateEnum.SignedIn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHouse();
            }
        });

        return viewInflater;
    }

    private void AddDummyData()
    {
        Korisnik korisnik=Globals.getInstance().getKorisnik();
        korisnik.addKuca("Kuća", "nema");
        korisnik.addKuca("Klet", "nema");
        korisnik.addKuca("Vikendica", "nema");

    }

    public void InitializeFragment(){
        Korisnik korisnik = Globals.getInstance().getKorisnik();
        if(korisnik.getKuce().size() < 1)
            AddDummyData();

        adapter=new HouseAdapter(getActivity(), new ArrayList<Kuca>());
        final List<Kuca> houses=korisnik.getKuce();
        adapter.addAll(houses);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Globals.getInstance().setCurrentHouse(houses.get(position));
                Globals.getInstance().ShowFragment(FragmentEnum.RoomsFragment);
            }
        });
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(new ListView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), "Put Menu Now", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    private void addHouse() {
        Globals.getInstance().setFragmentState(FragmentStateEnum.New);
        Globals.getInstance().ShowFragment(FragmentEnum.NewHouseFragment);
    }
}
