package ml.smart_ideas.smarthome.ws.rest;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

import ml.smart_ideas.smarthome.ws.model.Odgovor;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public class RestClient {
    private static loginInterface gitApiInterface ;
    //  private static String baseUrl = "http://192.168.1.2" ;
    private static String baseUrl = "http://yourlolfriends.com" ;
    public static loginInterface getClient() {
        Log.d("MainActivity", "Status Code = " + "dasdsd");
        if (gitApiInterface == null) {

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
            gitApiInterface = client.create(loginInterface.class);
        }
        return gitApiInterface ;
    }

    public interface loginInterface {

        // @Headers("User-Agent: Retrofit2.0Tutorial-App")
        @FormUrlEncoded
        @POST("/ServerAPI/Register.php")
        Call<Odgovor> register(
                //tu treba implementirati da se šalju podaci preko json formata jer je više fieldova.
        );
        @FormUrlEncoded
        @POST("/airAPI/login.php")
        Call<Odgovor> postWithFormParams(
                @Field("username") String username,
                @Field("lozinka") String lozinka
        );
    }
}