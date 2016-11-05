package testing.example.com.codesprint;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText mText;
    TextView mScore;
    Button nextBtn;
    ImageView logo;
    Button mSubmit;

    String key = "";
    int score = 0;

    HashMap<String, Drawable> logos = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (EditText) findViewById(R.id.txt);
        mScore = (TextView) findViewById(R.id.score);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        logo = (ImageView) findViewById(R.id.logo);
        mSubmit = (Button) findViewById(R.id.submit);
        final Random generator = new Random();

        logos.put("Andorra", getDrawable(R.drawable.ad));
        logos.put("United Arab Emirates", getDrawable(R.drawable.ae));
        logos.put("Afghanistan", getDrawable(R.drawable.af));
        logos.put("Albania", getDrawable(R.drawable.al));
        logos.put("Argentina", getDrawable(R.drawable.ar));
        logos.put("Australia", getDrawable(R.drawable.au));
        logos.put("Armenia", getDrawable(R.drawable.am));
        logos.put("Angola", getDrawable(R.drawable.ao));
        logos.put("Austria", getDrawable(R.drawable.at));
        logos.put("Azerbaijan", getDrawable(R.drawable.az));
        logos.put("Barbados", getDrawable(R.drawable.bb));
        logos.put("Bangladesh", getDrawable(R.drawable.bd));
        logos.put("Belgium", getDrawable(R.drawable.be));
        logos.put("Bulgaria", getDrawable(R.drawable.bg));
        logos.put("Bahrain", getDrawable(R.drawable.bh));
        logos.put("Burundi", getDrawable(R.drawable.bi));
        logos.put("Brunei", getDrawable(R.drawable.bn));
        logos.put("Bolivia", getDrawable(R.drawable.bo));
        logos.put("Brazil", getDrawable(R.drawable.br));
        logos.put("Bahamas", getDrawable(R.drawable.bs));
        logos.put("Bhutan", getDrawable(R.drawable.bt));
        logos.put("Botswana", getDrawable(R.drawable.bw));
        logos.put("Canada", getDrawable(R.drawable.ca));
        logos.put("Switzerland", getDrawable(R.drawable.ch));
        logos.put("Cyprus", getDrawable(R.drawable.cy));
        logos.put("China", getDrawable(R.drawable.cn));
        /*logos.put("Germany", getDrawable(R.drawable.de));
        logos.put("France", getDrawable(R.drawable.fr));
        logos.put("Spain", getDrawable(R.drawable.es));
        logos.put("India", getDrawable(R.drawable.in));
        logos.put("Japan", getDrawable(R.drawable.jp));
        logos.put("North Korea", getDrawable(R.drawable.kp));
        logos.put("South Korea", getDrawable(R.drawable.kr));
        logos.put("Sri Lanka", getDrawable(R.drawable.lk));
        logos.put("Malaysia", getDrawable(R.drawable.my));
        logos.put("Mexico", getDrawable(R.drawable.mx));
        logos.put("Nigeria", getDrawable(R.drawable.ng));
        logos.put("Nepal", getDrawable(R.drawable.np));
        logos.put("New Zealand", getDrawable(R.drawable.nz));
        logos.put("Pakistan", getDrawable(R.drawable.pk));*/

        final ArrayList<String> keysAsArray = new ArrayList<String>(logos.keySet());

        key = keysAsArray.get(generator.nextInt(keysAsArray.size()));
        logo.setImageDrawable(logos.get(key));

        nextBtn.setEnabled(Boolean.FALSE);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText.setText("");
                key = keysAsArray.get(generator.nextInt(keysAsArray.size()));
                logo.setImageDrawable(logos.get(key));
                mSubmit.setEnabled(Boolean.TRUE);
                nextBtn.setEnabled(Boolean.FALSE);

            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans = mText.getText().toString();
                if(key.equals(ans))
                {
                    Toast.makeText(getBaseContext(), "Correct Answer!", Toast.LENGTH_LONG).show();
                    mSubmit.setEnabled(Boolean.FALSE);
                    nextBtn.setEnabled(Boolean.TRUE);
                    score+=10;
                }
                else if (getCode(key).equals(getCode(ans))) {
                    Toast.makeText(getBaseContext(), "Correct Answer!", Toast.LENGTH_LONG).show();
                    mSubmit.setEnabled(Boolean.FALSE);
                    nextBtn.setEnabled(Boolean.TRUE);
                    score+=5;
                } else {
                    Toast.makeText(getBaseContext(), "Hard Luck, Try Again!", Toast.LENGTH_LONG).show();
                }
                mScore.setText("Score : "+score);


            }
        });


        //logo.setImageDrawable(logos.get("UAE"));


    }


    //Soundex Algorithm

    public static String getCode(String s) {
        char[] x = s.toUpperCase().toCharArray();


        char firstLetter = x[0];

        //RULE [ 2 ]
        //Convert letters to numeric code
        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {
                case 'B':
                case 'F':
                case 'P':
                case 'V': {
                    x[i] = '1';
                    break;
                }

                case 'C':
                case 'G':
                case 'J':
                case 'K':
                case 'Q':
                case 'S':
                case 'X':
                case 'Z': {
                    x[i] = '2';
                    break;
                }

                case 'D':
                case 'T': {
                    x[i] = '3';
                    break;
                }

                case 'L': {
                    x[i] = '4';
                    break;
                }

                case 'M':
                case 'N': {
                    x[i] = '5';
                    break;
                }

                case 'R': {
                    x[i] = '6';
                    break;
                }

                default: {
                    x[i] = '0';
                    break;
                }
            }
        }

        //Remove duplicates
        //RULE [ 1 ]
        String output = "" + firstLetter;

        //RULE [ 3 ]
        for (int i = 1; i < x.length; i++)
            if (x[i] != x[i - 1] && x[i] != '0')
                output += x[i];

        //RULE [ 4 ]
        //Pad with 0's or truncate
        output = output + "0000";
        return output.substring(0, 4);
    }

}
