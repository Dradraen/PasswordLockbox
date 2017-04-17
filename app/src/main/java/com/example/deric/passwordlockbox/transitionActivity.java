package com.example.deric.passwordlockbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class transitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
    }
    public void transitionCreate(View view){
        Intent intent = new Intent(this,passwordCreateActivity.class);
        startActivity(intent);
        
    }
    public void transitionRecover(View view) {
        Intent intent = new Intent(this,passwordRecoverActivity.class);
        startActivity(intent);
    }
    public void logout(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
