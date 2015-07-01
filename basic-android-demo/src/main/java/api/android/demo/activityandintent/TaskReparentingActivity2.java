package api.android.demo.activityandintent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Edward Lin
 * on 6/22/15 12:17 PM.
 */
public class TaskReparentingActivity2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(this) ;
        button.setText("allowTaskReparenting=true/n launcherMode = singleTask");
        button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT ));
        setContentView(button);

    }
}
