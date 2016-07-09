package com.yinchuan.ycbus.entity;

/**
 * Created by zhouwei on 2016/7/8.
 */
public class BeautyCategory {
    private String url;
    private String t;
    private String title;
    public BeautyCategory(){
        super();
    }
    public BeautyCategory(String t, String title, String url) {
        this.t = t;
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return "BeautyCategory{" +
                "t='" + t + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
