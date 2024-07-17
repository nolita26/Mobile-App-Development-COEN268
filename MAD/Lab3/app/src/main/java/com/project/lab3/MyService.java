package com.project.lab3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {
    private static String TAG = "MyService";
    int rNum;
    boolean isRandOn;
    public MyService() { }

    private Binder myBinder = new MyLocalBinder();
    class MyLocalBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(TAG, "onBind");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        Log.i(TAG, "Thread ID " + Thread.currentThread().getId() + " Start ID:" + startId);
        if (!isRandOn) {
            // generate random number
            Runnable runnable = () -> {
                Log.i(TAG, "Inside run");
                isRandOn = true;
                generateRandomNumber();
            };
            Thread t = new Thread(runnable);
            t.start();
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "On destroy");
        isRandOn = false;
        super.onDestroy();
    }

    public void generateRandomNumber() {
        while(isRandOn) {
            try {
                Thread.sleep(1000);
                rNum = new Random().nextInt(100);
                Log.i(TAG, "Current thread ID" + Thread.currentThread().getId() + " Random Number " + rNum);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

        }
    }

    public int getRandomNumber() {
        return rNum;
    }
}