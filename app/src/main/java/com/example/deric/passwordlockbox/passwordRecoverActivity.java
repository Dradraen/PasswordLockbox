package com.example.deric.passwordlockbox;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class passwordRecoverActivity extends AppCompatActivity {

    EditText cDomain;
    EditText mPass;
    TextView fPassText;
    private final String PASSWORD_STORE = "passwordList";
    SharedPreferences passwords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recover);
        cDomain = (EditText) findViewById(R.id.cDomain);
        mPass = (EditText) findViewById(R.id.mPass);
        fPassText = (TextView) findViewById(R.id.fPassText);
        passwords = getSharedPreferences(PASSWORD_STORE,0);

    }

    public void retrievePassword(View v){
        String master = mPass.getText().toString();
        String domain = cDomain.getText().toString();
        String pword = passwords.getString(domain,"");
        fPassText.setText(pword);
        Log.d("Retrieve", pword);
        Log.d("Domain",domain);

    }
}
