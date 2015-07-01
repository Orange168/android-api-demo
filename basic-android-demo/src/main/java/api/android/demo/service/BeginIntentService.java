package api.android.demo.service;

import api.android.demo.configuration.CommonConstants;
import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BeginIntentService extends IntentService {

	public BeginIntentService() {
		super("sundy");
	}

	@Override
	public void onCreate() {
		//    
		super.onCreate();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "IntentService oncreate") ;
	}

	@Override
	public IBinder onBind(Intent intent) {
		//    
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onBind") ;
		return super.onBind(intent);
	}

	@Override
	public void onDestroy() {
		  
		super.onDestroy();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "IntentService Destory") ;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//    
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "StartCommand") ;
		return super.onStartCommand(intent, flags, startId);
		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
        Log.i(CommonConstants.LOGCAT_TAG_NAME, "onHandleIntent") ;
		for(int i=0;i<20;i++)
		{
			try {
				Thread.sleep(1000) ;
				Log.i(CommonConstants.LOGCAT_TAG_NAME, "Starting IntentService :"+i) ;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Log.i(CommonConstants.LOGCAT_TAG_NAME,"IntentService Threadid="+Thread.currentThread().getId())  ;
	}

}
