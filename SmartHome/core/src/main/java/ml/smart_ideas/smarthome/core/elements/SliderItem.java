package ml.smart_ideas.smarthome.core.elements;


import ml.smart_ideas.smarthome.db.enums.Category;
import ml.smart_ideas.smarthome.db.Element;

public class SliderItem implements ElementItem{

    private Element element;

    public SliderItem(Element element){
        this.element = element;
    }

    public String getName(){
        return element.getName();
    }
    public void setName(String name){
        element.setName(name);
    }

    public boolean getState(){
        return element.getSlider().getState();
    }
    public void setState(boolean state){
        element.getSlider().setState(state);
    }

    public int getSliderValue(){
        return element.getSlider().getSliderValue();
    }
    public void setSliderValue(int sliderValue){
        element.getSlider().setSliderValue(sliderValue);
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
