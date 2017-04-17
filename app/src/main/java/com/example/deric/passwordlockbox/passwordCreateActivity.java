package com.example.deric.passwordlockbox;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.NumberPicker;

public class passwordCreateActivity extends AppCompatActivity {

    SharedPreferences passwords;
    private static String PASSWORD_STORE = "passwordList";
    NumberPicker numChar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_create);
        passwords = getSharedPreferences(PASSWORD_STORE,0);
        numChar = (NumberPicker) findViewById(R.id.numCharPicker);
        numChar.setMinValue(7);
        numChar.setMaxValue(20);
        numChar.setValue(7);
        

    }

    public void createPassword(View v){
        int characters = numChar.getValue();

    }



}
