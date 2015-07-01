package api.android.demo.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import api.android.demo.configuration.CommonConstants;

public class MusicService extends Service {


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
        return null;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        Log.i(CommonConstants.LOGCAT_TAG_NAME, "IntentService Destory") ;
    }

//    @Override
//    protected void onHandleIntent(Intent intent) {
//        Log.i(CommonConstants.LOGCAT_TAG_NAME, "onHandleIntent") ;
//    }

    @Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		MediaPlayer mp = MediaPlayer.create(this, api.android.demo.R.raw.maria) ;
		mp.start() ;
		return super.onStartCommand(intent, flags, startId);
	}

}
