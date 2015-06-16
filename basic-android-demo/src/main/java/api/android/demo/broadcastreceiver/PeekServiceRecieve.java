package api.android.demo.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import api.android.demo.service.MyService;

/**
 * Created by Edward Lin
 * on 6/13/15 8:35 PM.
 */
public class PeekServiceRecieve extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        IBinder binder = peekService(context, new Intent(context, MyService.class));

        if (binder == null)
            Log.d("ServiceSSCCE.MyReceiver", "I just received a null binder.");
        else
            Log.d("ServiceSSCCE.MyReceiver", "Awesome, let's get this **** done!");
    }
}
