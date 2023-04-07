package com.danielbiddlecom.contactlensweartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Credits extends AppCompatActivity {

    //Getting the TextViews.
    TextView mContactEmailTV;
    private AdView mAdViewCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        //Getting the email address TextView and setting an onclick listener on it. When the user
        //clicks on the email address it will call the method that will handle the Email Intent.
        mContactEmailTV = findViewById(R.id.credits_contact_email_text_view);
        mContactEmailTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                composeEmail();
            }
        });

        //Google Admob ads.
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //Finding the Adview and showing ads in it.
        mAdViewCredits = findViewById(R.id.google_ad_view_credits);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewCredits.loadAd(adRequest);

        //Overrides for Google Admob.
        mAdViewCredits.setAdListener(new AdListener() {
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

    } //End of onCreate

    //This method will populate a list of email apps on the device.  The user can then choose which
    //email app they want to send an email from.
    public void composeEmail() {
        //This Intent will fill in the email address and subject line of the email for the user.
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"
                + Uri.encode(getString(R.string.credits_contact_email_address))
                + "?subject=" + Uri.encode(getString(R.string.credits_contact_email_subject))));
        //Only email apps should handle this.
        //Checking to see if there is an email app on the device and that it is not null.
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        } else {
            //If there are no email apps on the device, display a toast letting the user know
            //they need to install an email app to complete the action.
            Toast.makeText(Credits.this,
                    getString(R.string.credits_no_email_app_on_device), Toast.LENGTH_LONG).show();
        }
    }
}