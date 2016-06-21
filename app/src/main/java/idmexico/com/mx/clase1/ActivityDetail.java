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

/**
 * Created by Alumno on 10/06/2016.
 */
public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {

    TextView txtTimer ;
    private BroadcastReceiver broadcastReceiver= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int counter = intent.getExtras().getInt("timer");
            txtTimer.setText(String.format("Session Length %s seconds", counter));

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //TextView txt = (TextView) findViewById(R.id.username_text);
        //String data=getIntent().getExtras().getString("key_user");
        //String usernamedetail=  String.format(getString(R.string.hello),data);

        //txt.setText(usernamedetail);

        //getFragmentManager().beginTransaction().replace(R.id.FragmentoHolder,new FragmentProfile()).commit();
        findViewById(R.id.btnFragmentA).setOnClickListener(this);
        findViewById(R.id.btnFragmentB).setOnClickListener(this);
        txtTimer = (TextView) findViewById(R.id.txtTimer);

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
        Log.d(serviceTimer.TAG,"REsume");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(serviceTimer.ACTION_SEND_TIMER);
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(serviceTimer.TAG,"PAuse");
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(serviceTimer.TAG,"Destroy");
        stopService(new Intent(getApplicationContext(),serviceTimer.class));
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

