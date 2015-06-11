/**
 * @author Sundy
 * @decription auto init view e.g. Button . then set onClickLisntener 
 */
package api.android.demo.java4android;

import api.android.demo.R;
import api.android.demo.configration.CommonConstants;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class InstanceofActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LinearLayout layoutRoot = (LinearLayout)this.getLayoutInflater().inflate(R.layout.layout_instanceof, null)  ;
		setContentView(layoutRoot)  ;
		
		Button buttonTest = new Button(this) ;
		if(buttonTest instanceof TextView)
		{
			Log.i("sundy", "yes")  ;
		}
		
		int childCount = layoutRoot.getChildCount()  ;
		Log.i(CommonConstants.LOGCAT_TAG_NAME, childCount+"") ;
		if(childCount >0)
		{
			for(int i=0 ;i<childCount;i++)
			{
				View childView = layoutRoot.getChildAt(i)  ;
				if(childView instanceof Button)
				{
					childView.setOnClickListener(new OnClickListener()
					{

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Toast.makeText(InstanceofActivity.this, "�������ǰ�ť", 3000).show()  ;
							
							
						}
						
					})  ;
				}else if(childView instanceof TextView)
				{
					childView.setOnClickListener(new OnClickListener()
					{

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Toast.makeText(InstanceofActivity.this, "���������ı�", 3000).show()  ;
						}
						
					})  ;
				}
			}
		}
		

	}

	
	
}
