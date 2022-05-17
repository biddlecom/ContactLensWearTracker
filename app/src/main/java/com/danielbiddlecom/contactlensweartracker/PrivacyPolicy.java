package com.danielbiddlecom.contactlensweartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class PrivacyPolicy extends AppCompatActivity {

    //WebView Member Variable
    WebView mPrivacyPolicyWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        mPrivacyPolicyWebView = findViewById(R.id.privacy_policy_web_view);
        mPrivacyPolicyWebView.loadUrl(getString(R.string.privacy_policy_url));

        //TODO: write the actual privacy policy and put the url into the Strings.xml

        //TODO: perform a check to make sure there is an actual internet app on the device that can
        // show the webview.

    }  //End of onCreate


}