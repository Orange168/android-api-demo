package api.android.demo.uiadv.style;

import android.app.Activity;
import android.os.Bundle;

import api.android.demo.R;

public class StyleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyTheme);
        setContentView(R.layout.layout_style2);
    }
    
}
