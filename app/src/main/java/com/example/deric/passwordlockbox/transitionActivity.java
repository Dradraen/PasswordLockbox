package com.example.deric.passwordlockbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class transitionActivity extends AppCompatActivity {
    String currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        currentUser  = getIntent().getStringExtra("currentUser");
    }
    public void transitionCreate(View view){
        Intent intent = new Intent(this,passwordCreateActivity.class);
        intent.putExtra("currentUser",currentUser);
        startActivity(intent);
    }
    public void transitionRecover(View view) {
        Intent intent = new Intent(this,passwordRecoverActivity.class);
        intent.putExtra("currentUser",currentUser);
        startActivity(intent);
    }
    public void transitionChangeDomain(View view){
        Intent intent = new Intent(this,changeDomainPassword.class);
        intent.putExtra("currentUser",currentUser);
        startActivity(intent);
    }
    public void logout(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void transitionSettings(View view){
        Intent intent = new Intent(this,AccountActivity.class);
        intent.putExtra("currentUser",currentUser);
        startActivity(intent);

    }
}
