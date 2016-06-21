package idmexico.com.mx.clase1;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Model.ModelUSer;
import idmexico.com.mx.clase1.util.PreferenceUtil;

/**
 * Created by Alumno on 17/06/2016.
 */
public class Register extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        final EditText mUser = (EditText) findViewById(R.id.mUser_regiter);
        final EditText mPassword = (EditText) findViewById(R.id.mPassword_register);

        findViewById(R.id.btnRegisterUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUserName = mUser.getText().toString();
                String mPass = mPassword.getText().toString();
                //revisar si viene vacio
                PreferenceUtil util = new PreferenceUtil(getApplicationContext());

                util.saveUser(new ModelUSer(mUserName,mPass,"Default"));
                finish();



            }
        });


    }
}
