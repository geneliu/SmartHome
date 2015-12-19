package ml.smart_ideas.smarthome.core.fragments;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.activeandroid.Model;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ml.smart_ideas.smarthome.core.DialogEventListener;
import ml.smart_ideas.smarthome.core.LongClickDialog;
import ml.smart_ideas.smarthome.core.RefreshEventListener;
import ml.smart_ideas.smarthome.core.adapters.HouseAdapter;
import ml.smart_ideas.smarthome.core.enums.AppStateEnum;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentStateEnum;
import ml.smart_ideas.smarthome.db.User;
import ml.smart_ideas.smarthome.db.House;


public class HousesFragment extends Fragment implements RefreshEventListener,DialogEventListener{
    HouseAdapter adapter;

    ListView listView;
    ImageView imageView;
    FloatingActionButton floatingActionButton;
    LinearLayout linearLayoutEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewInflater = inflater.inflate(R.layout.houses_fragment, container, false);

        listView = (ListView) viewInflater.findViewById(R.id.list_of_houses);
        floatingActionButton = (FloatingActionButton) viewInflater.findViewById(R.id.fab_add_house);
        linearLayoutEmpty = (LinearLayout) viewInflater.findViewById(R.id.empty_list);


        InitializeFragment();

        Globals.getInstance().ShowTitle(Globals.getInstance().getContext().getString(R.string.houses_fragment_title));
        Globals.getInstance().setAppStateEnum(AppStateEnum.SignedIn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHouse();
            }
        });
        Globals.getInstance().addRefreshListener(this);
        Globals.getInstance().addDialogListener(this);

        return viewInflater;
    }

    public void InitializeFragment(){
        User user = Globals.getInstance().getUser();
        if (user.getKuce().size() > 0)
            linearLayoutEmpty.setVisibility(View.INVISIBLE);

        refreshHouses();
        final List<House> houses = user.getKuce();

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
                LongClickDialog longClickDialog = LongClickDialog.newInstance(houses.get(position));
                longClickDialog.show(getFragmentManager(), "");
                return true;
            }
        });

    }

    private void addHouse() {
        Globals.getInstance().setFragmentState(FragmentStateEnum.New);
        Globals.getInstance().ShowFragment(FragmentEnum.NewHouseFragment);
    }


    private void refreshHouses() {
        adapter = new HouseAdapter(getActivity(), new ArrayList<House>());
        List<House> houses = Globals.getInstance().getUser().getKuce();
        adapter.addAll(houses);
        listView.setAdapter(adapter);
    }


    @Override
    public void refresh() {
        refreshHouses();
    }

    @Override
    public void deletePrompt(String message,Model model){
        LongClickDialog yesNoDialog = LongClickDialog.newYesNo(message, model);
        yesNoDialog.show(getFragmentManager(),"");
    }
}
