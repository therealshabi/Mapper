package testing.example.com.codesprint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Intermediate extends AppCompatActivity {

    Button mFlags;
    Button mOrg;
    Button mAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);

        mFlags = (Button) findViewById(R.id.flags);
        mOrg = (Button) findViewById(R.id.govt);
        mAbout = (Button) findViewById(R.id.about);

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

            }
        });
    }
}
