package testing.example.com.codesprint;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import testing.example.com.codesprint.Utils.SharedPreferenceUtils;

public class Intermediate extends Activity {

    Button mFlags;
    Button mOrg;
    Button mAbout;
    Button mHelp;
    Button mHighScores;
    ToggleButton toggle;
    boolean toggleState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);


        mFlags = (Button) findViewById(R.id.flags);
        mOrg = (Button) findViewById(R.id.govt);
        mAbout = (Button) findViewById(R.id.about);
        toggle = (ToggleButton) findViewById(R.id.toggleButton);
        mHelp = (Button) findViewById(R.id.help);
        mHighScores = (Button) findViewById(R.id.highScore);

        mHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intermediate.this,Help.class));
            }
        });

        mHighScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intermediate.this,HighScores.class));
            }
        });




        mFlags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intermediate.this, Mapper.class));
            }
        });

        mOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intermediate.this, GovtOrg.class));
            }
        });

        mAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intermediate.this, About.class));
            }
        });

        toggleState = SharedPreferenceUtils.getToggleStatus(getBaseContext(),toggleState);
        if (!toggleState) {
            toggle.setChecked(Boolean.FALSE);
        } else {
            toggle.setChecked(Boolean.TRUE);
        }

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggle.isChecked()) {
                    toggleState = true;
                    SharedPreferenceUtils.setToggleStatus(getBaseContext(),toggleState);

                } else {
                    toggleState = false;
                    SharedPreferenceUtils.setToggleStatus(getBaseContext(),toggleState);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (toggleState)
            toggle.setChecked(true);
        else
            toggle.setChecked(false);
    }
}
