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


public class LoginFragment extends Fragment implements MessageEventListener {

    @Bind(R.id.username)
    EditText ETusername;
    EditText ETpassword;
    TextView TVregister;
    Button btnLogin;

    TextView TVMessage;

    RadioButton rbHomeServer;
    RadioButton rbRemoteServer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, viewInflater);

        ETpassword = (EditText) viewInflater.findViewById(R.id.password);
        TVregister = (TextView) viewInflater.findViewById(R.id.txtRegister);
        btnLogin = (Button) viewInflater.findViewById(R.id.btnLogin);

        rbHomeServer = (RadioButton) viewInflater.findViewById(R.id.rbHomeServer);
        rbRemoteServer = (RadioButton) viewInflater.findViewById(R.id.rbRemoteServer);

        if(Utils.getInstance().isHomeServer())
            rbHomeServer.setChecked(true);
        else
            rbRemoteServer.setChecked(true);


        TVMessage = (TextView) viewInflater.findViewById(R.id.TVMessage);
        TVMessage.setText("");


        //ETpassword.setTransformationMethod(new PasswordTransformationMethod());


        Globals.getInstance().ShowTitle(Globals.getInstance().getContext().getString(R.string.login_fragment_title));
        Globals.getInstance().setAppStateEnum(AppStateEnum.NotSignedIn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });

        TVregister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToRegistration();
            }
        });

        Globals.getInstance().addMessageListener(this);

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion < android.os.Build.VERSION_CODES.LOLLIPOP) {
            btnLogin.setBackgroundColor(ContextCompat.getColor(Globals.getInstance().getContext(), R.color.white));
        }


        rbHomeServer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    Utils.getInstance().setHomeServer(true);
                    Log.d("LoginFragment", "Home Server");
                }
            }
        });
        rbRemoteServer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    Utils.getInstance().setHomeServer(false);
                    Log.d("LoginFragment", "Remote Server");
                }
            }
        });

        //test
        ETusername.setText("test1");
        ETpassword.setText("test1");

        return viewInflater;
    }

    public void goToRegistration() {
        Globals.getInstance().ShowFragment(FragmentEnum.RegistrationFragment, true);
    }


    public void login() {
        String username = ETusername.getText().toString();
        String password = ETpassword.getText().toString();

        if (Globals.getInstance().getAppStateEnum() == AppStateEnum.LoggingIn) {
            Globals.getInstance().ShowMessage(Globals.getInstance().getContext().getString(R.string.login_in_progress));
            Globals.getInstance().setAppStateEnum(AppStateEnum.NotSignedIn);
        } else {
            Globals.getInstance().setAppStateEnum(AppStateEnum.LoggingIn);

            ServerCommunication.getInstance().loginToServer(username, password);
        }
    }

    @Override
    public void ShowMessage(String message) {
        //if(TVMessage != null)
        TVMessage.setText(message);
    }
}