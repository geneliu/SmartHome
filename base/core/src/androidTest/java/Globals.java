/**
 * Created by mario on 11.11.2015..
 */
public class Globals {

    //region Constructor
    private Globals() { }

    private Globals _instance;

    public Globals getInstance() {
        if (_instance == null)
            _instance = new Globals();
        return _instance;
    }
    //endregion


}
