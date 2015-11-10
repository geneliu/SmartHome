package ml.smart_ideas.smarthome;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ml.smart_ideas.smarthome.ws.model.Odgovor;
import ml.smart_ideas.smarthome.ws.rest.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class InitialActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "ml.smart_ideas.smarthome";

    String user="";
    String pw="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_initial, menu);

        getSupportActionBar().setTitle(R.string.app_view_name);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Pozivanje web servisa i prelazak na novi activity
    @OnClick(R.id.btnLogin)
    public void login()
    {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        user= username.getText().toString();
        pw= password.getText().toString();


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
                        Intent intent= new Intent(InitialActivity.this,AfterLogin.class);
                        intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);
                    }
                    else
                    {
                        String message="";
                        message="Krivi podaci";
                        Intent intent= new Intent(InitialActivity.this,AfterLogin.class);
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
