package testing.example.com.codesprint.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import static testing.example.com.codesprint.R.id.highScore;

/**
 * Created by shahbaz on 23/11/16.
 */

public class SharedPreferenceUtils {
    private static final String SHARED_PREFERENCES_FILE = "MyPrefs";
    private static final String SHARED_PREFERENCES_HIGH_SCORE_ORG = "high_score_org";
    private static final String SHARED_PREFERENCES_HIGH_SCORE_FLAG = "high_score_flag";
    private static final String SHARED_PREFERENCES_TOGGLE_BUTTON = "toggleButton";

    /**
     * Change the High Score for Org  sharedpreferences
     */
    public static void setHighScoreOrg(Context context, int highScore) {
        //Get shared preferences file
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);

        //Get editor
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Add username
        editor.putInt(SHARED_PREFERENCES_HIGH_SCORE_ORG, highScore);

        editor.commit();
    }

    /**
     * Get the High Score for Org from shared preferences
     */
    public static int getHighScoreOrg(Context context, int highScore) {
        //Get shared preferences file
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);

        return sharedPreferences.getInt(SHARED_PREFERENCES_HIGH_SCORE_ORG, highScore);
    }

    /**
     * Change the High Score for Flag  sharedpreferences
     */
    public static void setHighScoreFlag(Context context, int highScore) {
        //Get shared preferences file
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);

        //Get editor
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Add username
        editor.putInt(SHARED_PREFERENCES_HIGH_SCORE_FLAG, highScore);

        editor.commit();
    }

    /**
     * Get the High Score Flag from shared preferences
     */
    public static int getHighScoreFlag(Context context, int highScore) {
        //Get shared preferences file
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);

        return sharedPreferences.getInt(SHARED_PREFERENCES_HIGH_SCORE_FLAG, highScore);
    }

    /**
     * Change the Sound Toggle sharedpreferences
     */
    public static void setToggleStatus(Context context, boolean status) {
        //Get shared preferences file
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);

        //Get editor
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Add username
        editor.putBoolean(SHARED_PREFERENCES_TOGGLE_BUTTON, status);

        editor.commit();
    }

    /**
     * Get the Soung Toggle from shared preferences
     */
    public static boolean getToggleStatus(Context context, boolean status) {
        //Get shared preferences file
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(SHARED_PREFERENCES_TOGGLE_BUTTON, status);
    }
}
