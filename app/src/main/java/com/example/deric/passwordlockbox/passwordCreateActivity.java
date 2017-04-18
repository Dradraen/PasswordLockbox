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
    private static String PASSWORD_STORE = "passwordList";
    NumberPicker numChar;
    EditText nPassword;
    String availableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*~";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_create);
        passwords = getSharedPreferences(PASSWORD_STORE,0);
        numChar = (NumberPicker) findViewById(R.id.numCharPicker);
        numChar.setMinValue(7);
        numChar.setMaxValue(20);
        numChar.setValue(7);
        nPassword = (EditText) findViewById(R.id.nPassword);

    }

    public void createPassword(View v){
        int characters = numChar.getValue();
        Random rand = new Random();
        StringBuilder pword= new StringBuilder();
        for (int i = 0; i < characters; i++) {
            int charIndex = (int)(rand.nextFloat() * availableChars.length());
            pword.append(availableChars.charAt(charIndex));
        }
        Log.d("beforeEncrypt",pword.toString());
        nPassword.setText(pword.toString());

    }



}
