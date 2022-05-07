package com.danielbiddlecom.contactlensweartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView mAverageWearTimeNumberTV;

    //Getting the member variables that will hold the information in the textviews.
    String mCurrentLensCountString;
    String mLastFiveSlotOneString;
    String mLastFiveSlotTwoString;
    String mLastFiveSlotThreeString;
    String mLastFiveSlotFourString;
    String mLastFiveSlotFiveString;
    String mAverageWearTimeString;
    int mCurrentLensCountInteger = 0;
    int mLastFiveSlotOneInteger = 0;
    int mLastFiveSlotTwoInteger = 0;
    int mLastFiveSlotThreeInteger = 0;
    int mLastFiveSlotFourInteger = 0;
    int mLastFiveSlotFiveInteger = 0;
    int mAverageWearTimeInteger = 0;

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

        //Finding the Average Wear Time number TextView.
        mAverageWearTimeNumberTV = findViewById(R.id.average_wear_time_number_tv);


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

        //TODO: Put this value (current days worn) in the shared prefs so it will be remembered when
        // the user reopens the app in the future.
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

        //TODO: Put this value (current days worn) in the shared prefs so it will be remembered when
        // the user reopens the app in the future.
    }

    //This method has multiple steps.
    //
    //High Level picture: we are shifting the numbers of the Last Five
    //Wear Times one spot to the right.  One will move to two, two will move to three, three will move
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

        //STEP SIX- calling the method that will calculate the Average Wear Time based on the last five wear
        //times and then show it is in the Average Wear Time number Textview.
        calculateAverageWearTime();

        //STEP SEVEN-
        //TODO- commit the Last Five Wear (slots 1-5) strings and the Average Wear Time to the Shared Prefs.

        //LASTLY- Set the value of the "Current Days Worn" counter TextView to "0" (zero).
        mMainLensCounterTV.setText(getString(R.string.main_lens_counter_placeholder));
    }

    //This method will calculate the Average Wear Time of the user based on the Last Five Wear Times.
    //Then it will convert that integer into a string and store it in the mAverageWearTimeString
    //member variable so that it can later be set on the Average Wear Time number TextView.
    private void calculateAverageWearTime() {
        //Checking to see if any of the Last Five Wear Time numbers are zeros ("0").
        //If they are zeros then the slot likely hasn't been filled in with user generated data yet.
        //For example, if the user has only been using the app for a few weeks.
        if(mLastFiveSlotOneInteger == 0 && mLastFiveSlotTwoInteger == 0 && mLastFiveSlotThreeInteger == 0
        && mLastFiveSlotFourInteger ==0 && mLastFiveSlotFiveInteger == 0){
            mAverageWearTimeNumberTV.setText(R.string.main_lens_counter_placeholder);
        } else {
            //Show a toast message letting the user know that something went wrong and to try again.
            Toast.makeText(MainActivity.this,
                    getString(R.string.main_error_calculating_last_five), Toast.LENGTH_LONG).show();
        }

        //Checking to see if any of the Last Five Wear Time numbers are zeros ("0").
        //If the first slot is not a zero and the last four are, then set the Average Wear Time to
        //the value of slot one.
        if(mLastFiveSlotOneInteger != 0 && mLastFiveSlotTwoInteger == 0 && mLastFiveSlotThreeInteger == 0
                && mLastFiveSlotFourInteger ==0 && mLastFiveSlotFiveInteger == 0){
            mAverageWearTimeNumberTV.setText(mLastFiveSlotOneString);
        } else {
            //Show a toast message letting the user know that something went wrong and to try again.
            Toast.makeText(MainActivity.this,
                    getString(R.string.main_error_calculating_last_five), Toast.LENGTH_LONG).show();
        }

        //Checking to see if any of the Last Five Wear Time numbers are zeros ("0").
        //If the first and second slots are not zeros and the last three are, then add the first two
        //together and divide by 2 to get the Average Wear Time number.
        if(mLastFiveSlotOneInteger != 0 && mLastFiveSlotTwoInteger != 0 && mLastFiveSlotThreeInteger == 0
                && mLastFiveSlotFourInteger ==0 && mLastFiveSlotFiveInteger == 0){

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
        } else {
            //Show a toast message letting the user know that something went wrong and to try again.
            Toast.makeText(MainActivity.this,
                    getString(R.string.main_error_calculating_last_five), Toast.LENGTH_LONG).show();
        }

        //Checking to see if any of the Last Five Wear Time numbers are zeros ("0").
        //If the first, second and third slots are not zeros and the last two are, then add the
        //first three together and divide by 3 to get the Average Wear Time number.
        if(mLastFiveSlotOneInteger != 0 && mLastFiveSlotTwoInteger != 0 && mLastFiveSlotThreeInteger != 0
                && mLastFiveSlotFourInteger ==0 && mLastFiveSlotFiveInteger == 0){

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
        } else {
            //Show a toast message letting the user know that something went wrong and to try again.
            Toast.makeText(MainActivity.this,
                    getString(R.string.main_error_calculating_last_five), Toast.LENGTH_LONG).show();
        }

        //Checking to see if any of the Last Five Wear Time numbers are zeros ("0").
        //If the first, second, third and fourth slots are not zeros and the last one is, then add
        //the first four together and divide by 4 to get the Average Wear Time number.
        if(mLastFiveSlotOneInteger != 0 && mLastFiveSlotTwoInteger != 0 && mLastFiveSlotThreeInteger != 0
                && mLastFiveSlotFourInteger !=0 && mLastFiveSlotFiveInteger == 0){

            //Adding the first four "Last Five Wear Times" slots up and storing it in the
            //tempAverageWearTimeInteger.
            int tempAverageWearTimeInteger = mLastFiveSlotOneInteger + mLastFiveSlotTwoInteger
                    + mLastFiveSlotThreeInteger +mLastFiveSlotFourInteger;

            //Dividing the newly created tempAverageWearTimeInteger by 4 to get the average time of
            //the Last Five Wear Times and then storing it in the mAverageWearTimeInteger.
            mAverageWearTimeInteger = tempAverageWearTimeInteger / 4;

            //Converting the mAverageWearTimeInteger to a String and storing it in the
            //mAverageWearTimeString member variable.
            mAverageWearTimeString = String.valueOf(mAverageWearTimeInteger);

            //Setting the mAverageWearTimeString on the Average Wear Time number TextView.
            mAverageWearTimeNumberTV.setText(mAverageWearTimeString);
        } else {
            //Show a toast message letting the user know that something went wrong and to try again.
            Toast.makeText(MainActivity.this,
                    getString(R.string.main_error_calculating_last_five), Toast.LENGTH_LONG).show();
        }

        //Checking to see if any of the Last Five Wear Time numbers are zeros ("0").
        //If the first, second, third, fourth and fifth slots are all not zeros, then add
        //them all together and divide by 5 to get the Average Wear Time number.
        if(mLastFiveSlotOneInteger != 0 && mLastFiveSlotTwoInteger != 0 && mLastFiveSlotThreeInteger != 0
                && mLastFiveSlotFourInteger !=0 && mLastFiveSlotFiveInteger != 0){

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
        } else {
            //Show a toast message letting the user know that something went wrong and to try again.
            Toast.makeText(MainActivity.this,
                    getString(R.string.main_error_calculating_last_five), Toast.LENGTH_LONG).show();
        }
    }


}