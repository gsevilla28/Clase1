package idmexico.com.mx.clase1;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.net.ssl.HandshakeCompletedEvent;

import Model.ModelUSer;
import idmexico.com.mx.clase1.Services.serviceTimer;
import idmexico.com.mx.clase1.sql.ItemDataSource;
import idmexico.com.mx.clase1.util.PreferenceUtil;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText user;
    private EditText pwd;
    private View loading;
    private CheckBox check;
    private int idusuario;
    private TextView txtnombre;

    private PreferenceUtil PrefenciaUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         user= (EditText) findViewById(R.id.username);
         pwd = (EditText) findViewById(R.id.pwd);
        txtnombre = (TextView) findViewById(R.id.txtviewNombre);

        findViewById(R.id.btnlogin).setOnClickListener(this);
        findViewById(R.id.btnRegiter_main).setOnClickListener(this);
        loading= findViewById(R.id.progress);

        PrefenciaUtil = new PreferenceUtil(getApplicationContext());
        ItemDataSource itemDataSource =  new ItemDataSource(getApplicationContext());

        check = (CheckBox) findViewById(R.id.cheRemeber);

        /*verificar si existe algun id guardado en shared preferences*/
        idusuario = PrefenciaUtil.getIdUser();
        if (idusuario>0){
            ModelUSer datos =itemDataSource.getUser(idusuario);
            txtnombre.setText(datos.Nombre);
            user.setText(datos.userName);
            pwd.setText(datos.password);
            check.setChecked(true);
        }
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

    private void processData() { //DoLogin
        String usuario = user.getText().toString();
        String pass = pwd.getText().toString();
        loading.setVisibility(View.VISIBLE);
         new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                loading.setVisibility(View.GONE);
            }
        },1000*4);

        /*ModelUSer ModeloUsuario = PrefenciaUtil.getUser(); se guardaba en shared preferences*/
        //obtener datos del usuario en BD local

        ItemDataSource itemDataSource = new ItemDataSource(getApplicationContext());

        ModelUSer ModeloUsuario = itemDataSource.ValidaUsuario(usuario,pass);


        if (ModeloUsuario.Nombre !=null) {

            if (usuario.equals(ModeloUsuario.userName) && pass.equals(ModeloUsuario.password)) {
                Toast.makeText(getApplicationContext(), R.string.msjOKLogin, Toast.LENGTH_SHORT).show();

                if (check.isChecked()){ /*guardar id usuario en preferencias*/
                    PrefenciaUtil.saveIDuser(ModeloUsuario.id_usuario);
                } else{
                    PrefenciaUtil.DeleteIdUser();
                }


                Intent intent= new Intent(getApplicationContext(),ActivityDetail.class);
                intent.putExtra("key_user",usuario);
                startActivity(intent);

                /**iniciar servicio para contar tiempo*/
                startService(new Intent(getApplicationContext(), serviceTimer.class));
                //finish();

            } else {
                Toast.makeText(getApplicationContext(), R.string.msjErrorLogin, Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.msjErrorRegistro, Toast.LENGTH_SHORT).show();
        }
    }
}
