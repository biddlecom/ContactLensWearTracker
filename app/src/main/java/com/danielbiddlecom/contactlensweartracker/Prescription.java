package com.danielbiddlecom.contactlensweartracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

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
    String mIsUserNewContactPrescriptionPref = "mIsUserNewContactPrescriptionPref";
    String mIsUserNewGlassesPrescriptionPref = "mIsUserNewGlassesPrescriptionPref";
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
    boolean mIsUserNewContactPrescriptionBool = false;
    boolean mIsUserNewGlassesPrescriptionBool = false;

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

    //TODO: still not working. Won't save the prefs and won't recall them.  It will still only recall the generic info.
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
            SharedPreferences sharedPrefsPrescriptionActivity = PreferenceManager.getDefaultSharedPreferences(this);

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
        //TODO: since we only have to worry about what happens when they hit the save button,
        // get the data from the edit texts and save it in the shared preferences and then set the
        // data in the edit texts.
        // Check it, save it, set it.
        // Check to see if it is blank or not, if it is blank then save a dash or something. (or the "Ex. =3.00" or something like that).
        // If it is not blank then save the data into the shared prefs.
        // Then set it in the edit texts for immediate viewing.

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

//        //Update the "Contact Prescription" text views with the user info.
//        mPrescriptionContactsLeftPowerET.setText(mContactLeftPowerString);
//        mPrescriptionContactsRightPowerET.setText(R.string.prescription_power_hint);
//        mPrescriptionContactsLeftBCET.setText(R.string.prescription_bc_hint);
//        mPrescriptionContactsRightBCET.setText(R.string.prescription_bc_hint);
//        mPrescriptionContactsLeftDiaET.setText(R.string.prescription_dia_hint);
//        mPrescriptionContactsRightDiaET.setText(R.string.prescription_dia_hint);
//        mPrescriptionContactsLeftBrandET.setText(R.string.prescription_brand_hint);
//        mPrescriptionContactsRightBrandET.setText(R.string.prescription_brand_hint);



    }


    //This method will get the information from the edit texts and then save it in the shared
    //preferences.  The user doesn't have to fill in all the empty spaces in order to save their
    //information.  It will get whatever information the user does input and save that into the
    //shared preferences.
    private void saveGlassesPrescription() {
        //TODO: since we only have to worry about what happens when they hit the save button,
        // get the data from the edit texts and save it in the shared preferences and then set the
        // data in the edit texts.
        // Check it, save it, set it.
        // Check to see if it is blank or not, if it is blank then save a dash or something. (or the "Ex. =3.00" or something like that).
        // If it is not blank then save the data into the shared prefs.
        // Then set it in the edit texts for immediate viewing. 
        //Getting the Contact Lens Wear Tracker Shared Preferences.
        SharedPreferences contactLensWearSharedPreferences = getSharedPreferences(CONTACT_LENS_WEAR_TRACKER_PREFS, MODE_PRIVATE);

        //Perform a check to see if the CONTACT_LENS_WEAR_TRACKER_PREFS contains the key "mIsUserNewGlassesPrescriptionPref".
        if (!contactLensWearSharedPreferences.contains(mIsUserNewGlassesPrescriptionPref)) {
            //There is no mIsUserNewGlassesPrescriptionPref shared preference. The user is opening
            //the app for the very first time.  We will create all the shared preferences and give
            //them all a generic value. We will also then set the isUserNewGlassesPrescriptionBool
            //to false since the user is no longer new to the app.
            //Save the values into the Shared Preferences.
            SharedPreferences.Editor sharedPrefsContactPrescriptionEditor = contactLensWearSharedPreferences.edit();
            sharedPrefsContactPrescriptionEditor.putBoolean(mIsUserNewGlassesPrescriptionPref,
                    mIsUserNewGlassesPrescriptionBool);
            sharedPrefsContactPrescriptionEditor.putString(mGlassesLeftSpherePref,
                    getString(R.string.prescription_sphere_hint));
            sharedPrefsContactPrescriptionEditor.putString(mGlassesRightSpherePref,
                    getString(R.string.prescription_sphere_hint));
            sharedPrefsContactPrescriptionEditor.putString(mGlassesLeftCylinderPref,
                    getString(R.string.prescription_cylinder_hint));
            sharedPrefsContactPrescriptionEditor.putString(mGlassesRightCylinderPref,
                    getString(R.string.prescription_cylinder_hint));
            sharedPrefsContactPrescriptionEditor.putString(mGlassesLeftAxisPref,
                    getString(R.string.prescription_axis_hint));
            sharedPrefsContactPrescriptionEditor.putString(mGlassesRightAxisPref,
                    getString(R.string.prescription_axis_hint));
            sharedPrefsContactPrescriptionEditor.putString(mGlassesLeftPrismPref,
                    getString(R.string.prescription_prism_hint));
            sharedPrefsContactPrescriptionEditor.putString(mGlassesRightPrismPref,
                    getString(R.string.prescription_prism_hint));
            sharedPrefsContactPrescriptionEditor.putString(mGlassesLeftBasePref,
                    getString(R.string.prescription_base_hint));
            sharedPrefsContactPrescriptionEditor.putString(mGlassesRightBasePref,
                    getString(R.string.prescription_base_hint));
            sharedPrefsContactPrescriptionEditor.apply();

            //Update the "Glasses Prescription" text views with the generic values listed above.
            mPrescriptionGlassesLeftSphereET.setText(R.string.prescription_sphere_hint);
            mPrescriptionGlassesRightSphereET.setText(R.string.prescription_sphere_hint);
            mPrescriptionGlassesLeftCylinderET.setText(R.string.prescription_cylinder_hint);
            mPrescriptionGlassesRightCylinderET.setText(R.string.prescription_cylinder_hint);
            mPrescriptionGlassesLeftAxisET.setText(R.string.prescription_axis_hint);
            mPrescriptionGlassesRightAxisET.setText(R.string.prescription_axis_hint);
            mPrescriptionGlassesLeftPrismET.setText(R.string.prescription_prism_hint);
            mPrescriptionGlassesRightPrismET.setText(R.string.prescription_prism_hint);
            mPrescriptionGlassesLeftBaseET.setText(R.string.prescription_base_hint);
            mPrescriptionGlassesRightBaseET.setText(R.string.prescription_base_hint);

        } else {
            //The user is NOT new so we will get all the values from the shared preferences and put
            //them into their respective text views.

            //Get the values of the "Glasses Prescription" shared preference and update the values
            //of the respective edit text views.
            mGlassesLeftSphereString = contactLensWearSharedPreferences.getString(mGlassesLeftSpherePref,
                    getString(R.string.prescription_sphere_hint));
            mPrescriptionGlassesLeftSphereET.setText(mGlassesLeftSphereString);

            mGlassesRightSphereString = contactLensWearSharedPreferences.getString(mGlassesRightSpherePref,
                    getString(R.string.prescription_sphere_hint));
            mPrescriptionGlassesRightSphereET.setText(mGlassesRightSphereString);

            mGlassesLeftCylinderString = contactLensWearSharedPreferences.getString(mGlassesLeftCylinderPref,
                    getString(R.string.prescription_cylinder_hint));
            mPrescriptionGlassesLeftCylinderET.setText(mGlassesLeftCylinderString);

            mGlassesRightCylinderString = contactLensWearSharedPreferences.getString(mGlassesRightCylinderPref,
                    getString(R.string.prescription_cylinder_hint));
            mPrescriptionGlassesRightCylinderET.setText(mGlassesRightCylinderString);

            mGlassesLeftAxisString = contactLensWearSharedPreferences.getString(mGlassesLeftAxisPref,
                    getString(R.string.prescription_axis_hint));
            mPrescriptionGlassesLeftAxisET.setText(mGlassesLeftAxisString);

            mGlassesRightAxisString = contactLensWearSharedPreferences.getString(mGlassesRightAxisPref,
                    getString(R.string.prescription_axis_hint));
            mPrescriptionGlassesRightAxisET.setText(mGlassesRightAxisString);

            mGlassesLeftPrismString = contactLensWearSharedPreferences.getString(mGlassesLeftPrismPref,
                    getString(R.string.prescription_prism_hint));
            mPrescriptionGlassesLeftPrismET.setText(mGlassesLeftPrismString);

            mGlassesRightPrismString = contactLensWearSharedPreferences.getString(mGlassesRightPrismPref,
                    getString(R.string.prescription_prism_hint));
            mPrescriptionGlassesRightPrismET.setText(mGlassesRightPrismString);

            mGlassesLeftBaseString = contactLensWearSharedPreferences.getString(mGlassesLeftBasePref,
                    getString(R.string.prescription_base_hint));
            mPrescriptionGlassesLeftBaseET.setText(mGlassesLeftBaseString);

            mGlassesRightBaseString = contactLensWearSharedPreferences.getString(mGlassesRightBasePref,
                    getString(R.string.prescription_base_hint));
            mPrescriptionGlassesRightBaseET.setText(mGlassesRightBaseString);
        }
    }
}