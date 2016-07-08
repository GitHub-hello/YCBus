package com.yinchuan.ycbus.entity;

import java.util.ArrayList;

/**
 * Created by zhouwei on 2016/7/7.
 */
public class GirlResult {
    private String category;
    private String tag;
    private int startIndex;
    private int maxEnd;
    private String items;
    private String newsResult;
    private String itemsOnPage;
    private ArrayList<Girl> all_items;

    public ArrayList<Girl> getAll_items() {
        return all_items;
    }

    public void setAll_items(ArrayList<Girl> all_items) {
        this.all_items = all_items;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getItemsOnPage() {
        return itemsOnPage;
    }

    public void setItemsOnPage(String itemsOnPage) {
        this.itemsOnPage = itemsOnPage;
    }

    public int getMaxEnd() {
        return maxEnd;
    }

    public void setMaxEnd(int maxEnd) {
        this.maxEnd = maxEnd;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public String getNewsResult() {
        return newsResult;
    }

    public void setNewsResult(String newsResult) {
        this.newsResult = newsResult;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
