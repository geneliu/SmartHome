package ml.smart_ideas.smarthome.ws.connection;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import ml.smart_ideas.smarthome.core.enums.ActivityEnum;
import ml.smart_ideas.smarthome.core.enums.AppStateEnum;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.db.User;
import ml.smart_ideas.smarthome.ws.Helpers.DataParser;
import ml.smart_ideas.smarthome.ws.R;
import ml.smart_ideas.smarthome.ws.model.UserModel;
import ml.smart_ideas.smarthome.ws.model.ReplyModel;
import ml.smart_ideas.smarthome.ws.model.synchronization.UserData;
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

    //region Private Fields


    //endregion

    //region Public Properties

    public Context getContext() {
        return Globals.getInstance().getContext();
    }

    public String getString(int stringId) {
        return getContext().getString(stringId);
    }

    //endregion

    //region Methods

    public void loginToServer(final String username, String password) {

        Log.d("ServerCommunication", "Login connection starting...");
        Globals.getInstance().ShowMessage("");
        RestClient.LoginInterface service = RestClient.getLoginClient();
        Call<UserModel> call = service.login(username, password);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Response<UserModel> response) {

                Log.d("ServerCommunication", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // (status code 200, 201)
                    UserModel userModelResponse = response.body();
                    Log.d("ServerCommunication", "response = " + new Gson().toJson(userModelResponse));

                    String stringUsername = userModelResponse.getUsername();
                    String error = userModelResponse.getError();

                    Globals.getInstance().ShowMessage("");
                    if (error.compareTo(getString(R.string.server_reply_no_error)) == 0) {
                        User user = User.checkExistingUser(username);
                        if (user == null) {
                            Globals.getInstance().setUser(stringUsername,
                                    userModelResponse.getPassword(),
                                    userModelResponse.getName(),
                                    userModelResponse.getSurname());
                            Log.d("ServerCommunication", "Created new local user.");
                        } else {
                            Globals.getInstance().setUser(user);
                        }
                        Log.d("ServerCommunication", "User " + stringUsername + " successfully signed in.");
                        //  Globals.getInstance().ShowFragment(FragmentEnum.HousesFragment, true);
                        Globals.getInstance().ShowActivity(ActivityEnum.MainActivity);
                    } else {
                        Globals.getInstance().ShowMessage(getString(R.string.server_login_failed));
                    }


                } else {
                    // (like 400,401,403 etc)
                    Log.d("ServerCommunication", "Response is not successful");
                    Globals.getInstance().setAppStateEnum(AppStateEnum.NotSignedIn);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("ServerCommunication", "No response from login server");
                Globals.getInstance().ShowMessage(getString(R.string.server_login_no_reply));
                Globals.getInstance().setAppStateEnum(AppStateEnum.NotSignedIn);
            }
        });
    }

    //endregion

    public void registerOnServer(String name, String surname, final String username, String password) {

        Log.d("ServerCommunication", "Registration connection starting...");
        Globals.getInstance().ShowMessage("");
        UserModel userModel = new UserModel(name, surname, username, password);
        RestClient.RegistrationInterface service = RestClient.getRegistrationnClient();
        Call<ReplyModel> call = service.register(userModel);
        call.enqueue(new Callback<ReplyModel>() {
            @Override
            public void onResponse(Response<ReplyModel> response) {

                Log.d("ServerCommunication", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // (status code 200, 201)
                    ReplyModel result = response.body();
                    String error = result.getError();
                    Log.d("ServerCommunication", "response = error:" + new Gson().toJson(error));

                    Globals.getInstance().ShowMessage("");
                    if (error.compareTo(getString(R.string.server_reply_error_registration_exists)) == 0) {
                        Globals.getInstance().ShowMessage(getString(R.string.user) + " " + username + " " + getString(R.string.already_exists));
                    } else if (error.compareTo(getString(R.string.server_reply_no_error)) == 0) {
                        Globals.getInstance().ShowMessage(getString(R.string.user) + " " + username + " " + getString(R.string.successfully_registered));
                    } else {
                        Globals.getInstance().ShowMessage(getString(R.string.error_registration_message));
                    }


                } else {
                    // (like 400,401,403 etc)
                    Log.d("ServerCommunication", "Response is not successful");
                    Globals.getInstance().setAppStateEnum(AppStateEnum.NotSignedIn);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("ServerCommunication", "No response from login server");
                Globals.getInstance().ShowMessage(getString(R.string.server_registration_no_reply));
                Globals.getInstance().setAppStateEnum(AppStateEnum.NotSignedIn);
            }
        });
    }

    public void SynchronizeDataFromServer(){
        SynchronizeDataFromServer(Globals.getInstance().getUser(), false);
    }

    public void SynchronizeDataFromServer(final User user, final boolean isAutoSync)
    {
        UserData userData= DataParser.userDataToCompleteJsonObject(user);
        RestClient.SynchronizationInterface service = RestClient.sendDataToServer();
        Call<UserData> call = service.CheckForChanges(userData);

        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Response<UserData> response) {

                Log.d("ServerCommunication", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // (status code 200, 201)
                    UserData result = response.body();
                    String error = result.getError();
                    if(error != "true")
                    {
                        DataParser.jsonObjectToDatabase(user,result);
                        if(isAutoSync == false)
                            Globals.getInstance().SyncData();
                    }

                } else {
                    // (like 400,401,403 etc)
                    String hello="hello";
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }



    private void setAppStateEnum(AppStateEnum appStateEnum) {
        Globals globals = Globals.getInstance();
        if (appStateEnum == AppStateEnum.NotSignedIn) {
            if (globals.getAppStateEnum() == AppStateEnum.LoggingIn
                    || globals.getAppStateEnum() == AppStateEnum.NotSignedIn) {
                globals.setAppStateEnum(appStateEnum);
            }
        } else {
            globals.setAppStateEnum(appStateEnum);
        }
    }

}
