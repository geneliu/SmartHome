package ml.smart_ideas.smarthome.core;

import ml.smart_ideas.smarthome.db.Korisnik;

/**
 * Created by mario on 11.11.2015..
 */
public class Globals {

    //region Constructor
    private Globals() { }

    private static Globals _instance;

    public static Globals getInstance() {
        if (_instance == null)
            _instance = new Globals();
        return _instance;
    }
    //endregion

    //region Private Fields

    private Korisnik _korisnik;

    //endregion

    //region Public Properties

    public Korisnik getKorisnik(){
        return _korisnik;
    }
    public void setKorisnik(Korisnik korisnik){
        _korisnik = korisnik;
    }

    //endregion


}
