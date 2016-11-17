package testing.example.com.codesprint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import static testing.example.com.codesprint.R.drawable.to;

public class Intermediate extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    Button mFlags;
    Button mOrg;
    Button mAbout;
    ToggleButton toggle;
    boolean toggleState;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);

        mFlags = (Button) findViewById(R.id.flags);
        mOrg = (Button) findViewById(R.id.govt);
        mAbout = (Button) findViewById(R.id.about);
        toggle = (ToggleButton) findViewById(R.id.toggleBtn);

        mFlags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intermediate.this,Mapper.class));
            }
        });

        mOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intermediate.this,GovtOrg.class));
            }
        });

        mAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Intermediate.this, About.class));
            }
        });

        sharedPref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggle.isChecked()) {
                    toggleState = true;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean(getString(R.string.toggleState), toggleState);
                    editor.apply();

                } else {
                    toggleState = false;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean(getString(R.string.toggleState), toggleState);
                    editor.apply();
                }
            }
        });
    }
}
