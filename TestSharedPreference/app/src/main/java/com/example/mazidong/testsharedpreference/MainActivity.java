package com.example.mazidong.testsharedpreference;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "====MainActivity====";

    private EditText local_input;
    private TextView local_file_show;

    private TextView sp_content;

    private SqliteCustomUtil sqliteCustomUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //读写本地文件
        local_file_show = (TextView) findViewById(R.id.local_file_show);
        local_input = (EditText) findViewById(R.id.local_input);
        findViewById(R.id.local_read_file).setOnClickListener(this);
        findViewById(R.id.local_save_file).setOnClickListener(this);

        //sharedPreference存储读取
        sp_content = (TextView) findViewById(R.id.sp_content);
        findViewById(R.id.sp_save).setOnClickListener(this);
        findViewById(R.id.sp_read).setOnClickListener(this);

        //sqlite 数据存储读取
        findViewById(R.id.sqlite_create_database).setOnClickListener(this);
        findViewById(R.id.sqlite_insert_record).setOnClickListener(this);
        findViewById(R.id.sqlite_search).setOnClickListener(this);
        findViewById(R.id.sqlite_update).setOnClickListener(this);
        findViewById(R.id.sqlite_delete).setOnClickListener(this);

        sqliteCustomUtil = new SqliteCustomUtil(this, "BookStore.db", null, 1);
    }

    @Override
    public void onClick(View v) {
        String input = null;
        String load = null;
        SQLiteDatabase db = null;
        ContentValues values = null;

        switch (v.getId()) {
            case R.id.local_save_file:
                input = local_input.getText().toString();
                if (!TextUtils.isEmpty(input)) {
                    SaveOrReadFromLocalFile.local_save(input, this);
                } else {
                    Toast.makeText(this, "请输入要存储的文件内容", Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG, "onClick: btn_save_file");
                break;
            case R.id.local_read_file:
                load = SaveOrReadFromLocalFile.local_read(this);
                local_file_show.setText(load);
                local_input.setText(load);
                Log.d(TAG, "onClick: btn_read_file");
                break;
            case R.id.sp_save:
                //SharedPreferencesUtil.sp_first_write(this);
                SharedPreferencesUtil.sp_second_write(this);
                Log.d(TAG, "onClick: sp_save");
                break;
            case R.id.sp_read:
                sp_content.setText(SharedPreferencesUtil.sp_read(this));
                Log.d(TAG, "onClick: sp_read");
                break;
            case R.id.sqlite_create_database:
                db = sqliteCustomUtil.getWritableDatabase();
                Log.d(TAG, "onClick: sqlite_save");
                break;
            case R.id.sqlite_insert_record:
                db = sqliteCustomUtil.getWritableDatabase();
                values = new ContentValues();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values);
                values.clear();
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values);
                values.clear();
                db.close();
                Log.d(TAG, "onClick: sqlite_read");
                break;
            case R.id.sqlite_update:
                db = sqliteCustomUtil.getWritableDatabase();
                values = new ContentValues();
                values.put("price", 10.99);
                db.update("Book", values, "name = ?", new String[]{"The Da Vinci Code"});
                db.close();

                Log.d(TAG, "onClick: sqlite_read");
                break;
            case R.id.sqlite_delete:
                db = sqliteCustomUtil.getWritableDatabase();
                db.delete("Book", "pages > ?", new String[]{"500"});
                db.close();
                Log.d(TAG, "onClick: sqlite_read");
                break;
            case R.id.sqlite_search:
                db = sqliteCustomUtil.getWritableDatabase();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d(TAG, "========================================");
                        Log.d(TAG, "book name is: " + name);
                        Log.d(TAG, "book author is: " + author);
                        Log.d(TAG, "book pages is: " + name);
                        Log.d(TAG, "book price is: " + name);
                        Log.d(TAG, "========================================");
                    } while (cursor.moveToNext());
                }
                Log.d(TAG, "onClick: sqlite_read");
                db.close();
                break;
                default:;
        }
    }
}
