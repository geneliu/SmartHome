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
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentStateEnum;
import ml.smart_ideas.smarthome.db.User;
import ml.smart_ideas.smarthome.db.House;


public class NewHouseFragment extends Fragment{

    EditText ETname;
    EditText ETaddress;
    TextView TVmessage;
    House house;

    Button btnKreiraj;

    String houseName;
    String houseAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.new_house_fragment, container, false);

        ETname = (EditText)viewInflater.findViewById(R.id.new_house_name);
        ETaddress = (EditText)viewInflater.findViewById(R.id.new_house_address);
        btnKreiraj = (Button)viewInflater.findViewById(R.id.btn_create_new_house);

        if(Globals.getInstance().getFragmentState()== FragmentStateEnum.Edit)
        {
            house=Globals.getInstance().getCurrentHouse();
            ETname.setText(house.getName());
            ETaddress.setText(house.getAddress());
            btnKreiraj.setText(R.string.update_button);
            Globals.getInstance().ShowTitle(house.getName());
        }
        else {
            btnKreiraj.setText(R.string.new_room_button);
            Globals.getInstance().ShowTitle(Globals.getInstance().getContext().getString(R.string.new_house));
        }

        TVmessage = (TextView)viewInflater.findViewById(R.id.TVMessage);
        TVmessage.setText("");

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion < android.os.Build.VERSION_CODES.LOLLIPOP){
            btnKreiraj.setBackgroundColor(ContextCompat.getColor(Globals.getInstance().getContext(), R.color.white));
        }

        btnKreiraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Globals.getInstance().getFragmentState()== FragmentStateEnum.Edit)
                    updateHouse();
                else createNewHouse();
            }
        });

        return viewInflater;
    }

    private void createNewHouse()
    {
        if(checkHouseAttributes()){
            User user = Globals.getInstance().getUser();
            user.addHouse(houseName,houseAddress);
            Globals.getInstance().RefreshNavigation();
            //Globals.getInstance().PressBack();
            Globals.getInstance().ClearBackStack();
            Globals.getInstance().ShowFragment(FragmentEnum.HousesFragment);
        }
    }
    private void updateHouse()
    {
        if(checkHouseAttributes()){
            house.setName(houseName);
            house.setAddress(houseAddress);
            house.setLast_modified();
            Globals.getInstance().RefreshNavigation();
            //Globals.getInstance().PressBack();
            Globals.getInstance().ClearBackStack();
            Globals.getInstance().ShowFragment(FragmentEnum.HousesFragment);
        }
    }

    private Boolean checkHouseAttributes(){
        houseName = ETname.getText().toString();
        houseAddress = ETaddress.getText().toString();

        if(houseName.equals("") && houseAddress.equals("")){
            TVmessage.setText(R.string.error_new_house_no_name_address);
        }
        else if(houseName.equals("")){
            TVmessage.setText(R.string.error_new_house_no_name);
        }
        else if(houseAddress.equals("")){
            TVmessage.setText(R.string.error_new_house_no_address);
        }else {
            return true;
        }
        return false;
    }
}
