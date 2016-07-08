package com.yinchuan.ycbus.entity;

/**
 * Created by zhouwei on 2016/7/8.
 */
public class BeautyPicture {
    private String id;
    private String group_title;
    private String tag;
    private String cover_imgurl;
    private String cover_height;
    private String cover_width;
    private String total_count;
    private String index;
    private String qhimg_url;//封面图

    public String getCover_height() {
        return cover_height;
    }

    public void setCover_height(String cover_height) {
        this.cover_height = cover_height;
    }

    public String getCover_imgurl() {
        return cover_imgurl;
    }

    public void setCover_imgurl(String cover_imgurl) {
        this.cover_imgurl = cover_imgurl;
    }

    public String getCover_width() {
        return cover_width;
    }

    public void setCover_width(String cover_width) {
        this.cover_width = cover_width;
    }

    public String getGroup_title() {
        return group_title;
    }

    public void setGroup_title(String group_title) {
        this.group_title = group_title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getQhimg_url() {
        return qhimg_url;
    }

    public void setQhimg_url(String qhimg_url) {
        this.qhimg_url = qhimg_url;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }
}
