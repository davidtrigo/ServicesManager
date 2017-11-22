package davidtrigo.com.servicemanager;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by A8Alumno on 22/11/2017.
 */

public class MyIntentService extends IntentService {

    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService() {
        super(MyIntentService.class.getSimpleName());
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        try {
            Log.i(MyIntentService.class.getSimpleName(), "-->Starting work.....");
            Thread.sleep(5000);
            Log.i(MyIntentService.class.getSimpleName(), "-->Work finished.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
