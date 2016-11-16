package testing.example.com.codesprint;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import static testing.example.com.codesprint.R.drawable.ad;
import static testing.example.com.codesprint.R.drawable.ae;
import static testing.example.com.codesprint.R.drawable.af;
import static testing.example.com.codesprint.R.drawable.al;
import static testing.example.com.codesprint.R.drawable.am;
import static testing.example.com.codesprint.R.drawable.ao;
import static testing.example.com.codesprint.R.drawable.ar;
import static testing.example.com.codesprint.R.drawable.at;
import static testing.example.com.codesprint.R.drawable.au;
import static testing.example.com.codesprint.R.drawable.az;
import static testing.example.com.codesprint.R.drawable.bb;
import static testing.example.com.codesprint.R.drawable.bd;
import static testing.example.com.codesprint.R.drawable.be;
import static testing.example.com.codesprint.R.drawable.br;
import static testing.example.com.codesprint.R.drawable.bt;
import static testing.example.com.codesprint.R.drawable.ca;
import static testing.example.com.codesprint.R.drawable.ch;
import static testing.example.com.codesprint.R.drawable.cn;
import static testing.example.com.codesprint.R.drawable.cy;

public class Mapper extends AppCompatActivity implements TextToSpeech.OnInitListener {
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

    private TextToSpeech tts;

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


