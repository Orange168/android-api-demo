package api.android.demo.uiadv.fragActivity.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import api.android.demo.R;

/**
 * Created by Edward Lin
 * on 6/21/15 6:14 PM.
 */
public class ExampleFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.sample_fragment_layout,null) ;
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("ExampleFragment","onDestroy");
    }
}
