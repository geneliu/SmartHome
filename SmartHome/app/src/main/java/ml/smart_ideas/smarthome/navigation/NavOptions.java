package ml.smart_ideas.smarthome.navigation;


import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.enums.ActivityEnum;

public class NavOptions implements NavigationItem {

    private Boolean isSettings;
    private Boolean isLogout;

    public Boolean getIsLogout() {
        return isLogout;
    }

    public void setIsLogout(Boolean isLogout) {
        this.isLogout = isLogout;
        this.isSettings = !isLogout;
    }

    public Boolean getIsSettings() {
        return isSettings;
    }

    public void setIsSettings(Boolean isSettings) {
        this.isSettings = isSettings;
        this.isLogout = !isSettings;
    }

    public void openOptions() {
        if (isSettings) {

        } else if (isLogout) {
            Globals.getInstance().ShowActivity(ActivityEnum.InitialActivity);
        }
    }
}
