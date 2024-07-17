package com.example.quiz1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class layout2 extends AppCompatActivity {
    Context con = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout2);
    }

    public void correct(View view) {
        Intent intent = new Intent(this, correctPage.class);
        startActivity(intent);
    }

    public void wrong(View view) {
        showAlert();
    }

    private void showAlert() {
        new AlertDialog.Builder(con)
                .setTitle("Wrong Answer")
                .setMessage("your answer is wrong, do you want to try again?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}