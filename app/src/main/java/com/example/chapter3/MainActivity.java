package com.example.chapter3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView DispNama,tvCounter;
    Button btnCount, btnReload, btnLogout;
    Integer Counter;

    SharedPreferences sp,sp_login;



    public static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_COUNTER = "Key_counter";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DispNama = findViewById(R.id.DispNama);
        tvCounter = findViewById(R.id.tvCounter);
        btnCount = findViewById(R.id.btnCount);
        btnReload = findViewById(R.id.btnReload);
        btnLogout = findViewById(R.id.btnLogout);
        Counter = 0;

        // Assign Shared Preference
        sp = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        sp_login = getSharedPreferences("PrefLogin",MODE_PRIVATE);

        Bundle extras = getIntent().getExtras();
        String strNama = extras.getString("intentNama");

        DispNama.setText(strNama);
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter++;
             // Saved Shared Preferences
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt(KEY_COUNTER,Counter);
                editor.commit();

                tvCounter.setText("" + Counter);
            }
        });

        btnReload.setOnClickListener(new View.OnClickListener() {

            // Retrieve SharedPreference
            @Override
            public void onClick(View v) {
                Integer s1 = sp.getInt(KEY_COUNTER, 0);
                tvCounter.setText("" + s1);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp_login.edit();
                editor.remove("key_username");
                editor.commit();

                Intent mainIntent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(mainIntent);

            }
        });




    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState ) {
       super.onSaveInstanceState(outState);
        outState.putInt("key_counter", Counter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Counter = savedInstanceState.getInt("key_counter",0);
        tvCounter.setText("" + Counter);
    }
}