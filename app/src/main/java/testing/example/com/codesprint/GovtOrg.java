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

public class GovtOrg extends AppCompatActivity {
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
    int penaltyTime = 0;
    HashMap<String, Drawable> logos = new HashMap<>();
    HashMap<String, String> infos = new HashMap<>();
    ArrayList<String> keysAsArray;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govt_org);

        mText = (EditText) findViewById(R.id.txt);
        mScore = (TextView) findViewById(R.id.score);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        infoBtn = (ImageButton) findViewById(R.id.info);
        skipBtn = (Button) findViewById(R.id.skipBtn);
        logo = (ImageView) findViewById(R.id.logo);
        mSubmit = (Button) findViewById(R.id.submit);
        final Random generator = new Random();

        logos.put("Delhi Metro", getDrawable(R.drawable.metro));
        infos.put("Delhi Metro", "Capital: Andorra la Vella\n" +
                "Dialing code: +376\n" +
                "Currency: Euro\n" +
                "Population: 79,218 (2013) World Bank\n" +
                "Official language: Catalan");

        logos.put("Airport Authority of India", getDrawable(R.drawable.airport_authority_of_india));
        infos.put("Airport Authority of India", "Capital: Abu Dhabi\n" +
                "Dialing code: +971\n" +
                "Currency: United Arab Emirates dirham\n" +
                "Population: 9.346 million (2013) World Bank\n" +
                "President: Khalifa bin Zayed Al Nahyan");
        logos.put("PETA", getDrawable(R.drawable.peta));
        infos.put("PETA", "Capital: Kabul\n" +
                "Currency: Afghan afghani\n" +
                "President: Ashraf Ghani\n" +
                "Population: 30.55 million (2013) World Bank\n" +
                "Official languages: Pashto, Dari");

        logos.put("Indian Army", getDrawable(R.drawable.army));
        infos.put("Indian Army", "Capital: Tirana\n" +
                "Dialing code: +355\n" +
                "Currency: Albanian lek\n" +
                "President: Bujar Nishani\n" +
                "Population: 2.774 million (2013) World Bank");


        logos.put("Make In India", getDrawable(R.drawable.makeinindia));
        infos.put("Make In India", "Capital: Buenos Aires\n" +
                "Dialing code: +54\n" +
                "Currency: Argentine peso\n" +
                "President: Mauricio Macri\n" +
                "Population: 41.45 million (2013)");

        logos.put("UGC", getDrawable(R.drawable.ugc));
        infos.put("UGC", "Capital: Canberra\n" +
                "Dialing code: +61\n" +
                "Currency: Australian dollar\n" +
                "Population: 23.13 million (2013) World Bank\n" +
                "Prime minister: Malcolm Turnbull");

        logos.put("Aadhar", getDrawable(R.drawable.aadhar));
        infos.put("Armenia", "Capital: Yerevan\n" +
                "Dialing code: +374\n" +
                "Continent: Asia\n" +
                "Population: 2.977 million (2013) World Bank\n" +
                "Currencies: Noah's Ark silver coins, Armenian dram");

        logos.put("Air India", getDrawable(R.drawable.air));
        infos.put("Air India", "Capital: Luanda\n" +
                "Dialing code: +244\n" +
                "Currency: Angolan kwanza\n" +
                "President: José Eduardo dos Santos\n" +
                "Population: 21.47 million (2013) World Bank\n" +
                "Official language: Portuguese");

        logos.put("Indian Air Force", getDrawable(R.drawable.airforce));
        infos.put("Indian Air Force", "Capital: Vienna\n" +
                "Dialing code: +43\n" +
                "Currency: Euro\n" +
                "Population: 8.474 million (2013) World Bank\n" +
                "Official languages: German, Hungarian, Slovenian, Austrian German");

        logos.put("Sarva Shiksha Abhiyan", getDrawable(R.drawable.shiksha));
        infos.put("Sarva Shiksha Abhiyan", "Capital: Baku\n" +
                "Dialing code: +994\n" +
                "Currency: Azerbaijani manat\n" +
                "Population: 9.417 million (2013) World Bank\n" +
                "Official language: Azerbaijani");

        logos.put("BSNL", getDrawable(R.drawable.bsnl));
        infos.put("BSNL", "Capital: Bridgetown\n" +
                "Dialing code: +1\n" +
                "Currency: Barbadian dollar\n" +
                "Continent: North America\n" +
                "Official language: English");

        logos.put("ISRO", getDrawable(R.drawable.isro));
        infos.put("ISRO", "Capital: Dhaka\n" +
                "Dialing code: +880\n" +
                "Currency: Bangladeshi taka\n" +
                "Prime minister: Sheikh Hasina\n" +
                "Population: 156.6 million (2013) World Bank\n" +
                "President: Abdul Hamid");

        logos.put("Swatch Bharat", getDrawable(R.drawable.swatch));
        infos.put("Swatch Bharat", "Capital: City of Brussels\n" +
                "Dialing code: +32\n" +
                "Currency: Euro\n" +
                "Population: 11.2 million (2013) World Bank\n" +
                "Official languages: French, Dutch, German");

        logos.put("Digital India", getDrawable(R.drawable.digital_india_logo_small));
        infos.put("Digital India", "Capital: Brasília\n" +
                "Dialing code: +55\n" +
                "Currency: Brazilian real\n" +
                "President: Michel Temer\n" +
                "Population: 200.4 million (2013) World Bank\n" +
                "Official language: Portuguese");

        logos.put("Lion Capital", getDrawable(R.drawable.emblem1));
        infos.put("Lion Capital", "Capital: Thimphu\n" +
                "King: Jigme Khesar Namgyel Wangchuck\n" +
                "Prime minister: Tshering Tobgay\n" +
                "Currencies: Bhutanese ngultrum, Indian rupee\n" +
                "Official language: Dzongkha");

        logos.put("Hindustan Petroleum", getDrawable(R.drawable.hindustanpetroleum));
        infos.put("Canada", "Capital: Ottawa\n" +
                "Dialing code: +1\n" +
                "Currency: Canadian dollar\n" +
                "Population: 35.16 million (2013) World Bank\n" +
                "Prime minister: Justin Trudeau\n" +
                "Provinces: Ontario, British Columbia, Québec, Alberta, more");
        logos.put("PETA", getDrawable(R.drawable.peta));
        infos.put("PETA", "Dialing code: +41\n" +
                "Currency: Swiss franc\n" +
                "Population: 8.081 million (2013) World Bank\n" +
                "Official languages: French, Romansh, German, Italian\n" +
                "Colleges and Universities: ETH Zurich, more");
        logos.put("Indian Post", getDrawable(R.drawable.indian_post));
        infos.put("Indian Post", "Capital: Nicosia\n" +
                "Dialing code: +357\n" +
                "Currency: Euro\n" +
                "Official languages: Turkish, Greek\n" +
                "Colleges and Universities: University of Cyprus, more");
        logos.put("Indian Oil", getDrawable(R.drawable.indianoil));
        infos.put("Indian Oil", "Capital: Beijing\n" +
                "Dialing code: +86\n" +
                "Currency: Renminbi\n" +
                "Population: 1.357 billion (2013) World Bank\n" +
                "President: Xi Jinping");
        keysAsArray = new ArrayList<String>(logos.keySet());

        key = keysAsArray.get(generator.nextInt(keysAsArray.size()));
        logo.setImageDrawable(logos.get(key));

        nextBtn.setEnabled(Boolean.FALSE);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!keysAsArray.isEmpty()) {
                    mText.setText("");
                    skipBtn.setEnabled(Boolean.TRUE);
                    tStart = System.currentTimeMillis();
                    key = keysAsArray.get(generator.nextInt(keysAsArray.size()));
                    logo.setImageDrawable(logos.get(key));
                    mSubmit.setEnabled(Boolean.TRUE);
                    nextBtn.setEnabled(Boolean.FALSE);
                }
                else
                {
                    //Start an activity
                }

            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GovtOrg.this);
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
                Toast.makeText(getBaseContext(), "Correct Answer was " + key, Toast.LENGTH_LONG).show();

            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans = mText.getText().toString();
                ans = ans.trim();
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                String tempKey = key.replaceAll("\\B.|\\P{L}", "").toUpperCase();

                double elapsedSeconds = tDelta / 2000.0;

                if (ans != "" || ans != " ") {
                    if (key.equalsIgnoreCase(ans) || (tempKey.equalsIgnoreCase(ans) && tempKey.length() > 1)) {
                        Toast.makeText(getBaseContext(), "Correct Answer!", Toast.LENGTH_LONG).show();
                        keysAsArray.remove(key);
                        mSubmit.setEnabled(Boolean.FALSE);
                        skipBtn.setEnabled(Boolean.FALSE);
                        nextBtn.setEnabled(Boolean.TRUE);
                        if ((int) elapsedSeconds < 5)
                            score += 10 - ((int) elapsedSeconds + penaltyTime);
                        else
                            score += 5 - penaltyTime;

                    } else if (getCode(key).equalsIgnoreCase(getCode(ans)) || key.contains(ans)) {
                        Toast.makeText(getBaseContext(), "Almost Correct Answer!", Toast.LENGTH_SHORT).show();
                        keysAsArray.remove(key);
                        mSubmit.setEnabled(Boolean.FALSE);
                        skipBtn.setEnabled(Boolean.FALSE);
                        nextBtn.setEnabled(Boolean.TRUE);
                        if ((int) elapsedSeconds < 5)
                            score += 9 - ((int) elapsedSeconds + penaltyTime);
                        else
                            score += 4 - penaltyTime;

                    } else {
                        Toast.makeText(getBaseContext(), "Hard Luck, Try Again!", Toast.LENGTH_SHORT).show();
                        score += -1;
                        if (penaltyTime < 4)
                            penaltyTime++;
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Please provide valid input", Toast.LENGTH_SHORT).show();
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