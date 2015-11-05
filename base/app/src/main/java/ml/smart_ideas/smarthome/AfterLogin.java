package ml.smart_ideas.smarthome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AfterLogin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        Intent intent=getIntent();
        String message= intent.getStringExtra(InitialActivity.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.postLoginText);
        textView.setText(message);

    }
}