    //Soundex Algorithm

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapper);

        mText = (EditText) findViewById(R.id.txt);
        mScore = (TextView) findViewById(R.id.score);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        infoBtn = (ImageButton) findViewById(R.id.info);
        skipBtn = (Button) findViewById(R.id.skipBtn);
        logo = (ImageView) findViewById(R.id.logo);
        mSubmit = (Button) findViewById(R.id.submit);
        final Random generator = new Random();

        tts = new TextToSpeech(this, this);
        tts.setLanguage(Locale.UK);
        tts.setSpeechRate(0.7f);

        Bitmap temp = ((BitmapDrawable)getDrawable(ad)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable ad = new BitmapDrawable(getResources(),temp);

        logos.put("Andorra", ad);
        infos.put("Andorra", "Capital: Andorra la Vella\n" +
                "Dialing code: +376\n" +
                "Currency: Euro\n" +
                "Population: 79,218 (2013) World Bank\n" +
                "Official language: Catalan");

        temp = ((BitmapDrawable)getDrawable(ae)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable ae = new BitmapDrawable(getResources(),temp);

        logos.put("United Arab Emirates", ae);
        infos.put("United Arab Emirates", "Capital: Abu Dhabi\n" +
                "Dialing code: +971\n" +
                "Currency: United Arab Emirates dirham\n" +
                "Population: 9.346 million (2013) World Bank\n" +
                "President: Khalifa bin Zayed Al Nahyan");

        temp = ((BitmapDrawable)getDrawable(af)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable af = new BitmapDrawable(getResources(),temp);

        logos.put("Afghanistan", af);
        infos.put("Afghanistan", "Capital: Kabul\n" +
                "Currency: Afghan afghani\n" +
                "President: Ashraf Ghani\n" +
                "Population: 30.55 million (2013) World Bank\n" +
                "Official languages: Pashto, Dari");

        temp = ((BitmapDrawable)getDrawable(al)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable al = new BitmapDrawable(getResources(),temp);

        logos.put("Albania", al);
        infos.put("Albania", "Capital: Tirana\n" +
                "Dialing code: +355\n" +
                "Currency: Albanian lek\n" +
                "President: Bujar Nishani\n" +
                "Population: 2.774 million (2013) World Bank");

        temp = ((BitmapDrawable)getDrawable(ar)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable ar = new BitmapDrawable(getResources(),temp);


        logos.put("Argentina", ar);
        infos.put("Argentina", "Capital: Buenos Aires\n" +
                "Dialing code: +54\n" +
                "Currency: Argentine peso\n" +
                "President: Mauricio Macri\n" +
                "Population: 41.45 million (2013)");

        temp = ((BitmapDrawable)getDrawable(au)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable au = new BitmapDrawable(getResources(),temp);

        logos.put("Australia", au);
        infos.put("Australia", "Capital: Canberra\n" +
                "Dialing code: +61\n" +
                "Currency: Australian dollar\n" +
                "Population: 23.13 million (2013) World Bank\n" +
                "Prime minister: Malcolm Turnbull");

        temp = ((BitmapDrawable)getDrawable(am)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable am = new BitmapDrawable(getResources(),temp);

        logos.put("Armenia", am);
        infos.put("Armenia", "Capital: Yerevan\n" +
                "Dialing code: +374\n" +
                "Continent: Asia\n" +
                "Population: 2.977 million (2013) World Bank\n" +
                "Currencies: Noah's Ark silver coins, Armenian dram");

        temp = ((BitmapDrawable)getDrawable(ao)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable ao = new BitmapDrawable(getResources(),temp);

        logos.put("Angola", ao);
        infos.put("Angola", "Capital: Luanda\n" +
                "Dialing code: +244\n" +
                "Currency: Angolan kwanza\n" +
                "President: José Eduardo dos Santos\n" +
                "Population: 21.47 million (2013) World Bank\n" +
                "Official language: Portuguese");

        temp = ((BitmapDrawable)getDrawable(at)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable at = new BitmapDrawable(getResources(),temp);

        logos.put("Austria", at);
        infos.put("Austria", "Capital: Vienna\n" +
                "Dialing code: +43\n" +
                "Currency: Euro\n" +
                "Population: 8.474 million (2013) World Bank\n" +
                "Official languages: German, Hungarian, Slovenian, Austrian German");

        temp = ((BitmapDrawable)getDrawable(az)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable az = new BitmapDrawable(getResources(),temp);

        logos.put("Azerbaijan", az);
        infos.put("Azerbaijan", "Capital: Baku\n" +
                "Dialing code: +994\n" +
                "Currency: Azerbaijani manat\n" +
                "Population: 9.417 million (2013) World Bank\n" +
                "Official language: Azerbaijani");

        temp = ((BitmapDrawable)getDrawable(bb)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable bb = new BitmapDrawable(getResources(),temp);

        logos.put("Barbados", bb);
        infos.put("Barbados", "Capital: Bridgetown\n" +
                "Dialing code: +1\n" +
                "Currency: Barbadian dollar\n" +
                "Continent: North America\n" +
                "Official language: English");

        temp = ((BitmapDrawable)getDrawable(bd)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable bd = new BitmapDrawable(getResources(),temp);

        logos.put("Bangladesh", bd);
        infos.put("Bangladesh", "Capital: Dhaka\n" +
                "Dialing code: +880\n" +
                "Currency: Bangladeshi taka\n" +
                "Prime minister: Sheikh Hasina\n" +
                "Population: 156.6 million (2013) World Bank\n" +
                "President: Abdul Hamid");

        temp = ((BitmapDrawable)getDrawable(be)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable be = new BitmapDrawable(getResources(),temp);

        logos.put("Belgium", be);
        infos.put("Belgium", "Capital: City of Brussels\n" +
                "Dialing code: +32\n" +
                "Currency: Euro\n" +
                "Population: 11.2 million (2013) World Bank\n" +
                "Official languages: French, Dutch, German");

        temp = ((BitmapDrawable)getDrawable(br)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable br = new BitmapDrawable(getResources(),temp);

        logos.put("Brazil", br);
        infos.put("Brazil", "Capital: Brasília\n" +
                "Dialing code: +55\n" +
                "Currency: Brazilian real\n" +
                "President: Michel Temer\n" +
                "Population: 200.4 million (2013) World Bank\n" +
                "Official language: Portuguese");

        temp = ((BitmapDrawable)getDrawable(bt)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable bt = new BitmapDrawable(getResources(),temp);

        logos.put("Bhutan", bt);
        infos.put("Bhutan", "Capital: Thimphu\n" +
                "King: Jigme Khesar Namgyel Wangchuck\n" +
                "Prime minister: Tshering Tobgay\n" +
                "Currencies: Bhutanese ngultrum, Indian rupee\n" +
                "Official language: Dzongkha");

        temp = ((BitmapDrawable)getDrawable(ca)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable ca = new BitmapDrawable(getResources(),temp);

        logos.put("Canada", ca);
        infos.put("Canada", "Capital: Ottawa\n" +
                "Dialing code: +1\n" +
                "Currency: Canadian dollar\n" +
                "Population: 35.16 million (2013) World Bank\n" +
                "Prime minister: Justin Trudeau\n" +
                "Provinces: Ontario, British Columbia, Québec, Alberta, more");

        temp = ((BitmapDrawable)getDrawable(ch)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable ch = new BitmapDrawable(getResources(),temp);

        logos.put("Switzerland", ch);
        infos.put("Switzerland", "Dialing code: +41\n" +
                "Currency: Swiss franc\n" +
                "Population: 8.081 million (2013) World Bank\n" +
                "Official languages: French, Romansh, German, Italian\n" +
                "Colleges and Universities: ETH Zurich, more");

        temp = ((BitmapDrawable)getDrawable(cy)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable cy = new BitmapDrawable(getResources(),temp);

        logos.put("Cyprus", cy);
        infos.put("Cyprus", "Capital: Nicosia\n" +
                "Dialing code: +357\n" +
                "Currency: Euro\n" +
                "Official languages: Turkish, Greek\n" +
                "Colleges and Universities: University of Cyprus, more");

        temp = ((BitmapDrawable)getDrawable(cn)).getBitmap();
        temp = Bitmap.createScaledBitmap(temp,800,600,false);
        Drawable cn = new BitmapDrawable(getResources(),temp);

        logos.put("China", cn);
        infos.put("China", "Capital: Beijing\n" +
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

        keysAsArray = new ArrayList<String>(logos.keySet());

        key = keysAsArray.get(generator.nextInt(keysAsArray.size()));
        logo.setImageDrawable(logos.get(key));

        nextBtn.setEnabled(Boolean.FALSE);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!keysAsArray.isEmpty()) {
                    mText.setText("");
                    skipBtn.setEnabled(Boolean.TRUE);
                    tStart = System.currentTimeMillis();
                    key = keysAsArray.get(generator.nextInt(keysAsArray.size()));
                    logo.setImageDrawable(logos.get(key));
                    mSubmit.setEnabled(Boolean.TRUE);
                    nextBtn.setEnabled(Boolean.FALSE);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Mapper.this);
                    builder.setMessage("Congratulations! You have finished the Quiz.\n" + "Your Final Score is " + score).setCancelable(false).
                            setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(Mapper.this,Intermediate.class));
                                }
                            });
                    speakOut("Congratulations! You have finished the Quiz.\n" + "Your Final Score is " + score);
                    AlertDialog alert = builder.create();
                    alert.show();                }

            }
        });

        //Info Button Action
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

        //Skip Button Action
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSubmit.setEnabled(Boolean.FALSE);
                skipBtn.setEnabled(Boolean.FALSE);
                nextBtn.setEnabled(Boolean.TRUE);
                keysAsArray.remove(key);
                score -= 1;
                mScore.setText("Score : " + score);
                Toast.makeText(getBaseContext(), "Correct Answer was " + key, Toast.LENGTH_LONG).show();
                speakOut("Correct Answer was " + key);

            }
        });

        //Submit Button
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans = mText.getText().toString();
                ans = ans.trim();
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                String tempKey = key.replaceAll("\\B.|\\P{L}", "").toUpperCase();

                double elapsedSeconds = tDelta / 2000.0;

                if (!ans.equals("") && !ans.equals(" ")) {
                    if (key.equalsIgnoreCase(ans) || (tempKey.equalsIgnoreCase(ans) && tempKey.length() > 1)) {
                        Toast.makeText(getBaseContext(), "Correct Answer!", Toast.LENGTH_LONG).show();
                        mSubmit.setEnabled(Boolean.FALSE);
                        skipBtn.setEnabled(Boolean.FALSE);
                        nextBtn.setEnabled(Boolean.TRUE);
                        keysAsArray.remove(key);
                        if ((int) elapsedSeconds < 5)
                            score += 10 - ((int) elapsedSeconds + penaltyTime);
                        else
                            score += 5 - penaltyTime;

                        speakOut("Good! Correct Answer is " + key);

                    } else if (getCode(key).equals(getCode(ans)) && ans.length() > 1) {
                        Toast.makeText(getBaseContext(), "Almost Correct Answer!", Toast.LENGTH_SHORT).show();
                        keysAsArray.remove(key);
                        mSubmit.setEnabled(Boolean.FALSE);
                        skipBtn.setEnabled(Boolean.FALSE);
                        nextBtn.setEnabled(Boolean.TRUE);
                        if ((int) elapsedSeconds < 5)
                            score += 9 - ((int) elapsedSeconds + penaltyTime);
                        else
                            score += 4 - penaltyTime;
                        speakOut("Almost! Correct Answer is " + key);

                    } else {
                        Toast.makeText(getBaseContext(), "Hard Luck, Try Again!", Toast.LENGTH_SHORT).show();
                        score += -1;
                        if (penaltyTime < 4)
                            penaltyTime++;
                        speakOut("Hard Luck, Try Again!");

                    }
                } else {
                    Toast.makeText(getBaseContext(), "Please provide a valid input", Toast.LENGTH_SHORT).show();

                }
                mScore.setText("Score : " + score);
            }
        });


    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.UK);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut("");
            }

        } else {
            Log.e("TTS", "Initialization Failed!");
        }

    }

    private void speakOut(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}