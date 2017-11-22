package davidtrigo.com.servicemanager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private final static String TAG = StartedService.class.getSimpleName();
    private BoundService boundService;
    private boolean isBound = false;
    private TextView lblBoundService;


    // Interfaz comunicacion BoundService - Activity
    private ServiceConnection serviceConnection = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "--> BoundService  onServiceConnected()");
            boundService = new BoundService(service);
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "--> BoundService  onServiceConnected()");
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnStartedService).setOnClickListener(this);
        findViewById(R.id.btnBoundService).setOnClickListener(this);


        lblBoundService = findViewById(R.id.lblBoundService);
        lblBoundService.setOnClickListener(this);



        findViewById(R.id.btnIntentService).setOnClickListener(this);

    }

    @Override
    protected void onRestart() {
        super.onRestart();



    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "--> onStart startService");

        if (isBound) {
            startBoundService();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();



            if (isBound) {
                log(TAG, "--> onStop unbindService");
                unbindService(serviceConnection);
            }


    }

private void log (String tag , String message){

    if(  BuildConfig.DEBUG){

        Log.i(tag,message);

    }

}
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(boundService != null){
            Log.i(TAG, "--> onDestroy stopService");
            Intent  stopServiceIntent = new Intent(this, BoundService.class);
            stopService(stopServiceIntent);

        }


    }

    private void startedService() {

        Log.i(TAG, "--> StartedService  startedService()");

        Intent intent = new Intent(this, StartedService.class);
        startService(intent);

    }


    private void startBoundService() {

        Log.i(TAG, "--> StartedService  startBoundService()");

        Intent intentBoundService = new Intent(this, BoundService.class);
        startService(intentBoundService);
        bindService(intentBoundService, serviceConnection, Context.BIND_AUTO_CREATE);


    }


    private void getDataFromBoundService() {

        Log.i(TAG, "--> getDataFromBoundService()");
        //Obtener datos servicios

        if(isBound){
            //mostrar datos en mi TextView (lblBoundService)
            lblBoundService.setText(String.valueOf(boundService.getData()));

        }else{

            lblBoundService.setText(getString(R.string.no_data));
        }


    }

    private void startIntentService() {

        Log.i(TAG, "--> startIntentService()");

        Intent intent = new Intent(this,MyIntentService.class );
        startService(intent);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnStartedService:
                startedService();
                break;


            case R.id.btnBoundService:
                startBoundService();
                break;


            case R.id.lblBoundService:

                getDataFromBoundService();
                break;


            case R.id.btnIntentService:
                startIntentService();

                break;

        }
    }


}
