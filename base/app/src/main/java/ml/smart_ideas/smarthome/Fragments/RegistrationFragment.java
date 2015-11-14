package ml.smart_ideas.smarthome.Fragments;


import android.app.Fragment;


import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import ml.smart_ideas.smarthome.R;

import ml.smart_ideas.smarthome.ws.connection.ServerCommunication;



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

        ServerCommunication.getInstance().registerOnServer(name, surname, user, pw);
    }


}