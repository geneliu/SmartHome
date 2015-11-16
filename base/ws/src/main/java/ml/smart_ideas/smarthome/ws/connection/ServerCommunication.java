package ml.smart_ideas.smarthome.ws.connection;

import android.util.Log;

import com.google.gson.Gson;

import ml.smart_ideas.smarthome.core.Enums.AppStateEnum;
import ml.smart_ideas.smarthome.core.Enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.Fragmenti.PrikazKucaFragment;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.db.Korisnik;
import ml.smart_ideas.smarthome.ws.model.NoviKorisnik;
import ml.smart_ideas.smarthome.ws.model.Odgovor;
import ml.smart_ideas.smarthome.ws.rest.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by mario on 14.11.2015..
 */
public class ServerCommunication {

    //region Constructor

    private ServerCommunication() {
    }

    private static ServerCommunication instance;

    public static ServerCommunication getInstance() {
        if (instance == null)
            instance = new ServerCommunication();
        return instance;
    }

    //endregion

    //region Methods

    public void loginToServer(final String username, String password) {

        Log.d("ServerCommunication", "Login connection starting...");
        Globals.getInstance().ShowMessage("");
        RestClient.LoginInterface service = RestClient.getClient();
        Call<NoviKorisnik> call = service.login(username, password);
        call.enqueue(new Callback<NoviKorisnik>() {
            @Override
            public void onResponse(Response<NoviKorisnik> response) {

                Log.d("ServerCommunication", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    NoviKorisnik result = response.body();
                    Log.d("ServerCommunication", "response = " + new Gson().toJson(result));

                    String stringUsername = result.getUsername();
                    String error = result.getError();

                    Globals.getInstance().ShowMessage("");
                    if (error.compareTo("false") == 0) {
                        Korisnik korisnik = Korisnik.checkExistingKorisnik(username);
                        if (korisnik == null){
                            Globals.getInstance().setKorisnik(stringUsername,
                                    result.getPassword(),
                                    result.getIme(),
                                    result.getPrezime());
                            Log.d("ServerCommunication", "Created new local user.");
                        }
                        else {
                            Globals.getInstance().setKorisnik(korisnik);
                        }
                        Log.d("ServerCommunication", "User "+korisnik.getUsername()+" successfully signed in.");
                        Globals.getInstance().ShowFragment(FragmentEnum.PrikazKucaFragment, true);
                    } else {
                        Globals.getInstance().ShowMessage("Neispravno korisničko ime i/ili lozinka.");
                    }


                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.d("ServerCommunication", "Response is not successful");
                    Globals.getInstance().setAppStateEnum(AppStateEnum.NotSignedIn);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("ServerCommunication", "No response from login server");
                Globals.getInstance().ShowMessage("Poslužitelj za prijavu ne odgovara.");
                Globals.getInstance().setAppStateEnum(AppStateEnum.NotSignedIn);
            }
        });
    }

    //endregion

    public void registerOnServer(String name, String surname, final String username, String password) {

        Log.d("ServerCommunication","Registration connection starting...");
        Globals.getInstance().ShowMessage("");
        NoviKorisnik noviKorisnik = new NoviKorisnik(name, surname, username, password);
        RestClient.LoginInterface service = RestClient.getClient();
        Call<Odgovor> call = service.register(noviKorisnik);
        call.enqueue(new Callback<Odgovor>() {
            @Override
            public void onResponse(Response<Odgovor> response) {

                Log.d("ServerCommunication", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    Odgovor result = response.body();
                    String error = result.getError();
                    Log.d("ServerCommunication", "response = error:" + new Gson().toJson(error));

                    Globals.getInstance().ShowMessage("");
                    if (error.compareTo("exist") == 0) {
                        Globals.getInstance().ShowMessage("Korisnik " + username + " već postoji.");
                    } else if (error.compareTo("false") == 0) {
                        Globals.getInstance().ShowMessage("Korisnik " + username + " uspješno registriran.");
                    } else {
                        Globals.getInstance().ShowMessage("Dogodila se greška kod registracije.");
                    }


                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.d("ServerCommunication", "Response is not successful");
                    Globals.getInstance().setAppStateEnum(AppStateEnum.NotSignedIn);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("ServerCommunication", "No response from login server");
                Globals.getInstance().ShowMessage("Poslužitelj za registraciju ne odgovara.");
                Globals.getInstance().setAppStateEnum(AppStateEnum.NotSignedIn);
            }
        });
    }

}
