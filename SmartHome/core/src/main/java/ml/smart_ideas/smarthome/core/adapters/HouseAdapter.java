package ml.smart_ideas.smarthome.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentStateEnum;
import ml.smart_ideas.smarthome.db.Korisnik;
import ml.smart_ideas.smarthome.db.Kuca;


public class HouseAdapter extends ArrayAdapter<Kuca> {
    public HouseAdapter(Context context, ArrayList<Kuca> kuce) {
        super(context, 0, kuce);
        isForNavigation = false;
    }
    public HouseAdapter(Context context, ArrayList<Kuca> kuce, Boolean isForNavigation) {
        super(context, 0, kuce);
        this.isForNavigation = isForNavigation;
    }

    private TextView naziv;
    private TextView adresa;
    private ImageView image;
    private ImageView uredi;
    private LinearLayout houseItemLayout;

    private Boolean isForNavigation;


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Kuca kuca = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.house_item, parent, false);
        }

        houseItemLayout = (LinearLayout) convertView.findViewById(R.id.house_item_layout);

        naziv = (TextView) convertView.findViewById(R.id.nazivKuce);
        adresa = (TextView) convertView.findViewById(R.id.adresaKuce);

        image = (ImageView) convertView.findViewById(R.id.slikaKuce);
        uredi = (ImageView) convertView.findViewById(R.id.img_uredi_kucu);

        image.setImageResource(R.drawable.ic_home_black_24dp);

        if(isForNavigation){
            uredi.setVisibility(View.INVISIBLE);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)houseItemLayout.getLayoutParams();
            params.setMargins(0, 0, 0, 0);
            houseItemLayout.setLayoutParams(params);
        }else {
            uredi.setImageResource(R.drawable.ic_create_black_24dp);
        }


        naziv.setText(kuca.getNaziv());
        adresa.setText(kuca.getAdresa());

        uredi.setOnClickListener(UrediClickListener);
        uredi.setTag(position);


        return convertView;
    }


    private View.OnClickListener UrediClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Korisnik korisnik = Globals.getInstance().getKorisnik();
            final List<Kuca> houses=korisnik.getKuce();


            String s=v.getTag().toString();
            Globals.getInstance().setFragmentState(FragmentStateEnum.Edit);
            Globals.getInstance().setCurrentHouse(houses.get(Integer.parseInt(s)));
            Globals.getInstance().ShowFragment(FragmentEnum.NewHouseFragment);
        }
    };


}

