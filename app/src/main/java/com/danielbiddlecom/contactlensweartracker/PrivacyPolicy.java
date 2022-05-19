package com.danielbiddlecom.contactlensweartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class PrivacyPolicy extends AppCompatActivity {

    //WebView Member Variable
    WebView mPrivacyPolicyWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        //Finding the Privacy Policy Web View.
        mPrivacyPolicyWebView = findViewById(R.id.privacy_policy_web_view);

        //Calling the method that will check for internet browsing apps installed on the users
        //device and then populate a list of all internet browsing apps so the user can choose
        //which one they want to use to view the Privacy Policy.
        checkForInternetApp();


        //TODO: write the actual privacy policy and put the url into the Strings.xml

        //TODO: create a prescription activity for their contact RX AND their glasses RX.

        //TODO: fix the bug that the user returns back to a blank activity after viewing the Privacy Policy.
        // The UP NAVIGATION button is active and the back button also works BUT it is a bad user experience to come
        // back to an empty activity. We could add text and a back button to let the user know what to do
        // OR automatically send the user back to the MainActivity while they are viewing the Privacy Policy
        // (so when they come back to the app they will be back at the main activity.)


    }  //End of onCreate

    //This method will check to see if there is an internet browser installed on the device.
    //If there is at least one internet browser installed, it will populate a list of all internet
    //browsing apps currently installed on the device.  The user can then choose which internet
    //browsing app they want to use to view the Privacy Policy with.
    public void checkForInternetApp() {
        //Getting the url.
        Uri webpage = Uri.parse(getString(R.string.privacy_policy_url));
        //This Intent will take the url and send an intent to open an internet browser.
        Intent internetIntent = new Intent(Intent.ACTION_VIEW, webpage);
        //Only internet browsing apps should handle this.
        //Checking to see if there is an internet browsing app on the device and that it is not null.
        if (internetIntent.resolveActivity(getPackageManager()) != null) {
            //If there is an internet browser app installed and it is not null, start the intent.
            startActivity(internetIntent);
        } else {
            //If there are no internet browsing apps on the device, display a toast letting the
            //user know they need to install an internet browsing app to complete the action.
            Toast.makeText(PrivacyPolicy.this,
                    getString(R.string.no_internet_browser_installed), Toast.LENGTH_LONG).show();
        }
    }
}