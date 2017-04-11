package com.example.deric.passwordlockbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {
    String LOGIN_SETTINGS = "login_credentials";
    SharedPreferences settings;
    Button loginButton;
    Button regButton;
    EditText usernameField;
    EditText passwordField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        settings = getSharedPreferences(LOGIN_SETTINGS,MODE_PRIVATE);
        usernameField = (EditText)findViewById(R.id.UsernameField);
        passwordField = (EditText) findViewById(R.id.PasswordField);
        loginButton = (Button)findViewById(R.id.loginButton);
        regButton = (Button)findViewById(R.id.regButton);
        if(settings.getString("registered",null)==null) {

            regButton.performClick();
        }

    }
    public void regRequest(View view)
    {
        Intent intent = new Intent(LoginActivity.this,registerActivity.class);
        intent.putExtra("Prefs",LOGIN_SETTINGS);
        startActivityForResult(intent,100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 100) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

            }
        }
    }
    public void loginRequest(View view)
    {
        if(settings.getString(usernameField.getText().toString(),null)==null){


        }
        else if(settings.getString(usernameField.getText().toString(),null).equals(passwordField.getText().toString())){
            Intent intent = new Intent(LoginActivity.this,transitionActivity.class);
            startActivity(intent);
        }
    }
}
