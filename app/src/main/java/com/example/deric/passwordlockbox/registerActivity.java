package com.example.deric.passwordlockbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class registerActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    TextView displayText;
    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameEditText = (EditText)findViewById(R.id.usernameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        displayText = (TextView)findViewById(R.id.DisplayText);
        settings = getSharedPreferences(getIntent().getStringExtra("login"),MODE_PRIVATE);

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
                    intent.putExtra("USER",usernameEditText.getText().toString());
                    intent.putExtra("PASS",passwordEditText.getText().toString());
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
