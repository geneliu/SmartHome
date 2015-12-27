package ml.smart_ideas.smarthome.core.elements;


import ml.smart_ideas.smarthome.db.Element;
import ml.smart_ideas.smarthome.db.enums.Category;

public interface ElementItem {
    Category getCategory();
    Element getElement();
}
