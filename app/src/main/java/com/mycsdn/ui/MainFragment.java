package com.mycsdn.ui;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baoyz.widget.PullRefreshLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.mycsdn.bean.CommonException;
import com.mycsdn.biz.NewsItemBiz;
import com.mycsdn.utils.Constaint;
import com.mycsdn.bean.NewsItem;
import com.mycsdn.dao.NewsListDao;
import com.wzy.mycsdn.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzy on 2016/3/14.
 */
public class MainFragment extends Fragment {


    private static final int LOAD_MORE = 0x110;
    private static final int LOAD_REFREASH = 0x111;

    private static final int TIP_ERROR_NO_NETWORK = 0X112;
    private static final int TIP_ERROR_SERVER = 0X113;

    private int newsType = Constaint.NEWS_TYEP_YEJIE;

    private NewsItem newsItem;

    private List<NewsItem> mData = new ArrayList<NewsItem>();

    private PullToRefreshListView newsList;

    private NewsListAdapter mAdapter;

    private int currentPage = 1;

    private NewsItemBiz mNewsItemBiz;

    private PullRefreshLayout layout;

    private NewsListDao newsListDao;

    public MainFragment() {

    }

    public MainFragment(int newsType) {
        this.newsType = newsType;
        mNewsItemBiz = new NewsItemBiz();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        newsList = (PullToRefreshListView) getView().findViewById(R.id.pull_refresh_list);


        initData();


        newsListDao = new NewsListDao(getActivity());
        mAdapter = new NewsListAdapter(getActivity(), mData);
        newsList.setAdapter(mAdapter);



        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsItem newsItem = mData.get(position-1);
                String url = newsItem.getLink();

                Intent intent = new Intent(getActivity(), NewsContentActivty.class);
                intent.putExtra("url", url);
                startActivity(intent);

            }
        });

        newsList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                onRefresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                onLoadMoreData();
            }
        });


    }

    private void onRefresh() {
        new LoadDatasTask().execute(LOAD_REFREASH);
    }

    private void onLoadMoreData(){
        new LoadDatasTask().execute(LOAD_MORE);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout, container, false);

        return view;
    }

    /**
     * 记载数据的异步任务
     *
     * @author zhy
     */
    class LoadDatasTask extends AsyncTask <Integer, Void, Integer> {


        @Override
        protected Integer doInBackground(Integer... params) {
            switch (params[0]) {
                case LOAD_REFREASH:
                    refreashData();

                    return LOAD_REFREASH;

                case LOAD_MORE:
                    loadMoreData();
                    return LOAD_MORE;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {

            switch (result){
                case LOAD_REFREASH:
                    newsListDao.delAll(newsType);
                    newsListDao.addList(mData);
                    mAdapter.setDatas(mData);
                    mAdapter.notifyDataSetChanged();
                    newsList.onRefreshComplete();
                    break;
                case LOAD_MORE:
                    newsListDao.addList(mData);
                    mAdapter.addAll(mData);
                    mAdapter.notifyDataSetChanged();
                    newsList.onRefreshComplete();
                    break;
            }
        }
    }

    public void refreashData(){
        try {
            mData = mNewsItemBiz.getNewsItems(newsType, 1);
            currentPage = 1;
        } catch (CommonException e) {
            e.printStackTrace();
        }
    }

    public void loadMoreData(){

        try {
            currentPage++;
            List<NewsItem> newsItems = mNewsItemBiz.getNewsItems(newsType, currentPage);
            mData.addAll(newsItems);


        } catch (CommonException e) {
            e.printStackTrace();
        }

    }

    public void initData(){
        newsListDao = new NewsListDao(getActivity());
        mData = newsListDao.list(newsType);
        if (mData.size() == 0){
            onRefresh();
        }
    }
}
