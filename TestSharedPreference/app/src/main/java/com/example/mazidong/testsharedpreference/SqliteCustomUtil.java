package com.example.mazidong.testsharedpreference;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by mazidong on 2018/3/20.
 */

public class SqliteCustomUtil extends SQLiteOpenHelper {
    public  static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement,"
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";

    private Context mContext;

    public SqliteCustomUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);

        Toast.makeText(mContext, "create success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
