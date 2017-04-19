package com.example.deric.passwordlockbox;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class passwordRecoverActivity extends AppCompatActivity {

    EditText cDomain;
    EditText mPass;
    TextView fPassText;
    String currentUser;
    private final String LOGIN_SETTINGS = "login_credentials";
    String masterPass;
    private final String PASSWORD_STORE = "passwordList";
    SharedPreferences passwords;
    Crypto c;
    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recover);
        cDomain = (EditText) findViewById(R.id.cDomain);
        mPass = (EditText) findViewById(R.id.mPass);
        fPassText = (TextView) findViewById(R.id.fPassText);
        passwords = getSharedPreferences(PASSWORD_STORE,0);

        c = new Crypto();

        settings = getSharedPreferences(LOGIN_SETTINGS,0);
        currentUser =  getIntent().getStringExtra("currentUser");
        masterPass = settings.getString(currentUser,"");

    }

    public void retrievePassword(View v){
        String master = mPass.getText().toString();
        String domain = cDomain.getText().toString();
        String pass = passwords.getString(domain,"");
        String key = passwords.getString(domain + "k", "");
        String pword = c.decryption(pass, key);
        if(domain.equals("") || master.equals("")){
            AlertDialog alertDialog = new AlertDialog.Builder(passwordRecoverActivity.this).create();
            alertDialog.setMessage("Please fill in both fields to retrieve your password");
            alertDialog.setTitle("Alert");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else if (!master.equals(masterPass)){
            AlertDialog alertDialog = new AlertDialog.Builder(passwordRecoverActivity.this).create();
            alertDialog.setMessage("Master password incorrect");
            alertDialog.setTitle("Alert");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }
        else {
            fPassText.setText(pword);
            Log.d("Retrieve", pword);
            Log.d("Domain", domain);
        }

    }
}
