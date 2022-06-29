package com.danielbiddlecom.contactlensweartracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class Prescription extends AppCompatActivity {

    //Member variables for the contacts and glasses EditTexts and save Buttons.
    AppCompatEditText mPrescriptionContactsLeftPowerET;
    AppCompatEditText mPrescriptionContactsRightPowerET;
    AppCompatEditText mPrescriptionContactsLeftBCET;
    AppCompatEditText mPrescriptionContactsRightBCET;
    AppCompatEditText mPrescriptionContactsLeftDiaET;
    AppCompatEditText mPrescriptionContactsRightDiaET;
    AppCompatEditText mPrescriptionContactsLeftBrandET;
    AppCompatEditText mPrescriptionContactsRightBrandET;
    Button mContactsSaveButton;
    AppCompatEditText mPrescriptionGlassesLeftSphereET;
    AppCompatEditText mPrescriptionGlassesRightSphereET;
    AppCompatEditText mPrescriptionGlassesLeftCylinderET;
    AppCompatEditText mPrescriptionGlassesRightCylinderET;
    AppCompatEditText mPrescriptionGlassesLeftAxisET;
    AppCompatEditText mPrescriptionGlassesRightAxisET;
    AppCompatEditText mPrescriptionGlassesLeftPrismET;
    AppCompatEditText mPrescriptionGlassesRightPrismET;
    AppCompatEditText mPrescriptionGlassesLeftBaseET;
    AppCompatEditText mPrescriptionGlassesRightBaseET;
    Button mGlassesSaveButton;

    //Shared Prefs Constants.
    public static final String CONTACT_LENS_WEAR_TRACKER_PREFS =
            "com.danielbiddlecom.contactlensweartracker.CONTACT_LENS_TRACKER_PREFS";

    //Shared Prefs Member Variables.
    String mIsUserNewPrescriptionActivityPref = "mIsUserNewPrescriptionActivityPref";
    String mContactLeftPowerPref = "contactLeftPowerPref";
    String mContactRightPowerPref = "contactRightPowerPref";
    String mContactsLeftBCPref = "contactLeftBCPref";
    String mContactsRightBCPref = "contactRightBCPref";
    String mContactsLeftDiaPref = "contactLeftDiaPref";
    String mContactsRightDiaPref = "contactRightDiaPref";
    String mContactsLeftBrandPref = "contactLeftBrandPref";
    String mContactsRightBrandPref = "contactRightBrandPref";
    String mGlassesLeftSpherePref = "glassesLeftSpherePref";
    String mGlassesRightSpherePref = "glassesRightSpherePref";
    String mGlassesLeftCylinderPref = "glassesLeftCylinderPref";
    String mGlassesRightCylinderPref = "glassesRightCylinderPref";
    String mGlassesLeftAxisPref = "glassesLeftAxisPref";
    String mGlassesRightAxisPref = "glassesRightAxisPref";
    String mGlassesLeftPrismPref = "glassesLeftPrismPref";
    String mGlassesRightPrismPref = "glassesRightPrismPref";
    String mGlassesLeftBasePref = "glassesLeftBasePref";
    String mGlassesRightBasePref = "glassesRightBasePref";

    //Boolean that will hold whether the user is new or not
    boolean mIsUserNewPrescriptionActivityBool = false;

    //Member variables for the strings that will hold the users information from the contacts and
    //glasses prescriptions edit texts.
    String mContactLeftPowerString;
    String mContactRightPowerString;
    String mContactsLeftBCString;
    String mContactsRightBCString;
    String mContactsLeftDiaString;
    String mContactsRightDiaString;
    String mContactsLeftBrandString;
    String mContactsRightBrandString;
    String mGlassesLeftSphereString;
    String mGlassesRightSphereString;
    String mGlassesLeftCylinderString;
    String mGlassesRightCylinderString;
    String mGlassesLeftAxisString;
    String mGlassesRightAxisString;
    String mGlassesLeftPrismString;
    String mGlassesRightPrismString;
    String mGlassesLeftBaseString;
    String mGlassesRightBaseString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        //Getting the Contacts EditTexts and Save Button.
        mPrescriptionContactsLeftPowerET = findViewById(R.id.prescription_contacts_left_power_edit_text);
        mPrescriptionContactsRightPowerET = findViewById(R.id.prescription_contacts_right_power_edit_text);
        mPrescriptionContactsLeftBCET = findViewById(R.id.prescription_contacts_left_bc_edit_text);
        mPrescriptionContactsRightBCET = findViewById(R.id.prescription_contacts_right_bc_edit_text);
        mPrescriptionContactsLeftDiaET = findViewById(R.id.prescription_contacts_left_dia_edit_text);
        mPrescriptionContactsRightDiaET = findViewById(R.id.prescription_contacts_right_dia_edit_text);
        mPrescriptionContactsLeftBrandET = findViewById(R.id.prescription_contacts_left_brand_edit_text);
        mPrescriptionContactsRightBrandET = findViewById(R.id.prescription_contacts_right_brand_edit_text);
        mContactsSaveButton = findViewById(R.id.prescription_save_contacts_button);
        mContactsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calling the method that will get the users contacts prescription information from
                //the edit texts and save it into the shared preferences.
                saveContactsPrescription();
            }
        });

        //Getting the Glasses EditTexts and Save Button.
        mPrescriptionGlassesLeftSphereET = findViewById(R.id.prescription_glasses_left_sphere_edit_text);
        mPrescriptionGlassesRightSphereET = findViewById(R.id.prescription_glasses_right_sphere_edit_text);
        mPrescriptionGlassesLeftCylinderET = findViewById(R.id.prescription_glasses_left_cylinder_edit_text);
        mPrescriptionGlassesRightCylinderET = findViewById(R.id.prescription_glasses_right_cylinder_edit_text);
        mPrescriptionGlassesLeftAxisET = findViewById(R.id.prescription_glasses_left_axis_edit_text);
        mPrescriptionGlassesRightAxisET = findViewById(R.id.prescription_glasses_right_axis_edit_text);
        mPrescriptionGlassesLeftPrismET = findViewById(R.id.prescription_glasses_left_prism_edit_text);
        mPrescriptionGlassesRightPrismET = findViewById(R.id.prescription_glasses_right_prism_edit_text);
        mPrescriptionGlassesLeftBaseET = findViewById(R.id.prescription_glasses_left_base_edit_text);
        mPrescriptionGlassesRightBaseET = findViewById(R.id.prescription_glasses_right_base_edit_text);
        mGlassesSaveButton = findViewById(R.id.prescription_save_glasses_button);
        mGlassesSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calling the method that will get the users glasses prescription information from
                //the edit texts and save it into the shared preferences.
                saveGlassesPrescription();
            }
        });

        //Calling the method that will check to see if the user is new or not.
        isUserNewPrescriptionActivity();

    }  //End Of OnCreate
    
    //This method will check to see if the user is new or not.
    //If they are new, then put the new preference and boolean in the shared prefs.
    //If they are not new then get the info from the shared preferences and set the text on the text views.
    //This should be done when the activity loads so that they see the info immediately.
    //And again, if they are new then they will just see the hint text in the edit texts.
    private void isUserNewPrescriptionActivity() {
        //Getting the Contact Lens Wear Tracker Shared Preferences.
        SharedPreferences contactLensWearSharedPreferences = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);
        //Perform a check to see if the CONTACT_LENS_WEAR_TRACKER_PREFS contains the key "mIsUserNewPrescriptionActivityPref".
        if (!contactLensWearSharedPreferences.contains(mIsUserNewPrescriptionActivityPref)) {
            //There is no mIsUserNewPrescriptionActivityPref shared preference. The user is opening
            //the prescription activity for the very first time.  We will then set the
            //mIsUserNewPrescriptionActivityPref to false since the user is no longer new to the app.
            //Save the values into the Shared Preferences.
            SharedPreferences.Editor sharedPrefsContactPrescriptionEditor = contactLensWearSharedPreferences.edit();
            sharedPrefsContactPrescriptionEditor.putBoolean(mIsUserNewPrescriptionActivityPref, mIsUserNewPrescriptionActivityBool);
            sharedPrefsContactPrescriptionEditor.apply();
        } else {
            //The user is not new so we need to get the contact and/or glasses prescription information
            //from the shared prefs and set it into the appropriate text views so the user can view
            //their information.
            SharedPreferences sharedPrefsPrescriptionActivity = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);

            //Contacts Prescription from shared prefs.
            mContactLeftPowerString = sharedPrefsPrescriptionActivity.getString(mContactLeftPowerPref,
                    getString(R.string.prescription_power_hint));
            mContactRightPowerString = sharedPrefsPrescriptionActivity.getString(mContactRightPowerPref,
                    getString(R.string.prescription_power_hint));
            mContactsLeftBCString = sharedPrefsPrescriptionActivity.getString(mContactsLeftBCPref,
                    getString(R.string.prescription_bc_hint));
            mContactsRightBCString = sharedPrefsPrescriptionActivity.getString(mContactsRightBCPref,
                    getString(R.string.prescription_bc_hint));
            mContactsLeftDiaString = sharedPrefsPrescriptionActivity.getString(mContactsLeftDiaPref,
                    getString(R.string.prescription_dia_hint));
            mContactsRightDiaString = sharedPrefsPrescriptionActivity.getString(mContactsRightDiaPref,
                    getString(R.string.prescription_dia_hint));
            mContactsLeftBrandString = sharedPrefsPrescriptionActivity.getString(mContactsLeftBrandPref,
                    getString(R.string.prescription_brand_hint));
            mContactsRightBrandString = sharedPrefsPrescriptionActivity.getString(mContactsRightBrandPref,
                    getString(R.string.prescription_brand_hint));

            //Glasses Prescription from shared prefs.
            mGlassesLeftSphereString = sharedPrefsPrescriptionActivity.getString(mGlassesLeftSpherePref,
                    getString(R.string.prescription_sphere_hint));
            mGlassesRightSphereString = sharedPrefsPrescriptionActivity.getString(mGlassesRightSpherePref,
                    getString(R.string.prescription_sphere_hint));
            mGlassesLeftCylinderString = sharedPrefsPrescriptionActivity.getString(mGlassesLeftCylinderPref,
                    getString(R.string.prescription_cylinder_hint));
            mGlassesRightCylinderString = sharedPrefsPrescriptionActivity.getString(mGlassesRightCylinderPref,
                    getString(R.string.prescription_cylinder_hint));
            mGlassesLeftAxisString = sharedPrefsPrescriptionActivity.getString(mGlassesLeftAxisPref,
                    getString(R.string.prescription_axis_hint));
            mGlassesRightAxisString = sharedPrefsPrescriptionActivity.getString(mGlassesRightAxisPref,
                    getString(R.string.prescription_axis_hint));
            mGlassesLeftPrismString = sharedPrefsPrescriptionActivity.getString(mGlassesLeftPrismPref,
                    getString(R.string.prescription_prism_hint));
            mGlassesRightPrismString = sharedPrefsPrescriptionActivity.getString(mGlassesRightPrismPref,
                    getString(R.string.prescription_prism_hint));
            mGlassesLeftBaseString = sharedPrefsPrescriptionActivity.getString(mGlassesLeftBasePref,
                    getString(R.string.prescription_base_hint));
            mGlassesRightBaseString = sharedPrefsPrescriptionActivity.getString(mGlassesRightBasePref,
                    getString(R.string.prescription_base_hint));

            //Set the prescription strings we got from the shared prefs into their respective text views.
            mPrescriptionContactsLeftPowerET.setText(mContactLeftPowerString);
            mPrescriptionContactsRightPowerET.setText(mContactRightPowerString);
            mPrescriptionContactsLeftBCET.setText(mContactsLeftBCString);
            mPrescriptionContactsRightBCET.setText(mContactsRightBCString);
            mPrescriptionContactsLeftDiaET.setText(mContactsLeftDiaString);
            mPrescriptionContactsRightDiaET.setText(mContactsRightDiaString);
            mPrescriptionContactsLeftBrandET.setText(mContactsLeftBrandString);
            mPrescriptionContactsRightBrandET.setText(mContactsRightBrandString);
            mPrescriptionGlassesLeftSphereET.setText(mGlassesLeftSphereString);
            mPrescriptionGlassesRightSphereET.setText(mGlassesRightSphereString);
            mPrescriptionGlassesLeftCylinderET.setText(mGlassesLeftCylinderString);
            mPrescriptionGlassesRightCylinderET.setText(mGlassesRightCylinderString);
            mPrescriptionGlassesLeftAxisET.setText(mGlassesLeftAxisString);
            mPrescriptionGlassesRightAxisET.setText(mGlassesRightAxisString);
            mPrescriptionGlassesLeftPrismET.setText(mGlassesLeftPrismString);
            mPrescriptionGlassesRightPrismET.setText(mGlassesRightPrismString);
            mPrescriptionGlassesLeftBaseET.setText(mGlassesLeftBaseString);
            mPrescriptionGlassesRightBaseET.setText(mGlassesRightBaseString);
        }
    }

    //This method will get the information from the edit texts and then save it in the shared
    //preferences.  The user doesn't have to fill in all the empty spaces in order to save their
    //information.  It will get whatever information the user does input and save that into the
    //shared preferences.  If the user leaves a field blank then we will save a "dash" into the
    //shared preferences.
    private void saveContactsPrescription() {
        //Checking to see if the contacts prescription edit texts are blank.
        if (Objects.requireNonNull(mPrescriptionContactsLeftPowerET.getText()).toString().isEmpty()) {
            mContactLeftPowerString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mContactLeftPowerString = mPrescriptionContactsLeftPowerET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionContactsRightPowerET.getText()).toString().isEmpty()) {
            mContactRightPowerString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mContactRightPowerString = mPrescriptionContactsRightPowerET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionContactsLeftBCET.getText()).toString().isEmpty()) {
            mContactsLeftBCString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mContactsLeftBCString = mPrescriptionContactsLeftBCET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionContactsRightBCET.getText()).toString().isEmpty()) {
            mContactsRightBCString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mContactsRightBCString = mPrescriptionContactsRightBCET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionContactsLeftDiaET.getText()).toString().isEmpty()) {
            mContactsLeftDiaString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mContactsLeftDiaString = mPrescriptionContactsLeftDiaET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionContactsRightDiaET.getText()).toString().isEmpty()) {
            mContactsRightDiaString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mContactsRightDiaString = mPrescriptionContactsRightDiaET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionContactsLeftBrandET.getText()).toString().isEmpty()) {
            mContactsLeftBrandString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mContactsLeftBrandString = mPrescriptionContactsLeftBrandET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionContactsRightBrandET.getText()).toString().isEmpty()) {
            mContactsRightBrandString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mContactsRightBrandString = mPrescriptionContactsRightBrandET.getText().toString();
        }

        //Getting the Contact Lens Wear Tracker Shared Preferences.
        SharedPreferences contactLensWearSharedPreferences = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);

        //Save the values into the Shared Preferences.
        SharedPreferences.Editor sharedPrefsContactPrescriptionEditor = contactLensWearSharedPreferences.edit();

        sharedPrefsContactPrescriptionEditor.putString(mContactLeftPowerPref, mContactLeftPowerString);
        sharedPrefsContactPrescriptionEditor.putString(mContactRightPowerPref, mContactRightPowerString);
        sharedPrefsContactPrescriptionEditor.putString(mContactsLeftBCPref, mContactsLeftBCString);
        sharedPrefsContactPrescriptionEditor.putString(mContactsRightBCPref, mContactsRightBCString);
        sharedPrefsContactPrescriptionEditor.putString(mContactsLeftDiaPref, mContactsLeftDiaString);
        sharedPrefsContactPrescriptionEditor.putString(mContactsRightDiaPref, mContactsRightDiaString);
        sharedPrefsContactPrescriptionEditor.putString(mContactsLeftBrandPref, mContactsLeftBrandString);
        sharedPrefsContactPrescriptionEditor.putString(mContactsRightBrandPref, mContactsRightBrandString);
        sharedPrefsContactPrescriptionEditor.apply();

        //Show a toast letting the user know that their contact prescription has been successfully saved.
        Toast.makeText(Prescription.this, getString(R.string.prescription_contact_lens_rx_saved),
                Toast.LENGTH_LONG).show();
    }


    //This method will get the information from the edit texts and then save it in the shared
    //preferences.  The user doesn't have to fill in all the empty spaces in order to save their
    //information.  It will get whatever information the user does input and save that into the
    //shared preferences.  If the user leaves a field blank then we will save a "dash" into the
    //shared preferences.
    private void saveGlassesPrescription() {
        //Checking to see if the glasses prescription edit texts are blank.
        if (Objects.requireNonNull(mPrescriptionGlassesLeftSphereET.getText()).toString().isEmpty()) {
            mGlassesLeftSphereString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mGlassesLeftSphereString = mPrescriptionGlassesLeftSphereET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionGlassesRightSphereET.getText()).toString().isEmpty()) {
            mGlassesRightSphereString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mGlassesRightSphereString = mPrescriptionGlassesRightSphereET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionGlassesLeftCylinderET.getText()).toString().isEmpty()) {
            mGlassesLeftCylinderString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mGlassesLeftCylinderString = mPrescriptionGlassesLeftCylinderET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionGlassesRightCylinderET.getText()).toString().isEmpty()) {
            mGlassesRightCylinderString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mGlassesRightCylinderString = mPrescriptionGlassesRightCylinderET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionGlassesLeftAxisET.getText()).toString().isEmpty()) {
            mGlassesLeftAxisString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mGlassesLeftAxisString = mPrescriptionGlassesLeftAxisET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionGlassesRightAxisET.getText()).toString().isEmpty()) {
            mGlassesRightAxisString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mGlassesRightAxisString = mPrescriptionGlassesRightAxisET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionGlassesLeftPrismET.getText()).toString().isEmpty()) {
            mGlassesLeftPrismString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mGlassesLeftPrismString = mPrescriptionGlassesLeftPrismET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionGlassesRightPrismET.getText()).toString().isEmpty()) {
            mGlassesRightPrismString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mGlassesRightPrismString = mPrescriptionGlassesRightPrismET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionGlassesLeftBaseET.getText()).toString().isEmpty()) {
            mGlassesLeftBaseString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mGlassesLeftBaseString = mPrescriptionGlassesLeftBaseET.getText().toString();
        }

        if (Objects.requireNonNull(mPrescriptionGlassesRightBaseET.getText()).toString().isEmpty()) {
            mGlassesRightBaseString = getString(R.string.prescription_empty_text_view_dash);
        } else {
            mGlassesRightBaseString = mPrescriptionGlassesRightBaseET.getText().toString();
        }

        //Getting the Contact Lens Wear Tracker Shared Preferences.
        SharedPreferences contactLensWearSharedPreferences = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);

        //Save the values into the Shared Preferences.
        SharedPreferences.Editor sharedPrefsContactPrescriptionEditor = contactLensWearSharedPreferences.edit();

        sharedPrefsContactPrescriptionEditor.putString(mGlassesLeftSpherePref, mGlassesLeftSphereString);
        sharedPrefsContactPrescriptionEditor.putString(mGlassesRightSpherePref, mGlassesRightSphereString);
        sharedPrefsContactPrescriptionEditor.putString(mGlassesLeftCylinderPref, mGlassesLeftCylinderString);
        sharedPrefsContactPrescriptionEditor.putString(mGlassesRightCylinderPref, mGlassesRightCylinderString);
        sharedPrefsContactPrescriptionEditor.putString(mGlassesLeftAxisPref, mGlassesLeftAxisString);
        sharedPrefsContactPrescriptionEditor.putString(mGlassesRightAxisPref, mGlassesRightAxisString);
        sharedPrefsContactPrescriptionEditor.putString(mGlassesLeftPrismPref, mGlassesLeftPrismString);
        sharedPrefsContactPrescriptionEditor.putString(mGlassesRightPrismPref, mGlassesRightPrismString);
        sharedPrefsContactPrescriptionEditor.putString(mGlassesLeftBasePref, mGlassesLeftBaseString);
        sharedPrefsContactPrescriptionEditor.putString(mGlassesRightBasePref, mGlassesRightBaseString);
        sharedPrefsContactPrescriptionEditor.apply();

        //Show a toast letting the user know that their contact prescription has been successfully saved.
        Toast.makeText(Prescription.this, getString(R.string.prescription_glasses_rx_saved),
                Toast.LENGTH_LONG).show();
    }
}