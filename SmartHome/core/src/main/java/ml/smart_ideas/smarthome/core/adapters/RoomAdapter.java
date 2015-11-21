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
import ml.smart_ideas.smarthome.db.Prostorija;


public class RoomAdapter extends ArrayAdapter<Prostorija> {
    public RoomAdapter(Context context, ArrayList<Prostorija> rooms) {

        super(context, 0, rooms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Prostorija room = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.room_item, parent, false);
        }

        TextView naziv = (TextView) convertView.findViewById(R.id.room_name);

        ImageView image = (ImageView) convertView.findViewById(R.id.room_icon);

        image.setImageResource(R.drawable.ic_home_black_24dp);


        naziv.setText(room.getNaziv());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRoom(room);
            }
        });

        return convertView;
    }

    private void openRoom(Prostorija room) {
        Globals.getInstance().setCurrentRoom(room);
        //Globals.getInstance().ShowFragment(FragmentEnum.RoomsFragment);
    }

}

