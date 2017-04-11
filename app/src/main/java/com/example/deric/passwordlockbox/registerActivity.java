package com.example.deric.passwordlockbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class registerActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameEditText = (EditText)findViewById(R.id.usernameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        Intent intent = getIntent();
        settings = getSharedPreferences(intent.getStringExtra("Prefs"),MODE_PRIVATE);

    }
    public void regRequest(View view)
    {
        if(usernameEditText.getText().toString().equals("")|| passwordEditText.getText().toString().equals(""))
        {

        }
        else{
            if(settings.getString(usernameEditText.getText().toString(),null)!=null)
            {

            }
            else{
                if(passwordEditText.getText().toString().length()>7){
                    settings.edit().putString(usernameEditText.getText().toString(),passwordEditText.getText().toString()).commit();
                    settings.edit().putString("registered","true").commit();
                    System.out.println(settings.getString("registered",null));
                    Intent intent = new Intent();
                    intent.putExtra("userName",usernameEditText.getText().toString());
                    intent.putExtra("pass",passwordEditText.getText().toString());
                    finish();
                }
                else{

                }
            }
        }
    }
}
