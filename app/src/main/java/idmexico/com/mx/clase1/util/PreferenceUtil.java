package idmexico.com.mx.clase1.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.Display;

import Model.ModelUSer;

/**
 * Created by Alumno on 17/06/2016.
 */
public class PreferenceUtil {

    private static final String File_Name="Preferences_Clase1";
    private final SharedPreferences sp;


    public PreferenceUtil(Context context) {
        sp = context.getSharedPreferences(File_Name,Context.MODE_PRIVATE);
    }
    /*metodo para guardar usuario y contraseña en shared preferences*/
    public void saveUser(ModelUSer mUser){
        //validar que nosea nul
        sp.edit().putString("user_name",mUser.userName).apply();
        sp.edit().putString("password",mUser.password).apply();
    }
    public ModelUSer getUser(){
        String mUser=sp.getString("user_name",null);
        String mPassword=sp.getString("password",null);

        if (TextUtils.isEmpty(mUser) || TextUtils.isEmpty(mPassword)) {
            return null;
        }
        return new ModelUSer(mUser,mPassword,"Default");

    }

}
