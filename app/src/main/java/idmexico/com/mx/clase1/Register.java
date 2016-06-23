package idmexico.com.mx.clase1;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.ModelUSer;
import idmexico.com.mx.clase1.sql.ItemDataSource;
import idmexico.com.mx.clase1.util.PreferenceUtil;

/**
 * Created by Alumno on 17/06/2016.
 */
public class Register extends AppCompatActivity {

    private EditText mUser;
    private EditText mPassword;
    private EditText mNombreUsuario;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        mUser = (EditText) findViewById(R.id.mUser_regiter);
        mPassword = (EditText) findViewById(R.id.mPassword_register);
        mNombreUsuario = (EditText) findViewById(R.id.txtNombreUsuario);

        findViewById(R.id.btnRegisterUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUserName = mUser.getText().toString();
                String mPass = mPassword.getText().toString();
                String mNombre = mNombreUsuario.getText().toString();

                /**validacion de los campos antes de registrar*/

                if (!TextUtils.isEmpty(mNombre)) {
                    if (!TextUtils.isEmpty(mUserName)) {
                        if (!TextUtils.isEmpty(mPass)) {

                            ItemDataSource dataSource = new ItemDataSource(getApplicationContext());
                            if(dataSource.SaveUser(new ModelUSer(0,mUserName, mPass, mNombre))) {  /*insertar registro en B.D. Local*/
                                Toast.makeText(getApplicationContext(), "Registro Exitoso!!", Toast.LENGTH_SHORT).show();
                                ClearControls();
                                finish(); /*terminar la actividad [cerrar form]*/
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Intente mas tarde...", Toast.LENGTH_SHORT).show();
                                ClearControls();
                            }
                            //PreferenceUtil util = new PreferenceUtil(getApplicationContext());
                            //util.saveUser(new ModelUSer(mUserName,mPass,"Default"));
                            //finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "El password no puede ir vacio", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "El usuario no puede ir vacio", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Es necesario agregar un nombre", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void ClearControls (){
        mUser.setText("");
        mPassword.setText("");
        mNombreUsuario.setText("");
    }

}
