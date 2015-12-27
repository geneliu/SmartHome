package ml.smart_ideas.smarthome.core.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.activeandroid.Model;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.LongClickDialog;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.adapters.ElementAdapter;
import ml.smart_ideas.smarthome.core.elements.ElementItem;
import ml.smart_ideas.smarthome.core.eventlisteners.DialogEventListener;
import ml.smart_ideas.smarthome.core.eventlisteners.RefreshEventListener;
import ml.smart_ideas.smarthome.db.Room;
import ml.smart_ideas.smarthome.db.User;
import ml.smart_ideas.smarthome.db.enums.Category;

public class ElementsFragment extends Fragment implements RefreshEventListener,DialogEventListener {

    ElementAdapter adapter;

    RecyclerView recyclerView;
    ImageView imageView;
    FloatingActionButton floatingActionButton;
    LinearLayout linearLayoutEmpty;
    Room room;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewInflater = inflater.inflate(R.layout.elements_fragment, container, false);

        recyclerView = (RecyclerView) viewInflater.findViewById(R.id.list_of_elements);
        floatingActionButton = (FloatingActionButton) viewInflater.findViewById(R.id.fab_add_element);
        linearLayoutEmpty = (LinearLayout) viewInflater.findViewById(R.id.empty_list);


        InitializeFragment();

        room = Globals.getInstance().getCurrentRoom();

        Globals.getInstance().ShowTitle(room.getName());

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addElement();
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

        recyclerView.setLongClickable(true);
        refreshElements();
        final List<ElementItem> elementItems = adapter.getElements();


        /*
        recyclerView.setOnItemLongClickListener(new ListView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                LongClickDialog longClickDialog = LongClickDialog.newInstance(houses.get(position));
                longClickDialog.show(getFragmentManager(), "");
                return true;
            }
        });
        */

    }

    private void addElement(){
        if(room.getElements().size() == 0) {
            room.addSliderElement("Prvi", Category.Grijanje);
            room.addSliderElement("Drugi", Category.Klimatizacija);
            refreshElements();
        }
    }

    private void refreshElements() {
        Log.d("ElementsFragment", "______________________________________________________________");
        adapter = new ElementAdapter(recyclerView);
    }

    @Override
    public void deletePrompt(String message, Model model) {
        LongClickDialog yesNoDialog = LongClickDialog.newYesNo(message, model);
        yesNoDialog.show(getFragmentManager(), "");
    }

    @Override
    public void createDialog(Model model) {
        LongClickDialog longClickDialog = LongClickDialog.newInstance(model);
        longClickDialog.show(getFragmentManager(), "");
    }

    @Override
    public void refresh() {
        refreshElements();
    }
}
