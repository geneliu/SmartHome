package ml.smart_ideas.smarthome.ws.rest;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

import ml.smart_ideas.smarthome.ws.model.UserModel;
import ml.smart_ideas.smarthome.ws.model.ReplyModel;
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
    private static String baseUrl = "http://smart-ideas.ml/" ;
    public static LoginInterface getLoginClient() {
        if (loginInterface == null) {

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            loginInterface = client.create(LoginInterface.class);
        }
        return loginInterface;
    }
    public static RegistrationInterface getRegistrationnClient() {
        if (registrationInterface == null) {

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            registrationInterface = client.create(RegistrationInterface.class);
        }
        return registrationInterface;
    }

    public interface LoginInterface {

        @FormUrlEncoded
        @POST("/new/login.php")
        Call<UserModel> login(
                @Field("username") String username,
                @Field("password") String password
        );
    }
    public interface RegistrationInterface {

        @POST("/new/register.php")
        Call<ReplyModel> register(@Body UserModel user);
    }
}