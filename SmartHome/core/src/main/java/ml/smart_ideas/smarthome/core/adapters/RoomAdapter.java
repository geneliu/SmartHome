package ml.smart_ideas.smarthome.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentStateEnum;
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

        TextView roomName = (TextView) convertView.findViewById(R.id.room_name);

        ImageView image = (ImageView) convertView.findViewById(R.id.room_icon);
        ImageView imgEdit = (ImageView) convertView.findViewById(R.id.img_edit_room);


        image.setImageResource(R.drawable.ic_weekend_black_24dp);
        imgEdit.setImageResource(R.drawable.ic_create_black_24dp);

        imgEdit.setOnClickListener(UrediClickListener);
        imgEdit.setTag(position);

        roomName.setText(room.getNaziv());


        return convertView;
    }
    private View.OnClickListener UrediClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Kuca house = Globals.getInstance().getCurrentHouse();
            List<Prostorija> prostorije= house.getProstorije();


            String s=v.getTag().toString();
            Globals.getInstance().setFragmentState(FragmentStateEnum.Edit);
            Globals.getInstance().setCurrentRoom(prostorije.get(Integer.parseInt(s)));
            Globals.getInstance().ShowFragment(FragmentEnum.NewRoomFragment);

        }
    };

}

