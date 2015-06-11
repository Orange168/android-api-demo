package android.api.test.demo;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "test.demo" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Log.i(TAG,"onCreate TaskId = " + getTaskId()) ;
    }


    public void onClick(View view)  {
        switch (view.getId()){
            case R.id.singleTop:
                try {
                    //pre-Lollipop: placed on top of the caller Task, Lollipop: a new Task would be created
                    //The FirstActivity in the Manifest.xml android:exported="true"
                    Intent s = new Intent() ;
                    ComponentName componetName = new ComponentName("api.android.demo", "api.android.demo.activityandintent.FirstActivity");
                    s.setComponent(componetName) ;
                    startActivity(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break ;
            case R.id.taskAffinity_singleTask:
                //set SecondActivity and FirstActivity android:taskAffinity=""  FirstActivity is singleTask
                Intent a = new Intent() ;
                ComponentName componetName2 = new ComponentName("api.android.demo", "api.android.demo.activityandintent.SecondActivity");
                a.setComponent(componetName2) ;
                startActivity(a);
                break ;
        }
    }
}
