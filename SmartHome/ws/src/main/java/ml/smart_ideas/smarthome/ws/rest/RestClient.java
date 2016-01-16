package ml.smart_ideas.smarthome.ws.rest;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

import ml.smart_ideas.smarthome.ws.model.UserModel;
import ml.smart_ideas.smarthome.ws.model.ReplyModel;
import ml.smart_ideas.smarthome.ws.model.synchronization.UserData;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public class RestClient {
    private static LoginInterface loginInterface;
    private static RegistrationInterface registrationInterface;
    private static SynchronizationInterface synchronizationInterface;
    private static String baseUrl = "http://smart-ideas.ml/" ;
    public static LoginInterface getLoginClient() {
        if (loginInterface == null ||
                Utils.getInstance().isHomeServer() != Utils.getInstance().isLastConnectionHome()) {

            Utils.getInstance().setLastConnectionHome(Utils.getInstance().isHomeServer());

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            loginInterface = client.create(LoginInterface.class);
        }
        return loginInterface;
    }
    public static RegistrationInterface getRegistrationnClient() {
        if (registrationInterface == null ||
                Utils.getInstance().isHomeServer() != Utils.getInstance().isLastConnectionHome()) {

            Utils.getInstance().setLastConnectionHome(Utils.getInstance().isHomeServer());

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            registrationInterface = client.create(RegistrationInterface.class);
        }
        return registrationInterface;
    }

    public static SynchronizationInterface sendDataToServer()
    {
        if (synchronizationInterface == null ||
                Utils.getInstance().isHomeServer() != Utils.getInstance().isLastConnectionHome()) {

            Utils.getInstance().setLastConnectionHome(Utils.getInstance().isHomeServer());

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            synchronizationInterface = client.create(SynchronizationInterface.class);
        }
        return synchronizationInterface;
    }

    private static String getBaseUrl(){
        String baseUrl = "";
        if(Utils.getInstance().isHomeServer()) {
            String currentIP = Utils.getIPAddress(true);
            if (currentIP.contains("1.2.3"))
                baseUrl = "http://1.2.3.28/";
            else
                baseUrl =  "http://smart-ideas.asuscomm.com/";
        }
        else
            baseUrl = "http://smart-ideas.ml/";
        Log.d("RestClient","Connecting using: " + baseUrl);
        return baseUrl;
    }

    public interface LoginInterface {

        @FormUrlEncoded
        @POST("/login.php")
        Call<UserModel> login(
                @Field("username") String username,
                @Field("password") String password
        );
    }
    public interface RegistrationInterface {

        @POST("/register.php")
        Call<ReplyModel> register(@Body UserModel user);
    }
    public interface SynchronizationInterface{

        @POST("/getJson.php")
        Call<UserData> CheckForChanges(@Body UserData userData);
    }



}