package com.example.deric.passwordlockbox;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.Random;

public class changeDomainPassword extends AppCompatActivity {
    private EditText masterPass;
    private EditText newPass;
    private final String LOGIN_SETTINGS = "login_credentials";
    SharedPreferences settings;
    private String currentUser;
    private final String PASSWORD_STORE = "passwordList";
    private EditText domainName;
    NumberPicker numChar;
    Crypto c;
    SharedPreferences passwords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_domain_password);
        currentUser = getIntent().getStringExtra("currentUser");
        masterPass = (EditText)findViewById(R.id.masterPass);
        newPass = (EditText)findViewById(R.id.newPass);
        domainName = (EditText)findViewById(R.id.domainName);
        numChar = (NumberPicker) findViewById(R.id.numChar);
        numChar.setMinValue(7);
        numChar.setMaxValue(20);
        c = new Crypto();
        numChar.setValue(7);
        passwords= getSharedPreferences(PASSWORD_STORE,0);
        settings = getSharedPreferences(LOGIN_SETTINGS,0);


    }
    public void returnFromPasswordChange(View view){
        Intent intent = new Intent(this,transitionActivity.class);
        intent.putExtra("currentUser",currentUser);
        startActivity(intent);
        finish();
    }
    public void confirmChange(View view){
        String domain = domainName.getText().toString() + currentUser;
        String masterText = masterPass.getText().toString();
        String master = settings.getString(currentUser,"");

        if(domainName.getText().toString().equals("") || masterPass.getText().toString().equals("")) {
            AlertDialog alertDialog = new AlertDialog.Builder(changeDomainPassword.this).create();
            alertDialog.setMessage("Please fill in both fields to change your password");
            alertDialog.setTitle("Alert");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }
        else if(passwords.getString(domain,"").equals("")){
            AlertDialog alertDialog = new AlertDialog.Builder(changeDomainPassword.this).create();
            alertDialog.setMessage("You do not have a password for this domain yet");
            alertDialog.setTitle("Alert");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
            newPass.setText("");
        }
        else if(!c.validate(masterText,master)){
            AlertDialog alertDialog = new AlertDialog.Builder(changeDomainPassword.this).create();
            alertDialog.setMessage("Incorrect master password");
            alertDialog.setTitle("Alert");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }
        else{
            int characters = numChar.getValue();
            Random rand = new Random();
            StringBuilder pword = new StringBuilder();
            for (int i = 0; i < characters; i++) {
                int charIndex = rand.nextInt(126 - 33 + 1) + 33;
                Log.d("Char", charIndex + "");

                pword.append(Character.toString((char) charIndex));
            }
            Log.d("beforeEncrypt", pword.toString());
            newPass.setText(pword.toString());
            String key = c.getKey(pword.toString()).toString();
            String enc = c.encryption(pword.toString(), key);
            passwords.edit().putString(domain, enc).commit();
            passwords.edit().putString(domain + "k", key).commit();
        }
    }
}
