package idmexico.com.mx.clase1.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Alumno on 18/06/2016.
 */
public class serviceTimer extends Service {
    int counter;
    private Handler handler = new Handler();
    public static final String ACTION_SEND_TIMER="mx.com.idmexico.Clase1.SEND_TIMER";

    public static final String TAG="clase1_tag";

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            counter++;
            handler.postDelayed(runnable,1000);
            Intent intent = new Intent(ACTION_SEND_TIMER);  /*quien escuche este string puede obtener los datos*/
            intent.putExtra("timer",counter);
            sendBroadcast(new Intent(intent));
            //Log.d(TAG,"contador: " + counter);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();


        Log.d(TAG,"Oncreate servicio");
        handler.post(runnable);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return  null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"reiniciando proceso");
        return START_NOT_STICKY; /*para reiniciar el sercicio y andorid lo mata*/
    }
}
