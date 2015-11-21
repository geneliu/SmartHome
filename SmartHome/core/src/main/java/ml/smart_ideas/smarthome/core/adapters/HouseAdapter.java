package ml.smart_ideas.smarthome.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.db.Kuca;

/**
 * Created by Admin on 13.11.2015..
 */
/*
public class HouseAdapter extends BaseAdapter {

    Context context;
    ArrayList<Kuca> kuce;
    private static LayoutInflater inflater = null;

    public HouseAdapter(Context context,ArrayList<Kuca> kuce)
    {
        this.context=context;
        this.kuce=kuce;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return kuce.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
            view = inflater.inflate(R.layout.houses,null);
        TextView text = (TextView) view.findViewById(R.id.nazivKuce);
        ImageView image = (ImageView) view.findViewById(R.id.slikaKuce);
        image.setImageResource(R.drawable.ic_home_black_24dp);

        text.setText(getItem(i).getNaziv());


    }
}
*/

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

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHouse(kuca);
            }
        });

        return convertView;
    }

    private void openHouse(Kuca kuca) {
        Globals.getInstance().setCurrentHouse(kuca);
        Globals.getInstance().ShowFragment(FragmentEnum.RoomsFragment);
    }

}

