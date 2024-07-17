package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.edtName);
        password = findViewById(R.id.edtPassword);
    }

    public void login(View view) {
        String userName = username.getText().toString();
        String passWord = password.getText().toString();

        if (userName.equals("") && passWord.equals("")) {
            Toast.makeText(this, "enter your name or password", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, AddCourse.class);
        startActivity(intent);
    }
}