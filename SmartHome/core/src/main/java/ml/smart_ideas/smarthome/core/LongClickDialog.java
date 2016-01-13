package ml.smart_ideas.smarthome.core;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
        else if(object.getClass() == Room.class)
        {
            frag.setRoom((Room) object);
        }
        else if(object.getClass() == Element.class)
        {
            frag.setElement((Element) object);
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


    //region Getters and setters
    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
        isSet = true;
        objectForList = ObjectForList.House;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
        isSet=true;
        objectForList= ObjectForList.Room;
    }

    public void setElement(Element element) {
        this.element = element;
        isSet=true;
        objectForList= ObjectForList.Element;
    }

    //endregion


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

    private void deleteRoom(Room room)
    {
        Globals.getInstance().deletePrompt(Globals.getInstance().getContext().getString(R.string.prompt_delete_room_message),room);
    }

    private void deleteElement(Element element)
    {
        Globals.getInstance().deletePrompt(Globals.getInstance().getContext().getString(R.string.prompt_delete_element_message),element);
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
                    Globals.getInstance().setCurrentHouse(house);
                    deleteHouse(house);
                }
            }
            else if(objectForList == ObjectForList.Room)
            {
                if(which == 0){
                    Globals.getInstance().setCurrentRoom(room);
                    Globals.getInstance().ShowFragment(FragmentEnum.NewRoomFragment);
                }
                else if(which ==1) {
                    deleteRoom(room);
                }
            }
            else if(objectForList == ObjectForList.Element)
            {
                if(which == 0){
                    Globals.getInstance().setCurrentElement(element);
                    Globals.getInstance().ShowFragment(FragmentEnum.NewElementFragment);
                } else if (which ==1) {
                    deleteElement(element);
                }
            }
        }
    };

    DialogInterface.OnClickListener onYesNoClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            if (which == 0) {//DELETE
                if(Globals.getInstance().getFragmentState()==FragmentStateEnum.Deleting) {
                    House house = Globals.getInstance().getCurrentHouse();
                  if(house.getRemoteID()<10000) SaveSharedPreferences.saveDeletedHouses(Globals.getInstance().getContext(), house.getRemoteID().toString());
                }
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