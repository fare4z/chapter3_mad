package com.example.chapter3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername,etPassword;
    Button btn_login;
TextView txtCreate;

    String strEmail , strPassword, dummyUsername = "fareez@psp.edu.my", dummyPassword = "123456";

    SharedPreferences sp;

    public static final String PREFS_NAME = "PrefLogin";
    private static final String KEY_USERNAME = "key_username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SQLiteDatabase mDatabase = openOrCreateDatabase("chapter33",MODE_PRIVATE,null);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btn_login = findViewById(R.id.btnLogin);
        txtCreate = findViewById(R.id.txtCreateAcc);


        // Assign Shared Preference
        sp = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);

        String is_login = sp.getString(KEY_USERNAME, "");

//        if (!is_login.equals("")) {
//            Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
//            mainIntent.putExtra("intentNama",is_login);
//            startActivity(mainIntent);
//        }


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strEmail=etUsername.getText().toString();
                strPassword=etPassword.getText().toString();

                Cursor cursorUser = mDatabase.rawQuery("SELECT * FROM LoginTable WHERE username=? and password=?",new String[]{strEmail,strPassword});
                if(cursorUser != null && cursorUser.moveToFirst()){
                cursorUser.moveToFirst();

                String username = cursorUser.getString(0);
                String fullname = cursorUser.getString(1);
                String password = cursorUser.getString(2);



                if(strEmail.equals(username) && strPassword.equals(password)) {
                    Toast.makeText(getApplicationContext(),
                                    "Login success!!",
                                    Toast.LENGTH_LONG)
                            .show();


                    // Saved Shared Preferences
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(KEY_USERNAME, strEmail);
                    editor.commit();

                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    mainIntent.putExtra("intentNama", fullname);
                    startActivity(mainIntent);
                }
                }else{
                    Toast.makeText(getApplicationContext(),
                                    "Invalid email or password!!",
                                    Toast.LENGTH_LONG)
                            .show();
                }

            }
        });

        txtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regIntent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(regIntent);
            }
        });

    }


}