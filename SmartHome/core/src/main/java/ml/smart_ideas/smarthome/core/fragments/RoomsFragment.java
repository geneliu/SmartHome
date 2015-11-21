package ml.smart_ideas.smarthome.core.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.adapters.RoomAdapter;
import ml.smart_ideas.smarthome.core.enums.AppStateEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.db.Korisnik;
import ml.smart_ideas.smarthome.db.Kuca;
import ml.smart_ideas.smarthome.db.Prostorija;


public class RoomsFragment extends Fragment {
    RoomAdapter adapter;

    ListView listView;

    FloatingActionButton floatingActionButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.rooms_fragment, container, false);

        listView = (ListView) viewInflater.findViewById(R.id.rooms_list);
        floatingActionButton = (FloatingActionButton)viewInflater.findViewById(R.id.fab_add_room);


        InitializeFragment();
        Kuca house = Globals.getInstance().getCurrentHouse();

        Globals.getInstance().ShowTitle( house.getNaziv());
        Globals.getInstance().setAppStateEnum(AppStateEnum.SignedIn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRoom();
            }
        });

        return viewInflater;
    }

    private void AddDummyData()
    {
        Kuca house = Globals.getInstance().getCurrentHouse();
        house.addRoom("Kuhinja");
        house.addRoom("Soba");
        house.addRoom("Blagovaonica");
        house.addRoom("Hodnik");
    }

    public void InitializeFragment(){
        Kuca house = Globals.getInstance().getCurrentHouse();
        if(house.getProstorije().size() < 1)
            AddDummyData();

        ArrayList<Prostorija> rooms= new ArrayList<>();
        adapter=new RoomAdapter(getActivity(),rooms);
        List<Prostorija> roomsList = house.getProstorije();
        adapter.addAll(roomsList);

        listView.setAdapter(adapter);

    }

    private void addRoom() {
        //not yet
        //Globals.getInstance().ShowFragment(FragmentEnum.NewRoomFragment);
    }
}
