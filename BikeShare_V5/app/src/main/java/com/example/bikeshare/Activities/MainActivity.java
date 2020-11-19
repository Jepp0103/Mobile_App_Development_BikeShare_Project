package com.example.bikeshare.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

import com.example.bikeshare.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Calledwhentheactivityisabouttobecomevisible.
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Calledwhentheactivityhasbecomevisible.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Calledwhenanotheractivityistakingfocus.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Calledwhentheactivityisnolongervisible.
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Calledwhenwhentheactivityisbeingre-displayed
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * Calledjustbeforetheactivityisdestroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
