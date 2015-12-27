package ml.smart_ideas.smarthome.core.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.activeandroid.Model;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ml.smart_ideas.smarthome.core.eventlisteners.DialogEventListener;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.LongClickDialog;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.eventlisteners.RefreshEventListener;
import ml.smart_ideas.smarthome.core.adapters.RoomAdapter;
import ml.smart_ideas.smarthome.core.enums.AppStateEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentStateEnum;
import ml.smart_ideas.smarthome.db.House;
import ml.smart_ideas.smarthome.db.Room;


public class RoomsFragment extends Fragment implements RefreshEventListener, DialogEventListener {

    RoomAdapter adapter;
    ListView listView;
    FloatingActionButton floatingActionButton;
    LinearLayout linearLayoutEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.rooms_fragment, container, false);

        listView = (ListView) viewInflater.findViewById(R.id.rooms_list);
        floatingActionButton = (FloatingActionButton) viewInflater.findViewById(R.id.fab_add_room);
        linearLayoutEmpty = (LinearLayout) viewInflater.findViewById(R.id.empty_list);

        InitializeFragment();

        House house = Globals.getInstance().getCurrentHouse();

        Globals.getInstance().ShowTitle(house.getName());
        Globals.getInstance().setAppStateEnum(AppStateEnum.SignedIn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRoom();
            }
        });

        Globals.getInstance().addRefreshListener(this);
        Globals.getInstance().addDialogListener(this);

        return viewInflater;
    }

    public void InitializeFragment() {
        House house = Globals.getInstance().getCurrentHouse();
        if (house.getProstorije().size() > 0)
            linearLayoutEmpty.setVisibility(View.INVISIBLE);

        final List<Room> rooms = house.getProstorije();
        refreshRooms();
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Globals.getInstance().setCurrentRoom(rooms.get(position));
                Globals.getInstance().ShowFragment(FragmentEnum.ElementsFragment);
            }
        });

        listView.setOnItemLongClickListener(new ListView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                LongClickDialog longClickDialog = LongClickDialog.newInstance(rooms.get(position));
                longClickDialog.show(getFragmentManager(), "");
                return true;
            }
        });

    }

    private void addRoom() {
        Globals.getInstance().setFragmentState(FragmentStateEnum.New);
        Globals.getInstance().ShowFragment(FragmentEnum.NewRoomFragment);
    }


    private void refreshRooms() {
        House house = Globals.getInstance().getCurrentHouse();
        adapter = new RoomAdapter(getActivity(), new ArrayList<Room>());
        List<Room> rooms = house.getProstorije();
        adapter.addAll(rooms);
        listView.setAdapter(adapter);
    }

    @Override
    public void refresh() {
        refreshRooms();
    }

    @Override
    public void deletePrompt(String message, Model model) {
        LongClickDialog yesNoDialog = LongClickDialog.newYesNo(message, model);
        yesNoDialog.show(getFragmentManager(), "");
    }

    @Override
    public void createDialog(Model model) {

    }
}
