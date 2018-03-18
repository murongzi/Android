package com.example.mazidong.testbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadCastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: MyBroadCastReceiver");
        Toast.makeText(context, "received in MyBroadCastReceiver", Toast.LENGTH_SHORT).show();
    }
}
