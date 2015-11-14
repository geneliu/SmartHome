package ml.smart_ideas.smarthome.Fragments;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import ml.smart_ideas.smarthome.HomeScreenActivity;
import ml.smart_ideas.smarthome.R;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.db.Korisnik;
import ml.smart_ideas.smarthome.ws.model.Odgovor;
import ml.smart_ideas.smarthome.ws.rest.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


/**
 * Created by mario on 10.11.2015..
 */
public class RegistrationFragment extends Fragment {

    public final static String EXTRA_MESSAGE = "ml.smart_ideas.smarthome";

    EditText ETname;
    EditText ETsurname;
    EditText ETusername;
    EditText ETpassword;
    EditText ETrepeatPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.registration_fragment, container, false);
        //getActivity().setTitle("SmartHome registracija");

        ETname = (EditText) viewInflater.findViewById(R.id.name);
        ETsurname = (EditText) viewInflater.findViewById(R.id.surname);
        ETusername = (EditText) viewInflater.findViewById(R.id.username);
        ETpassword = (EditText) viewInflater.findViewById(R.id.password);
        ETrepeatPassword = (EditText) viewInflater.findViewById(R.id.repeatPassword);
        Button BtnRegister = (Button) viewInflater.findViewById(R.id.btnRegister);

        ETpassword.setTransformationMethod(new PasswordTransformationMethod());
        ETrepeatPassword.setTransformationMethod(new PasswordTransformationMethod());

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register();
            }
        });

        return viewInflater;

    }

    public void register() {
        String name = ETname.getText().toString();
        String surname = ETsurname.getText().toString();
        String user = ETusername.getText().toString();
        String pw = ETpassword.getText().toString();
        String rpw = ETrepeatPassword.getText().toString();

        /*

        RestClient.loginInterface service = RestClient.getClient();
        //     Call<Odgovor> call = service.postWithJson(new LoginInfo("test1", "test1"));
        Call<Odgovor> call = service.postWithFormParams(user, pw);
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
                    if (stringUsername != null) {
                        String message = "";

                        Globals.getInstance().setKorisnik(new Korisnik(stringUsername, "Ime", "Prezime"));

                        message = "Pozdrav " + stringUsername;
                        Intent intent = new Intent(getContext(), HomeScreenActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);
                    } else {
                        String message = "";
                        message = "Krivi podaci";
                        Intent intent = new Intent(getContext(), HomeScreenActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);
                    }


                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        */
    }


}