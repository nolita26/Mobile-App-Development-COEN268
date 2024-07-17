package com.example.app2;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView lvProgram;
    WebView webView;

    String[] programName = {"Alpha", "Petit Four", "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich",
            "Jelly Bean", "KitKat", "Lollipop", "Marshmallow"};
    String[] programDescription = {"API 1","API 2","API 3","API 4","API 5-7",
            "API 8","API 9-10","API 11-13","API 14-15","API 16-18","API 19","API 21-22","API 23"};
    int[] programImages = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,
            R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,R.drawable.m};

    String[] urls = {"https://en.wikipedia.org/wiki/Android_version_history#Android_1.0",
            "https://en.wikipedia.org/wiki/Android_version_history#Android_1.1",
            "https://en.wikipedia.org/wiki/Android_Cupcake",
            "https://en.wikipedia.org/wiki/Android_Donut",
            "https://en.wikipedia.org/wiki/Android_Eclair",
            "https://en.wikipedia.org/wiki/Android_Froyo",
            "https://en.wikipedia.org/wiki/Android_Gingerbread",
            "https://en.wikipedia.org/wiki/Android_Honeycomb",
            "https://en.wikipedia.org/wiki/Android_Icecream_sandwich",
            "https://en.wikipedia.org/wiki/Android_Jelly_Bean",
            "https://en.wikipedia.org/wiki/Android_KitKat",
            "https://en.wikipedia.org/wiki/Android_Lollipop",
            "https://en.wikipedia.org/wiki/Android_Marshmallow"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvProgram = findViewById(R.id.lvProgram);
//        webView = findViewById(R.id.web);

//        setContentView(R.layout.activity_main);
//        Bundle extras = getIntent().getExtras();
//        WebView w = (WebView) findViewById(R.id.web);
//        String url = "https://en.wikipedia.org/wiki/" + extras.getString("bandUrl");
//        w.loadUrl(url);
//        w.getSettings().setJavaScriptEnabled(true);
//        w.setWebViewClient(new WebViewClient());

//        ProgramAdapter programAdapter = new ProgramAdapter(this, programName, programImages, programDescription);
        ProgramAdapter programAdapter = new ProgramAdapter(this, programName, programImages, programDescription, urls);

        lvProgram.setAdapter(programAdapter);
    }
}