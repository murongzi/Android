package com.example.mazidong.testsharedpreference;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by mazidong on 2018/3/20.
 */

public class SaveOrReadFromLocalFile {
    private final  static String local_file_name = "data";

    //写入数据值本地文件
    public static void local_save(String inputText, Context context) {
        FileOutputStream out = null;
        BufferedWriter writer = null;

        try {
            out = context.openFileOutput(local_file_name, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //从本地文件中读取数据
    public static String local_read(Context context) {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuffer content = new StringBuffer();

        try {
            in = context.openFileInput(local_file_name);
            reader = new BufferedReader(new InputStreamReader(in));

            String line = "";

            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return content.toString();
    }
}
