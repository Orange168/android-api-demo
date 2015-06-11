package api.android.demo.graphic;

import com.google.android.maps.TrackballGestureDetector;

import api.android.demo.R;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CustomDrawableActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState) ;
		
		CustomDrawableView newView = new CustomDrawableView(this) ;
		setContentView(newView) ;
	}

}
