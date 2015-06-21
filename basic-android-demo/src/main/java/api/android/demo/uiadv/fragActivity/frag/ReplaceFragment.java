package api.android.demo.uiadv.fragActivity.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import api.android.demo.R;

/**
 * Created by Edward Lin
 * on 6/21/15 8:51 PM.
 */
public class ReplaceFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sample_fragment_layout, null) ;
        Button button = (Button) view.findViewById(R.id.button);
        button.setText("Replace");
        return view ;
    }
}
