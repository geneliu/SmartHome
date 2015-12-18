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
import ml.smart_ideas.smarthome.db.User;
import ml.smart_ideas.smarthome.db.House;


public class HouseAdapter extends ArrayAdapter<House> {
    public HouseAdapter(Context context, ArrayList<House> houses) {
        super(context, 0, houses);
        isForNavigation = false;
    }
    public HouseAdapter(Context context, ArrayList<House> houses, Boolean isForNavigation) {
        super(context, 0, houses);
        this.isForNavigation = isForNavigation;
    }

    private Boolean isForNavigation;


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final House house = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.house_item, parent, false);
        }

        LinearLayout houseItemLayout = (LinearLayout) convertView.findViewById(R.id.house_item_layout);

        TextView name = (TextView) convertView.findViewById(R.id.houseName);
        TextView address = (TextView) convertView.findViewById(R.id.houseAddress);

        ImageView image = (ImageView) convertView.findViewById(R.id.houseImage);

        image.setImageResource(R.drawable.ic_home_black_24dp);

        if(isForNavigation){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)houseItemLayout.getLayoutParams();
            params.setMargins(0, 0, 0, 0);
            houseItemLayout.setLayoutParams(params);
        }


        name.setText(house.getName());
        address.setText(house.getAddress());




        return convertView;
    }





}

