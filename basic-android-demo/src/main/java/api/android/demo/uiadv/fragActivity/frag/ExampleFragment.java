package api.android.demo.uiadv.fragActivity.frag;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import api.android.demo.R;

/**
 * Created by Edward Lin
 * on 6/21/15 6:14 PM.
 */
public class ExampleFragment extends Fragment {

    private final static String TAG  = "ExampleFragment";
    /**
     * interaction with other fragment
     */
    private ExampleFragmentButtonClick listener = null ;

    public void setExampleFragmentButtonClick(ExampleFragmentButtonClick listener){
        this.listener = listener ;
    }

    public  interface ExampleFragmentButtonClick {
        void onClick(ExampleFragment fragment) ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sample_fragment_layout, null);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onClick(ExampleFragment.this);
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG,"onAttach Activity" + activity);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"onStop()");
    }
}
