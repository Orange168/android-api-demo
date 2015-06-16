package api.android.demo.broadcastreceiver;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import api.android.demo.service.MyService;

public class SendBroadcastActivity extends Activity {

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {

            Log.d("ServiceConnection", "SendBroadcastActivity ==>>Service disconnected.");

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("ServiceConnection", "SendBroadcastActivity==>>Service connected.");
        }
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO adb shell am broadcast -a api.android.demo.HiMessage
		super.onCreate(savedInstanceState);
		LinearLayout lin = new LinearLayout(this) ;
		lin.setOrientation(LinearLayout.VERTICAL)  ;
		Button button1 = new Button(this) ;
		button1.setText("SendBroadcast") ;
        Button peekServerButton = new Button(this) ;
        peekServerButton.setText("scheduleAlarm") ;
        peekServerButton.setOnClickListener(scheduleAlarm);

		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("api.android.demo.HiMessage");
		registerReceiver(mBroadcastReceiver, intentFilter) ;

        button1.setOnClickListener(onClickListener)  ;
		
		lin.addView(button1) ;
        lin.addView(peekServerButton);
        setContentView(lin) ;
	}


    @Override
    protected void onResume() {
        startService(new Intent(this, MyService.class));
        bindService(new Intent(this, MyService.class), mConnection, 0);
        super.onResume();

    }

    @Override
    protected void onStop() {
        unbindService(mConnection);
        super.onStop();

    }

    View.OnClickListener scheduleAlarm = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("SendBroadcastActivity","scheduleAlarm") ;
            AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            PendingIntent pi = PendingIntent.getBroadcast(SendBroadcastActivity.this,
                    101, new Intent(SendBroadcastActivity.this, PeekServiceRecieve.class), 0);
            am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000, pi);
        }
    };

    OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setAction("api.android.demo.HiMessage");
            intent.putExtra("message", "from SendBroadcastActivity");
            SendBroadcastActivity.this.sendBroadcast(intent);
        }

    };


	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mBroadcastReceiver);
	}

	BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(context,"Receive from registerReceiver", Toast.LENGTH_LONG).show();
		}
	} ;

}
