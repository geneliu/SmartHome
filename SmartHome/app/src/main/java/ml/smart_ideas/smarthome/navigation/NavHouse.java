package ml.smart_ideas.smarthome.navigation;


import ml.smart_ideas.smarthome.db.House;

public class NavHouse implements NavigationItem{

    public NavHouse(House house){
        this.house = house;
    }

    private House house;

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
