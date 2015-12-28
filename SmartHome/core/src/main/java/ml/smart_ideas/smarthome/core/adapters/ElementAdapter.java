package ml.smart_ideas.smarthome.core.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.adapters.viewholders.VHElement;
import ml.smart_ideas.smarthome.core.adapters.viewholders.VHHeader;
import ml.smart_ideas.smarthome.core.adapters.viewholders.VHSlider;
import ml.smart_ideas.smarthome.core.adapters.viewholders.VHSwitch;
import ml.smart_ideas.smarthome.core.elements.ElementItem;
import ml.smart_ideas.smarthome.core.elements.HeaderItem;
import ml.smart_ideas.smarthome.core.elements.SliderItem;
import ml.smart_ideas.smarthome.core.elements.SwitchItem;
import ml.smart_ideas.smarthome.db.Element;
import ml.smart_ideas.smarthome.db.Room;
import ml.smart_ideas.smarthome.db.enums.Category;
import ml.smart_ideas.smarthome.core.enums.CategoryHelp;
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
                setUpMenuClick(vhSwitch, position);
                break;
            case SLIDER:
                VHSlider vhSlider = (VHSlider) holder;
                configureSlider(vhSlider, position);
                setUpMenuClick(vhSlider, position);
                break;
            default:
                VHSwitch vhDefault = (VHSwitch) holder;
                configureSwitch(vhDefault, position);
                break;
        }
    }
    public void setUpMenuClick(VHElement vhElement, final int position){

        ImageButton imageButton = (ImageButton)vhElement.getView().findViewById(R.id.menu_button);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ElementItem elementItem = elements.get(position);
                if(elementItem != null){
                    Globals.getInstance().createDialog(elementItem.getElement());
                }
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

        //extra space
        elementsHeaders.add(new HeaderItem(null));
        elementsHeaders.add(new HeaderItem(null));

        return elementsHeaders;
    }

    public List<ElementItem> getElements() {
        return elements;
    }
}


