package com.example.deric.passwordlockbox;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.Random;

public class passwordCreateActivity extends AppCompatActivity {

    SharedPreferences passwords;
    private final String PASSWORD_STORE = "passwordList";
    NumberPicker numChar;
    EditText nPassword;
    EditText domainText;
    //private final String availableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*~";
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

    public void createPassword(View v){
        int characters = numChar.getValue();
        Random rand = new Random();
        StringBuilder pword= new StringBuilder();
        for (int i = 0; i < characters; i++) {
            int charIndex = rand.nextInt(126-33+1)+33;
            Log.d("Char",charIndex+"");

                pword.append(Character.toString((char) charIndex));

        }
        Log.d("beforeEncrypt",pword.toString());
        String domain = domainText.getText().toString()+currentUser;
        nPassword.setText(pword.toString());
        String key = c.getKey(pword.toString()).toString();
        String enc = c.encryption(pword.toString(), key);
        passwords.edit().putString(domain,enc).commit();
        passwords.edit().putString(domain + "k", key).commit();
    }



}
