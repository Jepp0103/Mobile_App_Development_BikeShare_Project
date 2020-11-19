package dk.itu.mmad.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String STATE = "state";
    private static final String CHECK = "checkbox";

    private TextView mTextView;
    private Button mTrueButton;
    private Button mFalseButton;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement your code here.
                mTextView.setText("You clicked on true button");
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement your code here.
                mTextView.setText("You clicked on false button");
            }
        });

        mCheckBox = (CheckBox) findViewById(R.id.check_box);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String status = b ? "checked" : "unchecked";
                mTextView.setText("You " + status + " the checkbox");
            }
        });

        if (savedInstanceState != null) {
            boolean status = savedInstanceState.getBoolean(CHECK,
                    true);
            mCheckBox.setChecked(status);

            String state = savedInstanceState.getString(STATE,
                    "Hello World!");
            mTextView.setText(state);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        String state = mTextView.getText().toString();
        saveInstanceState.putString(STATE, state);

        boolean status = mCheckBox.isChecked();
        saveInstanceState.putBoolean(CHECK, status);

        super.onSaveInstanceState(saveInstanceState);
    }

}
