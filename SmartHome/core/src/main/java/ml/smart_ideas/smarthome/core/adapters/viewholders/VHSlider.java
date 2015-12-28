package ml.smart_ideas.smarthome.core.adapters.viewholders;

import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.elements.ElementItem;
import ml.smart_ideas.smarthome.core.elements.SliderItem;
import ml.smart_ideas.smarthome.db.enums.Category;
import ml.smart_ideas.smarthome.core.enums.CategoryHelp;

public class VHSlider extends VHElement {

    private View thisView;
    private SliderItem sliderItem;
    private android.widget.Switch aSwitch;
    private SeekBar slider;
    private TextView tvValue;

    private int max;
    private int min;
    private boolean reversed = false;

    public VHSlider(View view) {
        super(view);
        thisView = view;
    }

    public SliderItem getSliderItem() {
        return sliderItem;
    }

    public void setSliderItem(SliderItem sliderItem) {
        this.sliderItem = sliderItem;

        aSwitch = (android.widget.Switch) thisView.findViewById(R.id.slider_switch);
        tvValue = (TextView) thisView.findViewById(R.id.slider_value);
        setSlider((SeekBar) thisView.findViewById(R.id.slider));

        aSwitch.setText(sliderItem.getName());
        aSwitch.setChecked(sliderItem.getState());
        slider.setProgress(sliderItem.getSliderValue());
    }

    public SeekBar getSlider() {
        return slider;
    }

    private void setSlider(SeekBar newSlider) {
        slider = newSlider;
        max = CategoryHelp.getMaxValue(sliderItem.getCategory());
        min = CategoryHelp.getMinValue(sliderItem.getCategory());
        int value = CategoryHelp.parseValue(sliderItem.getSliderValue(), sliderItem.getCategory());

        if(min > max){
            int temp = min;
            min = max;
            max = temp;
            reversed = true;
        }
        int progress = 0;
        slider.setMax(max - min);

        if(reversed){
            progress = max - value;
        }
        else {
            progress = value - min;
        }
        //slider.setProgress(progress);

        final int finalProgress = progress;

        new Handler().post(new Runnable() {
            public void run() {
                slider.setVisibility(View.VISIBLE);
                slider.setProgress(finalProgress);
            }
        });

        //Log.d("ElementAdapter", "orginal value: " + sliderItem.getSliderValue() + ", parsed value: " + value + ", max: " + max + ", min: " + min + ", progress: " + progress + ",set progress: " + this.slider.getProgress());
        //Log.d("ElementAdapter", "slider max: " + this.slider.getMax() + ", max - min: " + (max - min) );



        this.slider.setOnSeekBarChangeListener(sliderListener);
        this.aSwitch.setOnCheckedChangeListener(changedListener);
    }

    private CompoundButton.OnCheckedChangeListener changedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            sliderItem.setState(isChecked);
        }
    };

    SeekBar.OnSeekBarChangeListener sliderListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            int value = 0;
            if(reversed)
                value = max - progress;            
            else
                value = progress + min;
            
            String stringValue = Integer.toString(value);
            
            if(sliderItem.getCategory() == Category.Heating || sliderItem.getCategory() == Category.Cooling)
                stringValue = stringValue + Globals.getInstance().getContext().getString(R.string.slider_degrees);
            
            tvValue.setText(stringValue);
            sliderItem.setSliderValue(value);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

    @Override
    public ElementItem getElementItem(){
        return sliderItem;
    }

    @Override
    public View getView(){
        return thisView;
    }
}