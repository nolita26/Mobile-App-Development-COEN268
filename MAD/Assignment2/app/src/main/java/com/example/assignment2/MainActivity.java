package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import com.example.assignment2.R.drawable;

public class MainActivity extends AppCompatActivity {
    String Name[];
    String Description[];
    int Images[] = {
            R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f,
            R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m
    };
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("DEBUG","On Create");
        Name = getResources().getStringArray(R.array.Android);
        int d = Name.length;
        Log.d("DEBUG","On Create "+ Name.length +" sad");
        Description = getResources().getStringArray(R.array.description);
        recyclerView = findViewById(R.id.android);
        Adapter myAdapter = new Adapter(this, Name, Description, Images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}