package ml.smart_ideas.smarthome.db.enums;


public class CategoryHelp {

    private static final int GRIJANJE_MAX = 30;
    private static final int GRIJANJE_MIN = 5;

    private static final int KLIMATIZACIJA_MAX = 15;
    private static final int KLIMATIZACIJA_MIN = 25;

    private static final int DEFAULT_MAX = 30;
    private static final int DEFAULT_MIN = 5;

    private static final int DEFAULT_VALUE = 20;

    public static int compare(Category first, Category second){
        return getCategoryValue(first) - getCategoryValue(second);
    }

    private static int getCategoryValue(Category category){
        switch (category){
            case Rasvjeta: return 0;
            case Grijanje: return 1;
            case Klimatizacija: return 2;
            case Prozori: return 3;
            default: return 0;
        }
    }

    public static int getMaxValue(Category category){
        switch (category){
            case Grijanje: return GRIJANJE_MAX;
            case Klimatizacija: return KLIMATIZACIJA_MAX;
            default: return DEFAULT_MAX;
        }
    }

    public static int getMinValue(Category category){
        switch (category){
            case Grijanje: return GRIJANJE_MIN;
            case Klimatizacija: return KLIMATIZACIJA_MIN;
            default: return DEFAULT_MIN;
        }
    }

    public static int parseValue(int value, Category category){
        switch (category){
            case Grijanje: {
                if(value > GRIJANJE_MAX || value < GRIJANJE_MIN){
                    return DEFAULT_VALUE;
                }
                else return value;
            }
            case Klimatizacija: {
                if(value < KLIMATIZACIJA_MAX || value > KLIMATIZACIJA_MIN){
                    return DEFAULT_VALUE;
                }
                else return value;
            }
            default: return DEFAULT_VALUE;
        }
    }
}
