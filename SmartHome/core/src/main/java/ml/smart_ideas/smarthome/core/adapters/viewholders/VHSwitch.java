package ml.smart_ideas.smarthome.core.adapters.viewholders;

import android.view.View;
import android.widget.CompoundButton;

import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.elements.ElementItem;
import ml.smart_ideas.smarthome.core.elements.SwitchItem;

public class VHSwitch extends VHElement {

    private View thisView;
    private SwitchItem switchItem;
    private android.widget.Switch aSwitch;

    public VHSwitch(View view) {
        super(view);
        thisView = view;
    }

    public SwitchItem getSwitchItem() {
        return switchItem;
    }

    public void setSwitchItem(SwitchItem switchItem) {
        this.switchItem = switchItem;

        aSwitch = (android.widget.Switch) thisView.findViewById(R.id.element_switch);
        aSwitch.setChecked(switchItem.getState());
        aSwitch.setText(switchItem.getName());

        aSwitch.setOnCheckedChangeListener(changedListener);
    }

    public android.widget.Switch getSwitch() {
        return aSwitch;
    }

    private void setSwitch(android.widget.Switch aSwitch) {
        this.aSwitch = aSwitch;
    }

    private CompoundButton.OnCheckedChangeListener changedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switchItem.setState(isChecked);
        }
    };

    @Override
    public ElementItem getElementItem(){
        return  switchItem;
    }

    @Override
    public View getView(){
        return thisView;
    }
}
