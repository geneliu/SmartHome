package ml.smart_ideas.smarthome.core;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;


import com.activeandroid.Model;

import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentStateEnum;
import ml.smart_ideas.smarthome.db.Element;
import ml.smart_ideas.smarthome.db.House;
import ml.smart_ideas.smarthome.db.Room;

public class LongClickDialog extends DialogFragment {

    public static final String MESSAGE = "message";
    private House house;
    private Room room;
    private Element element;

    private Model yesNoModel;

    private boolean isSet;
    private ObjectForList objectForList;

    public static LongClickDialog newInstance(Object object) {
        LongClickDialog frag = new LongClickDialog();
        Bundle args = new Bundle();
        if (object.getClass() == House.class) {
            frag.setHouse((House) object);
        }
        args.putString("title", frag.getName());
        frag.setArguments(args);
        return frag;
    }

    public static LongClickDialog newYesNo(String message,Model model) {
        LongClickDialog frag = new LongClickDialog();
        Bundle args = new Bundle();
        frag.setObjectForList(ObjectForList.YesNo);
        frag.setModel(model);
        args.putString(MESSAGE, message);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (objectForList != ObjectForList.YesNo) {
            builder.setTitle(getName())
                    .setItems(getItems(), onClickListener
                    );
        } else {
            builder.setTitle(getArguments().getString(MESSAGE))
                    .setItems(getYesNoItems(), onYesNoClickListener
                    );
        }
        return builder.create();
    }

    public CharSequence[] getItems() {
        CharSequence charSequence[] = {
                Globals.getInstance().getContext().getString(R.string.dialog_item_edit),
                Globals.getInstance().getContext().getString(R.string.dialog_item_delete),
                Globals.getInstance().getContext().getString(R.string.dialog_item_cancel)};
        return charSequence;
    }

    public CharSequence[] getYesNoItems() {
        CharSequence charSequence[] = {
                Globals.getInstance().getContext().getString(R.string.prompt_delete),
                Globals.getInstance().getContext().getString(R.string.dialog_item_cancel)};
        return charSequence;
    }


    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
        isSet = true;
        objectForList = ObjectForList.House;
    }

    public void setObjectForList(ObjectForList objectForList) {
        this.objectForList = objectForList;
    }

    private String getName() {
        if (objectForList == ObjectForList.House) {
            return house.getName();
        } else if (objectForList == ObjectForList.Room) {
            return room.getName();
        } else if (objectForList == ObjectForList.Element) {
            return element.getName();
        }
        return "";
    }

    private void deleteHouse(House house) {
        Globals.getInstance().deletePrompt(Globals.getInstance().getContext().getString(R.string.prompt_delete_house_message),house);
    }


    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            if (which == 0) {//SETUP EDIT
                Globals.getInstance().setFragmentState(FragmentStateEnum.Edit);
            } else {
                Globals.getInstance().setFragmentState(FragmentStateEnum.Off);
            }
            if (objectForList == ObjectForList.House) {
                if (which == 0) { //GO TO EDIT
                    Globals.getInstance().setCurrentHouse(house);
                    Globals.getInstance().ShowFragment(FragmentEnum.NewHouseFragment);
                } else if (which == 1) {//PROPMT DELETE
                    deleteHouse(house);
                }
            }
        }
    };

    DialogInterface.OnClickListener onYesNoClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            if (which == 0) {//DELETE
                yesNoModel.delete();
                Globals.getInstance().Refresh();
            }
        }
    };

    public void setModel(Model model) {
        this.yesNoModel = model;
    }

    private enum ObjectForList {
        YesNo,
        House,
        Room,
        Element,
        None
    }
}