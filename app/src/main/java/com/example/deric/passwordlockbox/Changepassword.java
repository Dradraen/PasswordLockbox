package com.example.deric.passwordlockbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Changepassword extends AppCompatActivity {
    private EditText oldPass;
    private EditText newPass;
    private EditText newPassCon;
    private String currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        currentUser = getIntent().getStringExtra("currentUser");
        oldPass = (EditText)findViewById(R.id.oldPass);
        newPass = (EditText)findViewById(R.id.newPass);
        newPassCon = (EditText)findViewById(R.id.newPassCon);

    }
    public void cancelPasswordChange(View view){
        Intent intent = new Intent(this,AccountActivity.class);
        startActivity(intent);
        finish();
    }
    public void confirmChange(View view){
        //TODO make the encryption on the new password and implement the override of the previous password with the currentUser
        Intent intent = new Intent(this,AccountActivity.class);
        startActivity(intent);
        finish();
    }
}
