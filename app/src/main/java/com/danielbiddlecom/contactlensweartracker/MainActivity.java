package com.danielbiddlecom.contactlensweartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Getting the Buttons and TextViews.
    ImageButton mMinusButton;
    ImageButton mPlusButton;
    ImageButton mResetButton;
    TextView mMainLensCounterTV;
    TextView mMainLastFiveSlotOneTV;
    TextView mMainLastFiveSlotTwoTV;
    TextView mMainLastFiveSlotThreeTV;
    TextView mMainLastFiveSlotFourTV;
    TextView mMainLastFiveSlotFiveTV;

    //Getting the variables that will hold the information in the textviews.
    String mCurrentLensCountString;
    int mCurrentLensCountInteger = 0;
    int mLastFiveSlotOneInteger = 0;
    int mLastFiveSlotTwoInteger = 0;
    int mLastFiveSlotThreeInteger = 0;
    int mLastFiveSlotFourInteger = 0;
    int mLastFiveSlotFiveInteger = 0;

    //Shared Prefs Member Variables.
    String mCurrentLensCountPref = "currentLensCountPref";
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
            String newString = String.valueOf(mCurrentLensCountInteger);
            //Setting the new value in the Current Lens Count TextView for the user to see.
            mMainLensCounterTV.setText(newString);
        } else {
            //Set the value of the "Current Days Worn" counter TextView to "0".
            mMainLensCounterTV.setText(getString(R.string.main_lens_counter_placeholder));
        }

        //TODO: Put this value in the shared prefs so it will be remembered when the user reopens
        // the app in the future.
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
        String newString = String.valueOf(mCurrentLensCountInteger);
        //Setting the new value in the Current Lens Count TextView for the user to see.
        mMainLensCounterTV.setText(newString);

        //TODO: Put this value in the shared prefs so it will be remembered when the user reopens
        // the app in the future.
    }

    //This method has multiple steps.
    //
    //FIRST- get the value from the MainLastFiveSlotFour TextView and convert it to an integer.
    //Store that value in mLastFiveSlotFourInteger member value. Convert that integer back into
    //a string and set it in the MainLastFiveSlotFive TextView.
    //
    //SECOND- get the value from the MainLastFiveSlotThree TextView and convert it to an integer.
    //Store that value in mLastFiveSlotThreeInteger member value. Convert that integer back into
    //a string and set it in the MainLastFiveSlotFour TextView.
    //
    //THIRD- get the value from the MainLastFiveSlotTwo TextView and convert it to an integer.
    //Store that value in mLastFiveSlotTwoInteger member value. Convert that integer back into
    //a string and set it in the MainLastFiveSlotThree TextView.
    //
    // TODO: continue explaining what the method will do.
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //Reset the number in the "Current Days Worn" counter TextView to "0".
    private void resetCurrentDaysWorn() {

        //TODO: write the code following the explanation above.


        //Set the value of the "Current Days Worn" counter TextView to "0".
        mMainLensCounterTV.setText(getString(R.string.main_lens_counter_placeholder));


    }

    //TODO: average out the wear times using the values in the LastFiveSlotIntegers and then put
    // the new value in the average wear time number textview.


}