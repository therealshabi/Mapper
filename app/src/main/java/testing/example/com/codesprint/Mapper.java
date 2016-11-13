package testing.example.com.codesprint;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import testing.example.com.codesprint.R;

public class Mapper extends AppCompatActivity {
    EditText mText;
    TextView mScore;
    Button nextBtn;
    Button skipBtn;
    ImageButton infoBtn;
    ImageView logo;
    Button mSubmit;
    long tStart;
    String key = "";
    int score = 0;
    int penaltyTime=0;
    HashMap<String, Drawable> logos = new HashMap<>();
    HashMap<String, String> infos = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapper);

        mText = (EditText) findViewById(R.id.txt);
        mScore = (TextView) findViewById(R.id.score);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        infoBtn=(ImageButton)findViewById(R.id.info);
        skipBtn=(Button)findViewById(R.id.skipBtn);
        logo = (ImageView) findViewById(R.id.logo);
        mSubmit = (Button) findViewById(R.id.submit);
        final Random generator = new Random();

        logos.put("Andorra", getDrawable(R.drawable.ad));
        infos.put("Andorra", "Capital: Andorra la Vella\n" +
                "Dialing code: +376\n" +
                "Currency: Euro\n" +
                "Population: 79,218 (2013) World Bank\n" +
                "Official language: Catalan");

        logos.put("United Arab Emirates", getDrawable(R.drawable.ae));
        infos.put("United Arab Emirates","Capital: Abu Dhabi\n" +
                "Dialing code: +971\n" +
                "Currency: United Arab Emirates dirham\n" +
                "Population: 9.346 million (2013) World Bank\n" +
                "President: Khalifa bin Zayed Al Nahyan");
        logos.put("Afghanistan", getDrawable(R.drawable.af));
        infos.put("Afghanistan","Capital: Kabul\n" +
                "Currency: Afghan afghani\n" +
                "President: Ashraf Ghani\n" +
                "Population: 30.55 million (2013) World Bank\n" +
                "Official languages: Pashto, Dari");

        logos.put("Albania", getDrawable(R.drawable.al));
        infos.put("Albania","Capital: Tirana\n" +
                "Dialing code: +355\n" +
                "Currency: Albanian lek\n" +
                "President: Bujar Nishani\n" +
                "Population: 2.774 million (2013) World Bank");


        logos.put("Argentina", getDrawable(R.drawable.ar));
        infos.put("Argentina","Capital: Buenos Aires\n" +
                "Dialing code: +54\n" +
                "Currency: Argentine peso\n" +
                "President: Mauricio Macri\n" +
                "Population: 41.45 million (2013)");

        logos.put("Australia", getDrawable(R.drawable.au));
        infos.put("Australia","Capital: Canberra\n" +
                "Dialing code: +61\n" +
                "Currency: Australian dollar\n" +
                "Population: 23.13 million (2013) World Bank\n" +
                "Prime minister: Malcolm Turnbull");

        logos.put("Armenia", getDrawable(R.drawable.am));
        infos.put("Armenia","Capital: Yerevan\n" +
                "Dialing code: +374\n" +
                "Continent: Asia\n" +
                "Population: 2.977 million (2013) World Bank\n" +
                "Currencies: Noah's Ark silver coins, Armenian dram");

        logos.put("Angola", getDrawable(R.drawable.ao));
        infos.put("Angola","Capital: Luanda\n" +
                "Dialing code: +244\n" +
                "Currency: Angolan kwanza\n" +
                "President: José Eduardo dos Santos\n" +
                "Population: 21.47 million (2013) World Bank\n" +
                "Official language: Portuguese");

        logos.put("Austria", getDrawable(R.drawable.at));
        infos.put("Austria","Capital: Vienna\n" +
                "Dialing code: +43\n" +
                "Currency: Euro\n" +
                "Population: 8.474 million (2013) World Bank\n" +
                "Official languages: German, Hungarian, Slovenian, Austrian German");

        logos.put("Azerbaijan", getDrawable(R.drawable.az));
        infos.put("Azerbaijan","Capital: Baku\n" +
                "Dialing code: +994\n" +
                "Currency: Azerbaijani manat\n" +
                "Population: 9.417 million (2013) World Bank\n" +
                "Official language: Azerbaijani");

        logos.put("Barbados", getDrawable(R.drawable.bb));
        infos.put("Barbados", "Capital: Bridgetown\n" +
                "Dialing code: +1\n" +
                "Currency: Barbadian dollar\n" +
                "Continent: North America\n" +
                "Official language: English");

        logos.put("Bangladesh", getDrawable(R.drawable.bd));
        infos.put("Bangladesh","Capital: Dhaka\n" +
                "Dialing code: +880\n" +
                "Currency: Bangladeshi taka\n" +
                "Prime minister: Sheikh Hasina\n" +
                "Population: 156.6 million (2013) World Bank\n" +
                "President: Abdul Hamid");

        logos.put("Belgium", getDrawable(R.drawable.be));
        infos.put("Belgium","Capital: City of Brussels\n" +
                "Dialing code: +32\n" +
                "Currency: Euro\n" +
                "Population: 11.2 million (2013) World Bank\n" +
                "Official languages: French, Dutch, German");

        //logos.put("Bulgaria", getDrawable(R.drawable.bg));
        //  logos.put("Bahrain", getDrawable(R.drawable.bh));
        //  logos.put("Burundi", getDrawable(R.drawable.bi));
        //  logos.put("Brunei", getDrawable(R.drawable.bn));
        //  logos.put("Bolivia", getDrawable(R.drawable.bo));
        logos.put("Brazil", getDrawable(R.drawable.br));
        infos.put("Brazil","Capital: Brasília\n" +
                "Dialing code: +55\n" +
                "Currency: Brazilian real\n" +
                "President: Michel Temer\n" +
                "Population: 200.4 million (2013) World Bank\n" +
                "Official language: Portuguese");

        //   logos.put("Bahamas", getDrawable(R.drawable.bs));
        logos.put("Bhutan", getDrawable(R.drawable.bt));
        infos.put("Bhutan","Capital: Thimphu\n" +
                "King: Jigme Khesar Namgyel Wangchuck\n" +
                "Prime minister: Tshering Tobgay\n" +
                "Currencies: Bhutanese ngultrum, Indian rupee\n" +
                "Official language: Dzongkha");
        //   logos.put("Botswana", getDrawable(R.drawable.bw));
        logos.put("Canada", getDrawable(R.drawable.ca));
        infos.put("Canada","Capital: Ottawa\n" +
                "Dialing code: +1\n" +
                "Currency: Canadian dollar\n" +
                "Population: 35.16 million (2013) World Bank\n" +
                "Prime minister: Justin Trudeau\n" +
                "Provinces: Ontario, British Columbia, Québec, Alberta, more");
        logos.put("Switzerland", getDrawable(R.drawable.ch));
        infos.put("Switzerland","Dialing code: +41\n" +
                "Currency: Swiss franc\n" +
                "Population: 8.081 million (2013) World Bank\n" +
                "Official languages: French, Romansh, German, Italian\n" +
                "Colleges and Universities: ETH Zurich, more");
        logos.put("Cyprus", getDrawable(R.drawable.cy));
        infos.put("Cyprus","Capital: Nicosia\n" +
                "Dialing code: +357\n" +
                "Currency: Euro\n" +
                "Official languages: Turkish, Greek\n" +
                "Colleges and Universities: University of Cyprus, more");
        logos.put("China", getDrawable(R.drawable.cn));
        infos.put("China","Capital: Beijing\n" +
                "Dialing code: +86\n" +
                "Currency: Renminbi\n" +
                "Population: 1.357 billion (2013) World Bank\n" +
                "President: Xi Jinping");
       /* logos.put("Germany", getDrawable(R.drawable.de));
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
                skipBtn.setEnabled(Boolean.TRUE);
                tStart = System.currentTimeMillis();
                key = keysAsArray.get(generator.nextInt(keysAsArray.size()));
                logo.setImageDrawable(logos.get(key));
                mSubmit.setEnabled(Boolean.TRUE);
                nextBtn.setEnabled(Boolean.FALSE);

            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Mapper.this);
                builder.setMessage(infos.get(key))
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSubmit.setEnabled(Boolean.FALSE);
                skipBtn.setEnabled(Boolean.FALSE);
                nextBtn.setEnabled(Boolean.TRUE);
                Toast.makeText(getBaseContext(), "Correct Answer was "+key, Toast.LENGTH_LONG).show();

            }
        });
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans = mText.getText().toString();
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;

                double elapsedSeconds = tDelta / 2000.0;

                if (ans != "" || ans != " ") {
                    if (key.equals(ans)) {
                        Toast.makeText(getBaseContext(), "Correct Answer!", Toast.LENGTH_LONG).show();
                        mSubmit.setEnabled(Boolean.FALSE);
                        nextBtn.setEnabled(Boolean.TRUE);
                        if ((int) elapsedSeconds < 5)
                            score += 10 - ((int) elapsedSeconds + penaltyTime);
                        else
                            score += 5 - penaltyTime;

                    } else if (getCode(key).equals(getCode(ans))) {
                        Toast.makeText(getBaseContext(), "Correct Answer!", Toast.LENGTH_LONG).show();
                        mSubmit.setEnabled(Boolean.FALSE);
                        nextBtn.setEnabled(Boolean.TRUE);
                        if ((int) elapsedSeconds < 5)
                            score += 9 - ((int) elapsedSeconds + penaltyTime);
                        else
                            score += 4 - penaltyTime;

                    } else {
                        Toast.makeText(getBaseContext(), "Hard Luck, Try Again!", Toast.LENGTH_LONG).show();
                        if (penaltyTime < 4)
                            penaltyTime++;
                    }
                }
                mScore.setText("Score : " + score);


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