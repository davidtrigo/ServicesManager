package davidtrigo.com.servicemanager;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * Created by A8Alumno on 22/11/2017.
 */

public class BoundService extends Service {

    private final static String TAG = StartedService.class.getSimpleName();
    private IBinder service = new Binder();

    public BoundService() {
    }

    public BoundService(IBinder service) {

        Log.i(TAG, "--> BoundService  Consrtuctor de la clase");
        this.service = service;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "--> StartedService  onCreate()");


        super.onCreate();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "--> StartedService  onBind()");

        return service;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "--> StartedService  onUnbind()");

        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "--> StartedService  onRebind()");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "--> StartedService  onDestroy()");

        super.onDestroy();
    }


    public int getData(){
        Random random = new Random();

        return random.nextInt(100);
    }
}
