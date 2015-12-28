package ml.smart_ideas.smarthome.core.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.core.elements.HeaderItem;

public class VHHeader extends RecyclerView.ViewHolder {

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
