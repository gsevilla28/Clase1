package idmexico.com.mx.clase1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import idmexico.com.mx.clase1.Services.serviceTimer;
import idmexico.com.mx.clase1.util.PreferenceUtil;

/**
 * Created by Alumno on 10/06/2016.
 */
public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {

    TextView txtTimer, txtUltimaVez ;
    private PreferenceUtil PrefenciaUtil;

    private BroadcastReceiver broadcastReceiver= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int counter = intent.getExtras().getInt("timer");
            int i = PrefenciaUtil.getOnTime();

            txtTimer.setText(String.format("Session Length in seconds: %s", i !=0 ? i+counter:counter));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //getFragmentManager().beginTransaction().replace(R.id.FragmentoHolder,new FragmentProfile()).commit();
        findViewById(R.id.btnFragmentA).setOnClickListener(this);
        findViewById(R.id.btnFragmentB).setOnClickListener(this);
        txtTimer = (TextView) findViewById(R.id.txtTimer);
        txtUltimaVez = (TextView) findViewById(R.id.txtUltimaVez);

        PrefenciaUtil = new PreferenceUtil(getApplicationContext());

        String fecha = PrefenciaUtil.getLastSession() == null ? "NUNCA" : PrefenciaUtil.getLastSession();

        txtUltimaVez.setText(String.format(fecha, R.string.LastConnection));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnFragmentA:
                changeFragmentA();
                break;
            case R.id.btnFragmentB:
                changeFragmentB();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(serviceTimer.TAG,"Resume");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(serviceTimer.ACTION_SEND_TIMER);
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(serviceTimer.TAG,"Pause");
        unregisterReceiver(broadcastReceiver);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopService(new Intent(getApplicationContext(),serviceTimer.class));
        String counter= txtTimer.getText().toString().substring(txtTimer.getText().toString().indexOf(":")+2,txtTimer.getText().toString().length());

        /**guardar el tiempo de uso en preferencias */
        PrefenciaUtil.SaveOnTime(Integer.parseInt(counter));
        Log.d(serviceTimer.TAG,"Destroy counter in : " + counter);

        /**GUARDAR EL INICIO DE SESION */
        PrefenciaUtil.saveLastSession();

    }

    private void changeFragmentB() {
        //FragmentProfile f = FragmentProfile.newInstance("Adios");
        getFragmentManager().beginTransaction().replace(R.id.FragmentoHolder, new FragmentList()).commit();
    }

    private void changeFragmentA() {
        FragmentProfile f = FragmentProfile.newInstance("Hola Mundo");
        //getFragmentManager().beginTransaction().replace(R.id.FragmentoHolder, new FragmentList()).commit();
        getFragmentManager().beginTransaction().replace(R.id.FragmentoHolder, f).commit();
    }
}

