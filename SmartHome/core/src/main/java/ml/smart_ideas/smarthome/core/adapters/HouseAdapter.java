package ml.smart_ideas.smarthome.core.adapters;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.StanjeFragmentaEnum;
import ml.smart_ideas.smarthome.db.Korisnik;
import ml.smart_ideas.smarthome.db.Kuca;


public class HouseAdapter extends ArrayAdapter<Kuca> {
    public HouseAdapter(Context context, ArrayList<Kuca> kuce) {
        super(context, 0, kuce);
    }
    public int Position;



    public static class RowViewHolder
    {
        public TextView naziv;
        public TextView adresa;
        public ImageView image;
        public ImageView Uredi;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Kuca kuca = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.house_item, parent, false);
        }

        Position=position;
        RowViewHolder holder= new RowViewHolder();
        holder.naziv = (TextView) convertView.findViewById(R.id.nazivKuce);
        holder.adresa = (TextView) convertView.findViewById(R.id.adresaKuce);

        holder.image = (ImageView) convertView.findViewById(R.id.slikaKuce);
        holder.Uredi= (ImageView) convertView.findViewById(R.id.img_uredi_kucu);

        holder.image.setImageResource(R.drawable.ic_home_black_24dp);
        holder.Uredi.setImageResource(R.drawable.ic_create_black_24dp);


        holder.naziv.setText(kuca.getNaziv());
        holder.adresa.setText(kuca.getAdresa());

        holder.Uredi.setOnClickListener(UrediClickListener);
        holder.Uredi.setTag(position);


        return convertView;
    }


    private View.OnClickListener UrediClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Korisnik korisnik = Globals.getInstance().getKorisnik();
            final List<Kuca> houses=korisnik.getKuce();


            String s=v.getTag().toString();
            Globals.getInstance().setStanjeFragmenta(StanjeFragmentaEnum.uredi);
            Globals.getInstance().setCurrentHouse(houses.get(Integer.parseInt(s)));
          Globals.getInstance().ShowFragment(FragmentEnum.NewHouseFragment);

        }
    };


}

