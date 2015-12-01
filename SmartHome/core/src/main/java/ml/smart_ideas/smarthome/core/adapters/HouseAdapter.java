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
    public HouseAdapter(Context context, ArrayList<House> kuce) {
        super(context, 0, kuce);
        isForNavigation = false;
    }
    public HouseAdapter(Context context, ArrayList<House> kuce, Boolean isForNavigation) {
        super(context, 0, kuce);
        this.isForNavigation = isForNavigation;
    }

    private Boolean isForNavigation;


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final House kuca = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.house_item, parent, false);
        }

        LinearLayout houseItemLayout = (LinearLayout) convertView.findViewById(R.id.house_item_layout);

        TextView naziv = (TextView) convertView.findViewById(R.id.nazivKuce);
        TextView adresa = (TextView) convertView.findViewById(R.id.adresaKuce);

        ImageView image = (ImageView) convertView.findViewById(R.id.slikaKuce);
        ImageView uredi = (ImageView) convertView.findViewById(R.id.img_uredi_kucu);

        image.setImageResource(R.drawable.ic_home_black_24dp);

        if(isForNavigation){
            uredi.setVisibility(View.INVISIBLE);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)houseItemLayout.getLayoutParams();
            params.setMargins(0, 0, 0, 0);
            houseItemLayout.setLayoutParams(params);
        }else {
            uredi.setImageResource(R.drawable.ic_create_black_24dp);
        }


        naziv.setText(kuca.getName());
        adresa.setText(kuca.getAddress());

        uredi.setOnClickListener(UrediClickListener);
        uredi.setTag(position);


        return convertView;
    }


    private View.OnClickListener UrediClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            User user = Globals.getInstance().getUser();
            final List<House> houses=user.getKuce();


            String s=v.getTag().toString();
            Globals.getInstance().setFragmentState(FragmentStateEnum.Edit);
            Globals.getInstance().setCurrentHouse(houses.get(Integer.parseInt(s)));
            Globals.getInstance().ShowFragment(FragmentEnum.NewHouseFragment);
        }
    };


}

