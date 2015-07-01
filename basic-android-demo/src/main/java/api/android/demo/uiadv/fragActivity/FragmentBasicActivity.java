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
public class FragmentBasicActivity extends Activity implements  ExampleFragment.ExampleFragmentButtonClick{

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
        fragmentTransaction.commit(); //executePendingTransactions() is transaction immediately

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.replace_button:

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, RepFragment) ;
                //在提交之后这个Fragment是会被Stop而不是Destroyed
                /** when at addToBackStack condition replace over press back key return to ExampleFragment
                 *  not addToBackStack press back key  is normal function
                 */
                transaction.addToBackStack(null) ;
                transaction.commit() ;

                //ToDo When use the field fragmentTransition the App crash
//                fragmentTransaction.replace(R.id.fragment_container, RepFragment) ;
//                //在提交之后这个Fragment是会被Stop而不是Destroyed
//                fragmentTransaction.addToBackStack(null) ;
//                fragmentTransaction.commit() ;
                break;
            case R.id.replace_innerFragme:
                FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                /**
                 * when try to replace <fragment> tag fragment the transaction add a new fragment
                 * to display and replace SampleFragment but the SampleFragment is still display
                 * in the screen try to remove it is the same
                 *
                 */
                transaction2.replace(R.id.must_specify_unique_id_or_tag, new ReplaceFragment()) ;
                transaction2.addToBackStack(null) ;
                transaction2.commit() ;
                break ;
            case R.id.remove_innter_fragmet:
                Fragment fragment = getFragmentManager().findFragmentById(R.id.must_specify_unique_id_or_tag) ;
                getFragmentManager().beginTransaction().remove(fragment).commit() ;
                break ;
        }

    }

    @Override
    public void onClick(ExampleFragment fragment) {

    }
}
