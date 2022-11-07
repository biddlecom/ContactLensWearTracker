package com.danielbiddlecom.contactlensweartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class EditLastWornDate extends AppCompatActivity {

    //Getting the TextViews, EditTexts and Button.
    private AdView mAdView;
    //TextView mEditLastWornDateInstructionsTV;
    //TextView mEditLastWornDateMonthTitleTV;
    EditText mEditLastWornDateMonthET;
    //TextView mEditLastWornDateDayTitleTV;
    EditText mEditLastWornDateDayET;
    //TextView mEditLastWornDateYearTitleTV;
    EditText mEditLastWornDateYearET;
    Button mEditLastWornDateSubmitButton;

    //Getting the member variables that will hold the information from the EditTexts.
    String mNewMonthString;
    String mNewDayString;
    String mNewYearString;
    String mFinalNewDateString;

    //Shared Prefs Member Variables.
    String mLastWornActualDatePref = "lastWornActualDatePref";

    //Shared Prefs Constants.
    public static final String CONTACT_LENS_WEAR_TRACKER_PREFS =
            "com.danielbiddlecom.contactlensweartracker.CONTACT_LENS_TRACKER_PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_last_worn_date);

        //Getting the Month Edit Text.
        mEditLastWornDateMonthET = findViewById(R.id.edit_last_worn_date_month_edit_text);

        //Getting the Day Edit Text.
        mEditLastWornDateDayET = findViewById(R.id.edit_last_worn_date_day_edit_text);

        //Getting the Year Edit Text.
        mEditLastWornDateYearET = findViewById(R.id.edit_last_worn_date_year_edit_text);

        //Getting the Submit Button and setting an OnClickListener on it.
        mEditLastWornDateSubmitButton = findViewById(R.id.edit_last_worn_date_submit_button);
        mEditLastWornDateSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calling the method that will get the data from the EditTexts and then convert it
                //into the proper date format (MM-DD-YYYY). Then it will save that newly formatted
                //date into the Shared Prefs before returning the user to the MainActivity.
                submitButtonClicked();
            }
        });

        //Google Admob ads.
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //Finding the Adview and showing ads in it.
        mAdView = findViewById(R.id.google_ad_view_edit_last_worn_date);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Overrides for Google Admob.
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        });

    } //End of onCreate.

    //This method will get the user data from the EditTexts and then perform a check to make sure
    //they entered the correct amount of digits into each edit text.  If they entered the correct
    //amount of digits in each field, convert it into the proper date format (MM-dd-yyyy). Then we
    //will save that newly formatted date into the Shared Prefs before returning the user back to
    //the MainActivity.
    private void submitButtonClicked() {
        //Getting the user data from the Month edit text and saving it in a string.
        mNewMonthString = mEditLastWornDateMonthET.getText().toString();
        //Checking to make sure the mNewMonthString is equal to 2 (digits). If they didn't enter
        //anything or if they only entered 1 digit, show a toast letting the user know they need to
        //check the amount of numbers they entered into the Month edit text.
        if (mNewMonthString.length() != 2) {
            Toast.makeText(EditLastWornDate.this, getText(R.string.edit_last_worn_date_toast_wrong_length),
                    Toast.LENGTH_SHORT).show();
        }

        //Getting the user data from the Day edit text and saving it in a string.
        mNewDayString = mEditLastWornDateDayET.getText().toString();
        //Checking to make sure the mNewDayString is equal to 2 (digits). If they didn't enter anything
        //or if they only entered 1 digit, show a toast letting the user know they need to check the
        //amount of numbers they entered into the Day edit text.
        if (mNewDayString.length() != 2) {
            Toast.makeText(EditLastWornDate.this, getText(R.string.edit_last_worn_date_toast_wrong_length),
                    Toast.LENGTH_SHORT).show();
        }

        //Getting the user data from the Year edit text and saving it in a string.
        mNewYearString = mEditLastWornDateYearET.getText().toString();
        //Checking to make sure the mNewYearString is equal to 4 (digits). If they didn't enter anything
        //or if they only entered 1, 2 or 3 digits, show a toast letting the user know they need to
        //check the amount of numbers they entered into the Year edit text.
        if (mNewYearString.length() != 4) {
            Toast.makeText(EditLastWornDate.this, getText(R.string.edit_last_worn_date_toast_wrong_length),
                    Toast.LENGTH_SHORT).show();
        }

        //TODO: make the code stop from going beyond this point if the user enters less than the
        // required amount of digits.  Right now the code just continues on regardless of the amount
        // of digits the user enters.

        //Formatting the Month, Day and Year strings into the proper date format (MM-dd-yyyy).
        //We will save the final formatted string into the mFinalNewDateString variable.
        mFinalNewDateString = mNewMonthString + "-" + mNewDayString + "-" + mNewYearString;

        //Putting the newly formatted date string into the shared preferences.
        //Getting an instance of the Contact Lens Wear Tracker shared preference.
        SharedPreferences contactLensWearSharedPreferences = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);
        //Save the newly formed date string into the Shared Preferences.
        SharedPreferences.Editor sharedPrefsNewWornDateEditor = contactLensWearSharedPreferences.edit();
        sharedPrefsNewWornDateEditor.putString(mLastWornActualDatePref, mFinalNewDateString);
        sharedPrefsNewWornDateEditor.apply();
        //Show a toast message letting the user know that their newly formed date has been successfully
        //saved.
        Toast.makeText(EditLastWornDate.this, getText(R.string.edit_last_worn_date_toast_successfully_saved),
                Toast.LENGTH_LONG).show();

        //Send the user back to the MainActivity where they will see their new chosen date displayed
        //as the Last Worn date.
        Intent intentToMainActivity = new Intent(EditLastWornDate.this, MainActivity.class);
        startActivity(intentToMainActivity);
    }
}