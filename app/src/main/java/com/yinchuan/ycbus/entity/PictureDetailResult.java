package com.yinchuan.ycbus.entity;

import java.util.ArrayList;

/**
 * Created by zhouwei on 2016/7/9.
 */
public class PictureDetailResult {

    private ArrayList<PictureDetail> list;
    private int dspcnt;
    private String group_title;
    private String website_dspname;
    private String group_pageurl;
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getDspcnt() {
        return dspcnt;
    }

    public void setDspcnt(int dspcnt) {
        this.dspcnt = dspcnt;
    }

    public String getGroup_pageurl() {
        return group_pageurl;
    }

    public void setGroup_pageurl(String group_pageurl) {
        this.group_pageurl = group_pageurl;
    }

    public String getGroup_title() {
        return group_title;
    }

    public void setGroup_title(String group_title) {
        this.group_title = group_title;
    }

    public ArrayList<PictureDetail> getList() {
        return list;
    }

    public void setList(ArrayList<PictureDetail> list) {
        this.list = list;
    }

    public String getWebsite_dspname() {
        return website_dspname;
    }

    public void setWebsite_dspname(String website_dspname) {
        this.website_dspname = website_dspname;
    }
}
