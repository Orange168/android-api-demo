package api.android.demo.service;

import api.android.demo.R;
import api.android.demo.configuration.CommonConstants;
import api.android.demo.service.BeginService.MyBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BeginServiceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_begservice)  ;
		Button buttonStart = (Button)findViewById(R.id.buttonStartBeginService)  ;
		buttonStart.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {

				Intent _intent = new Intent(BeginServiceActivity.this,BeginService.class) ;
				startService(_intent)  ;
			}
			
		}) ;
		
		//Bind
		Button buttonBind= (Button)findViewById(R.id.buttonBindBeginService)  ;
		buttonBind.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {

				Intent _intent = new Intent(BeginServiceActivity.this,BeginService.class) ;
				bindService(_intent,conn,Context.BIND_AUTO_CREATE)  ;
			}
			
		}) ;
		
		//Start Intent Service
		Button buttonIntent= (Button)findViewById(R.id.buttonStartIntentService)  ;
		buttonIntent.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {

				Log.i(CommonConstants.LOGCAT_TAG_NAME,"Activity Threadid="+Thread.currentThread().getId())  ;
				Intent _intent2 = new Intent(BeginServiceActivity.this,BeginIntentService.class) ;
				startService(_intent2) ;
			}
			
		}) ;
		
	}
	
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {

			MyBinder binder = (MyBinder)service;
            BeginService bindService = binder.getService();
            bindService.MyMethod();
           
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

			
		}
        
      
    };

}
