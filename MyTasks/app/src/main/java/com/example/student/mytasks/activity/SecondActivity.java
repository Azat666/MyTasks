
package com.example.student.mytasks.activity;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.student.mytasks.R;
import com.example.student.mytasks.fragments.LoginFragment;


public class SecondActivity extends AppCompatActivity {


    android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        fragmentManager = getSupportFragmentManager();
        setFragment(new LoginFragment());


    }

    private void setFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(R.id.fragments_container, fragment).commit();
    }


}
