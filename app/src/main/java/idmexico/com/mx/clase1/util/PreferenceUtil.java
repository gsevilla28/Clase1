package idmexico.com.mx.clase1.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;

import java.text.SimpleDateFormat;
import java.util.Date;

import Model.ModelUSer;
import idmexico.com.mx.clase1.Services.serviceTimer;

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
        //validar que nosea null
        sp.edit().putString("user_name",mUser.userName).apply();
        sp.edit().putString("password",mUser.password).apply();
    }

    public void saveIDuser(int id){
        sp.edit().putInt("idusuario",id).apply();
    }
    public int getIdUser(){
        return sp.getInt("idusuario",0);
    }
    public void DeleteIdUser(){
        sp.edit().remove("idusuario").apply();
    }

    public ModelUSer getUser(){
        String mUser=sp.getString("user_name",null);
        String mPassword=sp.getString("password",null);

        if (TextUtils.isEmpty(mUser) || TextUtils.isEmpty(mPassword)) {
            return null;
        }
        return new ModelUSer(0,mUser,mPassword,"Default");
    }

    public void SaveOnTime (int time){
        sp.edit().putInt("TimeSession",time).apply();
    }
    public int getOnTime (){
        return sp.getInt("TimeSession",0);
    }

    public void saveLastSession(){
        Date date  = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaHoy = sdf.format(date);

        sp.edit().putString("UltimaVez",fechaHoy).apply();
        Log.d(serviceTimer.TAG,"ultimo inicio de session: " + fechaHoy);
    }
    public String getLastSession(){

        return sp.getString("UltimaVez",null);
    }
}
