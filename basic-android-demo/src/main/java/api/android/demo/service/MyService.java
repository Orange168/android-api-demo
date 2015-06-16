package api.android.demo.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;

/**
 * Created by Edward Lin
 * on 6/12/15 8:31 AM.
 */
public class MyService extends Service {

    static class MyHandler extends Handler {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        Log.d("ServiceSSCCE.MyService", "I've been bound.");
        return new Messenger(new MyHandler()).getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("ServiceSSCCE.MyService", "I've been unbound.");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d("ServiceSSCCE.MyService", "I've been rebound.");
        super.onRebind(intent);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("onStartCommand", "I've been onStartCommand.");
        Notification notification = new Notification.Builder(this)
                .setTicker("Bla bla...")
                .setContentTitle("Title...")
                .setContentText("Text...").build();

        startForeground(1234, notification);

        return super.onStartCommand(intent, flags, startId);
    }
}
