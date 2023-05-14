package com.example.chapter3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword;
    String nama, email, password;
    TextView textView4;
    Button btnRegister, btnShow;

    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SQLiteDatabase mDatabase = openOrCreateDatabase("chapter33",MODE_PRIVATE,null);


        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnShow = findViewById(R.id.btnShow);

        textView4 = findViewById(R.id.textView4);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String insertSQL = "INSERT INTO LoginTable \n" +
                        "(username,password,fullname) \n" +
                        "VALUES \n" +
                        "(?,?,?);";

                mDatabase.execSQL(insertSQL,new String[]{etEmail.getText().toString(), etPassword.getText().toString(), etName.getText().toString()});
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                etName.setText("");
                etPassword.setText("");

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursorUser = mDatabase.rawQuery("SELECT * FROM LoginTable",null);

                if(cursorUser != null && cursorUser.moveToFirst()){
                    cursorUser.moveToFirst();
                    String username = cursorUser.getString(0);
                    String password = cursorUser.getString(2);
                    cursorUser.close();

                    textView4.setText(username+"\n"+password);
                }





            }
        });
    }

    public void createTableLogin() {
        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS LoginTable(Username VARCHAR,Fullname VARCHAR, Password VARCHAR);");
    }

    public void deleteData() {
        mDatabase.execSQL("DELETE FROM LoginTable;");
    }
}