package ml.smart_ideas.smarthome.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.db.Kuca;


public class HouseAdapter extends ArrayAdapter<Kuca> {
    public HouseAdapter(Context context, ArrayList<Kuca> kuce) {
        super(context, 0, kuce);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Kuca kuca = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.house_item, parent, false);
        }

        TextView naziv = (TextView) convertView.findViewById(R.id.nazivKuce);
        TextView adresa = (TextView) convertView.findViewById(R.id.adresaKuce);

        ImageView image = (ImageView) convertView.findViewById(R.id.slikaKuce);

        image.setImageResource(R.drawable.ic_home_black_24dp);


        naziv.setText(kuca.getNaziv());
        adresa.setText(kuca.getAdresa());



        return convertView;
    }



}

