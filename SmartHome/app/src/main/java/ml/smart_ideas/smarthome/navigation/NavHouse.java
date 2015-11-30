package ml.smart_ideas.smarthome.navigation;


import ml.smart_ideas.smarthome.db.Kuca;

public class NavHouse implements NavigationItem{

    public NavHouse(Kuca house){
        this.house = house;
    }

    private Kuca house;

    public Kuca getHouse() {
        return house;
    }

    public void setHouse(Kuca house) {
        this.house = house;
    }
}
