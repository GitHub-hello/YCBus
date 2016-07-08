package com.yinchuan.ycbus.entity;

import java.util.ArrayList;

/**
 * Created by zhouwei on 2016/7/7.
 */
public class Girl {
    private String id;
    private String sthumbUrl;
    private String thumbUrl;
    private String ori_pic_url;
    private String page_url;
    private String title;
    private String pic_url;
    private ArrayList<String> tags;

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOri_pic_url() {
        return ori_pic_url;
    }

    public void setOri_pic_url(String ori_pic_url) {
        this.ori_pic_url = ori_pic_url;
    }

    public String getPage_url() {
        return page_url;
    }

    public void setPage_url(String page_url) {
        this.page_url = page_url;
    }

    public String getSthumbUrl() {
        return sthumbUrl;
    }

    public void setSthumbUrl(String sthumbUrl) {
        this.sthumbUrl = sthumbUrl;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
