package com.example.mazidong.testlitepal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_click).setOnClickListener(this);
        findViewById(R.id.add_data).setOnClickListener(this);
        findViewById(R.id.select_data).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Book book;
        List<Book> books;

        switch (view.getId()) {
            case R.id.btn_click:
                Connector.getDatabase();
                break;
            case R.id.add_data:
                book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Ban Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
                Log.d(TAG, "insert data ");
                break;
            case R.id.select_data:
                books = DataSupport.findAll(Book.class);
                for (Book book1: books) {
                    Log.d(TAG, "book name is " + book1.getName());
                    Log.d(TAG, "book author is " + book1.getAuthor());
                    Log.d(TAG, "book pages is " + book1.getPages());
                    Log.d(TAG, "book price is " + book1.getPrice());
                    Log.d(TAG, "book press is " + book1.getPress());
                }
                break;
            default:;
        }
    }
}
