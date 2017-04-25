package com.example.deric.passwordlockbox;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.Random;

public class passwordCreateActivity extends AppCompatActivity {

    SharedPreferences passwords;
    //used for sharedpreferences
    private final String PASSWORD_STORE = "passwordList";
    NumberPicker numChar;
    EditText nPassword;
    EditText domainText;
    String currentUser;
    Crypto c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_create);
        passwords = getSharedPreferences(PASSWORD_STORE,0);
        domainText = (EditText) findViewById(R.id.domainText);
        numChar = (NumberPicker) findViewById(R.id.numCharPicker);
        numChar.setMinValue(7);
        numChar.setMaxValue(20);
        numChar.setValue(7);
        nPassword = (EditText) findViewById(R.id.nPassword);
        c = new Crypto();
        currentUser = getIntent().getStringExtra("currentUser");
    }
    //creates new password and displays it
    public void createPassword(View v){
        //get domain and add username to the end
        String domain = domainText.getText().toString()+currentUser;
        //Ensure domain has been entered by user
        if(domain.equals("")){
            AlertDialog alertDialog = new AlertDialog.Builder(passwordCreateActivity.this).create();
            alertDialog.setMessage("Please fill in the domain field");
            alertDialog.setTitle("Alert");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }
        //If user already has a password for the domain entered, do nothing
        else if (!passwords.getString(domain,"").equals("")){
            AlertDialog alertDialog = new AlertDialog.Builder(passwordCreateActivity.this).create();
            alertDialog.setMessage("You already have a password for this domain");
            alertDialog.setTitle("Alert");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }
        //Create new password and dispaly it
        else {
            //get number of characters to generate
            int characters = numChar.getValue();
            Random rand = new Random();
            //Create stringbuilder object to build password
            StringBuilder pword = new StringBuilder();
            for (int i = 0; i < characters; i++) {
                //Pick random number between 33 and 126 (inclusive) and convert it to ascii character
                int charIndex = rand.nextInt(126 - 33 + 1) + 33;
                Log.d("Char", charIndex + "");
                //append ascii character
                pword.append(Character.toString((char) charIndex));

            }
            Log.d("beforeEncrypt", pword.toString());
            //display password
            nPassword.setText(pword.toString());
            //encrypt password
            String key = c.getKey(pword.toString()).toString();
            String enc = c.encryption(pword.toString(), key);
            //save password
            passwords.edit().putString(domain, enc).commit();
            passwords.edit().putString(domain + "k", key).commit();
        }
    }



}
