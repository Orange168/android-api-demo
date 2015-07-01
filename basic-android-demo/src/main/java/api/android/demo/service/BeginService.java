/**
 * һ���򵥵����ż���Service
 */
package api.android.demo.service;

import api.android.demo.configuration.CommonConstants;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Administrator
 *
 */
public class BeginService extends Service {


	@Override
	public IBinder onBind(Intent arg0) {

		
		return myBinder;
	}

	public void MyMethod(){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<100;i++)
				{
					try {
						Thread.sleep(1000) ;
						Log.i(CommonConstants.LOGCAT_TAG_NAME, "Binding BeginService :"+i) ;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start() ;
		
    }
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		for(int i=0;i<100;i++)
		{
			try {
				Thread.sleep(1000) ;
				Log.i(CommonConstants.LOGCAT_TAG_NAME, "Starting BeginService :"+i) ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		/*new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<100;i++)
				{
					try {
						Thread.sleep(1000) ;
						Log.i(CommonConstants.LOGCAT_TAG_NAME, "Starting BeginService :"+i) ;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}).start() ;*/
		return super.onStartCommand(intent, flags, startId);
	}
	
	 public class MyBinder extends Binder{
	        
	        public BeginService getService(){
	            return BeginService.this;
	        }
	        
	        public void helloSundy()
	        {
	        	
	        }
	    }
	    
	    private MyBinder myBinder = new MyBinder();

}
