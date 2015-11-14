package ml.smart_ideas.smarthome.core;

import java.util.List;

import ml.smart_ideas.smarthome.db.Kuca;

/**
 * Created by Admin on 13.11.2015..
 */
public class DataLoader {
    public static List<Kuca> kuce;

    public static List<Kuca> getKuce() {

        kuce=Kuca.getSveKuce();
        return kuce;
    }
}
