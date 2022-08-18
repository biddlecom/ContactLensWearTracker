package com.danielbiddlecom.contactlensweartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //Getting the Buttons and TextViews.
    ImageButton mMinusButton;
    ImageButton mPlusButton;
    ImageButton mResetButton;
    TextView mMainLensCounterTV;
    TextView mMainWearStatusColorTV;
    TextView mMainLastWornActualDateTV;
    TextView mMainLastFiveSlotOneTV;
    TextView mMainLastFiveSlotTwoTV;
    TextView mMainLastFiveSlotThreeTV;
    TextView mMainLastFiveSlotFourTV;
    TextView mMainLastFiveSlotFiveTV;
    TextView mAverageWearTimeNumberTV;
    private AdView mAdView;

    //Getting the member variables that will hold the information in the textviews.
    String mCurrentLensCountString;
    String mLastWornActualDateString;
    String mLastFiveSlotOneString;
    String mLastFiveSlotTwoString;
    String mLastFiveSlotThreeString;
    String mLastFiveSlotFourString;
    String mLastFiveSlotFiveString;
    String mAverageWearTimeString;
    String mCurrentLensCountIntPref;
    int mCurrentLensCountInteger = 0;
    int mLastFiveSlotOneInteger = 0;
    int mLastFiveSlotTwoInteger = 0;
    int mLastFiveSlotThreeInteger = 0;
    int mLastFiveSlotFourInteger = 0;
    int mLastFiveSlotFiveInteger = 0;
    int mAverageWearTimeInteger = 0;

    //Boolean that will hold whether the user is new or not
    boolean mIsTheUserNew;

    //Shared Prefs Member Variables.
    String mIsTheUserNewPref = "isTheUserNewPref";
    String mCurrentLensCountPref = "currentLensCountPref";
    String mLastWornActualDatePref = "lastWornActualDatePref";
    String mLastFiveSlotOnePref = "lastFiveSlotOnePref";
    String mLastFiveSlotTwoPref = "lastFiveSlotTwoPref";
    String mLastFiveSlotThreePref = "lastFiveSlotThreePref";
    String mLastFiveSlotFourPref = "lastFiveSlotFourPref";
    String mLastFiveSlotFivePref = "lastFiveSlotFivePref";
    String mAverageWearTimePref = "averageWearTimePref";

    //Shared Prefs Constants.
    public static final String CONTACT_LENS_WEAR_TRACKER_PREFS =
            "com.danielbiddlecom.contactlensweartracker.CONTACT_LENS_TRACKER_PREFS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This Text View will have a colored background that will change depending on what the number
        //value is in the mCurrentLensCountInteger.  We will be getting this value from the Shared
        //Prefs so that we can show the user where they are in their contact lens wear cycle every
        //time they open the app.
        //Finding the text view.
        mMainWearStatusColorTV = findViewById(R.id.main_wear_status_color_tv);

        //This Text View will change every time the increment button is pressed.  It will display
        //the date that the user last wore their contact lens.  It is a way to visually help the
        //user keep track of when they last wore their contact lenses in case they don't wear them
        //every day or if they forget to increment the counter after each time they wear the lenses.
        mMainLastWornActualDateTV = findViewById(R.id.main_last_worn_actual_date_tv);

        //This button will decrease the count in the "Current Days Worn" counter TextView.
        //Finding the button and setting an onClick Listener on it.
        mMinusButton = findViewById(R.id.main_minus_button);
        mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementCurrentDaysWorn();
            }
        });

        //This button will increase the count in the "Current Days Worn" counter TextView.
        //Finding the button and setting an onClick Listener on it.
        mPlusButton = findViewById(R.id.main_plus_button);
        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementCurrentDaysWorn();
            }
        });

        //This button will reset the count in the "Current Days Worn" counter TextView.
        //Finding the button and setting an onClick Listener on it.
        mResetButton = findViewById(R.id.main_reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetCurrentDaysWorn();
                //Resetting the Wear Status Color to Green since we are resetting the Current Lens
                //Count to zero (0).
                mMainWearStatusColorTV.setBackgroundResource(R.color.wear_status_green);
            }
        });

        //Finding the Main Lens Counter TextView.
        mMainLensCounterTV = findViewById(R.id.main_lens_counter_tv);

        //Finding the Last Five Slot One TextView.
        mMainLastFiveSlotOneTV = findViewById(R.id.main_last_five_one);
        //Finding the Last Five Slot Two TextView.
        mMainLastFiveSlotTwoTV = findViewById(R.id.main_last_five_two);
        //Finding the Last Five Slot Three TextView.
        mMainLastFiveSlotThreeTV = findViewById(R.id.main_last_five_three);
        //Finding the Last Five Slot Four TextView.
        mMainLastFiveSlotFourTV = findViewById(R.id.main_last_five_four);
        //Finding the Last Five Slot Five TextView.
        mMainLastFiveSlotFiveTV = findViewById(R.id.main_last_five_five);

        //Finding the Average Wear Time number TextView.
        mAverageWearTimeNumberTV = findViewById(R.id.average_wear_time_number_tv);

        //Calling the method that will check to see if this is the first time the user has opened
        //the app. (Checking to see if this is a new user).
        isTheUserNew();




        //TODO  TESTING GOOGLE ADVIEW
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.google_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    } //End of OnCreate.


    //This method will decrease the number in the "Current Days Worn" counter TextView by "1".
    private void decrementCurrentDaysWorn() {
        //Getting the Current Lens Count and saving it to a string.
        mCurrentLensCountString = mMainLensCounterTV.getText().toString();
        try {
            //Parsing the string to get an integer so that we can decrement the value.
            mCurrentLensCountInteger = Integer.parseInt(mCurrentLensCountString);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        //Decreasing the value of the Current Lens Count by "1" each time the button is clicked.
        mCurrentLensCountInteger--;
        //Check that the number is "0" or above. If it is "0" and the user hits the decrement button
        //set the "Current Days Worn" to "0". We do NOT want to have negative numbers in the
        //"Current Days Worn".
        if (mCurrentLensCountInteger >= 0) {
            //Converting the current value of the CurrentLensCountInteger back to a String.
            mCurrentLensCountString = String.valueOf(mCurrentLensCountInteger);
            //Setting the new value in the Current Lens Count TextView for the user to see.
            mMainLensCounterTV.setText(mCurrentLensCountString);
        } else {
            //Set the value of the "Current Days Worn" counter TextView to "0".
            mMainLensCounterTV.setText(getString(R.string.main_lens_counter_placeholder));
        }

        //Putting the new current value (after the decrement) of the "Current Days Worn" into the
        //shared preferences.
        //Getting an instance of the Contact Lens Wear Tracker shared preference.
        SharedPreferences sharedPreferencesCurrentWear = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);
        //Getting the shared preference in edit mode so that we can put the Current Lens Wear Count
        //into the Contact Lens Wear Tracker shared preference.
        SharedPreferences.Editor currentWearSharedPrefsEditor = sharedPreferencesCurrentWear.edit();
        currentWearSharedPrefsEditor.putString(mCurrentLensCountPref, mCurrentLensCountString);
        currentWearSharedPrefsEditor.apply();

        //Calling the method that will check the mCurrentLensCountInteger and then set the correct
        //color in the Wear Status Text View.
        checkForColorChange();
    }

    //This method will increase the number in the "Current Days Worn" counter TextView by "1".
    private void incrementCurrentDaysWorn() {
        //Getting the Current Lens Count and saving it to a string.
        mCurrentLensCountString = mMainLensCounterTV.getText().toString();
        try {
            //Parsing the string to get an integer so that we can increment the value.
            mCurrentLensCountInteger = Integer.parseInt(mCurrentLensCountString);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        //Increasing the value of the Current Lens Count by "1" each time the button is clicked.
        mCurrentLensCountInteger++;
        //Converting the current value of the CurrentLensCountInteger back to a String.
        mCurrentLensCountString = String.valueOf(mCurrentLensCountInteger);
        //Setting the new value in the Current Lens Count TextView for the user to see.
        mMainLensCounterTV.setText(mCurrentLensCountString);

        //Putting the new current value (after the increment) of the "Current Days Worn" into the
        //shared preferences.
        //Getting an instance of the Contact Lens Wear Tracker shared preference.
        SharedPreferences sharedPreferencesCurrentWear = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);
        //Getting the shared preference in edit mode so that we can put the Current Lens Wear Count
        //into the Contact Lens Wear Tracker shared preference.
        SharedPreferences.Editor currentWearSharedPrefsEditor = sharedPreferencesCurrentWear.edit();
        currentWearSharedPrefsEditor.putString(mCurrentLensCountPref, mCurrentLensCountString);
        currentWearSharedPrefsEditor.apply();

        //Calling the method that will check the mCurrentLensCountInteger and then set the correct
        //color in the Wear Status Text View.
        checkForColorChange();

        //Calling the method that will get the current date from the users device.
        getTheDate();
    }

    //This method will check the value of the mCurrentLensCountInteger and then set the appropriate
    //color in the Wear Status Text View.  This wear status is a visual cue to the user of where
    //they are in their contact lens wear cycle.  Green is good. Yellow is more than half way through
    //their wear cycle. Red means stop wearing and change your contacts to avoid damaging your eyes.
    //We are then going to save the integer into the Shared Prefs so we can show the user the correct
    //wear status color every time they open the app.
    private void checkForColorChange() {
        //Getting the Contact Lens Wear Tracker Shared Preferences.
        SharedPreferences contactLensWearSharedPreferences = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);
        if (mCurrentLensCountInteger >= 0 && mCurrentLensCountInteger <= 8) {
            mMainWearStatusColorTV.setBackgroundResource(R.color.wear_status_green);
            //Save the Current Lens Count Integer value into the Shared Preferences.
            SharedPreferences.Editor sharedPrefsCurrentLensCountIntEditor = contactLensWearSharedPreferences.edit();
            sharedPrefsCurrentLensCountIntEditor.putInt(mCurrentLensCountIntPref, mCurrentLensCountInteger);
            sharedPrefsCurrentLensCountIntEditor.apply();
        }

        if (mCurrentLensCountInteger >= 9 && mCurrentLensCountInteger <= 13) {
            mMainWearStatusColorTV.setBackgroundResource(R.color.wear_status_yellow);
            //Save the Current Lens Count Integer value into the Shared Preferences.
            SharedPreferences.Editor sharedPrefsCurrentLensCountIntEditor = contactLensWearSharedPreferences.edit();
            sharedPrefsCurrentLensCountIntEditor.putInt(mCurrentLensCountIntPref, mCurrentLensCountInteger);
            sharedPrefsCurrentLensCountIntEditor.apply();
        }

        if (mCurrentLensCountInteger >= 14) {
            mMainWearStatusColorTV.setBackgroundResource(R.color.wear_status_red);
            //Save the Current Lens Count Integer value into the Shared Preferences.
            SharedPreferences.Editor sharedPrefsCurrentLensCountIntEditor = contactLensWearSharedPreferences.edit();
            sharedPrefsCurrentLensCountIntEditor.putInt(mCurrentLensCountIntPref, mCurrentLensCountInteger);
            sharedPrefsCurrentLensCountIntEditor.apply();
        }
    }

    //This method will get the current date from the users device and set it on the Last Worn Actual
    //Date text view.  We will then save that date into the Shared Prefs so that it can be recalled
    //every time the user enters the app.  The date will obviously be updated every time the user
    //clicks the increment button. NOTE: The user will only see a different date if they click the
    //increment button on a date that is different from the one shown in the Last Worn Actual Date.
    private void getTheDate(){
        //Getting the date and formatting it into a Month-Day-Year format.
        mLastWornActualDateString = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
        //Setting the newly formatted date onto the Last Worn Actual Date text view.
        mMainLastWornActualDateTV.setText(mLastWornActualDateString);

        //Getting the Contact Lens Wear Tracker Shared Preferences.
        SharedPreferences contactLensWearSharedPreferences = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);
        //Save the last worn actual date into the Shared Preferences.
        SharedPreferences.Editor sharedPrefsLastWornDateEditor = contactLensWearSharedPreferences.edit();
        sharedPrefsLastWornDateEditor.putString(mLastWornActualDatePref, mLastWornActualDateString);
        sharedPrefsLastWornDateEditor.apply();
    }

    //This method has multiple steps.
    //
    //High Level picture: we are shifting the numbers of the "Last Five
    //Wear Times" one spot to the right.  One will move to two, two will move to three, three will move
    //to four, four will move to five and five will drop off and disappear. Spot one is currently
    //vacant.  Spot one will be filled by the Current Days Worn value. To accomplish this we will
    //have to work in reverse, starting with position five and working backwards to position one and
    //then the Current Days Worn number.
    //
    //Directions for working backwards...
    //FIRST- get the value from the MainLastFiveSlotFour TextView and save it in the
    //mLastFiveSlotFiveString. Next convert it to an integer and store that value in
    //mLastFiveSlotFiveInteger member value. Take mLastFiveSlotFiveString and
    //set it in the MainLastFiveSlotFive TextView.
    //
    //SECOND- get the value from the MainLastFiveSlotThree TextView and save it in the
    //mLastFiveSlotFourString. Next convert it to an integer and store that value in
    //mLastFiveSlotFourInteger member value. Take mLastFiveSlotFourString and
    //set it in the MainLastFiveSlotFour TextView.
    //
    //THIRD- get the value from the MainLastFiveSlotTwo TextView and save it in the
    //mLastFiveSlotThreeString. Next convert it to an integer and store that value in
    //mLastFiveSlotThreeInteger member value. Take mLastFiveSlotThreeString and
    //set it in the MainLastFiveSlotThree TextView.
    //
    //FOURTH- get the value from the MainLastFiveSlotOne TextView and save it in the
    //mLastFiveSlotTwoString. Next convert it to an integer and store that value in
    //mLastFiveSlotTwoInteger member value. Take mLastFiveSlotTwoString and
    //set it in the MainLastFiveSlotTwo TextView.
    //
    //FIFTH- get the value from the Current Days Worn TextView and save it in the
    //mLastFiveSlotOneString. Next convert it to an integer and store that value in
    //mLastFiveSlotOneInteger member value. Take mLastFiveSlotOneString and
    //set it in the MainLastFiveSlotOne TextView.
    //
    //SIXTH- get all mLastFiveSlotIntegers (slots 1-5) and add them together.  Divide by 5.  This
    //will give us the average number of days (Average Wear Time) of the users contact lenses.
    //Convert the integer into a string and set it in the "average_wear_time_number_tv" textview.
    //
    //SEVENTH- Commit the Strings for the Last Five Wear Times and the Average Wear Time into the
    //Shared Prefs.  We will be committing the Strings not the Integers because we will have to
    //change the integers to Strings before we place then in their respective TexTViews.
    //
    //LASTLY- reset the number in the "Current Days Worn" counter TextView to "0" (zero).
    private void resetCurrentDaysWorn() {

        //STEP ONE- getting the information from LastFiveSlotFour TextView and saving it in the
        //mLastFiveSlotFiveString member variable.
        mLastFiveSlotFiveString = mMainLastFiveSlotFourTV.getText().toString();
        try {
            //Parsing the string to get an integer and storing it in the LastFiveSlotFiveInteger variable.
            mLastFiveSlotFiveInteger = Integer.parseInt(mLastFiveSlotFiveString);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        //Setting the new Slot Five value in the mMainLastFiveSlotFiveTV TextView for the user to see.
        mMainLastFiveSlotFiveTV.setText(mLastFiveSlotFiveString);

        //STEP TWO- getting the information from LastFiveSlotThree TextView and saving it in the
        //mLastFiveSlotFourString member variable.
        mLastFiveSlotFourString = mMainLastFiveSlotThreeTV.getText().toString();
        try {
            //Parsing the string to get an integer and storing it in the LastFiveSlotFourInteger variable.
            mLastFiveSlotFourInteger = Integer.parseInt(mLastFiveSlotFourString);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        //Setting the new Slot Four value in the mMainLastFiveSlotFourTV TextView for the user to see.
        mMainLastFiveSlotFourTV.setText(mLastFiveSlotFourString);

        //STEP THREE- getting the information from LastFiveSlotTwo TextView and saving it in the
        //mLastFiveSlotThreeString member variable.
        mLastFiveSlotThreeString = mMainLastFiveSlotTwoTV.getText().toString();
        try {
            //Parsing the string to get an integer and storing it in the LastFiveSlotThreeInteger variable.
            mLastFiveSlotThreeInteger = Integer.parseInt(mLastFiveSlotThreeString);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        //Setting the new Slot Three value in the mMainLastFiveSlotThreeTV TextView for the user to see.
        mMainLastFiveSlotThreeTV.setText(mLastFiveSlotThreeString);

        //STEP FOUR- getting the information from LastFiveSlotOne TextView and saving it in the
        //mLastFiveSlotTwoString member variable.
        mLastFiveSlotTwoString = mMainLastFiveSlotOneTV.getText().toString();
        try {
            //Parsing the string to get an integer and storing it in the LastFiveSlotTwoInteger variable.
            mLastFiveSlotTwoInteger = Integer.parseInt(mLastFiveSlotTwoString);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        //Setting the new Slot Two value in the mMainLastFiveSlotTwoTV TextView for the user to see.
        mMainLastFiveSlotTwoTV.setText(mLastFiveSlotTwoString);

        //STEP FIVE- getting the information from Current Days Worn TextView and saving it in the
        //mLastFiveSlotOneString member variable.
        mLastFiveSlotOneString = mMainLensCounterTV.getText().toString();
        try {
            //Parsing the string to get an integer and storing it in the LastFiveSlotFiveInteger variable.
            mLastFiveSlotOneInteger = Integer.parseInt(mLastFiveSlotOneString);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        //Setting the new Slot One value in the mMainLastFiveSlotOneTV TextView for the user to see.
        mMainLastFiveSlotOneTV.setText(mLastFiveSlotOneString);

        //STEP SIX- calling the method that will calculate the Average Wear Time based on the last
        //five wear times and then set it on the Average Wear Time number Textview.
        calculateAverageWearTime();

        //STEP SEVEN-
        //Commit the Last Five Wear Times (slots 1-5) strings and the Average Wear Time to the
        //Shared Preferences.  This is accomplished in the calculateAverageWearTime() method from
        //step six.

        //LASTLY- (Re)set the value of the "Current Days Worn" counter TextView to "0" (zero).
        mMainLensCounterTV.setText(getString(R.string.main_lens_counter_placeholder));
    }

    //This method will calculate the Average Wear Time of the user based on the Last Five Wear Times.
    //Then it will convert that integer into a string and store it in the mAverageWearTimeString
    //member variable so that it can later be set on the Average Wear Time number TextView.
    //We will also be saving the current values of the "Last Five Wear Times" into the shared preferences.
    private void calculateAverageWearTime() {
        //Checking to see if any of the Last Five Wear Time numbers are zeros ("0").
        //If they are all zeros then the slot likely hasn't been filled in with user generated data yet.
        //For example, if the user has only been using the app for a few weeks.
        if (mLastFiveSlotOneInteger == 0 && mLastFiveSlotTwoInteger == 0 && mLastFiveSlotThreeInteger == 0
                && mLastFiveSlotFourInteger == 0 && mLastFiveSlotFiveInteger == 0) {
            mAverageWearTimeNumberTV.setText(R.string.main_lens_counter_placeholder);

            //There is no need to commit the "Last Five Wear Times" and the "Average Wear Time" to
            //the shared preferences because they were added as zero ("0") into the shared
            //preferences when we initially set them up.  This was done in the isTheUserNew() method.
        } else if (mLastFiveSlotOneInteger != 0 && mLastFiveSlotTwoInteger == 0 && mLastFiveSlotThreeInteger == 0
                && mLastFiveSlotFourInteger == 0 && mLastFiveSlotFiveInteger == 0) {
            //^^Checking to see if any of the Last Five Wear Time numbers are zeros ("0").
            //If the first slot is not a zero and the last four are, then set the Average Wear Time to
            //the value of slot one.

            //Getting the string values of slots 1-2 so that we can save them into the
            //shared preferences later.
            mLastFiveSlotOneString = String.valueOf(mLastFiveSlotOneInteger);

            //Setting the Slot One value on the Average Wear Time textview.
            mAverageWearTimeNumberTV.setText(mLastFiveSlotOneString);

            //Saving the mLastFiveSlotOneString into the mLastFiveSlotOnePref.
            //Also saving the mAverageWearTimeString into the mAverageWearTimePref
            //Getting the Contact Lens Wear Tracker Shared Preferences.
            SharedPreferences contactLensLastFiveSharedPreferences = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor sharedPrefsSlotOneEditor = contactLensLastFiveSharedPreferences.edit();
            sharedPrefsSlotOneEditor.putString(mLastFiveSlotOnePref, mLastFiveSlotOneString);
            sharedPrefsSlotOneEditor.putString(mAverageWearTimePref, mLastFiveSlotOneString);
            sharedPrefsSlotOneEditor.apply();
        } else if (mLastFiveSlotOneInteger != 0 && mLastFiveSlotTwoInteger != 0 && mLastFiveSlotThreeInteger == 0
                && mLastFiveSlotFourInteger == 0 && mLastFiveSlotFiveInteger == 0) {
            //^^Checking to see if any of the Last Five Wear Time numbers are zeros ("0").
            //If the first and second slots are not zeros and the last three are, then add the first two
            //together and divide by 2 to get the Average Wear Time number.

            //Getting the string values of slots 1-2 so that we can save them into the
            //shared preferences later.
            mLastFiveSlotOneString = String.valueOf(mLastFiveSlotOneInteger);
            mLastFiveSlotTwoString = String.valueOf(mLastFiveSlotTwoInteger);

            //Adding the first two "Last Five Wear Times" slots up and storing it in the
            //tempAverageWearTimeInteger.
            int tempAverageWearTimeInteger = mLastFiveSlotOneInteger + mLastFiveSlotTwoInteger;

            //Dividing the newly created tempAverageWearTimeInteger by 2 to get the average time of
            //the Last Five Wear Times and then storing it in the mAverageWearTimeInteger.
            mAverageWearTimeInteger = tempAverageWearTimeInteger / 2;

            //Converting the mAverageWearTimeInteger to a String and storing it in the
            //mAverageWearTimeString member variable.
            mAverageWearTimeString = String.valueOf(mAverageWearTimeInteger);

            //Setting the mAverageWearTimeString on the Average Wear Time number TextView.
            mAverageWearTimeNumberTV.setText(mAverageWearTimeString);

            //Saving the mLastFiveSlotStrings (slot 1-2) into the mLastFiveSlotPrefs (1-2).
            //Also saving the mAverageWearTimeString into the mAverageWearTimePref
            //Getting the Contact Lens Wear Tracker Shared Preferences.
            SharedPreferences contactLensLastFiveSharedPreferences = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor sharedPrefsSlotOneTwoEditor = contactLensLastFiveSharedPreferences.edit();
            sharedPrefsSlotOneTwoEditor.putString(mLastFiveSlotOnePref, mLastFiveSlotOneString);
            sharedPrefsSlotOneTwoEditor.putString(mLastFiveSlotTwoPref, mLastFiveSlotTwoString);
            sharedPrefsSlotOneTwoEditor.putString(mAverageWearTimePref, mAverageWearTimeString);
            sharedPrefsSlotOneTwoEditor.apply();
        } else if (mLastFiveSlotOneInteger != 0 && mLastFiveSlotTwoInteger != 0 && mLastFiveSlotThreeInteger != 0
                && mLastFiveSlotFourInteger == 0 && mLastFiveSlotFiveInteger == 0) {
            //^^Checking to see if any of the Last Five Wear Time numbers are zeros ("0").
            //If the first, second and third slots are not zeros and the last two are, then add the
            //first three together and divide by 3 to get the Average Wear Time number.

            //Getting the string values of slots 1-3 so that we can save them into
            //the shared preferences later.
            mLastFiveSlotOneString = String.valueOf(mLastFiveSlotOneInteger);
            mLastFiveSlotTwoString = String.valueOf(mLastFiveSlotTwoInteger);
            mLastFiveSlotThreeString = String.valueOf(mLastFiveSlotThreeInteger);

            //Adding the first three "Last Five Wear Times" slots up and storing it in the
            //tempAverageWearTimeInteger.
            int tempAverageWearTimeInteger = mLastFiveSlotOneInteger + mLastFiveSlotTwoInteger
                    + mLastFiveSlotThreeInteger;

            //Dividing the newly created tempAverageWearTimeInteger by 3 to get the average time of
            //the Last Five Wear Times and then storing it in the mAverageWearTimeInteger.
            mAverageWearTimeInteger = tempAverageWearTimeInteger / 3;

            //Converting the mAverageWearTimeInteger to a String and storing it in the
            //mAverageWearTimeString member variable.
            mAverageWearTimeString = String.valueOf(mAverageWearTimeInteger);

            //Setting the mAverageWearTimeString on the Average Wear Time number TextView.
            mAverageWearTimeNumberTV.setText(mAverageWearTimeString);

            //Saving the mLastFiveSlotStrings (slot 1-3) into the mLastFiveSlotPrefs (1-3).
            //Also saving the mAverageWearTimeString into the mAverageWearTimePref
            //Getting the Contact Lens Wear Tracker Shared Preferences.
            SharedPreferences contactLensLastFiveSharedPreferences = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor sharedPrefsSlotOneTwoEditor = contactLensLastFiveSharedPreferences.edit();
            sharedPrefsSlotOneTwoEditor.putString(mLastFiveSlotOnePref, mLastFiveSlotOneString);
            sharedPrefsSlotOneTwoEditor.putString(mLastFiveSlotTwoPref, mLastFiveSlotTwoString);
            sharedPrefsSlotOneTwoEditor.putString(mLastFiveSlotThreePref, mLastFiveSlotThreeString);
            sharedPrefsSlotOneTwoEditor.putString(mAverageWearTimePref, mAverageWearTimeString);
            sharedPrefsSlotOneTwoEditor.apply();
        } else if (mLastFiveSlotOneInteger != 0 && mLastFiveSlotTwoInteger != 0 && mLastFiveSlotThreeInteger != 0
                && mLastFiveSlotFourInteger != 0 && mLastFiveSlotFiveInteger == 0) {
            //^^Checking to see if any of the Last Five Wear Time numbers are zeros ("0").
            //If the first, second, third and fourth slots are not zeros and the last one is, then add
            //the first four together and divide by 4 to get the Average Wear Time number.

            //Getting the string values of slots 1-4 so that we can save them into
            //the shared preferences later.
            mLastFiveSlotOneString = String.valueOf(mLastFiveSlotOneInteger);
            mLastFiveSlotTwoString = String.valueOf(mLastFiveSlotTwoInteger);
            mLastFiveSlotThreeString = String.valueOf(mLastFiveSlotThreeInteger);
            mLastFiveSlotFourString = String.valueOf(mLastFiveSlotFourInteger);

            //Adding the first four "Last Five Wear Times" slots up and storing it in the
            //tempAverageWearTimeInteger.
            int tempAverageWearTimeInteger = mLastFiveSlotOneInteger + mLastFiveSlotTwoInteger
                    + mLastFiveSlotThreeInteger + mLastFiveSlotFourInteger;

            //Dividing the newly created tempAverageWearTimeInteger by 4 to get the average time of
            //the Last Five Wear Times and then storing it in the mAverageWearTimeInteger.
            mAverageWearTimeInteger = tempAverageWearTimeInteger / 4;

            //Converting the mAverageWearTimeInteger to a String and storing it in the
            //mAverageWearTimeString member variable.
            mAverageWearTimeString = String.valueOf(mAverageWearTimeInteger);

            //Setting the mAverageWearTimeString on the Average Wear Time number TextView.
            mAverageWearTimeNumberTV.setText(mAverageWearTimeString);

            //Saving the mLastFiveSlotStrings (slot 1-4) into the mLastFiveSlotPrefs (1-4).
            //Also saving the mAverageWearTimeString into the mAverageWearTimePref
            //Getting the Contact Lens Wear Tracker Shared Preferences.
            SharedPreferences contactLensLastFiveSharedPreferences = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor sharedPrefsSlotOneTwoEditor = contactLensLastFiveSharedPreferences.edit();
            sharedPrefsSlotOneTwoEditor.putString(mLastFiveSlotOnePref, mLastFiveSlotOneString);
            sharedPrefsSlotOneTwoEditor.putString(mLastFiveSlotTwoPref, mLastFiveSlotTwoString);
            sharedPrefsSlotOneTwoEditor.putString(mLastFiveSlotThreePref, mLastFiveSlotThreeString);
            sharedPrefsSlotOneTwoEditor.putString(mLastFiveSlotFourPref, mLastFiveSlotFourString);
            sharedPrefsSlotOneTwoEditor.putString(mAverageWearTimePref, mAverageWearTimeString);
            sharedPrefsSlotOneTwoEditor.apply();
        } else if (mLastFiveSlotOneInteger != 0 && mLastFiveSlotTwoInteger != 0 && mLastFiveSlotThreeInteger != 0
                && mLastFiveSlotFourInteger != 0 && mLastFiveSlotFiveInteger != 0) {
            //^^Checking to see if any of the Last Five Wear Time numbers are zeros ("0").
            //If the first, second, third, fourth and fifth slots are all not zeros, then add
            //them all together and divide by 5 to get the Average Wear Time number.

            //Getting the string values of slots 1-5 so that we can save them into
            //the shared preferences later.
            mLastFiveSlotOneString = String.valueOf(mLastFiveSlotOneInteger);
            mLastFiveSlotTwoString = String.valueOf(mLastFiveSlotTwoInteger);
            mLastFiveSlotThreeString = String.valueOf(mLastFiveSlotThreeInteger);
            mLastFiveSlotFourString = String.valueOf(mLastFiveSlotFourInteger);
            mLastFiveSlotFiveString = String.valueOf(mLastFiveSlotFiveInteger);

            //Adding all the "Last Five Wear Times" slots up and storing it in the
            //tempAverageWearTimeInteger.
            int tempAverageWearTimeInteger = mLastFiveSlotOneInteger + mLastFiveSlotTwoInteger
                    + mLastFiveSlotThreeInteger + mLastFiveSlotFourInteger + mLastFiveSlotFiveInteger;

            //Dividing the newly created tempAverageWearTimeInteger by 5 to get the average time of
            //the Last Five Wear Times and then storing it in the mAverageWearTimeInteger.
            mAverageWearTimeInteger = tempAverageWearTimeInteger / 5;

            //Converting the mAverageWearTimeInteger to a String and storing it in the
            //mAverageWearTimeString member variable.
            mAverageWearTimeString = String.valueOf(mAverageWearTimeInteger);

            //Setting the mAverageWearTimeString on the Average Wear Time number TextView.
            mAverageWearTimeNumberTV.setText(mAverageWearTimeString);

            //Saving the mLastFiveSlotStrings (slot 1-5) into the mLastFiveSlotPrefs (1-5).
            //Also saving the mAverageWearTimeString into the mAverageWearTimePref
            //Getting the Contact Lens Wear Tracker Shared Preferences.
            SharedPreferences contactLensLastFiveSharedPreferences = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor sharedPrefsSlotOneTwoEditor = contactLensLastFiveSharedPreferences.edit();
            sharedPrefsSlotOneTwoEditor.putString(mLastFiveSlotOnePref, mLastFiveSlotOneString);
            sharedPrefsSlotOneTwoEditor.putString(mLastFiveSlotTwoPref, mLastFiveSlotTwoString);
            sharedPrefsSlotOneTwoEditor.putString(mLastFiveSlotThreePref, mLastFiveSlotThreeString);
            sharedPrefsSlotOneTwoEditor.putString(mLastFiveSlotFourPref, mLastFiveSlotFourString);
            sharedPrefsSlotOneTwoEditor.putString(mLastFiveSlotFivePref, mLastFiveSlotFiveString);
            sharedPrefsSlotOneTwoEditor.putString(mAverageWearTimePref, mAverageWearTimeString);
            sharedPrefsSlotOneTwoEditor.apply();
        } else {
            //Show a toast message letting the user know that something went wrong and to try again.
            Toast.makeText(MainActivity.this,
                    getString(R.string.main_error_calculating_last_five), Toast.LENGTH_LONG).show();
        }
    }

    //This method will check to see is the user is new or not.
    //If the user is new then we will create all the shared prefs with a value of zero.
    //If the user is NOT new then we will get all the current lens wear values and then set them
    //onto their respective text views.
    private void isTheUserNew() {
        //Getting the Contact Lens Wear Tracker Shared Preferences.
        SharedPreferences contactLensWearSharedPreferences = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);

        //Perform a check to see if the CONTACT_LENS_WEAR_TRACKER_PREFS contains the key "mIsTheUserNewPref".
        if (!contactLensWearSharedPreferences.contains(mIsTheUserNewPref)) {
            //There is no mIsTheUserNewPref shared preference. The user is opening the app for the
            //very first time.  We will create all the shared preferences and give them all a value
            //of zero. We will also then set the mIsTheUserNewPref to false since the user is no
            //longer new to the app.
            //Save the values into the Shared Preferences.
            SharedPreferences.Editor sharedPrefsCurrentDaysWornEditor = contactLensWearSharedPreferences.edit();
            sharedPrefsCurrentDaysWornEditor.putBoolean(mIsTheUserNewPref, mIsTheUserNew);
            sharedPrefsCurrentDaysWornEditor.putString(mLastWornActualDatePref, getString(R.string.main_last_worn_actual_date_no_data));
            sharedPrefsCurrentDaysWornEditor.putString(mCurrentLensCountPref, getString(R.string.main_lens_counter_placeholder));
            sharedPrefsCurrentDaysWornEditor.putString(mLastFiveSlotOnePref, getString(R.string.main_lens_counter_placeholder));
            sharedPrefsCurrentDaysWornEditor.putString(mLastFiveSlotTwoPref, getString(R.string.main_lens_counter_placeholder));
            sharedPrefsCurrentDaysWornEditor.putString(mLastFiveSlotThreePref, getString(R.string.main_lens_counter_placeholder));
            sharedPrefsCurrentDaysWornEditor.putString(mLastFiveSlotFourPref, getString(R.string.main_lens_counter_placeholder));
            sharedPrefsCurrentDaysWornEditor.putString(mLastFiveSlotFivePref, getString(R.string.main_lens_counter_placeholder));
            sharedPrefsCurrentDaysWornEditor.putString(mAverageWearTimePref, getString(R.string.main_lens_counter_placeholder));
            sharedPrefsCurrentDaysWornEditor.apply();

            //Update the "Current Days Worn" text view with the starting value of zero ("0").
            mMainLensCounterTV.setText(R.string.main_lens_counter_placeholder);

            //Update the "Last Worn Actual Date" text view with the starting value of ("No data Yet").
            mMainLastWornActualDateTV.setText(R.string.main_last_worn_actual_date_no_data);

            //Update the "Last Five Wear Times" text views with the starting value of zero ("0").
            mMainLastFiveSlotOneTV.setText(R.string.main_lens_counter_placeholder);
            mMainLastFiveSlotTwoTV.setText(R.string.main_lens_counter_placeholder);
            mMainLastFiveSlotThreeTV.setText(R.string.main_lens_counter_placeholder);
            mMainLastFiveSlotFourTV.setText(R.string.main_lens_counter_placeholder);
            mMainLastFiveSlotFiveTV.setText(R.string.main_lens_counter_placeholder);

            //Update the "Average Wear Time" text views with the starting value of zero ("0").
            mAverageWearTimeNumberTV.setText(R.string.main_lens_counter_placeholder);

        } else {
            //The user is NOT new so we will get all the values from the shared preferences and put
            //them into their respective text views.

            //Getting the Current Lens Count Integer from the Shared Preferences and then calling
            //the checkForColorChange method to determine what color to set in the Wear Status
            //text view.
            mCurrentLensCountInteger = contactLensWearSharedPreferences.getInt(mCurrentLensCountIntPref, 0);
            checkForColorChange();

            //Get the value of the mCurrentLensCountPref shared preference and update the value of the
            //"Current Days Worn" text view.
            mCurrentLensCountString = contactLensWearSharedPreferences.getString(mCurrentLensCountPref,
                    getString(R.string.main_lens_counter_placeholder));
            mMainLensCounterTV.setText(mCurrentLensCountString);

            //Get the value of the mLastWornActualDatePref from the shared preference and update the
            //value of the "Last Worn" actual date text view.
            mLastWornActualDateString = contactLensWearSharedPreferences.getString(mLastWornActualDatePref,
                    getString(R.string.main_last_worn_actual_date_no_data));
            mMainLastWornActualDateTV.setText(mLastWornActualDateString);

            //Getting the values of the mLastFiveSlotPrefs (slots 1-5) from the shared preferences
            //and update the value of the "Last Five Wear Times" text views.
            //Last five Slot One.
            mLastFiveSlotOneString = contactLensWearSharedPreferences.getString(mLastFiveSlotOnePref,
                    getString(R.string.main_lens_counter_placeholder));
            mMainLastFiveSlotOneTV.setText(mLastFiveSlotOneString);
            //Last five Slot Two.
            mLastFiveSlotTwoString = contactLensWearSharedPreferences.getString(mLastFiveSlotTwoPref,
                    getString(R.string.main_lens_counter_placeholder));
            mMainLastFiveSlotTwoTV.setText(mLastFiveSlotTwoString);
            //Last five Slot Three.
            mLastFiveSlotThreeString = contactLensWearSharedPreferences.getString(mLastFiveSlotThreePref,
                    getString(R.string.main_lens_counter_placeholder));
            mMainLastFiveSlotThreeTV.setText(mLastFiveSlotThreeString);
            //Last five Slot Four.
            mLastFiveSlotFourString = contactLensWearSharedPreferences.getString(mLastFiveSlotFourPref,
                    getString(R.string.main_lens_counter_placeholder));
            mMainLastFiveSlotFourTV.setText(mLastFiveSlotFourString);
            //Last five Slot Five.
            mLastFiveSlotFiveString = contactLensWearSharedPreferences.getString(mLastFiveSlotFivePref,
                    getString(R.string.main_lens_counter_placeholder));
            mMainLastFiveSlotFiveTV.setText(mLastFiveSlotFiveString);

            //Getting the values of the mAverageWearTimePref from the shared preferences and update
            //the value of the "Average Wear Time" text view.
            mAverageWearTimeString = contactLensWearSharedPreferences.getString(mAverageWearTimePref,
                    getString(R.string.main_lens_counter_placeholder));
            mAverageWearTimeNumberTV.setText(mAverageWearTimeString);
        }
    }

    //Inflating the Main Activity Overflow Menu.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu options from the res/menu/overflow_main_activity.xml file.
        //This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.overflow_main_activity, menu);
        return true;
    }

    //Menu items for the Main Activity Overflow Menu.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //User clicked on a menu option in the app bar overflow menu.
        switch (item.getItemId()) {
            //Respond to a click on the "Prescription" menu option.
            case R.id.action_prescription:
                Intent intentToPrescriptionActivity = new Intent(MainActivity.this,
                        Prescription.class);
                startActivity(intentToPrescriptionActivity);
                return true;
            //Respond to a click on the "Credit" menu option.
            case R.id.action_credits:
                Intent intentToCreditsActivity = new Intent(MainActivity.this, Credits.class);
                startActivity(intentToCreditsActivity);
                return true;
            //Respond to a click on the "Privacy Policy" menu option
            case R.id.action_privacy_policy:
                //Calling the method that will open our Privacy Policy in a web browser on the users device.
                contactLensPrivacyPolicyWeb();
                return true;
            default:
                //Show a toast letting the user know that there was an error processing their request.
                Toast.makeText(MainActivity.this,
                        getString(R.string.menu_options_default_case), Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    //This method will parse the privacy policy url and create an intent to launch the webpage so
    //the user can view our Privacy Policy.
    private void contactLensPrivacyPolicyWeb() {
        //Getting the url.
        Uri webpage = Uri.parse(getString(R.string.privacy_policy_url));
        //This Intent will take the url and send an intent to open an internet browser.
        Intent internetIntent = new Intent(Intent.ACTION_VIEW, webpage);
        //Try starting the internet intent.
        try {
            startActivity(internetIntent);
        } catch (Exception exception) {
            //If the intent fails, create a toast message letting the user know that the webpage
            //didn't load and to check their internet and try again.
            Toast.makeText(MainActivity.this,
                    getString(R.string.privacy_policy_problem_opening_webpage), Toast.LENGTH_LONG).show();
        }
    }
}