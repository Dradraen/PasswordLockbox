package com.example.deric.passwordlockbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Changepassword extends AppCompatActivity {
    private EditText oldPass;
    private EditText newPass;
    private EditText newPassCon;
    private String currentUser;
    private TextView alertText;
    private SharedPreferences settings;
    Crypto c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        currentUser = getIntent().getStringExtra("currentUser");
        oldPass = (EditText)findViewById(R.id.oldPass);
        newPass = (EditText)findViewById(R.id.newPass);
        newPassCon = (EditText)findViewById(R.id.newPassCon);
        alertText = (TextView)findViewById(R.id.alertText);
        settings = getSharedPreferences("login_credentials",MODE_PRIVATE);
        c = new Crypto();
    }
    public void cancelPasswordChange(View view){
        Intent intent = new Intent(this,AccountActivity.class);
        startActivity(intent);
        finish();
    }
    public void confirmChange(View view){
        //TODO make the encryption on the new password and implement the override of the previous password with the currentUser
        if(oldPass.getText().toString().equals("")|| newPass.getText().toString().equals("")||newPassCon.getText().toString().equals(""))
        {
            alertText.setText("Please fill in all fields");
        }
        else{
            if(!(newPass.getText().toString().equals(newPassCon.getText().toString())))
            {
                alertText.setText("New password must match in both fields.");
            }
            else{
                String pass = oldPass.getText().toString();
                String token = settings.getString(currentUser, "");
                if(c.validate(pass, token)){
                    if(newPass.getText().toString().length()>7){
                        String password = newPass.getText().toString();
                        String digest = c.makeDigest(password);
                        settings.edit().putString(currentUser,digest).commit();

                        finish();
                    }
                }
                else{
                    alertText.setText("Incorrect password");
                }
            }
        }
        Intent intent = new Intent(this,AccountActivity.class);
        startActivity(intent);
        finish();
    }
}
