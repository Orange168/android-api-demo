package com.orange.m;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.orange.m.databinding.ActivityMainBinding;

public class MainActivity extends Activity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        user = new User("FirstName", "LastName");
        binding.setUser(user);
    }

    public void onButtonClick(View view) {
        Toast.makeText(this, "CLICKED", Toast.LENGTH_LONG).show();
        user.setLastName("NewLastName");
    }

}