package ml.smart_ideas.smarthome.core.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.enums.FragmentStateEnum;
import ml.smart_ideas.smarthome.db.House;
import ml.smart_ideas.smarthome.db.Room;

public class NewRoomFragment extends Fragment {

    EditText ETnaziv;
    TextView TVmessage;
    Button btnKreiraj;
    Room room;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.new_room_fragment, container, false);


        ETnaziv = (EditText)viewInflater.findViewById(R.id.new_room_name);
        btnKreiraj = (Button)viewInflater.findViewById(R.id.btn_create_new_room);


        if(Globals.getInstance().getFragmentState()== FragmentStateEnum.Edit)
        {
            room=Globals.getInstance().getCurrentRoom();
            ETnaziv.setText(room.getName());
            btnKreiraj.setText(R.string.update_button);
        }
        else
        {
            btnKreiraj.setText(R.string.new_room_button);
        }


        TVmessage = (TextView)viewInflater.findViewById(R.id.TVMessageRoom);
        TVmessage.setText("");




        Globals.getInstance().ShowTitle(Globals.getInstance().getContext().getString(R.string.houses_fragment_title));


        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion < android.os.Build.VERSION_CODES.LOLLIPOP){
            btnKreiraj.setBackgroundColor(ContextCompat.getColor(Globals.getInstance().getContext(), R.color.white));
        }

        btnKreiraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Globals.getInstance().getFragmentState()== FragmentStateEnum.Edit)
                    updateRoom();
                else createNewRoom();

            }
        });


        return viewInflater;
    }

    private void createNewRoom()
    {
        String roomName= ETnaziv.getText().toString();
        if(roomName.equals(""))
        {
            TVmessage.setText(R.string.error_new_room_no_name);
        }
        else
        {
            House currentHouse= Globals.getInstance().getCurrentHouse();
            currentHouse.addRoom(roomName);
            Globals.getInstance().PressBack();
        }
    }

    private void updateRoom()
    {
        String roomName = ETnaziv.getText().toString();
        if(roomName.equals(""))
        {
            TVmessage.setText(R.string.error_new_room_no_name);
        }
        else
        {
            room.setName(roomName);
            room.updateRoom(room);
            //Globals.getInstance().setFragmentState(FragmentStateEnum.Off);
            Globals.getInstance().PressBack();
        }
    }
}
