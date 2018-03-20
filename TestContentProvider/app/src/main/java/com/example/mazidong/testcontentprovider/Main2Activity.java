package com.example.mazidong.testcontentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity";
    public static final String AUTHORITY = "content://com.example.mazidong.testsharedpreference.provider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.btn_get_another_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(AUTHORITY + "/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, "price desc");
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String disPlayName = cursor.getString(cursor.getColumnIndex("name"));
                        String price = cursor.getString(cursor.getColumnIndex("price"));

                        Log.d(TAG, "book price is " + price);
                    }
                }
                Log.d(TAG, "btn_get_another_app");
            }
        });

        findViewById(R.id.btn_add_another_app_data_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(AUTHORITY + "/book");
                ContentValues contentValues = new ContentValues();

                contentValues.put("name", "A Clash of Kings");
                contentValues.put("author", "George Martin");
                contentValues.put("pages", 1040);
                contentValues.put("price", 22.85);

                Uri newUri = getContentResolver().insert(uri, contentValues);
                String newId = newUri.getPathSegments().get(1);

                Log.d(TAG, "新生成的ID是：newId=" + newId);
            }
        });
    }
}
