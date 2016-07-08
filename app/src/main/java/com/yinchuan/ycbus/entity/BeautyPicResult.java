package com.yinchuan.ycbus.entity;

import java.util.ArrayList;

/**
 * Created by zhouwei on 2016/7/8.
 */
public class BeautyPicResult {
    private boolean end;
    private int count;
    private int lastid;
    private ArrayList<BeautyPicture> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public int getLastid() {
        return lastid;
    }

    public void setLastid(int lastid) {
        this.lastid = lastid;
    }

    public ArrayList<BeautyPicture> getList() {
        return list;
    }

    public void setList(ArrayList<BeautyPicture> list) {
        this.list = list;
    }
}
