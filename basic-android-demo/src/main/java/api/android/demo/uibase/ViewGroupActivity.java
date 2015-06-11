/**
 * 
 */
package api.android.demo.uibase;

import android.app.ActivityGroup;
import android.os.Bundle;
import api.android.demo.R; ;

/**
 * @author Administrator
 *
 */
public class ViewGroupActivity extends ActivityGroup {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.framelayout)  ;
		//setContentView(R.layout.relative_layout)  ;
		//setContentView(R.layout.linearlayout)  ;
		//setContentView(R.layout.linearlayout2)  ;
		//setContentView(R.layout.table_layout)  ;
	}

}
