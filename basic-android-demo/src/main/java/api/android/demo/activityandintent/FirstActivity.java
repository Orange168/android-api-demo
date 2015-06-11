package api.android.demo.activityandintent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import api.android.demo.R;
import api.android.demo.configration.CommonConstants;

public class FirstActivity extends Activity {
    /** Called when the activity is first created. */
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(CommonConstants.LOGCAT_TAG_NAME, "onCreate TaskID =" + this.getTaskId() )  ;
        if(savedInstanceState != null)
        {
        	Log.i("restore in onCreate:",savedInstanceState.getString("sundydemo"))  ;
        }
        
        setContentView(R.layout.layout_activity1);
        try {
			throw new Exception("dfd")  ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("sundylog", e.getMessage())  ;
		}

    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onDestroy")  ;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onPause")  ;
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onRestart")  ;
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null)
        {
        	Log.i("restore state:",savedInstanceState.getString("sundydemo"))  ;
        }
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putString("sundydemo", "onSaveInstanceState")  ;
		Log.i("save state:","onSaveInstanceState")  ;
		//super.onSaveInstanceState(outState);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onResume")  ;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onStart")  ;
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onStop")  ;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==1234)
		{
			String returnValue = data.getStringExtra("sundyintentreturn") ;
			Toast.makeText(this, "Return"+returnValue, Toast.LENGTH_LONG).show()  ;

		}
		
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onNewIntent Task id = " + this.getTaskId())  ;
	}

	public void onClick(View view) {
		switch (view.getId()){
			case R.id.singleTop:
				//Todo set the FirstActivity is singleTop
				startActivity(new Intent(FirstActivity.this, FirstActivity.class));
				break ;
			case R.id.button23:
				Intent _intent = new Intent(FirstActivity.this,SecondActivity.class) ;
				_intent.putExtra("sundyintent", "from FirstActivity")  ;
				_intent.putExtra("sundyintent1", "FirstActivity2")  ;
				//MainActivity.this.startActivity(_intent)  ;
				FirstActivity.this.startActivityForResult(_intent, 1234)  ;
				break ;
			case R.id.buttonDial:
				Intent _intentDial = new Intent() ;
				_intentDial.setAction(Intent.ACTION_DIAL)  ;
				_intentDial.setData(Uri.parse("tel:15608071871"))  ;
				startActivity(_intentDial)  ;
				break ;
		}
	}
}