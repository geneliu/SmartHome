package ml.smart_ideas.smarthome.navigation;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.microedition.khronos.opengles.GL10;

import ml.smart_ideas.smarthome.R;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.db.User;
import ml.smart_ideas.smarthome.db.House;

public class NavigationAdapter extends ArrayAdapter<NavigationItem> {
    public NavigationAdapter(Context context, ListView navDrawer) {
        super(context, 0, getNavigationItems());
        this.navDrawer = navDrawer;
        navItems = getNavigationItems();
    }

    private ListView navDrawer;
    final private List<NavigationItem> navItems;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final NavigationItem navigationItem = getItem(position);

        if (navigationItem.getClass() == NavUser.class) {
            NavUser navUser = (NavUser) navigationItem;

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.nav_user, parent, false);

            TextView nameSurname = (TextView) convertView.findViewById(R.id.user_name_surname);
            ImageView userImage = (ImageView) convertView.findViewById(R.id.user_image);

            nameSurname.setText(navUser.getUser().getIme() + " " + navUser.getUser().getPrezime());


        } else if (navigationItem.getClass() == NavHouse.class) {
            NavHouse navHouse = (NavHouse) navigationItem;
            House house = navHouse.getHouse();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.nav_house_item, parent, false);

            TextView name = (TextView) convertView.findViewById(ml.smart_ideas.smarthome.core.R.id.houseName);
            TextView address = (TextView) convertView.findViewById(ml.smart_ideas.smarthome.core.R.id.houseAddress);
            ImageView image = (ImageView) convertView.findViewById(ml.smart_ideas.smarthome.core.R.id.houseImage);
            image.setImageResource(ml.smart_ideas.smarthome.core.R.drawable.ic_home_black_24dp);

            name.setText(house.getName());
            address.setText(house.getAddress());

        } else if (navigationItem.getClass() == NavOptions.class) {
            NavOptions navOptions = (NavOptions) navigationItem;

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.nav_options, parent, false);

            TextView optionsName = (TextView) convertView.findViewById(R.id.options_name);
            ImageView optionsImage = (ImageView) convertView.findViewById(R.id.options_icon);

            if (navOptions.getIsSettings()) {
                optionsName.setText(R.string.nav_settings);
                optionsImage.setImageResource(R.drawable.ic_settings_black_24dp);
            } else if (navOptions.getIsLogout()) {
                optionsName.setText(R.string.nav_logout);
                optionsImage.setImageResource(R.drawable.ic_exit_to_app_black_24dp);
            }
        }

        navDrawer.setOnItemClickListener(NavigationClickListener);

        return convertView;
    }

    private ListView.OnItemClickListener NavigationClickListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            NavigationItem navigationItem = (NavigationItem) navItems.get(position);

            if (navigationItem.getClass() == NavUser.class) {
                NavUser navUser = (NavUser) navigationItem;
                Globals.getInstance().PressBack();
                Toast.makeText(Globals.getInstance().getContext(), "User info", Toast.LENGTH_SHORT).show();

            } else if (navigationItem.getClass() == NavHouse.class) {
                NavHouse navHouse = (NavHouse) navigationItem;
                House house = navHouse.getHouse();

                Globals.getInstance().setCurrentHouse(house);
                Globals.getInstance().PressBack();
                Globals.getInstance().ClearBackStack();
                Globals.getInstance().ShowFragment(FragmentEnum.RoomsFragment);


            } else if (navigationItem.getClass() == NavOptions.class) {
                NavOptions navOptions = (NavOptions) navigationItem;

                Globals.getInstance().PressBack();
                if (navOptions.getIsSettings()) {
                    Toast.makeText(Globals.getInstance().getContext(), "Settings", Toast.LENGTH_SHORT).show();
                } else if (navOptions.getIsLogout()) {
                    Toast.makeText(Globals.getInstance().getContext(), "Logout", Toast.LENGTH_SHORT).show();
                }
            }


        }
    };

    public static List<NavigationItem> getNavigationItems() {
        List<NavigationItem> navigationItems = new ArrayList<NavigationItem>();

        navigationItems.add(new NavUser());

        List<House> houses = Globals.getInstance().getUser().getKuce();

        for (House house : houses) {
            navigationItems.add(new NavHouse(house));
        }

        NavOptions settings = new NavOptions();
        settings.setIsSettings(true);
        NavOptions logout = new NavOptions();
        logout.setIsLogout(true);

        navigationItems.add(settings);
        navigationItems.add(logout);

        return navigationItems;
    }

}
