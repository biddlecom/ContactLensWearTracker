package com.danielbiddlecom.contactlensweartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    //This method that will get the data from the EditTexts and then convert it
    //into the proper date format (MM-dd-yyyy). Then it will save that newly formatted
    //date into the Shared Prefs before returning the user to the MainActivity.
    private void submitButtonClicked(){
        //TODO: check to make sure the user input the correct amount of numbers into the field.
        // 2 for the month, 2 for the day and 4 for the year.
        mNewMonthString = mEditLastWornDateMonthET.getText().toString();
        //TODO: check

        mNewDayString = mEditLastWornDateDayET.getText().toString();
        //TODO: check

        mNewYearString = mEditLastWornDateYearET.getText().toString();
        //TODO: check


        //TODO: format it into MM-DD-YYYY.
        //Formatting the strings into the proper date format (MM-dd-yyyy).
        mFinalNewDateString = mNewMonthString + "-" + mNewDayString + "-" + mNewYearString;

        //TODO: save it into the shared prefs.


        //TODO: return the user to the MainActivity.

    }


}