package ml.smart_ideas.smarthome.ws.connection;

import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.db.Korisnik;
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


    public void loginToServer(String username, String password){

        RestClient.loginInterface service = RestClient.getClient();
        Call<Odgovor> call = service.postWithFormParams(username, password);
        call.enqueue(new Callback<Odgovor>() {
            @Override
            public void onResponse(Response<Odgovor> response) {

                Log.d("MainActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    Odgovor result = response.body();
                    Log.d("MainActivity", "response = " + new Gson().toJson(result));

                    String stringUsername = result.getUsername();
                    Log.d("MainActivity", "username = " + stringUsername);

                    Globals.getInstance().ShowFragment(stringUsername);

                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    //endregion


}
