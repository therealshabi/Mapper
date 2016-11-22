package testing.example.com.codesprint;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import static android.widget.Toast.makeText;

public class Mapper extends AppCompatActivity implements TextToSpeech.OnInitListener {
    public static final String MyPREFERENCES = "MyPrefs";
    EditText mText;
    TextView mScore;
    TextView mTimer;
    Button nextBtn;
    Button skipBtn;
    ImageButton infoBtn;
    ImageView logo;
    Button mSubmit;
    long tStart;
    String key = "";
    int score = 0;
    int penaltyTime = 0;

    private boolean pause;


    long starttime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedtime = 0L;
    int t = 1;
    int secs = 0;
    int mins = 0;
    int milliseconds = 0;
    Handler handler = new Handler();

    Toast toast;

    HashMap<String, Integer> logos = new HashMap<>();
    HashMap<String, String> infos = new HashMap<>();
    ArrayList<String> keysAsArray;

    boolean toggleState;

    SharedPreferences sharedPref;
    private TextToSpeech tts;
    public int timeRemaining = 50000;

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

    static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int inSampleSize = 1;
        if (options.outHeight > reqHeight || options.outWidth > reqWidth) {
            final int halfHeight = options.outHeight / 2;
            final int halfWidth = options.outWidth / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

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
        mTimer = (TextView) findViewById(R.id.timer);

        tts = new TextToSpeech(this, this);
        tts.setLanguage(Locale.UK);
        tts.setSpeechRate(0.7f);


        sharedPref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        toggleState = Boolean.parseBoolean(getResources().getString(R.string.toggleState));
        toggleState = sharedPref.getBoolean(getString(R.string.toggleState), toggleState);

        logos.put("Andorra", R.drawable.ad);
        infos.put("Andorra", "Capital: ______ la Vella\n" +
                "Dialing code: +376\n" +
                "Currency: Euro\n" +
                "Population: 79,218 (2013) World Bank\n" +
                "Official language: Catalan");


        logos.put("United Arab Emirates", R.drawable.ae);
        infos.put("United Arab Emirates", "Capital: Abu Dhabi\n" +
                "Dialing code: +971\n" +
                "Currency: Dirham\n" +
                "Population: 9.346 million (2013) World Bank\n" +
                "President: Khalifa bin Zayed Al Nahyan");


        logos.put("Afghanistan", R.drawable.af);
        infos.put("Afghanistan", "Capital: Kabul\n" +
                "Currency: Afghan afghani\n" +
                "President: Ashraf Ghani\n" +
                "Population: 30.55 million (2013) World Bank\n" +
                "Official languages: Pashto, Dari");

        logos.put("Albania", R.drawable.al);
        infos.put("Albania", "Capital: Tirana\n" +
                "Dialing code: +355\n" +
                "Currency: Lek\n" +
                "President: Bujar Nishani\n" +
                "Population: 2.774 million (2013) World Bank");


        logos.put("Argentina", R.drawable.ar);
        infos.put("Argentina", "Capital: Buenos Aires\n" +
                "Dialing code: +54\n" +
                "Currency: Peso\n" +
                "President: Mauricio Macri\n" +
                "Population: 41.45 million (2013)");

        logos.put("Australia", R.drawable.au);
        infos.put("Australia", "Capital: Canberra\n" +
                "Dialing code: +61\n" +
                "Currency: Dollar\n" +
                "Population: 23.13 million (2013) World Bank\n" +
                "Prime minister: Malcolm Turnbull");

        logos.put("Armenia", R.drawable.am);
        infos.put("Armenia", "Capital: Yerevan\n" +
                "Dialing code: +374\n" +
                "Continent: Asia\n" +
                "Population: 2.977 million (2013) World Bank\n" +
                "Currencies: Noah's Ark silver coins, Dram");


        logos.put("Angola", R.drawable.ao);
        infos.put("Angola", "Capital: Luanda\n" +
                "Dialing code: +244\n" +
                "Currency: kwanza\n" +
                "President: José Eduardo dos Santos\n" +
                "Population: 21.47 million (2013) World Bank\n" +
                "Official language: Portuguese");

        logos.put("Austria", R.drawable.at);
        infos.put("Austria", "Capital: Vienna\n" +
                "Dialing code: +43\n" +
                "Currency: Euro\n" +
                "Population: 8.474 million (2013) World Bank\n" +
                "Official languages: German, Hungarian, Slovenian, Austrian German");


        logos.put("Azerbaijan", R.drawable.az);
        infos.put("Azerbaijan", "Capital: Baku\n" +
                "Dialing code: +994\n" +
                "Currency: Manat\n" +
                "Population: 9.417 million (2013) World Bank\n" +
                "Official language: Azerbaijani");


        logos.put("Barbados", R.drawable.bb);
        infos.put("Barbados", "Capital: Bridgetown\n" +
                "Dialing code: +1\n" +
                "Currency: Dollar\n" +
                "Continent: North America\n" +
                "Official language: English");


        logos.put("Bangladesh", R.drawable.bd);
        infos.put("Bangladesh", "Capital: Dhaka\n" +
                "Dialing code: +880\n" +
                "Currency: Taka\n" +
                "Prime minister: Sheikh Hasina\n" +
                "Population: 156.6 million (2013) World Bank\n" +
                "President: Abdul Hamid");


        logos.put("Belgium", R.drawable.be);
        infos.put("Belgium", "Capital: City of Brussels\n" +
                "Dialing code: +32\n" +
                "Currency: Euro\n" +
                "Population: 11.2 million (2013) World Bank\n" +
                "Official languages: French, Dutch, German");


        logos.put("Brazil", R.drawable.br);
        infos.put("Brazil", "Capital: Brasília\n" +
                "Dialing code: +55\n" +
                "Currency: Real\n" +
                "President: Michel Temer\n" +
                "Population: 200.4 million (2013) World Bank\n" +
                "Official language: Portuguese");

        logos.put("Bhutan", R.drawable.bt);
        infos.put("Bhutan", "Capital: Thimphu\n" +
                "King: Jigme Khesar Namgyel Wangchuck\n" +
                "Prime minister: Tshering Tobgay\n" +
                "Currencies: Ngultrum, Indian rupee\n" +
                "Official language: Dzongkha");


        logos.put("Canada", R.drawable.ca);
        infos.put("Canada", "Capital: Ottawa\n" +
                "Dialing code: +1\n" +
                "Currency: Dollar\n" +
                "Population: 35.16 million (2013) World Bank\n" +
                "Prime minister: Justin Trudeau\n" +
                "Provinces: Ontario, British Columbia, Québec, Alberta, more");

        logos.put("Switzerland", R.drawable.ch);
        infos.put("Switzerland", "Dialing code: +41\n" +
                "Currency: Franc\n" +
                "Population: 8.081 million (2013) World Bank\n" +
                "Official languages: French, Romansh, German, Italian\n" +
                "Colleges and Universities: ETH Zurich, more");

        logos.put("Cyprus", R.drawable.cy);
        infos.put("Cyprus", "Capital: Nicosia\n" +
                "Dialing code: +357\n" +
                "Currency: Euro\n" +
                "Official languages: Turkish, Greek\n");

        logos.put("China", R.drawable.cn);
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
        logo.setImageBitmap(decodeSampledBitmapFromResource(getResources(), logos.get(key), 130, 130));

        //Condition For Timer Start
        if (t == 1) {

//timer will start
            starttime = SystemClock.uptimeMillis();
            handler.postDelayed(updateTimer, 0);
            t = 0;
        }
        //Condition For Timer Pause
        else {
//timer will pause
            mTimer.setTextColor(Color.BLUE);
            timeSwapBuff += timeInMilliseconds;
            handler.removeCallbacks(updateTimer);
            t = 1;
        }

        nextBtn.setEnabled(Boolean.FALSE);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//pause will be false when skip button is called as we want to resume our timer right away
                if (pause == true) {
                    starttime = SystemClock.uptimeMillis();
                    handler.postDelayed(updateTimer, 0);
                }
                if (!keysAsArray.isEmpty()) {
                    mText.setText("");
                    skipBtn.setEnabled(Boolean.TRUE);
                    tStart = System.currentTimeMillis();
                    key = keysAsArray.get(generator.nextInt(keysAsArray.size()));
                    logo.setImageBitmap(decodeSampledBitmapFromResource(getResources(), logos.get(key), 130, 130));
                    mSubmit.setEnabled(Boolean.TRUE);
                    nextBtn.setEnabled(Boolean.FALSE);
                } else {

                    score -= ((secs + (mins * 60)) / 15) * 3;
                    if (score < 0) {
                        score = 0;
                    }

                    starttime = 0L;
                    timeInMilliseconds = 0L;
                    timeSwapBuff = 0L;
                    updatedtime = 0L;
                    t = 1;
                    secs = 0;
                    mins = 0;
                    milliseconds = 0;
                    handler.removeCallbacks(updateTimer);
                    mTimer.setText("TIMER - 0:00");

                    AlertDialog.Builder builder = new AlertDialog.Builder(Mapper.this);
                    builder.setMessage("Congratulations! You have finished the Quiz.\n" + "Your Final Score is " + score).setCancelable(false).
                            setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(Mapper.this, Intermediate.class));
                                }
                            });
                    if (toggleState) {
                        speakOut("Congratulations! You have finished the Quiz." + "Your Final Score is " + score);
                    }
                    AlertDialog alert = builder.create();
                    alert.show();
                }

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
                pause = false;
                mSubmit.setEnabled(Boolean.FALSE);
                skipBtn.setEnabled(Boolean.FALSE);
                nextBtn.setEnabled(Boolean.TRUE);
                keysAsArray.remove(key);
                score -= 1;
                if (score < 0) {
                    score = 0;
                }
                mScore.setText("Score : " + score);
                toast = Toast.makeText(getBaseContext(), "Correct Answer was " + key, Toast.LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 1000);

                if (toggleState) {
                    speakOut("Correct Answer was " + key);
                }

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

                        t = 0;
                        pause = true;
                        mTimer.setTextColor(Color.BLUE);
                        timeSwapBuff += timeInMilliseconds;
                        handler.removeCallbacks(updateTimer);
                        t = 1;

                        toast = Toast.makeText(getBaseContext(), "Correct Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 1000);

                        mSubmit.setEnabled(Boolean.FALSE);
                        skipBtn.setEnabled(Boolean.FALSE);
                        nextBtn.setEnabled(Boolean.TRUE);
                        keysAsArray.remove(key);
                        if ((int) elapsedSeconds < 5)
                            score += 10 - ((int) elapsedSeconds + penaltyTime);
                        else
                            score += 5 - penaltyTime;

                        if (toggleState) {
                            speakOut("Good! Correct Answer is " + key);
                        }

                    } else if (getCode(key).equals(getCode(ans)) && ans.length() > 1) {
                        toast = makeText(getBaseContext(), "Almost! Correct Answer is " + key, Toast.LENGTH_SHORT);
                        toast.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 1000);

                        t = 0;
                        pause = true;
                        mTimer.setTextColor(Color.BLUE);
                        timeSwapBuff += timeInMilliseconds;
                        handler.removeCallbacks(updateTimer);
                        t = 1;

                        keysAsArray.remove(key);
                        mSubmit.setEnabled(Boolean.FALSE);
                        skipBtn.setEnabled(Boolean.FALSE);
                        nextBtn.setEnabled(Boolean.TRUE);
                        if ((int) elapsedSeconds < 5)
                            score += 9 - ((int) elapsedSeconds + penaltyTime);
                        else
                            score += 4 - penaltyTime;

                        if (toggleState) {
                            speakOut("Almost! Correct Answer is " + key);
                        }

                    } else {
                        toast = makeText(getBaseContext(), "Hard Luck, Try Again!", Toast.LENGTH_SHORT);
                        toast.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 1000);
                        score += -1;
                        if (penaltyTime < 4)
                            penaltyTime++;

                        if (toggleState) {
                            speakOut("Hard Luck, Try Again!");
                        }

                    }
                } else {
                    toast = makeText(getBaseContext(), "Please provide a valid input", Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1000);

                }
                if (score < 0) {
                    score = 0;
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

    public Runnable updateTimer = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - starttime;
            updatedtime = timeSwapBuff + timeInMilliseconds;
            secs = (int) (updatedtime / 1000);
            mins = secs / 60;
            secs = secs % 60;
            mTimer.setText("TIMER - " + mins + ":" + String.format("%02d", secs));
            mTimer.setTextColor(Color.RED);
            handler.postDelayed(this, 0);
        }
    };
}