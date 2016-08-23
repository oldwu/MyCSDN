package com.mycsdn.utils;

import com.mycsdn.utils.*;

public class URLUtil {
	public static final String NEWS_LIST_URL = "http://www.csdn.net/headlines.html";
    public static final String NEWS_LIST_URL_YIDONG = "http://mobile.csdn.net/mobile";
    public static final String NEWS_LIST_URL_YANFA = "http://sd.csdn.net/sd";
    public static final String NEWS_LIST_URL_YUNJISUAN = "http://cloud.csdn.net/cloud";
    public static final String NEWS_LIST_URL_ZAZHI = "http://programmer.csdn.net/programmer";
    public static final String NEWS_LIST_URL_YEJIE = "http://news.csdn.net/news";


    public static String generateURL(int newsType, int currentPage){
    	currentPage = currentPage > 0 ? currentPage : 1;
    	String urlStr = "";
    	switch (newsType) {
		case Constaint.NEWS_TYEP_YEJIE:
			urlStr = NEWS_LIST_URL_YEJIE;
			break;
		case Constaint.NEWS_TYPE_YANFA:
			urlStr = NEWS_LIST_URL_YANFA;
			break;
		case Constaint.NEWS_TYPE_YUNJISUAN:
			urlStr = NEWS_LIST_URL_YUNJISUAN;
			break;
		case Constaint.NEWS_TYPE_YIDONG:
			urlStr = NEWS_LIST_URL_YIDONG;
			break;
		default:
			break;
		}

    	urlStr += "/" + currentPage;

    	return urlStr;
    }

}
