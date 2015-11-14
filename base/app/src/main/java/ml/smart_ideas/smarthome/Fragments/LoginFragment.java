package ml.smart_ideas.smarthome.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ml.smart_ideas.smarthome.R;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.core.MessageEventListener;
import ml.smart_ideas.smarthome.ws.connection.ServerCommunication;


public class LoginFragment extends Fragment  implements MessageEventListener{

    EditText ETusername;
    EditText ETpassword;
    TextView TVregister;

    TextView TVMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.login_fragment, container, false);

        ETusername = (EditText) viewInflater.findViewById(R.id.username);
        ETpassword = (EditText) viewInflater.findViewById(R.id.password);
        TVregister = (TextView) viewInflater.findViewById(R.id.txtRegister);
        Button BtnLogin = (Button) viewInflater.findViewById(R.id.btnLogin);

        TVMessage = (TextView) viewInflater.findViewById(R.id.TVMessage);
        TVMessage.setText("");


        ETpassword.setTransformationMethod(new PasswordTransformationMethod());



        BtnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });

        TVregister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register();
            }
        });

        Globals.getInstance().addMessageListener(this);

        //test
        ETusername.setText("test1");
        ETpassword.setText("test1");

        return viewInflater;
    }

    public void register() {
        //getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.fragment_container)).commit();

        RegistrationFragment registrationFragment = new RegistrationFragment();

        Globals.getInstance().ShowFragment(registrationFragment,true);

    }


    public void login() {
        String username = ETusername.getText().toString();
        String password = ETpassword.getText().toString();

        ServerCommunication.getInstance().loginToServer(username, password);
    }

    @Override
    public void ShowMessage(String message){
        //if(TVMessage != null)
            TVMessage.setText(message);
    }
}