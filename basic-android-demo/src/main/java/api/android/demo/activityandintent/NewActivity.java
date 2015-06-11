package api.android.demo.activityandintent;

import api.android.demo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Lesson4 Activity ת�� ����ֵ ���Լ��������ڷ�����ʾ��
 */
public class NewActivity extends Activity
{
	private static final String	TAG	= "Activity02";
	private final int REQUEST_CODE_ACTIVITY = 1234  ;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_newactivity);
		Log.v(TAG, "onCreate");
		Button button = (Button)findViewById(R.id.buttonReturnLastActivity);
		/* ����button���¼���Ϣ */
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				/* �½�һ��Intent���� */
				Intent intent = new Intent();
				/* ָ��intentҪ�������� */
				intent.setClass(NewActivity.this, StartNewActivity.class);
				/* ����һ���µ�Activity */
				startActivity(intent);
				/* �رյ�ǰ��Activity */
				NewActivity.this.finish();
			}
		});
	}

	public void onStart()
	{
		super.onStart();
		
		Log.v(TAG, "onStart");
	}
	
	public void onResume()
	{
		super.onResume();
		Log.v(TAG, "onResume");
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
	}

	public void onPause()
	{
		super.onPause();
		Log.v(TAG, "onPause");
	}
	
	public void onStop()
	{
		super.onStop();
		Log.v(TAG, "onStop");
	}

	public void onDestroy()
	{
		super.onDestroy();
		Log.v(TAG, "onDestroy");
	}

	public void onRestart()
	{
		super.onRestart();
		Log.v(TAG, "onReStart");
	}
}

