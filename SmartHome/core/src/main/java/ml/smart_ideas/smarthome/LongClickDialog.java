package ml.smart_ideas.smarthome;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;

public class LongClickDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        CharSequence charSequence[] = {"Uredi","Izbriši","Odustani"};
        builder.setTitle("Odabrana kuća")
                .setItems(charSequence, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
        return builder.create();
    }
}