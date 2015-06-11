package api.android.demo.broadcastreceiver;

import api.android.demo.R;
import api.android.demo.uibase.ViewActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class MyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
		if(arg1.getAction().equals("api.android.demo.HiMessage"))
		{
			NotificationManager notifyManager =
					(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
			

			CharSequence tickerText = "tickerText";
			long when = System.currentTimeMillis();

			Notification notify = new Notification(R.mipmap.ic_launcher, tickerText, when);
			
			PendingIntent pi = PendingIntent.getActivity(context, 1233,
					new Intent(Intent.ACTION_DIAL,Uri.parse("tel:10086")), 0)  ;

			notify.setLatestEventInfo(context, "LatestEventInfo", arg1.getStringExtra("message"), pi)  ;

			notifyManager.notify(11, notify) ;
		}
	}

}
