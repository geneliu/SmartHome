package ml.smart_ideas.smarthome.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ml.smart_ideas.smarthome.R;
import ml.smart_ideas.smarthome.core.enums.AppStateEnum;
import ml.smart_ideas.smarthome.core.enums.FragmentEnum;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.eventlisteners.MessageEventListener;
import ml.smart_ideas.smarthome.ws.connection.ServerCommunication;
import ml.smart_ideas.smarthome.ws.rest.Utils;


public class TestClass extends Fragment implements MessageEventListener {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.test_class, container, false);
        ButterKnife.bind(this, viewInflater);
        Button btn= (Button) viewInflater.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // addHouse(); //temp
                String hello="hallo";
                ServerCommunication.getInstance().SynchronizeDataWithServer();
                hello+="dudo";
            }
        });


        return viewInflater;
    }


    @Override
    public void ShowMessage(String message) {

    }
}