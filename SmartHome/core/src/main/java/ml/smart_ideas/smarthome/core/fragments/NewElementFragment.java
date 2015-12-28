package ml.smart_ideas.smarthome.core.fragments;

import android.app.Fragment;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.enums.CategoryHelp;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentStateEnum;
import ml.smart_ideas.smarthome.db.Element;
import ml.smart_ideas.smarthome.db.House;
import ml.smart_ideas.smarthome.db.Room;
import ml.smart_ideas.smarthome.db.enums.Category;
import ml.smart_ideas.smarthome.db.enums.ElementEnum;

public class NewElementFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText etElementName;
    TextView tvMessage;
    Button btnCreate;
    RadioButton rbSwitch;
    RadioButton rbSlider;
    Spinner spinnerCategory;
    Element element;

    List<String> categories;
    Category selectedCategory;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.new_element_fragment, container, false);


        etElementName = (EditText) viewInflater.findViewById(R.id.element_name);
        btnCreate = (Button) viewInflater.findViewById(R.id.btn_create_new_element);
        rbSwitch = (RadioButton) viewInflater.findViewById(R.id.rb_switch);
        rbSlider = (RadioButton) viewInflater.findViewById(R.id.rb_slider);
        spinnerCategory = (Spinner) viewInflater.findViewById(R.id.spinner_category);


        if (Globals.getInstance().getFragmentState() == FragmentStateEnum.Edit) {
            element = Globals.getInstance().getCurrentElement();
            etElementName.setText(element.getName());
            selectedCategory = element.getCategory();

            if(element.checkElement() == ElementEnum.Switch)
                rbSwitch.setChecked(true);
            else
                rbSlider.setChecked(true);

            btnCreate.setText(R.string.update_button);
            Globals.getInstance().ShowTitle(element.getName());
        } else {
            selectedCategory = Category.values()[0];
            rbSwitch.setChecked(true);
            btnCreate.setText(R.string.new_element_button);
            Globals.getInstance().ShowTitle(Globals.getInstance().getContext().getString(R.string.new_element));
        }

        tvMessage = (TextView) viewInflater.findViewById(R.id.tv_message_element);
        tvMessage.setText("");

        categories = new ArrayList<>();

        for (int i = 0; i < Category.values().length; i++)
            categories.add(CategoryHelp.getCategoryName(Category.values()[i]));

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, categories);
        spinnerCategory.setAdapter(categoryAdapter);

        if (Globals.getInstance().getFragmentState() == FragmentStateEnum.Edit) {
            spinnerCategory.setSelection(categories.indexOf(CategoryHelp.getCategoryName(selectedCategory)));
        }
        else
            spinnerCategory.setSelection(0);

        spinnerCategory.setOnItemSelectedListener(this);

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion < android.os.Build.VERSION_CODES.LOLLIPOP) {
            btnCreate.setBackgroundColor(ContextCompat.getColor(Globals.getInstance().getContext(), R.color.white));
        }

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Globals.getInstance().getFragmentState() == FragmentStateEnum.Edit)
                    updateElement();
                else createNewElement();
            }
        });


        return viewInflater;
    }

    private void createNewElement() {
        String elementName = etElementName.getText().toString();
        if (elementName.equals("")) {
            tvMessage.setText(R.string.error_new_element_no_name);
        } else {
            Room currentRoom = Globals.getInstance().getCurrentRoom();
            if (rbSwitch.isChecked())
                currentRoom.addSwitchElement(elementName, selectedCategory);
            else
                currentRoom.addSliderElement(elementName, selectedCategory);
            Globals.getInstance().ClearBackStack(1);
            Globals.getInstance().ShowFragment(FragmentEnum.ElementsFragment);
        }
    }

    private void updateElement() {
        String elementName = etElementName.getText().toString();
        if (elementName.equals("")) {
            tvMessage.setText(R.string.error_new_room_no_name);
        } else {
            element.setName(elementName);
            element.setCategory(selectedCategory);

            if (rbSwitch.isChecked()) {
                if (element.checkElement() == ElementEnum.Slider)
                    element.addSwitch();
            } else if (element.checkElement() == ElementEnum.Switch)
                element.addSlider();

            Globals.getInstance().ClearBackStack(1);
            Globals.getInstance().ShowFragment(FragmentEnum.ElementsFragment);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedCategory = Category.values()[position];
        Log.d("NewElementFragment","selected category: " + CategoryHelp.getCategoryName(selectedCategory));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
