package testing.example.com.codesprint;

import android.content.DialogInterface;
import android.content.Intent;
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

import testing.example.com.codesprint.Utils.SharedPreferenceUtils;

import static android.widget.Toast.makeText;

public class GovtOrg extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
    HashMap<String, Integer> logos = new HashMap<>();
    HashMap<String, String> infos = new HashMap<>();
    ArrayList<String> keysAsArray;
    boolean toggleState;
    private TextToSpeech tts;

    int high = 0;

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
    private TextView mTimer;
    private Toast toast;

    int size = 0;


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

    //Bitmap Size reduction
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
        setContentView(R.layout.activity_govt_org);

        tts = new TextToSpeech(this, this);
        tts.setLanguage(Locale.UK);
        tts.setSpeechRate(0.7f);


        mText = (EditText) findViewById(R.id.txt);
        mScore = (TextView) findViewById(R.id.score);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        infoBtn = (ImageButton) findViewById(R.id.info);
        skipBtn = (Button) findViewById(R.id.skipBtn);
        logo = (ImageView) findViewById(R.id.logo);
        mSubmit = (Button) findViewById(R.id.submit);
        mTimer = (TextView) findViewById(R.id.timer);
        final Random generator = new Random();

        toggleState = SharedPreferenceUtils.getToggleStatus(getBaseContext(),toggleState);


        logos.put("Delhi Metro", R.drawable.metro);
        infos.put("Delhi Metro", "This Railway system is serving Delhi and National Capital Region in India.\n" +
                "This is the world's 12th largest system in terms of both length and number of stations.\n" +
                "It was started under leadership of E.Sreedharan");

        logos.put("Airports Authority of India", R.drawable.airport_authority_of_india);
        infos.put("Airports Authority of India", "This organisation is under the Ministry of Civil Aviation and is responsible for creating, upgrading, maintaining and managing civil aviation infrastructure in India.\n" +
                "It provides Air traffic management (ATM) services over Indian airspace and adjoining oceanic areas.\n" +
                " It also manages a total of 125 Airports, including 18 International Airports, 7 Customs Airports, 78 Domestic Airports and 26 Civil enclaves at Military Airfields");

        logos.put("Archaeological Survey of India", R.drawable.archaeological);
        infos.put("Archaeological Survey of India", "This is an Indian Government agency attached to the Ministry of Culture that is responsible for Archaeological Research and the conservation and preservation of cultural monuments in the country.\n" +
                "It was founded in 1861 by Alexander Cunningham who also became its first Director-General.");

        logos.put("Indian Army", R.drawable.army);
        infos.put("Indian Army", "This is the land-based branch and the largest component of the Indian Forces.\n" +
                " The President serves as the Supreme Commander of this organisation.\n" +
                "The primary mission is to ensure national security and unity, defending the nation from external aggression and threats, and maintaining peace and security within its borders.\n" +
                "It is the 2nd largest standing army in the world, with 1,325,000 active troops and 1,155,000 reserve troops");


        logos.put("Make In India", R.drawable.makeinindia);
        infos.put("Make In India", "It is an initiative launched by the Government of India to encourage multi-national, as well as national companies to manufacture their products in India.\n" +
                "It was launched by Prime Minister Narendra Modi on 25 September 2014.\n" +
                "India emerged, after initiation of the programme in 2015 as the top destination globally for foreign direct investment, surpassing the United States of America as well as the People's Republic of China.\n" +
                "In 2015, India received US$63 billion in FDI");

        logos.put("UGC", R.drawable.ugc);
        infos.put("UGC", "This organisation of India is a statutory body set up by the Indian Union government under Ministry of Human Resource Development, and is charged with coordination, determination and maintenance of standards of higher education.\n" +
                "It provides recognition to universities in India, and disburses funds to such recognised universities and colleges");

        logos.put("Aadhaar", R.drawable.aadhar);
        infos.put("Aadhaar", "The Unique Identification Authority of India (UIDAI) is a central government agency of India.\n" +
                "Its objective is to collect the biometric and demographic data of residents, store them in a centralised database, and issue a 12-digit unique identity number called _______ to each resident." +
                "It is considered the world's largest national identification number project");

        logos.put("Air India", R.drawable.air);
        infos.put("Air India", "It is the flag carrier airline of India and the third-largest airline in India in terms of passengers carried, after IndiGo and Jet Airways.\n" +
                "It is owned by a Government of India enterprise, and operates a fleet of Airbus and Boeing aircraft serving 85 domestic and international destinations.\n" +
                "The airline was founded by J. R. D. Tata as Tata Airlines in 1932");

        logos.put("Sarva Shiksha Abhiyan", R.drawable.shiksha);
        infos.put("Sarva Shiksha Abhiyan", "It is an Indian Government programme aimed at the universalisation of elementary education \"in a time bound manner\", as mandated by the 86th Amendment to the Constitution of India making free and compulsory education to children between the ages of 6 to 14 (estimated to be 205 million children in 2001) a fundamental right.\n" +
                "The programme was pioneered by former Indian Prime Minister Atal Bihari Vajpayee.");

        logos.put("BSNL", R.drawable.bsnl);
        infos.put("BSNL", "It is an Indian state-owned telecommunications company headquartered in New Delhi.\n" +
                "It was incorporated on 15 September 2000 and took over the business of providing of telecom services and network management from the erstwhile Central Government Departments of Telecom Services (DTS) and Telecom Operations (DTO), with effect from 1 October 2000 on a going concern basis.\n" +
                "It is the largest provider of fixed telephony and broadband services with more than 60% market share and Fifth largest mobile telephony provider in India.");

        logos.put("ISRO", R.drawable.isro);
        infos.put("ISRO", "It is the only Space Agency in world to reach Mars in its very first attempt and is headed by the Government of Republic of India.\n" +
                "It is headquartered in the city of Bengaluru. Its vision is to \"harness space technology for national development\", while pursuing space science research and planetary exploration\n" +
                "Formed in 1969, it superseded the erstwhile Indian National Committee for Space Research (INCOSPAR) established in 1962 by the efforts of independent India's first Prime Minister, Jawaharlal Nehru, and his close aide and scientist Vikram Sarabhai.");

        logos.put("Swatch Bharat Abhiyan", R.drawable.swatch);
        infos.put("Swatch Bharat Abhiyan", "It is a national campaign by the Government of India, covering 4,041 statutory cities and towns, to clean the streets, roads and infrastructure of the country.\n" +
                "The campaign was officially launched on 2 October 2014 at Rajghat, New Delhi, by Prime Minister Narendra Modi. It is India's biggest ever cleanliness drive with 3 million government employees and school and college students of India participating in this event.");

        logos.put("Digital India", R.drawable.digital_india_logo_small);
        infos.put("Digital India", "It is a campaign launched by the Government of India to ensure that Government services are made available to citizens electronically by improving online infrastructure and by increasing Internet connectivity or by making the country digitally empowered in the field of technology.\n" +
                "It was launched on 1 July 2015 by Prime Minister Narendra Modi.\n" +
                "The initiative includes plans to connect rural areas with high-speed internet networks.");

        logos.put("Lion Capital", R.drawable.emblem1);
        infos.put("Lion Capital", "It is a sculpture of four Asiatic lions standing back to back, on an elaborate base that includes other animals.\n" +
                "A graphic representation of it was adopted as the official Emblem of India in 1950.\n" +
                "It was originally placed atop the Aśoka pillar at the important Buddhist site of Sarnath by the Emperor Ashoka, in about 250 BCE.\n" +
                "The pillar, sometimes called the Aśoka Column, is still in its original location, but the emblem is now in the Sarnath Museum, in the state of Uttar Pradesh, India");

        logos.put("Hindustan Petroleum", R.drawable.hindustanpetroleum);
        infos.put("Hindustan Petroleum", "It is an Indian state-owned oil and natural gas company with its headquarters at Mumbai, Maharashtra.\n" +
                "It has about 25% marketing share in India among PSUs and a strong marketing infrastructure.\n" +
                "The Government of India owns 51.11% shares in this and others are distributed amongst financial institutes, public and other investors.\n" +
                "The company is ranked 367th on the Fortune Global 500 list of the world's biggest corporations as of 2016.");

        logos.put("Indian Post", R.drawable.indian_post);
        infos.put("Indian Post", "It is a government-operated postal system in India.\n" +
                "Generally referred to within India as \"the post office\", it is the most widely distributed postal system in the world.\n" +
                "The postal service is under the Department of Posts, which is part of the Ministry of Communications and Information Technology of the Government of India.");

        logos.put("Indian Oil", R.drawable.indianoil);
        infos.put("Indian Oil", "It is India’s Largest Commercial Enterprise, with a net profit of ₹103.99 billion (US$1.5 billion) for the financial year 2015-16.\n" +
                "Standing true to its corporate vision of being ‘The Energy of India’, This company has been successfully meeting the energy demands of India for more than five decades.\n" +
                "Being India’s flagship national oil company, this company with a work-force of 33,000 efficient minds is living their vision of becoming ‘a globally admired company’.\n" +
                "It is the leading Indian Corporate in Fortune’s prestigious ‘Global 500’ listing of world’s largest corporates at 161st position for the year 2016.");

        keysAsArray = new ArrayList<String>(logos.keySet());
        size = keysAsArray.size();

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

                    int temp = (secs + (mins * 60));

                    if (temp > size * 10) {
                        //10 seconds max per question and 3 marks deducted for each violation
                        score -= Math.round(((temp - (size * 10)) / 10) * 3);
                    }
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
                    mTimer.setText("0:00");

                    if (score > high) {
                        SharedPreferenceUtils.setHighScoreOrg(getBaseContext(), score);
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(GovtOrg.this);
                    builder.setMessage("Congratulations! You have finished the Quiz.\n" + "Your Final Score is " + score).setCancelable(false).
                            setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(GovtOrg.this, Intermediate.class));
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
                        score += 5;

                        if (toggleState) {
                            speakOut("Good! Correct Answer is " + key);
                        }

                    } else if (getCode(key).equals(getCode(ans)) && ans.length() > 1) {
                        t = 0;
                        pause = true;
                        mTimer.setTextColor(Color.BLUE);
                        timeSwapBuff += timeInMilliseconds;
                        handler.removeCallbacks(updateTimer);
                        t = 1;

                        toast = makeText(getBaseContext(), "Almost! Correct Answer is " + key, Toast.LENGTH_SHORT);
                        toast.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 1000);


                        keysAsArray.remove(key);
                        mSubmit.setEnabled(Boolean.FALSE);
                        skipBtn.setEnabled(Boolean.FALSE);
                        nextBtn.setEnabled(Boolean.TRUE);

                        score += 3;

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


        //logo.setImageDrawable(logos.get("UAE"));


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

    public Runnable updateTimer = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - starttime;
            updatedtime = timeSwapBuff + timeInMilliseconds;
            secs = (int) (updatedtime / 1000);
            mins = secs / 60;
            secs = secs % 60;
            mTimer.setText("" + mins + ":" + String.format("%02d", secs));

            //Game Over if you take more than 5 minutes
            if (mins * 60 + secs > 300) {

                handler.removeCallbacks(updateTimer);
                handler.postDelayed(last, 0);


            }
            mTimer.setTextColor(Color.RED);
            handler.postDelayed(this, 0);
        }
    };

    Runnable last = new Runnable() {
        @Override
        public void run() {
            starttime = 0L;
            timeInMilliseconds = 0L;
            timeSwapBuff = 0L;
            updatedtime = 0L;
            t = 1;
            secs = 0;
            mins = 0;
            milliseconds = 0;
            handler.removeCallbacks(updateTimer);
            mTimer.setText("0:00");

            int temp = (secs + (mins * 60));

            if (temp > size * 10) {
                //10 seconds max per question and 3 marks deducted for each violation
                score -= Math.round(((temp - (size * 10)) / 10) * 3);
            }
            if (score < 0) {
                score = 0;
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(GovtOrg.this);
            builder.setMessage("Sorry! Times Up.\n" + "Your Final Score is " + score).setCancelable(false).
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(GovtOrg.this, Intermediate.class));
                        }
                    });
            if (toggleState) {
                speakOut("Sorry! Times Up." + "Your Final Score is " + score);
            }
            if (score > high) {
                SharedPreferenceUtils.setHighScoreOrg(getBaseContext(), score);
            }
            AlertDialog alert = builder.create();
            alert.show();

            handler.removeCallbacks(this);
        }
    };


}