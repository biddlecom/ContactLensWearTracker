package com.danielbiddlecom.contactlensweartracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    }  //End Of OnCreate

    //This method will get the information from the edit texts and then save it in the shared
    //preferences.  The user doesn't have to fill in all the empty spaces in order to save their
    //information.  It will get whatever information the user does input and save that into the
    //shared preferences.
    private void saveContactsPrescription(){
        //TODO: get the contacts info from the edit texts and save it into the shared prefs.
    }

    //This method will get the information from the edit texts and then save it in the shared
    //preferences.  The user doesn't have to fill in all the empty spaces in order to save their
    //information.  It will get whatever information the user does input and save that into the
    //shared preferences.
    private void saveGlassesPrescription(){
        //TODO: get the glasses info from the edit texts and save it into the shared prefs.
    }


}