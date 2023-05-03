package com.example.chapter3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername,etPassword;
    Button btn_login;
    String strEmail , strPassword, dummyUsername = "fareez@psp.edu.my", dummyPassword = "123456";

    SharedPreferences sp;

    public static final String PREFS_NAME = "PrefLogin";
    private static final String KEY_USERNAME = "key_username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btn_login = findViewById(R.id.btnLogin);

        // Assign Shared Preference
        sp = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);

        String is_login = sp.getString(KEY_USERNAME, "");

        if (!is_login.equals("")) {
            Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
            mainIntent.putExtra("intentNama",is_login);
            startActivity(mainIntent);
        }


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strEmail=etUsername.getText().toString();
                strPassword=etPassword.getText().toString();

                if(strEmail.equals(dummyUsername) && strPassword.equals(dummyPassword)){
                    Toast.makeText(getApplicationContext(),
                                    "Login success!!",
                                    Toast.LENGTH_LONG)
                            .show();


                    // Saved Shared Preferences
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(KEY_USERNAME,strEmail);
                    editor.commit();

                    Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
                    mainIntent.putExtra("intentNama",strEmail);
                    startActivity(mainIntent);

                }else{
                    Toast.makeText(getApplicationContext(),
                                    "Invalid email or password!!",
                                    Toast.LENGTH_LONG)
                            .show();
                }

            }
        });

    }


}