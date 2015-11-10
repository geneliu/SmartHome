package ml.smart_ideas.smarthome.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import butterknife.OnClick;
import ml.smart_ideas.smarthome.AfterLogin;
import ml.smart_ideas.smarthome.R;
import ml.smart_ideas.smarthome.ws.model.Odgovor;
import ml.smart_ideas.smarthome.ws.rest.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by mario on 10.11.2015..
 */
public class LoginFragment extends Fragment {


    public final static String EXTRA_MESSAGE = "ml.smart_ideas.smarthome";

    EditText ETusername;
    EditText ETpassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.login_fragment, container, false);

        //LinearLayout loginLayout = (LinearLayout) new Activity().getLayoutInflater().inflate(R.layout.login_fragment, null);
        ETusername = (EditText) viewInflater.findViewById(R.id.username);
        ETpassword = (EditText) viewInflater.findViewById(R.id.password);
        Button BtnLogin = (Button) viewInflater.findViewById(R.id.btnLogin);

        // ButterKnife nije radio za ovaj OnClickListener
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });

        return viewInflater;
    }




    public void login()
    {

        String user= ETusername.getText().toString();
        String pw= ETpassword.getText().toString();


        RestClient.loginInterface service= RestClient.getClient();
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

                    String o=result.getUsername();
                    Log.d("MainActivity", "username = " + o);
                    if(o!=null) {
                        String message="";

                        message="Pozdrav "+o;
                        Intent intent= new Intent(getContext(),AfterLogin.class);
                        intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);
                    }
                    else
                    {
                        String message="";
                        message="Krivi podaci";
                        Intent intent= new Intent(getContext(),AfterLogin.class);
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
    }
}