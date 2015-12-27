package ml.smart_ideas.smarthome.core.elements;


import ml.smart_ideas.smarthome.db.Element;
import ml.smart_ideas.smarthome.db.enums.Category;

public class HeaderItem implements ElementItem{

    private String categoryName;
    private Category category;

    public HeaderItem(Category category){
        this.category = category;
        categoryName = category.name();
    }

    public String getCategoryName(){
        return categoryName;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public Element getElement() {
        return null;
    }
}
