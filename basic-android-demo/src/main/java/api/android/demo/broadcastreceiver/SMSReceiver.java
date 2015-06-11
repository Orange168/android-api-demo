package api.android.demo.broadcastreceiver;

import api.android.demo.configration.CommonConstants;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
		{
			Log.i(CommonConstants.LOGCAT_TAG_NAME,"���յ�����")  ;
			
		}else if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
		{
			Log.i(CommonConstants.LOGCAT_TAG_NAME,"ϵͳ�������")  ;
		}else {
			Log.i(CommonConstants.LOGCAT_TAG_NAME,"�㲥����")  ;
		}
	}

}
