package davidtrigo.com.servicemanager;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by A8Alumno on 22/11/2017.
 */

public class StartedService extends Service {


    private final static String TAG = StartedService.class.getSimpleName();
    MediaPlayer mediaPlayer;
    private int counter = 0;

    @Override
    public void onCreate() {

        Log.i(TAG, "--> StartedService  onCreate()");

        super.onCreate();



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        counter++;
        Log.i(TAG, "--> StartedService  onStartCommand()counter= "+counter);


        // Create & init  nuevo  MediaPlayer
        if(mediaPlayer==null){
            mediaPlayer = MediaPlayer.create(this,R.raw.samba);
            mediaPlayer.start();

        //Pause MediaPlayer
        } else if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();

        // Reanudar MediaPlayer
        } else {

            mediaPlayer.start();

        }
        //return Service.START_STICKY;  Aunque lo cierres se volvera a iniciar

        return Service.START_NOT_STICKY;
        //    return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {

        Log.i(TAG, "--> StartedService  onDestroy()");
        if(mediaPlayer !=  null){

            mediaPlayer.stop();
            mediaPlayer.release();

        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
