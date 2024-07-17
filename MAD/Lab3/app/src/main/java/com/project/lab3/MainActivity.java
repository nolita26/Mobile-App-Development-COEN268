package com.project.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";

    Button buttonStart, buttonStop;
    Button buttonBind, buttonUnBind;
    Button buttonGetRandomNumber;
    TextView tvRandomNumber;
    Intent sIntent;
    boolean isBound;

    private ServiceConnection myConnection;
    MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout
        setContentView(R.layout.activity_main);

        //initialize UI elements
        buttonStart = findViewById(R.id.btn_start);
        buttonStop = findViewById(R.id.btn_stop);
        buttonBind = findViewById(R.id.btn_bind);
        buttonUnBind = findViewById(R.id.btn_unbind);
        buttonGetRandomNumber = findViewById(R.id.btn_getNumber);
        tvRandomNumber = findViewById(R.id.tv_random_number);

        //set on click listener
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonBind.setOnClickListener(this);
        buttonUnBind.setOnClickListener(this);
        buttonGetRandomNumber.setOnClickListener(this);

        sIntent = new Intent(this, MyService.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                //TODO 1: START A SERVICE
                startMyService();
                break;
            case R.id.btn_stop:
                //TODO 2: STOP A SERVICE
                stopMyService();
                break;
            case R.id.btn_bind:
                //TODO 3: BIND A SERVICE
                bindMyService();
                break;
            case R.id.btn_unbind:
                //TODO 4: UNBIND A SERVICE
                unbindMyService();
                break;
            case R.id.btn_getNumber:
                //TODO 5: GET AND RANDOM NUMBER FROM SERVICE AND DISPLAY IT
                getNumber();
                break;
        }
    }

    private void getNumber() {
        if (isBound) {
            tvRandomNumber.setText("Random number:  " + myService.getRandomNumber());
        }
        else {
            tvRandomNumber.setText("MyService is not connected to MainActivity");
        }
    }

    private void unbindMyService() {
        if (isBound) {
            Log.i(TAG, "Unbinding service");
            unbindService(myConnection);
            myConnection = null;
            isBound = false;
        }
    }

    private void bindMyService() {
        if (myConnection == null) {
            Log.i(TAG, "binding service");
            myConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder ibinder) {
                    Log.i(TAG, "onServiceConnected");
                    isBound = true;
                    MyService.MyLocalBinder myBinder = (MyService.MyLocalBinder) ibinder;
                    myService = myBinder.getService();
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.i(TAG, "onServiceDisconnected");
                    isBound = false;
                }
            };
        }
        bindService(sIntent, myConnection, BIND_AUTO_CREATE);
    }

    private void stopMyService() {
        Log.i(TAG, "Stopping Service");
        stopService(sIntent);
    }

    private void startMyService() {
        Log.i(TAG, "Starting Service");
        Log.i(TAG, "Thread id " + Thread.currentThread().getId());
        startService(sIntent);
    }
}