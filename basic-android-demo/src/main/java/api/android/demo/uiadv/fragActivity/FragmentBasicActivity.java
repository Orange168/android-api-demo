package api.android.demo.uiadv.fragActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import api.android.demo.R;
import api.android.demo.uiadv.fragActivity.frag.ExampleFragment;
import api.android.demo.uiadv.fragActivity.frag.ReplaceFragment;

/**
 * Created by Edward Lin
 * on 6/20/15 2:00 PM.
 */
public class FragmentBasicActivity extends Activity {

    private ReplaceFragment RepFragment = null ;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_basic_activity);
        FragmentManager fragmentManager = getFragmentManager();
        RepFragment = new ReplaceFragment() ;
        fragmentTransaction = fragmentManager.beginTransaction();
        ExampleFragment fragment = new ExampleFragment();
        fragmentTransaction.add(R.id.fragment_container, fragment, "sample");
//        fragmentTransaction.replace(R.id.frag_container,fragment) ;
        fragmentTransaction.commit();

    }

    public void onClick(View view) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, RepFragment) ;
        //在提交之后这个Fragment是会被Stop而不是Destroyed
        transaction.addToBackStack(null) ;
        transaction.commit() ;
    }
}
