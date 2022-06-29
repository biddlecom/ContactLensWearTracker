package com.danielbiddlecom.contactlensweartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Credits extends AppCompatActivity {

    //Getting the TextViews.
    TextView mContactEmailTV;

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