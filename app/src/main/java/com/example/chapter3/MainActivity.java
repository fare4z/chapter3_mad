package com.example.chapter3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView DispNama,tvCounter;
    Button btnCount;
    Integer Counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DispNama = findViewById(R.id.DispNama);
        tvCounter = findViewById(R.id.tvCounter);
        btnCount = findViewById(R.id.btnCount);
        Counter = 0;

        Bundle extras = getIntent().getExtras();
        String strNama = extras.getString("intentNama");

        DispNama.setText(strNama);

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter++;
                tvCounter.setText("" + Counter);
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