package com.example.deric.passwordlockbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {
    String LOGIN_SETTINGS = "login_credentials";
    SharedPreferences settings;
    Button loginButton;
    Button regButton;
    EditText usernameField;
    EditText passwordField;
    TextView displayText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        settings = getSharedPreferences(LOGIN_SETTINGS,MODE_PRIVATE);
        usernameField = (EditText)findViewById(R.id.UsernameField);
        passwordField = (EditText) findViewById(R.id.PasswordField);
        loginButton = (Button)findViewById(R.id.loginButton);
        regButton = (Button)findViewById(R.id.regButton);
        displayText = (TextView)findViewById(R.id.DisplayText);
        if(settings.getString("registered",null)==null) {

            regButton.performClick();
        }

    }
    public void regRequest(View view)
    {
        Intent intent = new Intent(LoginActivity.this,registerActivity.class);
        intent.putExtra("login",LOGIN_SETTINGS);
        startActivityForResult(intent,100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 100) {
            System.out.println(resultCode);
            if (resultCode == 0){
                settings.edit().putString(data.getStringExtra("USER"),data.getStringExtra("PASS")).commit();
                settings.edit().putString("registered","true").commit();
            }
        }
    }
    public void loginRequest(View view)
    {

        if(usernameField.getText().toString().equals("")|| passwordField.getText().toString().equals(""))
        {
            displayText.setText("Please fill in both fields");
        }
        else{
            if(settings.getString(usernameField.getText().toString(),null)==null)
            {
                displayText.setText("That account does not exist");
            }
            else{
                if(passwordField.getText().toString().equals(settings.getString(usernameField.getText().toString(),""))){
                    Intent intent = new Intent(LoginActivity.this,transitionActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    displayText.setText("Incorrect Username or Password");
                }
            }
        }

    }
}
