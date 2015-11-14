package ml.smart_ideas.smarthome.core.Adapteri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.db.Kuca;

/**
 * Created by Admin on 13.11.2015..
 */
/*
public class homeAdapter extends BaseAdapter {

    Context context;
    ArrayList<Kuca> kuce;
    private static LayoutInflater inflater = null;

    public homeAdapter(Context context,ArrayList<Kuca> kuce)
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

public class homeAdapter extends ArrayAdapter<Kuca> {
    public homeAdapter(Context context, ArrayList<Kuca> kuce) {
        super(context, 0, kuce);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Kuca kucica = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.houses, parent, false);
        }
        // Lookup view for data population
        TextView text = (TextView) convertView.findViewById(R.id.nazivKuce);
        ImageView image = (ImageView) convertView.findViewById(R.id.slikaKuce);
        image.setImageResource(R.drawable.ic_home_black_24dp);
        // Populate the data into the template view using the data object
        text.setText(kucica.getNaziv());
        // Return the completed view to render on screen
        return convertView;
    }
}

