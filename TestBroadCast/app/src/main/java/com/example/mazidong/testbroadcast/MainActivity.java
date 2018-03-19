package com.example.mazidong.testbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetWorkChangeReveiver netWorkChangeReveiver;

    private IntentFilter intentFilter2;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        netWorkChangeReveiver = new NetWorkChangeReveiver();

        registerReceiver(netWorkChangeReveiver, intentFilter);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.mazidong.testbroadcast.MY_BROADCAST");
                MainActivity.this.sendBroadcast(intent);
            }
        });
        /*button.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.mazidong.testbroadcast.MY_BROADCAST");
            sendBroadcast(intent);
        });*/

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        Button button1 = (Button) findViewById(R.id.button_local);
        button1.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.mazidong.testbroadcast.MY_LOCAL_BROADCAST");
            localBroadcastManager.sendBroadcast(intent);
        });

        intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.example.mazidong.testbroadcast.MY_LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();

        localBroadcastManager.registerReceiver(localReceiver, intentFilter2);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(netWorkChangeReveiver);

        localBroadcastManager.unregisterReceiver(localReceiver);

        super.onDestroy();
    }

    class NetWorkChangeReveiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Toast.makeText(context, "network changes hahaha", Toast.LENGTH_SHORT).show();

            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Receive local broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}
