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
import ml.smart_ideas.smarthome.db.Korisnik;
import ml.smart_ideas.smarthome.db.Kuca;

public class NewRoomFragment extends Fragment {

    EditText ETnaziv;
    TextView TVmessage;
    Button btnKreiraj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.new_room_fragment, container, false);

        ETnaziv = (EditText)viewInflater.findViewById(R.id.new_room_name);

        TVmessage = (TextView)viewInflater.findViewById(R.id.TVMessageRoom);
        TVmessage.setText("");

        btnKreiraj = (Button)viewInflater.findViewById(R.id.btn_create_new_room);

        Globals.getInstance().ShowTitle(Globals.getInstance().getContext().getString(R.string.houses_fragment_title));


        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion < android.os.Build.VERSION_CODES.LOLLIPOP){
            btnKreiraj.setBackgroundColor(ContextCompat.getColor(Globals.getInstance().getContext(), R.color.white));
        }

        btnKreiraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewRoom();
            }
        });


        return viewInflater;
    }

    private void createNewRoom()
    {
        String nazivSobe= ETnaziv.getText().toString();
        if(nazivSobe.equals(""))
        {
            TVmessage.setText("Soba ne može biti bez imena");
        }
        else
        {
            Kuca currentHouse= Globals.getInstance().getCurrentHouse();
            currentHouse.addRoom(nazivSobe);
            Globals.getInstance().PressBack();

        }
    }
}
