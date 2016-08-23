package com.mycsdn.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.mycsdn.bean.NewsItem;
import com.wzy.mycsdn.R;

import java.util.List;

/**
 * Created by wzy on 2016/3/16.
 */
public class NewsListAdapter extends BaseAdapter {

    private List<NewsItem> newsItems;
    private LayoutInflater mInflater;

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    public NewsListAdapter(Context context, List<NewsItem> newsItems) {

        mInflater = LayoutInflater.from(context);
        this.newsItems = newsItems;

        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        options = new DisplayImageOptions.Builder().showStubImage(R.drawable.images)
                .showImageForEmptyUri(R.drawable.images).showImageOnFail(R.drawable.images).cacheInMemory()
                .cacheOnDisc().displayer(new RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300))
                .build();
    }

    public void addAll(List<NewsItem> newsItems) {
        this.newsItems.addAll(newsItems);
    }

    public void setDatas(List<NewsItem> newsItems){
        this.newsItems = newsItems;
    }

    @Override
    public int getCount() {
        return newsItems.size();
    }

    @Override
    public Object getItem(int position) {
        return newsItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_news, null);
            holder = new ViewHolder();

            holder.mContent = (TextView) convertView.findViewById(R.id.id_news_content);
            holder.mTitle = (TextView) convertView.findViewById(R.id.id_news_title);
            holder.mDate = (TextView) convertView.findViewById(R.id.id_news_date);
            holder.mImg = (ImageView) convertView.findViewById(R.id.id_news_image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewsItem newsItem = newsItems.get(position);
        holder.mContent.setText(newsItem.getContent());
        holder.mTitle.setText(newsItem.getTitle());
        holder.mDate.setText(newsItem.getDate());
        if (newsItem.getImgLink() != null){
            holder.mImg.setVisibility(ImageView.VISIBLE);
            imageLoader.displayImage(newsItem.getImgLink(), holder.mImg, options);
        }else {
            holder.mImg.setVisibility(ImageView.GONE);
        }


        return convertView;
    }

    private final class ViewHolder {
        TextView mTitle;
        TextView mContent;
        ImageView mImg;
        TextView mDate;
    }
}
