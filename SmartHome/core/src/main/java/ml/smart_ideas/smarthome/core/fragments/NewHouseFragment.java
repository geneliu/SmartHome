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
import ml.smart_ideas.smarthome.core.enums.StanjeFragmentaEnum;
import ml.smart_ideas.smarthome.db.Korisnik;
import ml.smart_ideas.smarthome.db.Kuca;


public class NewHouseFragment extends Fragment{

    EditText ETnaziv;
    EditText ETadresa;
    TextView TVmessage;
    Kuca house;

    Button btnKreiraj;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.new_house_fragment, container, false);

        ETnaziv = (EditText)viewInflater.findViewById(R.id.new_house_name);
        ETadresa = (EditText)viewInflater.findViewById(R.id.new_house_address);
        btnKreiraj = (Button)viewInflater.findViewById(R.id.btn_create_new_house);

        if(Globals.getInstance().getStanjeFragmenta()== StanjeFragmentaEnum.uredi)
        {
            house=Globals.getInstance().getCurrentHouse();
            ETnaziv.setText(house.getNaziv());
            ETadresa.setText(house.getAdresa());
            btnKreiraj.setText(R.string.update_house_button);
        }


        TVmessage = (TextView)viewInflater.findViewById(R.id.TVMessage);
        TVmessage.setText("");


        Globals.getInstance().ShowTitle(Globals.getInstance().getContext().getString(R.string.houses_fragment_title));


        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion < android.os.Build.VERSION_CODES.LOLLIPOP){
            btnKreiraj.setBackgroundColor(ContextCompat.getColor(Globals.getInstance().getContext(), R.color.white));
        }

        btnKreiraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Globals.getInstance().getStanjeFragmenta()== StanjeFragmentaEnum.uredi)
                    updateRoom();
                else createNewHouse();
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
    private void updateRoom()
    {
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
            house.setNaziv(nazivKuce);
            house.setAdresa(adresaKuce);
            house.updateKuca(house);
            Globals.getInstance().setStanjeFragmenta(StanjeFragmentaEnum.off);
            Globals.getInstance().PressBack();
        }
    }
}
