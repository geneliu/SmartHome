package ml.smart_ideas.smarthome.fragments;

import android.app.Fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.method.PasswordTransformationMethod;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ml.smart_ideas.smarthome.R;

import ml.smart_ideas.smarthome.core.MessageEventListener;
import ml.smart_ideas.smarthome.core.Globals;
import ml.smart_ideas.smarthome.ws.connection.ServerCommunication;


public class RegistrationFragment extends Fragment implements MessageEventListener {


    EditText ETname;
    EditText ETsurname;
    EditText ETusername;
    EditText ETpassword;
    EditText ETrepeatPassword;
    TextView TVMessage;

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
        TVMessage = (TextView) viewInflater.findViewById(R.id.TVMessage);
        Button BtnRegister = (Button) viewInflater.findViewById(R.id.btnRegister);


        //ETpassword.setTransformationMethod(new PasswordTransformationMethod());
        //ETrepeatPassword.setTransformationMethod(new PasswordTransformationMethod());
        TVMessage.setText("");

        Globals.getInstance().ShowTitle(Globals.getInstance().getContext().getString(R.string.registration_fragment_title));

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register();
            }
        });

        Globals.getInstance().addMessageListener(this);

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion < android.os.Build.VERSION_CODES.LOLLIPOP){
            BtnRegister.setBackgroundColor(ContextCompat.getColor(Globals.getInstance().getContext(), R.color.white));
        }

        //test
        ETname.setText("Probni");
        ETsurname.setText("Robot");
        ETusername.setText("test1");
        ETpassword.setText("test1");
        ETrepeatPassword.setText("test1");

        return viewInflater;

    }

    public void register() {
        String name = ETname.getText().toString();
        String surname = ETsurname.getText().toString();
        String user = ETusername.getText().toString();
        String pw = ETpassword.getText().toString();
        String rpw = ETrepeatPassword.getText().toString();
        
        if(pw.equals(rpw)) {
            ServerCommunication.getInstance().registerOnServer(name, surname, user, pw);
        }
        else {
            ShowMessage(Globals.getInstance().getContext().getString(R.string.pass_no_match));
        }
    }

    @Override
    public void ShowMessage(String message){
        if(TVMessage != null)
            TVMessage.setText(message);
    }



}