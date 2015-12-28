package ml.smart_ideas.smarthome.core.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import ml.smart_ideas.smarthome.core.elements.ElementItem;

public abstract class VHElement extends RecyclerView.ViewHolder {
    public VHElement(View view) {
        super(view);
    }
    public abstract ElementItem getElementItem();
    public abstract View getView();
}