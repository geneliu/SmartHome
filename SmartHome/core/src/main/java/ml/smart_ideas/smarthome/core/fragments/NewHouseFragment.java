package ml.smart_ideas.smarthome.core.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.db.Korisnik;


public class NewHouseFragment extends Fragment{

    EditText ETnaziv;
    EditText ETadresa;
    TextView TVmessage;

    Button btnKreiraj;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.new_house_fragment, container, false);

        ETnaziv = (EditText)viewInflater.findViewById(R.id.new_house_name);
        ETadresa = (EditText)viewInflater.findViewById(R.id.new_house_address);

        TVmessage = (TextView)viewInflater.findViewById(R.id.TVMessage);
        TVmessage.setText("");

        btnKreiraj = (Button)viewInflater.findViewById(R.id.btn_create_new_house);

        Globals.getInstance().ShowTitle(Globals.getInstance().getContext().getString(R.string.houses_fragment_title));


        btnKreiraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewHouse();
            }
        });


        return viewInflater;
    }

    private void createNewHouse() {
        String nazivKuce = ETnaziv.getText().toString();
        String adresaKuce = ETadresa.getText().toString();

        if(nazivKuce.equals("") && adresaKuce.equals("")){
            TVmessage.setText(R.string.error_new_house_no_name_address);
        }
        else if(nazivKuce.equals("")){
            TVmessage.setText(R.string.error_new_house_no_name);
        }
        else if(adresaKuce.equals("")){
            TVmessage.setText(R.string.error_new_house_no_address);
        }else {
            Korisnik korisnik = Globals.getInstance().getKorisnik();
            korisnik.addKuca(nazivKuce,adresaKuce);
            Globals.getInstance().PressBack();
        }

    }
}
