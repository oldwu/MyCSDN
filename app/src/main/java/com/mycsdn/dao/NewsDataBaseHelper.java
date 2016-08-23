package com.mycsdn.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wzy on 2016/3/16.
 */
public class NewsDataBaseHelper extends SQLiteOpenHelper {

    private static String DBName = "csdn_new_database";

    public NewsDataBaseHelper(Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table tb_newsItem (id integer primary key autoincrement, " +
                        "title text, link text, date text , imgLink text , content text , newstype integer);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
