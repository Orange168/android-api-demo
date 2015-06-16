package api.android.demo.broadcastreceiver;

import api.android.demo.R;
import api.android.demo.service.MyService;
import api.android.demo.uibase.ViewActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {


    @Override
	public void onReceive(Context context, Intent intent) {

        IBinder binder = peekService(context, new Intent(context, MyService.class));

        if (binder == null)
            Log.d("ServiceSSCCE.MyReceiver", "I just received a null binder.");
        else
            Log.d("ServiceSSCCE.MyReceiver", "Awesome, let's get this **** done!");

		// TODO Auto-generated method stub
		if(intent.getAction().equals("api.android.demo.HiMessage"))
		{
			NotificationManager notifyManager =
					(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);



			CharSequence tickerText = "tickerText";
			long when = System.currentTimeMillis();

			Notification notify = new Notification(R.mipmap.ic_launcher, tickerText, when);
			
			PendingIntent pi = PendingIntent.getActivity(context, 1233,
					new Intent(Intent.ACTION_DIAL,Uri.parse("tel:10086")), 0)  ;

			notify.setLatestEventInfo(context, "LatestEventInfo", intent.getStringExtra("message"), pi)  ;

			notifyManager.notify(11, notify) ;
            //adb shell am broadcast -a  api.android.demo.HiMessage
            //Broadcast completed: result=168, data="goAsync()", extras: Bundle[mParcelledData.dataSize=40]
            //Todo BroadcastReceiver trying to return result during a non-ordered broadcast
//            BroadcastReceiver.PendingResult result = goAsync() ;
//            Bundle b = new Bundle();
//            b.putString("Key","Value");
//            result.setResult(168, "goAsync()",b);
//            result.finish();

		}
	}

}
