package ml.smart_ideas.smarthome.core.elements;


import ml.smart_ideas.smarthome.db.enums.Category;
import ml.smart_ideas.smarthome.db.Element;

public class SwitchItem implements ElementItem {

    private Element element;

    public SwitchItem(Element element){
        this.element = element;
    }

    public String getName(){
        return element.getName();
    }
    public void setName(String name){
        element.setName(name);
    }

    public boolean getState(){
        return element.getSwitch().getState();
    }
    public void setState(boolean state){
        element.getSwitch().setState(state);
    }

    @Override
    public Category getCategory(){
        return element.getCategory();
    }

    @Override
    public Element getElement() {
        return element;
    }

    public void setCategory(Category category){
        element.setCategory(category);
    }

}
