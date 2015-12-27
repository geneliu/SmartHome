package ml.smart_ideas.smarthome.core.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.LongClickDialog;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.elements.ElementItem;
import ml.smart_ideas.smarthome.core.elements.HeaderItem;
import ml.smart_ideas.smarthome.core.elements.SliderItem;
import ml.smart_ideas.smarthome.core.elements.SwitchItem;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.db.Element;
import ml.smart_ideas.smarthome.db.Room;
import ml.smart_ideas.smarthome.db.Slider;
import ml.smart_ideas.smarthome.db.Switch;
import ml.smart_ideas.smarthome.db.enums.Category;
import ml.smart_ideas.smarthome.db.enums.CategoryHelp;
import ml.smart_ideas.smarthome.db.enums.ElementEnum;

public class ElementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ElementItem> elements;
    private RecyclerView recyclerView;

    private final int HEADER = 0, SWITCH = 1, SLIDER = 2;

    public ElementAdapter(RecyclerView recyclerView) {
        elements = getElementList();
        this.recyclerView = recyclerView;
        this.recyclerView.setLayoutManager(new LinearLayoutManager(Globals.getInstance().getContext(), LinearLayoutManager.VERTICAL, false));
        this.recyclerView.setAdapter(this);
    }

    @Override
    public int getItemViewType(int position) {
        ElementItem el = elements.get(position);
        int type = 0;
        if (elements.get(position) instanceof HeaderItem)
            type = HEADER;
        else if (elements.get(position) instanceof SwitchItem)
            type = SWITCH;
        else if (elements.get(position) instanceof SliderItem)
            type = SLIDER;
        return type;
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());//.inflate(R.layout.some_layout, parent, false);
        RecyclerView.ViewHolder vh = null;
        switch (viewType) {
            case HEADER:
                vh = new VHHeader(li.inflate(R.layout.element_item_header, parent,false));
                break;
            case SWITCH:
                vh = new VHSwitch(li.inflate(R.layout.element_item_switch, parent, false));
                break;
            case SLIDER:
                vh = new VHSlider(li.inflate(R.layout.element_item_slider, parent, false));
                break;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case HEADER:
                VHHeader vhHeader = (VHHeader) holder;
                configureHeader(vhHeader, position);
                break;
            case SWITCH:
                VHSwitch vhSwitch = (VHSwitch) holder;
                configureSwitch(vhSwitch, position);
                setUpLongClick(vhSwitch,position);
                break;
            case SLIDER:
                VHSlider vhSlider = (VHSlider) holder;
                configureSlider(vhSlider, position);
                setUpLongClick(vhSlider, position);
                break;
            default:
                VHSwitch vhDefault = (VHSwitch) holder;
                configureSwitch(vhDefault, position);
                break;
        }
    }
    public void setUpLongClick(VHElement vhElement, final int position){
        vhElement.getView().setLongClickable(true);
        vhElement.getView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ElementItem elementItem = elements.get(position);
                if(elementItem != null){
                    Globals.getInstance().createDialog(elementItem.getElement());
                }
                return true;
            }
        });
    }

    private void configureSlider(VHSlider vhSlider, int position) {
        SliderItem sliderItem = (SliderItem) elements.get(position);
        vhSlider.setSliderItem(sliderItem);
    }

    private void configureSwitch(VHSwitch vhSwitch, int position) {
        SwitchItem switchItem = (SwitchItem) elements.get(position);
        vhSwitch.setSwitchItem(switchItem);
    }

    private void configureHeader(VHHeader vhHeader, int position) {
        HeaderItem headerItem = (HeaderItem) elements.get(position);
        vhHeader.setHeaderItem(headerItem);
    }

    private List<ElementItem> getElementList() {
        List<ElementItem> elementItems = new ArrayList<>();
        Room room = Globals.getInstance().getCurrentRoom();
        List<Element> els = room.getElements();
        for (Element element : room.getElements()) {
            ElementItem elementItem = null;
            if (element.checkElement() != ElementEnum.Both
                    && element.checkElement() != ElementEnum.None) {
                if (element.checkElement() == ElementEnum.Switch) {
                    elementItem = new SwitchItem(element);
                } else if (element.checkElement() == ElementEnum.Slider) {
                    elementItem = new SliderItem(element);
                }
            }
            if (elementItem != null)
                elementItems.add(elementItem);
        }

        Collections.sort(
                elementItems,
                new Comparator<ElementItem>() {
                    public int compare(ElementItem first, ElementItem second) {
                        return CategoryHelp.compare(first.getCategory(), second.getCategory());
                    }
                }
        );

        List<ElementItem> elementsHeaders = new ArrayList<>();

        Category currentCategory = null;
        for (ElementItem elementItem : elementItems) {
            if (currentCategory != elementItem.getCategory()) {
                elementsHeaders.add(new HeaderItem(elementItem.getCategory()));
                currentCategory = elementItem.getCategory();
            }
            elementsHeaders.add(elementItem);
        }

        return elementsHeaders;
    }

    public List<ElementItem> getElements() {
        return elements;
    }



    class VHHeader extends RecyclerView.ViewHolder {

        private View thisView;
        private TextView tvName;
        private HeaderItem headerItem;

        public VHHeader(View view) {
            super(view);
            thisView = view;
        }

        public HeaderItem getHeaderItem() {
            return headerItem;
        }

        public void setHeaderItem(HeaderItem headerItem) {
            this.headerItem = headerItem;

            tvName = (TextView) thisView.findViewById(R.id.category_name);

            tvName.setText(headerItem.getCategoryName());
        }
    }


    abstract class VHElement extends RecyclerView.ViewHolder {
        public VHElement(View view) {
            super(view);
        }
        public abstract ElementItem getElementItem();
        public abstract View getView();
    }


    class VHSwitch extends VHElement {

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


    class VHSlider extends VHElement {

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

            Log.d("ElementAdapter","orginal value: " + sliderItem.getSliderValue() + ", parsed value: " + value + ", max: " + max + ", min: " + min + ", progress: " + progress + ",set progress: " + this.slider.getProgress());
            Log.d("ElementAdapter", "slider max: " + this.slider.getMax() + ", max - min: " + (max - min) );



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
                if(reversed){
                    value = max - progress;
                }
                else
                    value = progress + min;
                Log.d("ElementAdapter","value: " + value + ", progress: " + progress + ", max: " + max + ", min: " + min );
                tvValue.setText(Integer.toString(value));
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
}


