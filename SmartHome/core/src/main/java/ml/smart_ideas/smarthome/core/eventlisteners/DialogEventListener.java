package ml.smart_ideas.smarthome.core.eventlisteners;

import com.activeandroid.Model;

import ml.smart_ideas.smarthome.db.Element;

public interface DialogEventListener {
    void deletePrompt(String message,Model model);
    void createDialog(Model model);
}
