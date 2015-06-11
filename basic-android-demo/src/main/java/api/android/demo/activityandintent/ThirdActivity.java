package api.android.demo.activityandintent;

import api.android.demo.R;
import api.android.demo.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class ThirdActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE)  ;
		setContentView(R.layout.layout_floatactivity)  ;
	}

	public void onClick(View view) {
		switch (view.getId()){
			case R.id.ideasandroidlogo:
				startActivity(new Intent(this,FirstActivity.class));
				break ;
		}

	}
}
