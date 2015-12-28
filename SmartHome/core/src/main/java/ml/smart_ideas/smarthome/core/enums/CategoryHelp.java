package ml.smart_ideas.smarthome.core.enums;


import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.R;
import ml.smart_ideas.smarthome.db.enums.Category;

public class CategoryHelp {

    private static final int HEATING_MAX = 30;
    private static final int HEATING_MIN = 5;

    private static final int COOLING_MAX = 15;
    private static final int COOLING_MIN = 25;

    private static final int DEFAULT_MAX = 100;
    private static final int DEFAULT_MIN = 0;

    private static final int DEFAULT_VALUE = 20;

    public static int compare(Category first, Category second){
        return getCategoryValue(first) - getCategoryValue(second);
    }

    private static int getCategoryValue(Category category){
        switch (category){
            case Lighting: return 0;
            case Heating: return 1;
            case Cooling: return 2;
            case Windows: return 3;
            case Other: return 4;
            default: return 0;
        }
    }

    public static int getMaxValue(Category category){
        switch (category){
            case Heating: return HEATING_MAX;
            case Cooling: return COOLING_MAX;
            default: return DEFAULT_MAX;
        }
    }

    public static int getMinValue(Category category){
        switch (category){
            case Heating: return HEATING_MIN;
            case Cooling: return COOLING_MIN;
            default: return DEFAULT_MIN;
        }
    }

    public static int parseValue(int value, Category category){
        switch (category){
            case Heating: {
                if(value > HEATING_MAX || value < HEATING_MIN){
                    return DEFAULT_VALUE;
                }
                else return value;
            }
            case Cooling: {
                if(value < COOLING_MAX || value > COOLING_MIN){
                    return DEFAULT_VALUE;
                }
                else return value;
            }
            default: return DEFAULT_VALUE;
        }
    }

    //region Name

    public static String getCategoryName(Category category){
        String name = "";
        switch (category){
            case Heating:
                name = Globals.getInstance().getContext().getString(R.string.category_heating);
                break;
            case Lighting:
                name = Globals.getInstance().getContext().getString(R.string.category_lighting);
                break;
            case Cooling:
                name = Globals.getInstance().getContext().getString(R.string.category_cooling);
                break;
            case Windows:
                name = Globals.getInstance().getContext().getString(R.string.category_windows);
                break;
            case Other:
                name = Globals.getInstance().getContext().getString(R.string.category_other);
                break;
            default:
                name = Globals.getInstance().getContext().getString(R.string.category_other);
        }
        return name;
    }

    //endregion
}
