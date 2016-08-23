package com.mycsdn.biz;

import com.mycsdn.bean.News;

import java.util.List;

public class NewsDto  
{  
    private List<News> newses;
    private String nextPageUrl ;  
    public List<News> getNewses()  
    {  
        return newses;  
    }  
    public void setNewses(List<News> newses)  
    {  
        this.newses = newses;  
    }  
    public String getNextPageUrl()  
    {  
        return nextPageUrl;  
    }  
    public void setNextPageUrl(String nextPageUrl)  
    {  
        this.nextPageUrl = nextPageUrl;  
    }   
      
      
}  
