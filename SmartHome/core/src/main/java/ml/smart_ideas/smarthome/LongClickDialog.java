package ml.smart_ideas.smarthome;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentStateEnum;
import ml.smart_ideas.smarthome.db.House;

public class LongClickDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        CharSequence charSequence[] = {getResources().getString(R.string.edit),getResources().getString(R.string.delete),getResources().getString(R.string.dismiss)};
        builder.setTitle(getResources().getString(R.string.current_house))
                .setItems(charSequence, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which)
                        {
                            case 0:
                                Globals.getInstance().setFragmentState(FragmentStateEnum.Edit);
                                Globals.getInstance().ShowFragment(FragmentEnum.NewHouseFragment);

                                break;
                            case 1: Globals.getInstance().getCurrentHouse().deleteHouse();
                              Globals.getInstance().ShowFragment(FragmentEnum.HousesFragment);

                                break;

                        }
                    }
                });


        return builder.create();
    }



}