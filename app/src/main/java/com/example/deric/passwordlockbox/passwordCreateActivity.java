package com.example.deric.passwordlockbox;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class passwordCreateActivity extends AppCompatActivity {

    SharedPreferences passwords;
    private static String PASSWORD_STORE = "passwordList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_create);
        passwords = getSharedPreferences(PASSWORD_STORE,0);


    }



}
