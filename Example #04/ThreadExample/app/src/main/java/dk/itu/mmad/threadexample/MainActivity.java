package dk.itu.mmad.threadexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Fragment mThreadFragment;
    private Fragment mHandlerFragment;
    private Fragment mAsyncTaskFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mThreadFragment = new ThreadFragment();
        mHandlerFragment = new HandlerFragment();
        mAsyncTaskFragment = new AsyncTaskFragment();

        fragmentManager = getSupportFragmentManager();
        Fragment fragment =
                fragmentManager.findFragmentById(R.id.fragment);
        if (fragment == null)
            fragmentManager.beginTransaction()
                    .add(R.id.fragment, mThreadFragment)
                    .commit();
    }

    public void selectFragment(View view) {
        Fragment fragment;
        if (view == findViewById(R.id.threadButton))
            fragment = mThreadFragment;
        else if (view == findViewById(R.id.handlerButton))
            fragment = mHandlerFragment;
        else
            fragment = mAsyncTaskFragment;

        fragmentManager.beginTransaction()
                .replace(R.id.fragment, fragment)
                .setTransition(FragmentTransaction
                        .TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

}
