package dk.itu.mmad.fragmentapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        Fragment fragment =
                fragmentManager.findFragmentById(R.id.fragment);
        if (fragment == null)
            fragmentManager.beginTransaction()
                    .add(R.id.fragment, new FirstFragment())
                    .commit();
    }

    public void selectFragment(View view) {
        Fragment fragment;
        if (view == findViewById(R.id.first_button))
            fragment = new FirstFragment();
        else if (view == findViewById(R.id.second_button))
            fragment = new SecondFragment();
        else
            fragment = new ThirdFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment, fragment)
                .setTransition(FragmentTransaction
                        .TRANSIT_FRAGMENT_FADE)
                .commit();
    }

}
