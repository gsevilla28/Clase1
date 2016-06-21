package idmexico.com.mx.clase1;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import javax.net.ssl.HandshakeCompletedEvent;

import Model.ModelUSer;
import idmexico.com.mx.clase1.Services.serviceTimer;
import idmexico.com.mx.clase1.util.PreferenceUtil;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText user;
    private EditText pwd;
    private View loading;
    private PreferenceUtil PrefenciaUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         user= (EditText) findViewById(R.id.username);
         pwd = (EditText) findViewById(R.id.pwd);

        findViewById(R.id.btnlogin).setOnClickListener(this);
        findViewById(R.id.btnRegiter_main).setOnClickListener(this);
        loading= findViewById(R.id.progress);


        PrefenciaUtil = new PreferenceUtil(getApplicationContext());

        CheckBox check = (CheckBox) findViewById(R.id.cheRemeber);

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(serviceTimer.TAG,"chequeo :" + isChecked);
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnlogin:
                processData();
                break;
            case R.id.btnRegiter_main:
                launcheRegister();
                break;
        }
    }

    private void launcheRegister() {
        startActivity(new Intent(getApplicationContext(),Register.class));

        /*otra forma de hacerlo*/
        //Intent intent = new Intent(getApplicationContext(),Register.class);
        //startActivity(intent);

    }

    private void processData() {
        String usuario = user.getText().toString();
        String pass = pwd.getText().toString();
        loading.setVisibility(View.VISIBLE);
         new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                loading.setVisibility(View.GONE);
            }
        },1000*4);
        ModelUSer ModeloUsuario = PrefenciaUtil.getUser();
        if (ModeloUsuario != null) {

            if (usuario.equals(ModeloUsuario.userName) && pass.equals(ModeloUsuario.password)) {
                Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getApplicationContext(),ActivityDetail.class);
                intent.putExtra("key_user",usuario);
                startActivity(intent);

                startService(new Intent(getApplicationContext(), serviceTimer.class));

            } else {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Falta Registro", Toast.LENGTH_SHORT).show();
        }
    }
}
