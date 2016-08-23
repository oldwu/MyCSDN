package com.mycsdn.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mycsdn.bean.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzy on 2016/3/16.
 */
public class NewsListDao {

    private List<NewsItem> newsItems = new ArrayList<>();

    private NewsDataBaseHelper dbHelper;

    public NewsListDao(Context context) {
        dbHelper = new NewsDataBaseHelper(context);
    }


    public void add(NewsItem newsItems) {
        String sql = "insert into tb_newsItem (title, link, date, imgLink, content, newstype) values(?, ?, ?, ?, ?, ?);";
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        db.execSQL(sql, new Object[]{newsItems.getTitle(), newsItems.getLink(), newsItems.getDate(),
                newsItems.getImgLink(), newsItems.getContent(), newsItems.getNewsType()});

        db.close();
    }

    public void addList(List<NewsItem> newsItemList){
        for (int i = 0; i< newsItemList.size(); i++){
            add(newsItemList.get(i));
        }
    }

    public void delAll(int newsType) {
        String sql = "delete from tb_newsItem where newstype = ?";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql, new Object[]{newsType});

        db.close();

    }


    public List<NewsItem>  list(int newsType) {

        // 0 -9 , 10 - 19 ,
        List<NewsItem> newsItems = new ArrayList<NewsItem>();
        try
        {
            String sql = "select title,link,date,imgLink,content,newstype from tb_newsItem where newstype = ?";
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor c = db.rawQuery(sql, new String[] { newsType + ""});

            NewsItem newsItem = null;

            while (c.moveToNext())
            {
                newsItem = new NewsItem();

                String title = c.getString(0);
                String link = c.getString(1);
                String date = c.getString(2);
                String imgLink = c.getString(3);
                String content = c.getString(4);
                Integer newstype = c.getInt(5);

                newsItem.setTitle(title);
                newsItem.setLink(link);
                newsItem.setImgLink(imgLink);
                newsItem.setDate(date);
                newsItem.setNewsType(newstype);
                newsItem.setContent(content);

                newsItems.add(newsItem);

            }
            c.close();
            db.close();
            Log.e("list", newsItems.size() + "  newsItems.size()");
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newsItems;
    }


}
