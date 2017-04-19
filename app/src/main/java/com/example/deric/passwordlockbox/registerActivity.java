package com.example.deric.passwordlockbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class registerActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    TextView displayText;
    SharedPreferences settings;
    Crypto c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameEditText = (EditText)findViewById(R.id.usernameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        displayText = (TextView)findViewById(R.id.DisplayText);
        settings = getSharedPreferences(getIntent().getStringExtra("login"),MODE_PRIVATE);
        c = new Crypto();


    }
    public void regRequest(View view)
    {
        if(usernameEditText.getText().toString().equals("")|| passwordEditText.getText().toString().equals(""))
        {
            displayText.setText("Please fill in both fields");
        }
        else{
            if(settings.getString(usernameEditText.getText().toString(),null)!=null)
            {
                displayText.setText("Please fill in both fields");
            }
            else{
                if(passwordEditText.getText().toString().length()>7){
                    Intent intent = new Intent();
                    String username = usernameEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    String digest = c.makeDigest(password);

                    intent.putExtra("USER",usernameEditText.getText().toString());
                    intent.putExtra("PASS",digest);
                    setResult(0,intent);
                    finish();
                }
                else{
                    displayText.setText("Masterpassword must be 7 characters");
                }
            }
        }
    }
}
