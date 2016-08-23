package com.mycsdn.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mycsdn.bean.CommonException;
import com.mycsdn.bean.News;
import com.mycsdn.biz.NewsDto;
import com.mycsdn.biz.NewsItemBiz;
import com.wzy.mycsdn.R;

import java.util.List;

/**
 * Created by wzy on 2016/3/17.
 */
public class NewsContentActivty extends Activity {


    /**
     * 该页面的url
     */
    private String url;
    private NewsItemBiz mNewsItemBiz;
    private List<News> mDatas;

    WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Bundle bundle = getIntent().getExtras();
        url = bundle.getString("url");
        System.out.println(url);

        webview = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webview.loadUrl(url);
        //设置Web视图
        webview.setWebViewClient(new webViewClient());



    }


    class LoadDataTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            try {
                mDatas = mNewsItemBiz.getNews(url).getNewses();
            } catch (CommonException e) {
                Looper.prepare();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (mDatas == null)
                return;
        }

    }

    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}
