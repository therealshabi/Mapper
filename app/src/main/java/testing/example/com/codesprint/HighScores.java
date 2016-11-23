package testing.example.com.codesprint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import testing.example.com.codesprint.Utils.SharedPreferenceUtils;


public class HighScores extends AppCompatActivity {

    TextView orgHighScore;
    TextView flagHighScore;
    int temp;
    int test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        orgHighScore = (TextView) findViewById(R.id.highScoreOrgText);
        flagHighScore = (TextView) findViewById(R.id.highScoreFlagText);


        temp = SharedPreferenceUtils.getHighScoreOrg(getBaseContext(),temp);
        orgHighScore.setText(""+temp);

        test = SharedPreferenceUtils.getHighScoreFlag(getBaseContext(),test);
        flagHighScore.setText(""+test);


    }
}
